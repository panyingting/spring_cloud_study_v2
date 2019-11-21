package common;

public class RefundSuccessMsg {

    private String orderNo;
    private String refundNo;
    private String thirdPartyRefundNo;
    private long refundFee;
    private long refundTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getThirdPartyRefundNo() {
        return thirdPartyRefundNo;
    }

    public void setThirdPartyRefundNo(String thirdPartyRefundNo) {
        this.thirdPartyRefundNo = thirdPartyRefundNo;
    }

    public long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(long refundFee) {
        this.refundFee = refundFee;
    }

    public long getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(long refundTime) {
        this.refundTime = refundTime;
    }

}
