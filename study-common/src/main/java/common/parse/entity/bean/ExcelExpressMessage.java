package common.parse.entity.bean;


import common.parse.entity.bean.annotation.ColumnIndex;

/**
 * 上传excel表格，模版字段优化，优化为：订单号+物流公司名称+物流单号；通过订单号关联相应订单，展示物流信息
 * http://wiki.babyfs-inc.cn/pages/viewpage.action?pageId=42468317
 */
public class ExcelExpressMessage {

    private int rowNum;

    @ColumnIndex(0)
    private String orderItemNo;

    @ColumnIndex(1)
    private String expressNo;

    @ColumnIndex(2)
    private String expressCom;


    public String getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(String orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCom() {
        return expressCom;
    }

    public void setExpressCom(String expressCom) {
        this.expressCom = expressCom;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}
