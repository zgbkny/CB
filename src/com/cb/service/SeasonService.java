package com.cb.service;

import java.util.List;

import com.cb.global.Global;
import com.cb.utils.CommonUtils;

public class SeasonService {
	public static void season5FilterService(String path, String subc) {
		List<String> pathes = CommonUtils.getFilesInPath(path);
		for (String item : pathes) {
			OneDegreeService ods = new OneDegreeService();
			SubcellularService scs = new SubcellularService();
			System.out.println("================================" + item);
			ods.process(item, item.replaceAll(".txt", "_onedu.txt"));
		//	scs.locationFilter(item.replaceAll(".txt", "_onedu.txt"), subc, item.replaceAll(".txt", "_onedu_subc.txt"));
			
		//	scs.locationFilter(item, subc, item.replaceAll(".txt", "_subc.txt"));
		//	ods.process(item.replaceAll(".txt", "_subc.txt"), item.replaceAll(".txt", "_subc_onedu.txt"));
		}
	}
	public static void season5CalEccPccSum(String path) {
		List<String> pathes = CommonUtils.getFilesInPath(path);
		ECCService eccService = new ECCService();
		PCCService pccService = new PCCService();
		for (String item : pathes) {
			
			eccService.calEcc(item, item.replaceAll(".txt", "_ecc.txt"));
			eccService.calSumEcc(item.replaceAll(".txt", "_ecc.txt"), item.replaceAll(".txt", "_ecc_sum.txt"), false);
			pccService.calPcc(item, "result36.txt", item.replaceAll(".txt", "_pcc.txt"));
			pccService.calSumPcc(item.replaceAll(".txt", "_pcc.txt"), item.replaceAll(".txt", "_pcc_sum.txt"));
		}
	}
	
	
}
