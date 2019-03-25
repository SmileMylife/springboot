package com.example.springboot.util;

/**
 * Created by ZhangPei on 2019/3/23.
 */
public class My {
    public static void main(String[] args) {
        String s = "INSERT INTO t_sr_service_request_type (\n" +
                "\t'REC_ID',\n" +
                "\t'SRV_REQST_TYPE_ID',\n" +
                "\t'TENANT_ID',\n" +
                "\t'PROV_CODE',\n" +
                "\t'SEQING_ID',\n" +
                "\t'SUPR_SEQING_ID',\n" +
                "\t'SUPR_SRV_REQST_TYPE_ID',\n" +
                "\t'SRV_REQST_BIG_CLA_ID',\n" +
                "\t'OPTOR_NO',\n" +
                "\t'SRV_REQST_TYPE_NM',\n" +
                "\t'SRV_REQST_TYPE_FULL_NM',\n" +
                "\t'SRV_REQST_TYPE_CNTT',\n" +
                "\t'LEAF_NODE_FLAG',\n" +
                "\t'RSN_TYPE_ID',\n" +
                "\t'KYWDS_DESC',\n" +
                "\t'ORG_BRNCH_ID',\n" +
                "\t'OP_STAFF_ID',\n" +
                "\t'OP_TIME',\n" +
                "\t'OP_NO',\n" +
                "\t'REC_STS_CD',\n" +
                "\t'VERNO',\n" +
                "\t'VER_NM',\n" +
                "\t'BELG_PLC_CODE',\n" +
                "\t'EFF_TIME',\n" +
                "\t'INVLD_TIME',\n" +
                "\t'VSBL_FLAG',\n" +
                "\t'OPTNL_FLAG',\n" +
                "\t'ACPT_FLAG',\n" +
                "\t'WHNTWK_SRV_REQST_ID',\n" +
                "\t'ISUD_FLAG',\n" +
                "\t'CUSE_WORK_GRP_ID',\n" +
                "\t'IS_NET',\n" +
                "\t'BIZ_BELG_DEPT_ID',\n" +
                "\t'SRV_REQST_TYPE_CLCK_QUT',\n" +
                "\t'BILL_FALG',\n" +
                "\t'LIMIT_VSBL_DEPT',\n" +
                "\t'NEW_SRV_REQST_ID',\n" +
                "\t'NEW_SUPR_SRV_REQST_ID',\n" +
                "\t'IS_CSVC_FLAG' \n" +
                ")\n" +
                "VALUES\n" +
                "\t(\n" +
                "\t\t% s（主键）,\n" +
                "\t\t% s（服务请求类型）,\n" +
                "\t\t'100000',\n" +
                "\t\t'00030010',\n" +
                "\t\t% s ( 服务请求类型 ),\n" +
                "\t\t% s（父服务请求类型）,\n" +
                "\t\t% s ( 父服务请求类型 ),\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\t% s ( 名称 ),\n" +
                "\t\t% s（全称）,\n" +
                "\t\tNULL,\n" +
                "\t\t% s（是否叶子）,\n" +
                "\t\tNULL,\n" +
                "\t\t% s ( 关键词 ),\n" +
                "\t\tNULL,\n" +
                "\t\t'SX0001',\n" +
                "\t\tnow( ),\n" +
                "\t\t'SX0001',\n" +
                "\t\t'1',\n" +
                "\t\t% s ( 版本号 ),\n" +
                "\t\t% s ( 版本名称 ),\n" +
                "\t\tNULL,\n" +
                "\t\tnow( ),\n" +
                "\t\tnow( ),\n" +
                "\t\t'1',\n" +
                "\t\t'1',\n" +
                "\t\t'1',\n" +
                "\t\t% s ( 全网节点 ),\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "\t\tNULL,\n" +
                "NULL \n" +
                "\t);";
        String replace = s.replace("\t\t", "").replace("\n", "");
        System.out.println(replace);
        
        String s1 = "INSERT INTO t_sr_service_request_type ('REC_ID','SRV_REQST_TYPE_ID','TENANT_ID','PROV_CODE','SEQING_ID','SUPR_SEQING_ID','SUPR_SRV_REQST_TYPE_ID','SRV_REQST_BIG_CLA_ID','OPTOR_NO','SRV_REQST_TYPE_NM','SRV_REQST_TYPE_FULL_NM','SRV_REQST_TYPE_CNTT','LEAF_NODE_FLAG','RSN_TYPE_ID','KYWDS_DESC','ORG_BRNCH_ID','OP_STAFF_ID','OP_TIME','OP_NO','REC_STS_CD','VERNO','VER_NM','BELG_PLC_CODE','EFF_TIME','INVLD_TIME','VSBL_FLAG','OPTNL_FLAG','ACPT_FLAG','WHNTWK_SRV_REQST_ID','ISUD_FLAG','CUSE_WORK_GRP_ID','IS_NET','BIZ_BELG_DEPT_ID','SRV_REQST_TYPE_CLCK_QUT','BILL_FALG','LIMIT_VSBL_DEPT','NEW_SRV_REQST_ID','NEW_SUPR_SRV_REQST_ID','IS_CSVC_FLAG' )VALUES(% s（主键）,% s（服务请求类型）,'100000','00030010',% s ( 服务请求类型 ),% s（父服务请求类型）,% s ( 父服务请求类型 ),NULL,NULL,% s ( 名称 ),% s（全称）,NULL,% s（是否叶子）,NULL,% s ( 关键词 ),NULL,'SX0001',now( ),'SX0001','1',% s ( 版本号 ),% s ( 版本名称 ),NULL,now( ),now( ),'1','1','1',% s ( 全网节点 ),NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);";
        
    }
}
