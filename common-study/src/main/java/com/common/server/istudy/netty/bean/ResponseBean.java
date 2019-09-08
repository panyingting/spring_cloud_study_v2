package com.common.server.istudy.netty.bean;

import java.io.Serializable;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2018/6/24
 * @ 功能描述 保存响应参数
 */
public class ResponseBean implements Serializable {
    /**
     * 默认序列ID
     */
    private static final long serialVersionUID = 1L;

    private int subReqID;

    private int respCode;

    private String desc;

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
     * @return the respCode
     */
    public final int getRespCode() {
        return respCode;
    }

    /**
     * @param respCode the respCode to set
     */
    public final void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    /**
     * @return the desc
     */
    public final String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public final void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "ResponseBean [subReqID=" + subReqID + ", respCode=" + respCode
                + ", desc=" + desc + "]";
    }
}
//
// ————————————————
//         版权声明：本文为CSDN博主「贾红平」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/qq_18603599/article/details/80768403