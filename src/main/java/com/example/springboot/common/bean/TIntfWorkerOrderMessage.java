package com.example.springboot.common.bean;

import java.util.Date;

/**
 * t_intf_worker_order_message
 * @author 
 */
public class TIntfWorkerOrderMessage {
    /**
     * 记录ID
     */
    private Long recId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 省份编码
     */
    private String provCode;

    /**
     * 关联系统编码
     */
    private String rltSysCode;

    /**
     * 操作类型代码
     */
    private String opTypeCd;

    /**
     * 工单流水号
     */
    private String wrkfmShowSwftno;

    /**
     * 报文内容1
     */
    private String msgCntt1;

    /**
     * 报文内容2
     */
    private String msgCntt2;

    /**
     * 报文内容3
     */
    private String msgCntt3;

    /**
     * 操作员工ID
     */
    private String opStaffId;

    /**
     * 操作员工部门ID
     */
    private String opStaffDeptId;

    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 修改时间
     */
    private Date modfTime;

    /**
     * 备注字段1
     */
    private String spareField1;

    /**
     * 备用字段2
     */
    private String spareField2;

    /**
     * 备用字段3
     */
    private String spareField3;

    /**
     * 备用字段4
     */
    private String spareField4;

    /**
     * 备用字段5
     */
    private String spareField5;

    private Date cmosModifyTime;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getRltSysCode() {
        return rltSysCode;
    }

    public void setRltSysCode(String rltSysCode) {
        this.rltSysCode = rltSysCode;
    }

    public String getOpTypeCd() {
        return opTypeCd;
    }

    public void setOpTypeCd(String opTypeCd) {
        this.opTypeCd = opTypeCd;
    }

    public String getWrkfmShowSwftno() {
        return wrkfmShowSwftno;
    }

    public void setWrkfmShowSwftno(String wrkfmShowSwftno) {
        this.wrkfmShowSwftno = wrkfmShowSwftno;
    }

    public String getMsgCntt1() {
        return msgCntt1;
    }

    public void setMsgCntt1(String msgCntt1) {
        this.msgCntt1 = msgCntt1;
    }

    public String getMsgCntt2() {
        return msgCntt2;
    }

    public void setMsgCntt2(String msgCntt2) {
        this.msgCntt2 = msgCntt2;
    }

    public String getMsgCntt3() {
        return msgCntt3;
    }

    public void setMsgCntt3(String msgCntt3) {
        this.msgCntt3 = msgCntt3;
    }

    public String getOpStaffId() {
        return opStaffId;
    }

    public void setOpStaffId(String opStaffId) {
        this.opStaffId = opStaffId;
    }

    public String getOpStaffDeptId() {
        return opStaffDeptId;
    }

    public void setOpStaffDeptId(String opStaffDeptId) {
        this.opStaffDeptId = opStaffDeptId;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getModfTime() {
        return modfTime;
    }

    public void setModfTime(Date modfTime) {
        this.modfTime = modfTime;
    }

    public String getSpareField1() {
        return spareField1;
    }

    public void setSpareField1(String spareField1) {
        this.spareField1 = spareField1;
    }

    public String getSpareField2() {
        return spareField2;
    }

    public void setSpareField2(String spareField2) {
        this.spareField2 = spareField2;
    }

    public String getSpareField3() {
        return spareField3;
    }

    public void setSpareField3(String spareField3) {
        this.spareField3 = spareField3;
    }

    public String getSpareField4() {
        return spareField4;
    }

    public void setSpareField4(String spareField4) {
        this.spareField4 = spareField4;
    }

    public String getSpareField5() {
        return spareField5;
    }

    public void setSpareField5(String spareField5) {
        this.spareField5 = spareField5;
    }

    public Date getCmosModifyTime() {
        return cmosModifyTime;
    }

    public void setCmosModifyTime(Date cmosModifyTime) {
        this.cmosModifyTime = cmosModifyTime;
    }
}