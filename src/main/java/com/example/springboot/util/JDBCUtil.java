package com.example.springboot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 原始JDBC
 * Created by ZhangPei on 2019/9/19.
 */
public class JDBCUtil {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ngwf_tj_sit", "root", "root");
        connection.setAutoCommit(false);

        String sql = "INSERT INTO `t_sr_problem_proces`(`WRKFM_ID`, `SEQPRC_TMPLT_ID`, `WRKFM_SHOW_SWFTNO`, `SEQPRC_ISTNC_ID`, `SRV_REQST_ID`, `SRV_REQST_TYPE_ID`, `ACPT_STAFF_NUM`, `ACPT_DEPT_ID`, `ACPT_MODE_CD`, `ACPT_CHNL_ID`, `ACPT_TIME`, `CALLING_NUM`, `CALLED_NUM`, `ORIG_CALLED_NUM`, `LANG_ID`, `ACPT_NUM_BELG_CITY_CODE`, `ACPT_NUM_BELG_DISTRT_CODE`, `CUST_MNGR_STAFF_NUM`, `CUST_MNGR_TELNUM`, `ACPT_USER_NAME`, `ACPT_NUM`, `CONC_PRSN_NAME`, `CNTWAY_CD`, `CRED_TYPE_CD`, `CRED_NUM`, `FST_CONC_TELNUM1`, `SECD_CONC_TELNUM2`, `EMAIL_ADDR`, `CONC_ADDR`, `BIZ_CNTT`, `URGNT_EXTENT_CD`, `DPLCT_FLAG`, `EXPCT_FDBK_TIME`, `ACTL_FDBK_TIME`, `ADTN_INFO_FLAG`, `CRT_TIME`, `ARC_TIME`, `CNTMNG_ID`, `FTIME_RPL_TIME`, `ORIG_SRV_REQST_TYPE_ID`, `LAST_ROUTE_LG_ID_SET`, `RETN_BL_FLAG`, `URGFRM_FLAG`, `DIRECT_DSPS_FLAG`, `PSTP_APPLY_TMS_CNT`, `PSTP_TMLEN_CNT`, `URGE_FLAG`, `WRKFM_STS_CD`, `CUST_MOOD_TYPE_CD`, `CMPLN_UPGD_FLAG`, `CUST_TYPE_CD`, `CUST_RCNTN_FLAG`, `NO_RCNTN_RSN_CD`, `CO_RESP_FLAG`, `RESP_RSN_LVL_CD`, `CURR_WORK_ITEM_OWNER`, `RESP_RSN_DESC`, `PHONE_TYPE`, `LAST_MODF_TIME`, `LAST_CUST_SATIS_DGR_ID`, `CURR_WORK_ITEM_GROUP`, `N_SATIS_RSN_DESC`, `DSPS_COMPLTE_TIME`, `EXPR_TIME`, `CALL_ID`, `VALID_FLAG`, `QULTEAM_FLAG`, `SEND_QULTEAM_FLAG`, `QULTEAM_SWFTNO`, `RPTLD_GROUP_FLAG`, `AFORM_TMS_CNT`, `UPGD_CMPLNTS_FLAG`, `DPLCT_CMPLNTS_TMS_CNT`, `DIFCLT_CMPLNTS_FLAG`, `RESP_DEPT_ID`, `ARC_MODE_CD`, `LOC_SP_BIZ_CD`, `CUST_STARGRD_CD`, `IS_IN_CUSTOMER`, `ACPT_CITY_CODE`, `CUST_BRAND_CODE`, `OP_STS_CD`, `FDBK_MODE_CD`, `RETN_MNY_FLAG`, `CUST_STS_CD`, `WEB_TYPE_CD`, `MAIN_WRKFM_SWFTNO`, `WRKFM_NM`, `WRKFM_TYPE_CD`, `NEED_FDBK_FLAG`, `BLST_FLAG`, `LOCK_FLAG`, `NEED_REVST_FLAG`, `RSLV_EXTENT_CD`, `AT_LINE_DSPS_FLAG`, `REVST_REMEDY_FLAG`, `DSPS_TMOUT_TLMT_TIME`, `CUST_PROV_CODE`, `UPGD_CMPLNTS_DESC`, `DPLCT_CMPLNTS_FLAG`, `CUST_LVL_CD`, `TENANT_ID`, `PROV_CODE`, `IS_4G_USER`, `DIFCLT_CMPLNTS_TYPE_CD`, `MAIN_BIZ_TYPE_ID`, `REVST_WRKFM_SWFTNO`, `REVST_WRKFM_SHOW_SWFTNO`, `SATIS_DGR_REVST_MODE_CD`, `ARTF_REVST_SATIS_DGR_ID`, `DEAL_REMEDY_FLAG`, `DEAL_WRKFM_SWFTNO`, `DEAL_WRKFM_SHOW_SWFTNO`, `AFORM_FLAG`, `ACPT_NUM_BELG_PROV_CODE`, `RLT_SYS_TYPE`, `TRMN_MODEL_NM`, `SPECL_DSPS_TYPE_CD`, `SRV_REQST_CUST_SRV_UNFY_CODE`, `CNTMNG_CUST_SRV_UNFY_CODE`, `DSPS_STAFF_OP_LIST`, `ARC_ADTNL_CNTT`, `BAT_CON_CUSTOMER`, `FTIME_SMS_RPY_FLAG`, `LTSZ_WRKFM_CONC_CUST_TIME`, `HAVE_RCDNG_FLAG`, `D_CENTER_WRKFM_ID`, `WHTLST_FLAG`, `WRKFM_SRC_MARK`, `RMDR_STAFF_NUM`, `HIS_WORK_ORDER_NUM`, `AUTO_AFORM_TGT_WORK_GRP_ID`) VALUES (?, 'ngwf_yn', '20180717101019X419869229', '1710102244273630D20T01H180702', 2000029389, '004002001001', 'YN0001', '00030009', '01', '01', '2018-07-17 10:11:26', '111', '10086', NULL, '1', '0871', NULL, NULL, NULL, '这是业务处理内容啊大简述浮动；gas大股东李撒酒疯大撒把附件但是拉法基东方闪电反倒是家乐福束带结发卡洛斯法拉第进水立方', '111', '?*', '0', NULL, NULL, '15987153600', NULL, NULL, '??', '受理内容????', '03', 0, '2018-07-17 18:11:28', NULL, 0, '2018-07-17 10:11:26', NULL, NULL, NULL, NULL, '2001027420,2001027421', 0, 0, 0, NULL, NULL, 1, '5', '0', 0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-07-17 10:11:26', NULL, '2017071100003', NULL, NULL, '2018-07-25 00:00:00', NULL, 1, 0, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, '09', 0, '0871', NULL, NULL, NULL, 0, NULL, '1', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '100000', '00030016', '1', NULL, 'TERMINAL', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '871', NULL, 'NHT', 1, NULL, NULL, 'YN0001-1,YN0001-6,YN0001-6', NULL, NULL, 1, NULL, NULL, 18071710112717602, 1, NULL, 'YN0001', 7, NULL);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();

        /*for (int i = 1; i < 100000; i++) {    //一次性插10万
            preparedStatement.setString(1, String.valueOf(i + 1000));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();*/

        for (int i = 1; i < 100000; i++) {      //分批次插10万
            preparedStatement.setString(1, String.valueOf(i + 502000));
            preparedStatement.addBatch();
            if (i % 100 == 0) {
                preparedStatement.executeBatch();
                connection.commit();
                preparedStatement.clearBatch();
            }
        }

        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.clearBatch();


        long end = System.currentTimeMillis();
        
        preparedStatement.close();
        connection.close();
        
//        System.out.println("批处理执行10万条插入sql共计消耗:" + (end - start) / 1000 + "秒");
        System.out.println("线程1批处理分批执行10万条插入sql共计消耗:" + (end - start) / 1000 + "秒");
    }
}
