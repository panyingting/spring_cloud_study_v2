package com.common.server.istudy.netty.bean;

import java.io.Serializable;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2018/6/24
 * @ 功能描述 保存请求参数
 */
public class RequestBean implements Serializable {
    /**
     * 默认的序列号ID
     */
    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String userName;

    private String productName;

    private String phoneNumber;

    private String address;

    /**
     * @return the subReqID
     */
    public final int getSubReqID() {
        return subReqID;
    }

    /**
     * @param subReqID the subReqID to set
     */
    public final void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    /**
     * @return the userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the productName
     */
    public final String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public final void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the phoneNumber
     */
    public final String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public final void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the address
     */
    public final String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public final void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "RequestBean [subReqID=" + subReqID + ", userName=" + userName
                + ", productName=" + productName + ", phoneNumber="
                + phoneNumber + ", address=" + address + "]";
    }
}
// ————————————————
//         版权声明：本文为CSDN博主「贾红平」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/qq_18603599/article/details/80768403
