package com.example.springboot.common.bean;

import java.util.Date;

/**
 * t_intf_const_config
 * @author 
 */
public class TIntfConstConfig {
    /**
     * 配置ID
     */
    private Long configId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 省份编码
     */
    private String provCode;

    /**
     * 编码类型代码
     */
    private String codeTypeCd;

    /**
     * 编码名称
     */
    private String codeNm;

    /**
     * 编码全称
     */
    private String codeFullNm;

    /**
     * 排序序号
     */
    private Short argeSeqno;

    /**
     * 叶节点标志
     */
    private Byte leafNodeFlag;

    /**
     * 状态
     */
    private Byte validFlag;

    /**
     * 备注
     */
    private String rmk;

    /**
     * 操作员工ID
     */
    private String opStaffId;

    /**
     * 组织机构ID
     */
    private String orgBrnchId;

    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 修改时间
     */
    private Date modfTime;

    /**
     * 业务编码
     */
    private String bizCode;

    /**
     * 上级业务编码
     */
    private String suprBizCode;

    /**
     * 编码类型名称
     */
    private String codeTypeNm;

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

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
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

    public String getCodeTypeCd() {
        return codeTypeCd;
    }

    public void setCodeTypeCd(String codeTypeCd) {
        this.codeTypeCd = codeTypeCd;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getCodeFullNm() {
        return codeFullNm;
    }

    public void setCodeFullNm(String codeFullNm) {
        this.codeFullNm = codeFullNm;
    }

    public Short getArgeSeqno() {
        return argeSeqno;
    }

    public void setArgeSeqno(Short argeSeqno) {
        this.argeSeqno = argeSeqno;
    }

    public Byte getLeafNodeFlag() {
        return leafNodeFlag;
    }

    public void setLeafNodeFlag(Byte leafNodeFlag) {
        this.leafNodeFlag = leafNodeFlag;
    }

    public Byte getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Byte validFlag) {
        this.validFlag = validFlag;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getOpStaffId() {
        return opStaffId;
    }

    public void setOpStaffId(String opStaffId) {
        this.opStaffId = opStaffId;
    }

    public String getOrgBrnchId() {
        return orgBrnchId;
    }

    public void setOrgBrnchId(String orgBrnchId) {
        this.orgBrnchId = orgBrnchId;
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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getSuprBizCode() {
        return suprBizCode;
    }

    public void setSuprBizCode(String suprBizCode) {
        this.suprBizCode = suprBizCode;
    }

    public String getCodeTypeNm() {
        return codeTypeNm;
    }

    public void setCodeTypeNm(String codeTypeNm) {
        this.codeTypeNm = codeTypeNm;
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