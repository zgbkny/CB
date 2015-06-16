package com.cb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.strategy.impl.CentralityStrategy;
import com.cb.strategy.impl.DcStrategy;
import com.cb.strategy.impl.DynamicNetworkStrategy;
import com.cb.strategy.impl.EccSumStrategy;
import com.cb.strategy.impl.EssSetStatStrategy;
import com.cb.strategy.impl.GenStatStrategy;
import com.cb.strategy.impl.GeneExpStrategy;
import com.cb.strategy.impl.HubEcc;
import com.cb.strategy.impl.HubUc;
import com.cb.strategy.impl.StatStrategy;
import com.cb.utils.CBUtils;
import com.cb.utils.CommonUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.EssUtils;
import com.cb.utils.PCCUtils;

public class cb {
	public static void find() {
		Map<String, Double> map1 = CommonUtils.getInputFileMapKey("/home/ww/cache/cb/20150614-4/Nucleus_PPI_dc.txt");
		Map<String, Double> map2 = CommonUtils.getInputFileMapKey("/home/ww/cache/cb/20150614-4/TS-PIN_bc.txt");
		int size = map1.size();
		Set<String> set = map2.keySet();
		Set<String> essSet = EssUtils.getEssentialSet();
		Object []os = set.toArray();
		for (int i = 0; i < set.size(); i++) {
			map2.put((String)os[i], map2.get((String)os[i]) * size / map2.size());
		}
		String small = null, big = null; 
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (String item : map1.keySet()) {
			if (map2.containsKey(item) && essSet.contains(item)) {
				int temp = (int) (map2.get(item) - map1.get(item));
				
				if (max < temp) {
					max = temp;
					big = item;
				} 
				if (min > temp) {
					min = temp;
					small = item;
				}
			}
			
		}
		
		System.out.println("big:" + big);
		System.out.println("small:" + small);
	}
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	EssSetStatStrategy esst = new EssSetStatStrategy();
    	esst.setSize(100);
    	esst.action("/home/ww/cache/cb/20150615/计算S1，S2，S3");
    	
    	//GenStatStrategy gss = new GenStatStrategy();
    	//gss.action("/home/ww/cache/cb/20150614-3/画折刀图需要的文件");
    	/*EccSumStrategy ess = new EccSumStrategy(
                "/home/ww/cache/cb/20150613-1/Nucleus_PPI_tc_pin_ecc_sum.txt",
                EccSumStrategy.FILE_PATH);
        ess.action("/home/ww/cache/cb/20150613-1/Nucleus_PPI_tc_pin.txt");*/
    	
    	
    	//CentralityStrategy cs = new CentralityStrategy();
    	//cs.action("/home/ww/cache/cb/20150614-4");
    	
    	
    	
    	//Strategy cs = new StatStrategy();
        //cs.action("/home/ww/cache/cb/20150614-2/所有网络的NC输出");
    	
    	/////////////////////////////////////////////////////////////////////////////////////////
        // TODO Auto-generated method stub
        // Strategy s = new HubEcc();
        // Strategy s = new HubUc();
    	//GeneExpStrategy ges = new GeneExpStrategy("/home/ww/cache/cb/20150613-1/out.txt");
    	//ges.action("/home/ww/cache/cb/20150613-1/Nucleus_PPI.txt");
        
    	
    	
    	
        /*Strategy cs = new StatStrategy();
        cs.action("/home/ww/cache/cb/PeC结果");*/
    	
    	/*List<String> list = CommonUtils.getFilesInPath("/home/ww/cache/cb/20150607/data/");
    	for (String str : list) {
    		EccSumStrategy ess = new EccSumStrategy(
        			str.replaceAll(".txt", "_eccsum.txt"),
                    EccSumStrategy.FILE_PATH);
        	ess.action(str);
    	}*/
    	//
    	/*
    	EccSumStrategy ess = new EccSumStrategy(
                "/home/ww/cache/cb/20150613/NF-PIN（肖强华）_ecc_sum.txt",
                EccSumStrategy.FILE_PATH);
        ess.action("/home/ww/cache/cb/20150613/NF-PIN（肖强华）.txt");
        
        EccSumStrategy ess2 = new EccSumStrategy(
                "/home/ww/cache/cb/20150613/TS-PIN（陈骁培）_ecc_sum.txt",
                EccSumStrategy.FILE_PATH);
        ess2.action("/home/ww/cache/cb/20150613/TS-PIN（陈骁培）.txt");
    	*/
    	/*
    	DynamicNetworkStrategy dns = new DynamicNetworkStrategy("/home/ww/cache/cb/20150609", true);
    	dns.action("/home/ww/cache/cb/20150609/pxq-subnet");*/
        
    	
        /*Strategy s = new DcStrategy(
                "/home/ww/cache/cb/pxq-subnet_out_subdir_dc.txt",
                DcStrategy.SUBDIR_IN_PATH);
        s.action("/home/ww/cache/cb/pxq-subnet");*/

        
        /*Strategy s3 = new EccSumStrategy(
                "/home/ww/cache/cb/pxq-subnet_out_subdir_ecc.txt",
                DcStrategy.SUBDIR_IN_PATH);
        s3.action("/home/ww/cache/cb/pxq-subnet");*/
    	/*
    	DcStrategy s1 = new DcStrategy(
                "/home/ww/cache/cb/20150609/pxq-subnet_out_merge_subdir_dc.txt",
                DcStrategy.MERGE_SUBDIR_IN_PATH);
        s1.setMergeFileName("/home/ww/cache/cb/20150609/pxq-subnet_out_merge_subdir.txt");
        s1.action("/home/ww/cache/cb/20150609/pxq-subnet");

        DcStrategy s2 = new DcStrategy(
                "/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files_dc.txt",
                DcStrategy.MERGE_FILES_IN_PATH);
        s2.setMergeFileName("/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files.txt");
        s2.action("/home/ww/cache/cb/20150609/pxq-subnet");*/

    	/*
        EccSumStrategy s4 = new EccSumStrategy(
                "/home/ww/cache/cb/20150609/pxq-subnet_out_merge_subdir_ecc.txt",
                DcStrategy.MERGE_SUBDIR_IN_PATH);
        s4.setMergeFileName("/home/ww/cache/cb/20150609/pxq-subnet_out_merge_subdir.txt");
        s4.action("/home/ww/cache/cb/20150609/pxq-subnet");

        EccSumStrategy s5 = new EccSumStrategy(
                "/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files_ecc.txt",
                DcStrategy.MERGE_FILES_IN_PATH);
        s5.setMergeFileName("/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files.txt");
        s5.action("/home/ww/cache/cb/20150609/pxq-subnet");*/
    }

}
