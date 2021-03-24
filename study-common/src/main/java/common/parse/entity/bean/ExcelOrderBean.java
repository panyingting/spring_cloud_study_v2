package common.parse.entity.bean;


import common.parse.entity.bean.annotation.ColumnIndex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ExcelOrderBean {

    public static final int LAST_COL_IDX = 18;

    @ColumnIndex(0)
    private String userOrderNo; //用户单号

    @NotEmpty(message = "订单渠道不能为空")
    @ColumnIndex(1)
    private String channelName; // 订单渠道

    @NotEmpty(message = "业务类型不能为空")
    @ColumnIndex(2)
    private String activeType;          // 业务类型

    @NotNull(message = "用户ID不能为空,或创建用户失败")
    @ColumnIndex(3)
    private Long userId;                // 用户ID

    @ColumnIndex(4)
    private String sourceStation;       // 来源分站

    @NotEmpty(message = "商品类型不能为空")
    @ColumnIndex(5)
    private String goodsTypeName;       // 商品类型

    @NotEmpty(message = "商品编码不能为空")
    @ColumnIndex(6)
    private String goodsCode;           // 商品编码

    @NotNull(message = "商品数量不能为空")
    @ColumnIndex(7)
    private Integer goodsNum;           // 商品数量

    @NotEmpty(message = "支付价格不能为空")
    @ColumnIndex(8)
    private String payMoney;            // 支付价格

    @NotEmpty(message = "运费不能为空")
    @ColumnIndex(9)
    private String expressPrice;        // 运费

    @NotEmpty(message = "订单状态不能为空")
    @ColumnIndex(10)
    private String orderStatName;       // 订单状态

    @NotEmpty(message = "收货人不能为空")
    @ColumnIndex(11)
    private String consignee;           // 收货人

    @NotEmpty(message = "收货人手机号不能为空")
    @ColumnIndex(12)
    private String consigneePhone;      // 收货人手机号

    @NotEmpty(message = "收货人省份不能为空")
    @ColumnIndex(13)
    private String toProvince;          // 收货人省份

    @NotEmpty(message = "收货人城市不能为空")
    @ColumnIndex(14)
    private String toCity;              // 收货人城市

    @NotEmpty(message = "收件人收件人区县不能为空")
    @ColumnIndex(15)
    private String toArea;              // 收件人区县

    @NotEmpty(message = "收件人详细地址不能为空")
    @ColumnIndex(16)
    private String detailAddress;      // 详细地址

    @ColumnIndex(17)
    private Long promotionId;      // 促销活动Id


    /*********** 非 Excel 字段 ****************/

    private int goodsType;

    private long goodsId;

    private int rowIndex;         // 行号

    private String errorMessage;

    private int retryCount;         // 如果允许重试的话，此计数可用于统计已重试次数

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getToArea() {
        return toArea;
    }

    public void setToArea(String toArea) {
        this.toArea = toArea;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getUserOrderNo() {
        return userOrderNo;
    }

    public void setUserOrderNo(String userOrderNo) {
        this.userOrderNo = userOrderNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getOrderStatName() {
        return orderStatName;
    }

    public void setOrderStatName(String orderStatName) {
        this.orderStatName = orderStatName;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    @Override
    public String toString() {
        return "ExcelOrderBean{" +
                "userOrderNo='" + userOrderNo + '\'' +
                ", channelName='" + channelName + '\'' +
                ", activeType='" + activeType + '\'' +
                ", userId=" + userId +
                ", sourceStation='" + sourceStation + '\'' +
                ", goodsTypeName='" + goodsTypeName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsNum=" + goodsNum +
                ", payMoney='" + payMoney + '\'' +
                ", expressPrice='" + expressPrice + '\'' +
                ", orderStatName='" + orderStatName + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", toProvince='" + toProvince + '\'' +
                ", toCity='" + toCity + '\'' +
                ", toArea='" + toArea + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", promotionId=" + promotionId +
                ", goodsType=" + goodsType +
                ", goodsId=" + goodsId +
                ", rowIndex=" + rowIndex +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
