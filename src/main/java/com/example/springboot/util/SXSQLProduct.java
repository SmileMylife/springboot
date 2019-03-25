package com.example.springboot.util;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangPei on 2019/3/22.
 */
public class SXSQLProduct {
    public static void main(String[] args) throws IOException {
        //服务请求类型编码
        List<String> srReqstTypeId = readSrReqstTypeId();
        List<String> suprSrvReqstTypeId = readSuprSrvReqstTypeId();
        List<String> srvReqstTypeNm = readSrvReqstTypeNm();
        List<String> srvReqstTypeFullNm = readSrvReqstTypeFullNm();
        List<String> leafNodeFlag = readLeafNodeFlag();
        List<String> kywdsDesc = readKywdsDesc();
        List<String> verno = readVerno();
        List<String> verNm = readVerNm();
        List<String> whntwkSrvReqstId = readWhntwkSrvReqstId();

        BigInteger bigInteger = new BigInteger("1903221259330146001");

        for (int i = 0; i < srReqstTypeId.size(); i++) {
            String template = "INSERT INTO t_sr_service_request_type (REC_ID,SRV_REQST_TYPE_ID,TENANT_ID,PROV_CODE,SEQING_ID,SUPR_SEQING_ID,SUPR_SRV_REQST_TYPE_ID,SRV_REQST_BIG_CLA_ID,OPTOR_NO,SRV_REQST_TYPE_NM,SRV_REQST_TYPE_FULL_NM,SRV_REQST_TYPE_CNTT,LEAF_NODE_FLAG,RSN_TYPE_ID,KYWDS_DESC,ORG_BRNCH_ID,OP_STAFF_ID,OP_TIME,OP_NO,REC_STS_CD,VERNO,VER_NM,BELG_PLC_CODE,EFF_TIME,INVLD_TIME,VSBL_FLAG,OPTNL_FLAG,ACPT_FLAG,WHNTWK_SRV_REQST_ID,ISUD_FLAG,CUSE_WORK_GRP_ID,IS_NET,BIZ_BELG_DEPT_ID,SRV_REQST_TYPE_CLCK_QUT,BILL_FALG,LIMIT_VSBL_DEPT,NEW_SRV_REQST_ID,NEW_SUPR_SRV_REQST_ID,IS_CSVC_FLAG)VALUES(%s,%s,'100000','00030010',%s,%s,%s,NULL,NULL,%s,%s,NULL,%s,NULL,%s,NULL,'SX0001',now(),'SX0001','1',%s,%s,NULL,now(),now(),'1','1','1',%s,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);";
            String del = "DELETE FROM t_sr_service_request_type WHERE rec_id = %s;";
            String format = String.format(template, "'" + bigInteger.toString() + "'",
                    "'" + srReqstTypeId.get(i) + "'", "'" + srReqstTypeId.get(i) + "'",
                    "'" + suprSrvReqstTypeId.get(i) + "'", "'" + suprSrvReqstTypeId.get(i) + "'",
                    "'" + srvReqstTypeNm.get(i) + "'", "'" + srvReqstTypeFullNm.get(i) + "'",
                    "'" + leafNodeFlag.get(i) + "'", "'" + kywdsDesc.get(i) + "'",
                    "'" + verno.get(i) + "'", "'" + verNm.get(i) + "'", "'" + whntwkSrvReqstId.get(i) + "'");
            String format1 = String.format(del, bigInteger);
            System.out.println(format1);
            bigInteger = bigInteger.add(new BigInteger("1"));

            System.out.println();
        }

    }

    //服务请求类型ID
    public static List<String> readSrReqstTypeId() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test1.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    //
    public static List<String> readSuprSrvReqstTypeId() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test2.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readSrvReqstTypeNm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test3.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readSrvReqstTypeFullNm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test4.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readLeafNodeFlag() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test5.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readKywdsDesc() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test6.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readVerno() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test7.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readVerNm() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test8.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }

    public static List<String> readWhntwkSrvReqstId() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test9.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        List<String> list = new ArrayList<>();
        while ((s = bufferedReader.readLine()) != null) {
            list.add(s);
        }
        return list;
    }
}
