package common.parse.handler.imports.impl;

import com.google.common.collect.ImmutableMap;
import common.parse.check.CheckUtils;
import common.parse.check.WbBusinessException;
import common.parse.context.ExcelParseContext;
import common.parse.entity.bean.ExcelErrorMessage;
import common.parse.entity.bean.annotation.ColumnIndex;
import common.parse.handler.imports.ExcelImportHandler;
import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * 解析Excel handler
 * <p>
 * Created by panyingting
 */
public abstract class AbstractExcelParseHandler<T> implements ExcelImportHandler<T> {

    private static final int MAX_LINE = 10000;

    protected static final int MULTI_THREAD_LIMIT_COUNT = 2000;

    private static final Logger logger = LoggerFactory.getLogger(AbstractExcelParseHandler.class);


    @Override
    final public void handle(ExcelParseContext<T> context) {

        logger.info("解析Excel前逻辑处理，文件名称：{}", context.getUploadFilename());
        long start = System.currentTimeMillis();

        beforeParseProceed(context);

        logger.info("解析Excel内容 开始，文件名称：{}", context.getUploadFilename());
        parse(context);

        logger.info("解析Excel后处理逻辑开始，文件名称：{}", context.getUploadFilename());
        afterParseProcess(context);

        logger.info("校验Excel内容信息 开始，文件名称：{}", context.getUploadFilename());
        valid(context);

        logger.info("处理内容信息开始，文件名称：{}", context.getUploadFilename());
        postProcess(context);

        long totalTimes = (System.currentTimeMillis() - start) / 1000;
        logger.info("Excel解析-处理内容信息完成，上传文件名称：{}, 总时间：{}秒", context.getUploadFilename(), totalTimes);
    }

    /**
     * 解析文档之前的操作
     */
    protected void beforeParseProceed(ExcelParseContext<T> context) {

    }

    /**
     * 解析完成之后，实体处理，比如去重，删除不需要实体等待
     */
    protected void afterParseProcess(ExcelParseContext<T> context) {

    }


    private void parse(ExcelParseContext<T> context) {

        String uploadFilename = context.getUploadFilename();
        CheckUtils.notNullAssert(uploadFilename, "请为上下文设置上传文件名称");
        logger.info("Excel解析-开始解析文件内容，fileName:{}", uploadFilename);
        Workbook workbook;
        try {
            if (uploadFilename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(context.getInputStream());
            } else {
                workbook = new HSSFWorkbook(context.getInputStream());
            }
        } catch (Exception e) {
            logger.warn("Excel 接收失败，", e);
            throw new WbBusinessException("当前文件格式不是 xlsx 或 xls 格式");
        }
        Sheet sheet = workbook.getSheetAt(0);

        String userPin = "获取用户";
        int lastRowNum = sheet.getLastRowNum();

        CheckUtils.trueAssert(lastRowNum > context.getBeginRowNum(), "导入模板数据不能为空！");
        CheckUtils.trueAssert(lastRowNum < MAX_LINE, "导入最大行数不能超过" + MAX_LINE + "行");

        //循环除了第一行的所有行
        try {
            if (lastRowNum > MULTI_THREAD_LIMIT_COUNT) {

                int totalWorker = context.initExecutorService("优惠券批量导入解析excel处理");
                int countPerTask = lastRowNum / totalWorker;
                for (int taskNum = 1; taskNum <= totalWorker; taskNum++) {

                    // 当前解析的开始行数
                    int currBeginRowNum = (taskNum == 0 ? context.getBeginRowNum() : (taskNum - 1) * countPerTask);


                    // 当前解析的结束行数
                    int currLastRowNum = ((taskNum == totalWorker) ? lastRowNum : countPerTask * taskNum) - 1;

                    Runnable runnable = new MyRunnable(sheet, context, currBeginRowNum, currLastRowNum, userPin);

                    String result = context.submitTask(runnable, "解析任务[" + taskNum + "/" + totalWorker + "]");
                    logger.info("解析Excel逻辑-当前提交的任务信息， taskNum：{}, currBeginRowNum:{}, currLastRowNum:{}, result:{}", taskNum, currBeginRowNum, currLastRowNum, result);
                }
            } else {
                Runnable runnable = new MyRunnable(sheet, context, context.getBeginRowNum(), lastRowNum, userPin);
                runnable.run();
                logger.info("数据量少，单线程执行! fileName:{}", context.getUploadFilename());
            }
        } finally {
            // 如果是多线程执行，则等待所有线程执行完成
            context.waitTermination();
        }

        context.setWorkBook(workbook);

    }

