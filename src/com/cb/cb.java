package com.cb;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.cb.strategy.impl.DcStrategy;
import com.cb.strategy.impl.DynamicNetworkStrategy;
import com.cb.strategy.impl.PaperFinalStrategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

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
	
	public static void calEssCount() {
		List<String> list = CommonUtils.getInputFileJustKey("/home/ww/cache/S-PIN（静态）.txt");
		int i = 0, count = 0;
		Set<String> essSet = EssUtils.getEssentialSet();
		for (String item : list) {
			if (essSet.contains(item)) {
				count++;
			}
			i++;
			if (i == 100) {
				break;
			}
		}
		System.out.println(i);
		System.out.println(count);
	}
	
	public static void genTable() {
		String file_s = "/home/ww/cache/cb/20150718/S-PIN（静态）_dc.txt";
		String file_nf = "/home/ww/cache/cb/20150718/NF-PIN（肖强华）_dc.txt";
		String file_ts = "/home/ww/cache/cb/20150718/TS-PIN（陈骁培）_dc.txt";
		List<String> list1 = CommonUtils.getInputFileJustKey(file_s);
		Map<String, Integer> map_s = CommonUtils.getInputFileMapByInteger(file_s);
		Map<String, Integer> map_nf = CommonUtils.getInputFileMapByInteger(file_nf);
		Map<String, Integer> map_ts = CommonUtils.getInputFileMapByInteger(file_ts);
		Set<String> essSet = EssUtils.getEssentialSet();
		
		List<String> list = new ArrayList<String>();
		
		for (String item : list1) {
			list.add(item + "	" + map_s.get(item) + "	" + map_nf.get(item) + "	" + map_ts.get(item) + "	" + essSet.contains(item));
		}
		CommonUtils.outputFile("/home/ww/cache/cb/20150718/table.txt", list);
		
	}
	
	public static void ttt() {
		List<String> list = CommonUtils.getFilesInSubPath("/home/ww/cache/cb/20150725/subnet");
		List<String> l = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		int i = 0;
		for (String filepath : list) {
			
			List<String> tmpList = CommonUtils.getInputFile(filepath);
			i += tmpList.size();
			for (String item : tmpList) {
				if (!set.contains(item)) {
					l.add(item);
					set.add(item);
				}
			}
		}
		System.out.println(i);
		System.out.println("all" + l.size());
	}
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	/* 第一个参数是动态网路目录
    	 * 第二个参数是细胞器文件
    	 */
    	
    	String dirPath = "/home/ww/cache/cb/20150725/12个时刻子网";
    	String filePath = "/home/ww/cache/cb/20150725/SubcellularLocationOfYeast.txt";
    	//PaperFinalStrategy pfs = new PaperFinalStrategy(filePath);
    	//pfs.action(dirPath);
    	//ttt();
    	DynamicNetworkStrategy dns = new DynamicNetworkStrategy("/home/ww/cache/cb/20150725/SubcellularLocationOfYeast.txt", false);
    	dns.action("/home/ww/cache/cb/20150725/12个时刻子网");
    	
    	/*
    	DcStrategy s2 = new DcStrategy(
                "/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files_dc.txt",
                DcStrategy.FILES_IN_PATH);
        //s2.setMergeFileName("/home/ww/cache/cb/20150609/pxq-subnet_out_merge_files.txt");
        s2.action("/home/ww/cache/cb/20150718");*/
    	
    	
    	//DCTableStrategy esst = new DCTableStrategy();
    	//esst.action("/home/ww/cache/cb/20150618/DC");
    	
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
