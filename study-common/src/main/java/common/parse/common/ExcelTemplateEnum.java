package common.parse.common;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel模板枚举
 * Created by panyingting
 */
public enum ExcelTemplateEnum { /**/
    COUPON_USER(10, "用户ID模板", new String[]{"用户ID"}),

    FISSION_ACCOUNT(20, "分销账户信息", new String[]{"用户ID", "微信昵称", "手机号", "身份证号", "姓名", "累计收益", "已提现收益", "剩余可提现收益", "税后剩余可提现收益", "账户状态"}),

    FISSION_WITHDRAW(30, "分销提现", new String[]{"日 期", "提现金额"}),

    GOODS_INFO_EXPORT(40, "商品信息导出", new String[]{"序号", "组合商品编码", "组合名称", "渠道", "是否上架", "实际售卖状态", "创建时间", "组合商品上架价格", "sku类型", "sku编码", "sku名称", "sku价格"}),

    EXPRESS_IMPORT(50, "批量发货模板", new String[]{"子订单号", "物流单号", "物流公司"});

    public final int code;
    public final String name;
    public final String[] titles;

    ExcelTemplateEnum(int code, String name, String[] titles) {
        this.code = code;
        this.name = name;
        this.titles = titles;
    }

    public static ExcelTemplateEnum getEnumInstance(Integer code) {
        if (code == null) {
            return null;
        } else {
            ExcelTemplateEnum[] instances = values();
            for (ExcelTemplateEnum excelTemplateEnum : instances) {
                if (excelTemplateEnum.code == code) {
                    return excelTemplateEnum;
                }
            }
            return null;
        }
    }

    /**
     * 获取 SXSSFWorkbook 模板对象
     */
    public SXSSFWorkbook getSXSSFWorkbookTemplate() {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        setupHeader(workbook);
        return workbook;
    }

    public XSSFWorkbook getTemplate() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        setupHeader(workbook);
        return workbook;
    }

    private void setupHeader(Workbook workBook) {
        Sheet sheet = workBook.createSheet(this.name);
        // 创建表格标题行 第一行
        Row titleRow = sheet.createRow(0);
        CellStyle cellStyleOrange = getCellStyleByColor(workBook, IndexedColors.LIGHT_ORANGE.getIndex());
        int charNum;
        if (titles.length <= 5) {
            charNum = 40;
        } else if (titles.length <= 10) {
            charNum = 26;
        } else {
            charNum = 20;
        }

        int width = charNum * 256;
        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnWidth(i, width);
            Cell xssfCell = titleRow.createCell(i);
            xssfCell.setCellValue(new XSSFRichTextString(titles[i]));
            xssfCell.setCellStyle(cellStyleOrange);
        }
        titleRow.setHeightInPoints(30);
    }

    private CellStyle getCellStyleByColor(Workbook workBook, short fg) {
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(fg);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
}