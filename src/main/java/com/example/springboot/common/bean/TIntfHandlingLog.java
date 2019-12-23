package com.example.springboot.common.bean;

import java.util.Date;

/**
 * t_intf_handling_log
 * @author 
 */
public class TIntfHandlingLog {
    /**
     * 日志ID
     */
    private Long lgId;

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
     * 操作状态代码
     */
    private String opStsCd;

    /**
     * 操作类型代码
     */
    private String opTypeCd;

    /**
     * 工单流水号
     */
    private String wrkfmShowSwftno;

    /**
     * 外系统工单流水号
     */
    private String outSysWrkfmShowSwftno;

    /**
     * 发起方
     */
    private String inttr;

    /**
     * 落地方
     */
    private String target;

    /**
     * 异步系统标识，0同步，1异步
     */
    private Byte asyncSysFlag;

    /**
     * 操作次数
     */
    private Byte opCnt;

    /**
     * 接口返回内容
     */
    private String intfBacktoCntt;

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

    private Date cmosModifyTime;

    public Long getLgId() {
        return lgId;
    }

    public void setLgId(Long lgId) {
        this.lgId = lgId;
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

    public String getOpStsCd() {
        return opStsCd;
    }

    public void setOpStsCd(String opStsCd) {
        this.opStsCd = opStsCd;
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

    public String getOutSysWrkfmShowSwftno() {
        return outSysWrkfmShowSwftno;
    }

    public void setOutSysWrkfmShowSwftno(String outSysWrkfmShowSwftno) {
        this.outSysWrkfmShowSwftno = outSysWrkfmShowSwftno;
    }

    public String getInttr() {
        return inttr;
    }

    public void setInttr(String inttr) {
        this.inttr = inttr;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Byte getAsyncSysFlag() {
        return asyncSysFlag;
    }

    public void setAsyncSysFlag(Byte asyncSysFlag) {
        this.asyncSysFlag = asyncSysFlag;
    }

    public Byte getOpCnt() {
        return opCnt;
    }

    public void setOpCnt(Byte opCnt) {
        this.opCnt = opCnt;
    }

    public String getIntfBacktoCntt() {
        return intfBacktoCntt;
    }

    public void setIntfBacktoCntt(String intfBacktoCntt) {
        this.intfBacktoCntt = intfBacktoCntt;
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

    public Date getCmosModifyTime() {
        return cmosModifyTime;
    }

    public void setCmosModifyTime(Date cmosModifyTime) {
        this.cmosModifyTime = cmosModifyTime;
    }
}