package common.parse.handler.imports.impl;

import com.google.common.collect.ImmutableMap;
import common.parse.check.CheckUtils;
import common.parse.common.ExcelUtils;
import common.parse.context.ExcelParseContext;
import common.parse.context.UserIdParseContext;
import common.parse.entity.bean.ExcelErrorMessage;
import common.study.option.ServiceResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("userEntityExcelParseHandler")
public class UserEntityExcelParseHandler extends AbstractUserEntityExcelParseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserEntityExcelParseHandler.class);

    @Override
    protected void postProcess(ExcelParseContext _context) {

        UserIdParseContext context = (UserIdParseContext) _context;

        String oldFilename = context.getUploadFilename();
        File uploadFile = null;
        try {

            // 根据错误信息处理文件信息，包括生成文件名，补充错误内容等（如果有错误信息的话）
            String uploadFilename = processFileInfoWithErrorMsg(context);

            // 生成本地文件,本地生成文件用的文件名，加时间戳避免名字重复
            uploadFile = createLocalFile(uploadFilename);

            Workbook workbook = context.getWorkBook();
            FileOutputStream outputStream = new FileOutputStream(uploadFile);
            workbook.write(outputStream);
            outputStream.flush();

            StringBuilder cookieValue = new StringBuilder(400);
            Cookie[] cookies = context.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                cookieValue.append(name).append("=").append(value).append(";");
            }
            logger.info("模拟cookie:{}", cookieValue.toString());

            // 原来上传工具类 HttpUploadFileUtil.httpUpload(uploadUrl, cookieValue.toString(), uploadFile, ExcelUtils.USERID_EXCEL_FILE_PREFIX);
            ServiceResponse<String> uploadResponse = null;
            Map<String, String> map = ImmutableMap.of("filename", uploadFilename, "url", ExcelUtils.HTTPS_PREFIX + uploadResponse.getData());

            if (uploadResponse.isFailure()) {
                context.setResult(ServiceResponse.createFailResponse(ServiceResponse.FAIL_KEY, map, uploadResponse.getMsg()));
            } else {
                if (context.getErrorMessages().isEmpty()) {
                    context.setResult(ServiceResponse.createSuccessResponse(map));
                } else {
                    context.setResult(ServiceResponse.createFailResponse(ServiceResponse.FAIL_KEY, map, "文件解析失败！"));
                }
            }


        } catch (Exception e) {
            logger.error("op上传excel出现异常，filename:{}", oldFilename, e);
            throw new RuntimeException("op上传excel出现异常" + e.getMessage());
        } finally {
            if (uploadFile != null) {
                boolean close = uploadFile.delete();
                logger.info("关闭文件完成， close:{}, filename:{}", close, oldFilename);
            }
        }
    }

    private String processFileInfoWithErrorMsg(UserIdParseContext context) {

        String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String filename = context.getUploadFilename();
        int index = filename.lastIndexOf(".");

        String prefixName = filename.substring(0, index);
        String suffixName = timeStr + filename.substring(index);
        ;
        String uploadName;

        List<ExcelErrorMessage> errorMessages = context.getErrorMessages();
        if (!errorMessages.isEmpty()) {
            uploadName = prefixName + "-误-" + suffixName;
            fillExcelErrorMessage(context);
        } else {
            uploadName = prefixName + "-" + suffixName;
        }

        return uploadName;

    }

    private void fillExcelErrorMessage(UserIdParseContext context) {

        Sheet sheet = context.getWorkBook().getSheetAt(0);

        for (ExcelErrorMessage msg : context.getErrorMessages()) {
            Row row = sheet.getRow(msg.getRow());
            Cell cell = row.createCell(1);
            cell.setCellValue(msg.getMessage());
        }

    }

    private File createLocalFile(String uploadFilename) throws IOException {
        File uploadFile = new File(this.getClass().getResource("").getPath() + File.separator + uploadFilename);
        if (uploadFile.exists()) {
            CheckUtils.checkIfTrue(!uploadFile.delete(), "文件名已被占用，请换文件名后再试！");
        }
        boolean created = uploadFile.createNewFile();
        if (!created) {
            throw new RuntimeException("创建本地文件失败, path:" + uploadFile.getCanonicalPath());
        }
        return uploadFile;
    }

}
