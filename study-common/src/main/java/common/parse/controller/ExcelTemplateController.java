package common.parse.controller;

import common.parse.common.ExcelTemplateEnum;
import common.parse.common.ExcelUtils;
import common.parse.context.UserIdParseContext;
import common.parse.entity.bean.ExcelUserEntity;
import common.parse.handler.imports.ExcelImportHandler;
import common.study.option.ServiceResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * excel 模板下载和数据导入
 * Created by panyingting
 */
@RestController
public class ExcelTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelTemplateController.class);

    @Resource(name = "userEntityExcelParseHandler")
    private ExcelImportHandler<ExcelUserEntity> userEntityExcelParseHandler;

    @RequestMapping("/excel/generateTemplateMapping/{type}")
    public void generateTemplateExcel(HttpServletResponse response, @PathVariable Integer type) throws Exception {

        logger.info("生成商品类目模板 begin，type：{}......", type);
        OutputStream outStream = null;

        try {
            ExcelTemplateEnum excelTemplateEnum = ExcelTemplateEnum.getEnumInstance(type);

            //文件输出流
            ExcelUtils.initHttpServletResponse(response, excelTemplateEnum.name);
            outStream = response.getOutputStream();

            XSSFWorkbook workbook = excelTemplateEnum.getTemplate();
            workbook.write(outStream);
            outStream.flush();

        } catch (Exception ex) {
            logger.error("生成excel模板异常，type:{}", type, ex);
            if (outStream != null) {
                outStream.write(ex.getMessage().getBytes());
            }
        } finally {
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    @RequestMapping("/excel/importExcelMapping/{type}")
    public ServiceResponse<?> importCategoryInfo(@RequestParam("excelFile") MultipartFile file, @PathVariable Integer type, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        InputStream inputStream = null;
        ServiceResponse<?> result;
        try {
            logger.info("importExcel：批量商品类目信息导入开始 FileName:{}", fileName);
            ExcelTemplateEnum excelTemplateEnum = ExcelTemplateEnum.getEnumInstance(type);
            inputStream = file.getInputStream();

            switch (excelTemplateEnum) {
                case COUPON_USER:
                    UserIdParseContext context = new UserIdParseContext(inputStream);
                    context.setUploadFilename(fileName);
                    context.setCookies(request.getCookies());
                    // 解析Excel内容
                    userEntityExcelParseHandler.handle(context);
                    result = context.getResult();
                    break;

                default:
                    result = ServiceResponse.createFailResponse(ServiceResponse.PARAM_ERROR_RESPONSE.getCode(), "模板类型不对！");
            }

            logger.info("importExcel：批量信息导入完成 FileName:{}", fileName);
        } catch (RuntimeException bex) {
            logger.warn("importExcel：批量信息导入异常, msg:{}", bex.getMessage());
            result = ServiceResponse.createFailResponse(ServiceResponse.PARAM_ERROR_RESPONSE.getCode(), bex.getMessage());
        } catch (Exception e) {
            logger.error("importExcel：批量信息导入异常 FileName:{}", fileName, e);
            result = ServiceResponse.createFailResponse(ServiceResponse.PARAM_ERROR_RESPONSE.getCode(), "批量信息导入异常，请联系运营人员!");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return result;
    }

}