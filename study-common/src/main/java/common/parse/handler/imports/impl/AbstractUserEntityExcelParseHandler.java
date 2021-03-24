package common.parse.handler.imports.impl;

import common.parse.check.CheckUtils;
import common.parse.context.ExcelParseContext;
import common.parse.context.UserIdParseContext;
import common.parse.entity.bean.ExcelUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Category
 * Created by panyingting on 2019 7 11
 */

public abstract class AbstractUserEntityExcelParseHandler extends AbstractExcelParseHandler<ExcelUserEntity> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractUserEntityExcelParseHandler.class);


    @Override
    protected ExcelUserEntity getInitEntity(int rowNum, String userPin) {
        ExcelUserEntity userIdEntity = new ExcelUserEntity();
        userIdEntity.setRowNum(rowNum);
        return userIdEntity;
    }

    @Override
    protected void afterParseProcess(ExcelParseContext _context) {

        UserIdParseContext context = (UserIdParseContext) _context;
        List<ExcelUserEntity> entityList = context.getEntityList();
        if (!entityList.isEmpty()) {
            entityList.removeIf(e -> e.getUserId() == null);
        }
    }

    @Override
    protected void valid(ExcelParseContext parentContext) {
        logger.info("校验ExcelUserEntity逻辑开始。。。。");

        UserIdParseContext context = (UserIdParseContext) parentContext;

        List<ExcelUserEntity> userEntities = context.getEntityList();
        ConcurrentHashMap<Long, Long> checkRepeatMap = new ConcurrentHashMap<>(userEntities.size());

        CheckUtils.checkIfEmpty(userEntities, "Excel 内容不能为空！");

        int totalData = userEntities.size();

        // 多线程执行
        if (userEntities.size() >= MULTI_THREAD_LIMIT_COUNT) {

            int totalWorker = context.initExecutorService("优惠券批量导入用户信息校验");
            int executeCountPerTask = totalData / totalWorker;
            try {
                for (int taskNum = 1; taskNum <= totalWorker; taskNum++) {

                    // subList 的两个参数
                    int begin = (taskNum - 1) * executeCountPerTask;
                    int last = taskNum == totalWorker ? totalData : taskNum * executeCountPerTask;

                    List<ExcelUserEntity> subList = userEntities.subList(begin, last);
                    ValidRunnable runnable = new ValidRunnable(context, subList, checkRepeatMap);

                    // 提交任务执行
                    String result = parentContext.submitTask(runnable, "校验任务[" + taskNum + "/" + totalWorker + "]");

                    logger.info("校验ExcelUserEntity逻辑-当前提交的任务信息， taskNum：{}, begin:{}, last:{}, result:{}", taskNum, begin, last, result);
                }
            } finally {
                context.waitTermination();
            }
        } else {
            logger.info("批量优惠价发放-即将进行Excel信息的校验，fileName:{}, dataSize:{}", context.getUploadFilename(), context.getEntityList().size());
            ValidRunnable runnable = new ValidRunnable(context, context.getEntityList(), checkRepeatMap);
            runnable.run();
        }

    }

    private class ValidRunnable implements Runnable {

        private final UserIdParseContext context;
        private final List<Long> userIdList;
        private final ConcurrentHashMap<Long, Long> checkRepeatMap;
        private final List<ExcelUserEntity> userEntities;

        private ValidRunnable(UserIdParseContext context, List<ExcelUserEntity> userEntities, ConcurrentHashMap<Long, Long> checkRepeatMap) {
            this.context = context;
            this.userEntities = userEntities;
            this.userIdList = userEntities.stream().map(ExcelUserEntity::getUserId).collect(Collectors.toList());
            this.checkRepeatMap = checkRepeatMap;

        }

        @Override
        public void run() {

            logger.info("批量优惠券发放-用户查询开始，");
            long startTime = System.currentTimeMillis();

            // 批量查询用户信息

            long times = (System.currentTimeMillis() - startTime) / 1000;
            logger.info("批量优惠券发放-用户信息查询完成:{}秒", times);

            Set<Long> dbUserIdSet = new HashSet<>(); // 用户数据信息
            dbUserIdSet.add(1L);

            long currThreadNum = Thread.currentThread().getId();
            for (ExcelUserEntity userEntity : userEntities) {

                Long value = checkRepeatMap.put(userEntity.getUserId(), currThreadNum);
                if (value != null) {
                    context.addErrorMsg(userEntity.getRowNum(), 0, "用户ID重复");
                }
                if (!dbUserIdSet.contains(userEntity.getUserId())) {
                    context.addErrorMsg(userEntity.getRowNum(), 0, "用户信息不存在");
                }
            }

        }
    }

    private static final Object lock = new Object();

}
