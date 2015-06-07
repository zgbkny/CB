package com.cb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.strategy.impl.CentralityStrategy;
import com.cb.strategy.impl.DcStrategy;
import com.cb.strategy.impl.DynamicNetworkStrategy;
import com.cb.strategy.impl.EccSumStrategy;
import com.cb.strategy.impl.HubEcc;
import com.cb.strategy.impl.HubUc;
import com.cb.strategy.impl.StatStrategy;
import com.cb.utils.CBUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.PCCUtils;

public class cb {

    public static void calEcc() {
        DCUtils dcUtils = new DCUtils();
        // dcUtils.calDc("Y2k.txt", "Essential.txt");

        ECCUtils eccUtils = new ECCUtils();
        eccUtils.calEcc("DIP20101010.txt");
    }

    public static void calGo() {
        GOUtils goUtils = new GOUtils();
        List<String> list = new ArrayList<String>();
        list.add("GO_Function_annotation.txt");
        list.add("GO_Process_annotation.txt");
        list.add("GO_Component_annotation.txt");
        // goUtils.calProbability(list);
        // goUtils.calSim("go_Probability.txt", list);
        // goUtils.calFS("DIP20101010.txt", "go_sim.txt", list);
        goUtils.calCvw("go_FS_DIP20101010.txt");
        goUtils.normalize("cvw_go_FS_DIP20101010.txt");
    }

    public static void calPcc() {
        PCCUtils pccUtils = new PCCUtils();
        pccUtils.calPcc("SC_net.txt", null, "result36.txt");
    }

    public static void cl() {
        CBUtils cb = new CBUtils();
        cb.classify("go_FSs.txt", "Essential.txt");
        cb.statistic("c3_not_go_FSs.txt", 10);
        cb.statistic("c2_notall_go_FSs.txt", 10);
        cb.statistic("c1_all_go_FSs.txt", 10);
    }

    /**
     * target: 计算数据集的ecc、 bc、pc等， 然后不用根据一个边包含几个关键蛋白分三类，直接根据计算的结果进行统计
     */
    public static void the3() {
        Season3 s3 = new Season3();

    }

    public static void the4() {
        Season4 season = new Season4();
        season.core();
    }

    public static void the5() {
        Season5 season = new Season5();
        season.core2();
    }

    public static void the6() {
        Season6 season = new Season6();
        season.core();
    }

    public static void the7() {
        Season7 season = new Season7();
        // season.checkHub();
        /*
         * double i = 0.1, j = 0.1; for ( ; i < 0.99; i += 0.1) {
         * 
         * for (j = 0.1 ; j <= 0.5; j += 0.1 ) {
         * season.calNewValueByPartyDateHub(0.5, i, j); } }
         */

        new StatService().statFileByPercent("H:\\金山网盘\\data\\CB\\inpath");
    }

    public static void the8() {
        Season8.core();
    }

    public static void the9() {
        Season9.pre();
        // Season9.eccSum();
        Season9.core();
        Season9.stat();
    }

    public static void the10() {
        Season10.core();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Strategy s = new HubEcc();
        // Strategy s = new HubUc();
        
        /*Strategy cs = new StatStrategy();
        cs.action("/home/ww/cache/cb/PeC结果");*/
    	
    	/*DynamicNetworkStrategy dns = new DynamicNetworkStrategy("/home/ww/cache/20150604实验/20150604实验/yeastsubcellular.txt");
    	dns.action("/home/ww/cache/20150604实验/20150604实验/xqh_subnet");*/
        
        Strategy s = new DcStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_subdir_dc.txt",
                DcStrategy.SUBDIR_IN_PATH);
        s.action("/home/ww/cache/生物/pxq_snbnet");

        DcStrategy s1 = new DcStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_merge_subdir_dc.txt",
                DcStrategy.MERGE_SUBDIR_IN_PATH);
        s1.setMergeFileName("/home/ww/cache/生物/pxq_snbnet_out_merge_subdir.txt");
        s1.action("/home/ww/cache/生物/pxq_snbnet");

        DcStrategy s2 = new DcStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_merge_files_dc.txt",
                DcStrategy.MERGE_FILES_IN_PATH);
        s2.setMergeFileName("/home/ww/cache/生物/pxq_snbnet_out_merge_files.txt");
        s2.action("/home/ww/cache/生物/pxq_snbnet");

        Strategy s3 = new EccSumStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_subdir_ecc.txt",
                DcStrategy.SUBDIR_IN_PATH);
        s3.action("/home/ww/cache/生物/pxq_snbnet");

        EccSumStrategy s4 = new EccSumStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_merge_subdir_ecc.txt",
                DcStrategy.MERGE_SUBDIR_IN_PATH);
        s4.setMergeFileName("/home/ww/cache/生物/pxq_snbnet_out_merge_subdir.txt");
        s4.action("/home/ww/cache/生物/pxq_snbnet");

        EccSumStrategy s5 = new EccSumStrategy(
                "/home/ww/cache/生物/pxq_snbnet_out_merge_files_ecc.txt",
                DcStrategy.MERGE_FILES_IN_PATH);
        s5.setMergeFileName("/home/ww/cache/生物/pxq_snbnet_out_merge_files.txt");
        s5.action("/home/ww/cache/生物/pxq_snbnet");
    }

}
