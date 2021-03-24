package common.parse.common;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author : Pan Yingting
 * @date : 2021/3/24 1:56 下午
 */
public class ExcelUtils {
    public static final String USERID_EXCEL_FILE_PREFIX = "user-coupon-poi-upload";

    public static final String HTTPS_PREFIX = "https://";


    /**
     * 文件输出流 HttpServletResponse 初始化
     */
    public static void initHttpServletResponse(HttpServletResponse response, String name) throws UnsupportedEncodingException {

        String templateName = name + ".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(templateName.getBytes(), "ISO8859-1"));
    }

    public static XSSFCellStyle getXSSFCellStyle(XSSFWorkbook workBook, IndexedColors colors, FillPatternType pattern) {
        XSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFillPattern(pattern);
        cellStyle.setFillForegroundColor(colors.index);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

}
