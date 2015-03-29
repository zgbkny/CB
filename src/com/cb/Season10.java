package com.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cb.global.Global;
import com.cb.service.StatService;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.HubUtils;
import com.cb.utils.PCCUtils;

public class Season10 {
	// file1: hub file, file2: ucfile, rate
	private static void calPccUc(String file1, String file2, String out, double rate) {
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
	
	public static void core() {
		String file = "H:\\金山网盘\\data\\CB\\inpath\\Static_network_lu.txt";
		String ucFile = "H:\\金山网盘\\data\\CB\\不同数据集的权值\\uc\\result-uc-y.txt";
		// TODO Auto-generated method stub
		DCUtils.calDc(file,
				file.replaceAll(".txt", "_dc.txt"));
		HubUtils.calHub(file.replaceAll(".txt", "_dc.txt"), 
				file.replaceAll(".txt", "_hub.txt"));
		PCCUtils.calPcc(file, file.replaceAll(".txt", "_pcc.txt"), "result36.txt");
		HubUtils.calHubValue(file.replaceAll(".txt", "_hub.txt"), 
				file.replaceAll(".txt", "_avgpcc.txt"), 
				file.replaceAll(".txt", "_pcc.txt"));
		
		CommonUtils.stat(file.replaceAll(".txt", "_avgpcc.txt"), file.replaceAll(".txt", "_hub_stat.txt"), -1, 1, 100);
			List<String> ucMap = CommonUtils.getInputFile(ucFile);
		List<String> ucList = Statistics.sortListAndNormal(ucMap, 1);
		CommonUtils.outputFile(ucFile.replace(".txt", "_normal.txt"), ucList);
		calPccUc(file.replaceAll(".txt", "_avgpcc.txt"), 
				ucFile.replace(".txt", "_normal.txt"), file.replaceAll(".txt", "_pccuc.txt"), 0.5);
	
		
		new StatService().statFileByNum("H:\\金山网盘\\data\\CB\\inpath");

		
	}
}