    /**
     * 初始化 ExcelGoodsCategory 对象信息
     *
     * @param row           excel行对象
     * @param entity        需要初始化的对象
     * @param errorMessages 有错误信息时，用于存放错误信息的
     * @return 初始化成功返回 true, 不通过返回 false,
     */
    private boolean initPropertiesByCell(Row row, int rowIndex, T entity, List<ExcelErrorMessage> errorMessages) {

        int lengthOfBegin = errorMessages.size();
        Set<Map.Entry<Field, Integer>> fieldColumnIndexEntrySet = getCellIndexMapSet(entity.getClass());
        for (Map.Entry<Field, Integer> fieldColumnIndexEntry : fieldColumnIndexEntrySet) {
            int index = fieldColumnIndexEntry.getValue();
            Field field = fieldColumnIndexEntry.getKey();
            Class<?> cla = field.getType();

            // 根据字段类型，获取Excel中对应字段值
            Object val = null;
            try {
                if (cla == String.class) {
                    val = getCellStringValue(row, rowIndex, index, errorMessages);
                } else if (cla == Integer.class) {
                    val = parseLongToInt(getCellLongValue(row, rowIndex, index, errorMessages));
                } else if (cla == BigDecimal.class) {
                    val = getCellBigDecimalValue(row, rowIndex, index, errorMessages);
                } else if (cla == Long.class) {
                    val = getCellLongValue(row, rowIndex, index, errorMessages);
                }
                field.set(entity, val);
            } catch (Exception ex) {
                errorMessages.add(new ExcelErrorMessage(rowIndex, index, "单元格内容解析失败：" + ex.toString()));
                logger.error("initPropertiesByCell 处理把cell值赋值到对应属性字段时异常，val：{}， field：{}", val, field, ex);
            }

        }
        return errorMessages.size() == lengthOfBegin;
    }

