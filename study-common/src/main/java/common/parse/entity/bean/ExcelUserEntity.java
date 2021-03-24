package common.parse.entity.bean;


import common.parse.entity.bean.annotation.ColumnIndex;

public class ExcelUserEntity {

    @ColumnIndex(0)
    private Long userId;

    private int rowNum;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
