package common.parse.entity.bean;

import java.util.List;

/**
 * Excel 错误信息
 * Created by panyingting
 */
public class ExcelErrorMessage {
    private Integer row;

    private Integer col;

    private String message;

    public ExcelErrorMessage() {
    }

    public ExcelErrorMessage(Integer row, Integer col, String message) {
        this.row = row;
        this.col = col;
        this.message = message;
    }

    public static ExcelErrorMessage builder() {
        return new ExcelErrorMessage();
    }

    public Integer getRow() {
        return row;
    }

    public ExcelErrorMessage setRow(Integer row) {
        this.row = row;
        return this;
    }

    public Integer getCol() {
        return col;
    }

    public ExcelErrorMessage setCol(Integer col) {
        this.col = col;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExcelErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public void saveMessage(List<ExcelErrorMessage> list) {
        list.add(this);
    }
}
