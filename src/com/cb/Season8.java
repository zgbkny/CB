package com.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cb.service.DCService;
import com.cb.service.ECCService;
import com.cb.service.HubService;
import com.cb.service.PCCService;
import com.cb.utils.CommonUtils;

public class Season8 {
	
	/**
	 * 1:使用ecc_sum排序
	 * 2：筛选出部分节点计算hubval
	 */
	public static void core() {
		List<String> list = CommonUtils.getFilesInPath("H:\\金山网盘\\data\\CB\\inpath");
		ECCService eccs = new ECCService();
		for (String path : list) {
			eccs.calEcc(path, path.replace(".txt", "_ecc.txt"));
			eccs.calSumEcc(path.replace(".txt", "_ecc.txt"), path.replaceAll(".txt", "_ecc_sum.txt"), true);
			action(path.replaceAll(".txt", "_ecc_sum.txt"), 50);
			HubService.divAndStat(path.replaceAll(".txt", "_hubval.txt"), path.replaceAll(".txt", "_hubval_big.txt"), path.replaceAll(".txt", "_hubval_small.txt"), 0.5);
			
		}
	}
	
	public static void action(String inpath, int percent) {
		DCService dcs = new DCService();
		HubService hubs = new HubService();
		String originpath = inpath.replaceAll("_ecc_sum.txt", ".txt");
		hubs.calHub2(inpath, originpath.replaceAll(".txt", "_hub.txt"), percent);
		PCCService.calPcc(originpath, "result36.txt", originpath.replaceAll(".txt", "_pcc.txt"));
		HubService.calHubValue(originpath.replaceAll(".txt", "_hub.txt"), originpath.replaceAll(".txt", "_hubval.txt"), originpath.replaceAll(".txt", "_pcc.txt"));
		System.out.println("stat");
		//CommonUtils.stat(originpath.replaceAll(".txt", "_hubval.txt"), originpath.replaceAll(".txt", "_hub_stat.txt"), -1, 1, 100);
	}
	
	private static void calPccEcc(String file1, String file2, String out, double rate) {
		List<String> hubList = CommonUtils.getInputFile(file1);
		Map<String, Double> ucMap = CommonUtils.getInputFileMap(file2);
		
		List<String> outList = new ArrayList<String>();
		for (String item : hubList) {
			String items[] = item.split("	");
			double value = Double.parseDouble(items[1]);
			double nvalue = 0, alfa = 0.5;
			if (value > rate) {
				nvalue = ucMap.get(items[0]) * alfa + (1 - alfa); 
			} else {
				nvalue = ucMap.get(items[0]) * alfa + (1 - alfa) * 0.5; 
			}
			outList.add(items[0] + "	" + nvalue);
		}
		CommonUtils.outputFile(out, outList);
	}
}