    private Long getCellLongValue(Row row, int rowIndex, int cellIndex, List<ExcelErrorMessage> errorMessages) {
        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                return null;
            }
            Long val;
            //判断数据的类型
            switch (cell.getCellType()) {
                case NUMERIC: //数字
                    double dVal = cell.getNumericCellValue();
                    val = new Double(dVal).longValue();
                    break;

                case STRING: //字符串
                    String sVal = cell.getStringCellValue();
                    val = (sVal == null) ? null : Long.parseLong(sVal);
                    break;

                //Boolean
                case BLANK:
                    val = null;
                    break;
                default:
                    throw new IllegalPropertySetDataException("Excel解析失败，需要的字段类型为 Long，但Excel的字段类型为" + cell.getCellType());
            }
            return val;
        } catch (NumberFormatException numberFormatex) {
            logger.error("getCellStringValue 解析Excel信息失败:{}", numberFormatex.getMessage(), numberFormatex);
            errorMessages.add(new ExcelErrorMessage(rowIndex, cellIndex, "此单元格格式错误,只允许填写数字！"));
            return null;
        } catch (Exception ex) {
            logger.error("getCellStringValue 解析Excel信息失败", ex);
            errorMessages.add(new ExcelErrorMessage(rowIndex, cellIndex, "Excel单元格内容转换成Number类型失败：" + ex.toString()));
            return null;
        }
    }

    private String getCellStringValue(Row row, int rowIndex, int cellIndex, List<ExcelErrorMessage> errorMessages) {

        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                return null;
            }
            //判断数据的类型
            String val;
            switch (cell.getCellType()) {
                case NUMERIC:
                    cell.setCellType(STRING);
                    val = cell.getStringCellValue();
                    break;

                case STRING:
                    val = cell.getStringCellValue();
                    break;

                case BLANK:
                    val = null;
                    break;
                default:
                    throw new IllegalArgumentException("Excel解析失败，需要的字段类型为 String，但Excel的字段类型为" + cell.getCellType());
            }
            return val;
        } catch (Exception ex) {
            logger.error("getCellStringValue 解析Excel信息失败", ex);
            errorMessages.add(new ExcelErrorMessage(rowIndex, cellIndex, "Excel单元格内容转换成String类型失败：" + ex.toString()));
            return null;
        }
    }

    private BigDecimal getCellBigDecimalValue(Row row, int rowIndex, int cellIndex, List<ExcelErrorMessage> errorMessages) {

        try {
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                return null;
            }
            //判断数据的类型
            BigDecimal val;
            switch (cell.getCellType()) {
                case NUMERIC:
                    double dVal = cell.getNumericCellValue();
                    String valStr = Double.toString(dVal);
                    val = new BigDecimal(valStr);
                    break;

                case STRING: //字符串
                    String sval = cell.getStringCellValue();
                    val = new BigDecimal(sval);
                    break;

                case BLANK:
                    val = null;
                    break;
                default:
                    throw new IllegalArgumentException("Excel解析失败，需要的字段类型为 String，但Excel的字段类型为" + cell.getCellType());
            }
            return val;
        } catch (NumberFormatException numberFormatex) {
            logger.error("方法getCellBigDecimalValue解析Excel单元格失败:{}", numberFormatex.getMessage(), numberFormatex);
            errorMessages.add(new ExcelErrorMessage(rowIndex, cellIndex, "单元格式错误,只允许填写数字！"));
            return null;
        } catch (Exception ex) {
            logger.error("方法getCellBigDecimalValue解析Excel信息失败", ex);
            errorMessages.add(new ExcelErrorMessage(rowIndex, cellIndex, "Excel单元格内容转换成BigDecimal类型失败：" + ex.toString()));
            return null;
        }
    }

    private class MyRunnable implements Runnable {
        private final int currBeginRowNum;
        private final int currLastRowNum;
        private final Sheet sheet;
        private final ExcelParseContext<T> context;
        private final String userPin;

        private MyRunnable(Sheet sheet, ExcelParseContext<T> context, int currBeginRowNum, int currLastRowNum, String userPin) {
            this.sheet = sheet;
            this.currBeginRowNum = currBeginRowNum;
            this.currLastRowNum = currLastRowNum;
            this.context = context;
            this.userPin = userPin;
        }

        @Override
        public void run() {
            logger.info("解析Excel中内容-开始，currBeginRowNum：{}, currLastRowNum:{}", currBeginRowNum, currLastRowNum);
            for (int rowIndex = currBeginRowNum; rowIndex <= currLastRowNum; rowIndex++) {
                try {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        // 维护一个行号，用户信息
                        T entity = getInitEntity(rowIndex, userPin);

                        //解析当前行的每个cell，如果解析失败则返回错误信息
                        if (initPropertiesByCell(row, rowIndex, entity, context.getErrorMessages())) {
                            context.addEntity(entity);
                        }
                    }
                } catch (Exception ex) {
                    logger.error("InitPropertiesWithExcelHandler.handler Excel解析失败：{}", ex.getMessage(), ex);
                    context.addErrorMsg(rowIndex, 0, ex.getMessage());
                }
            }
            logger.info("解析Excel中内容-完成，currBeginRowNum：{}, currLastRowNum:{}", currBeginRowNum, currLastRowNum);
        }
    }

    private Set<Map.Entry<Field, Integer>> getCellIndexMapSet(Class<?> tClass) {

        ImmutableMap.Builder<Field, Integer> builder = ImmutableMap.builder();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ColumnIndex annotation = field.getAnnotation(ColumnIndex.class);
            if (annotation != null) {
                builder.put(field, annotation.value());
            }
        }
        return builder.build().entrySet();
    }

    /**
     * 获取实例化的实体信息，可以自行完成一些初始化
     *
     * @param rowNum  行号，0 开始
     * @param userPin 用户 pin
     * @return 返回实例化好的实体
     */
    protected abstract T getInitEntity(int rowNum, String userPin);

    /**
     * 完成实体校验
     */
    protected abstract void valid(ExcelParseContext<T> context);

    /**
     * 后置处理
     */
    protected abstract void postProcess(ExcelParseContext<T> context);


    private Integer parseLongToInt(Long val) {
        return val == null ? null : val.intValue();
    }
}
