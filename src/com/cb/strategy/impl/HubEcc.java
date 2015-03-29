package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cb.service.ECCService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

public class HubEcc extends HubStrategy {
	
	private ECCService eccs = new ECCService();
	
	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		eccs.calEcc(path, path.replace(".txt", "_ecc.txt"));
		eccs.calSumEcc(path.replace(".txt", "_ecc.txt"), path.replaceAll(".txt", "_ecc_sum.txt"), true);
	}
	
	@Override
	public void post(String path) {
		List<String> hubList = CommonUtils.getInputFile(path.replace(".txt", "_hubval.txt"));
		Map<String, Double> eccMap = CommonUtils.getInputFileMap(path.replace(".txt", "_ecc_sum.txt"));
		String outpath = path.replace(".txt", "_hubecc.txt");
		List<String> outList = new ArrayList<String>();
		System.out.println(path.replace(".txt", "_hubval.txt") + hubList.size());
		for (String item : hubList) {
			String []items = item.split("	");
			double value = 0, alfa = 0.5;
			if (items[1].indexOf("NaN") == -1) {
				value = Double.parseDouble(items[1]);
				if (value > 0.5) {
					value = eccMap.get(items[0]) * alfa + (1 - alfa);
				} else {
					value = eccMap.get(items[0]) * alfa + (1 - alfa) * 0.5;
				}
			} else {
				value = -10000;
			}
			outList.add(items[0] + "	" + value);
			
		}
		CommonUtils.outputFile(outpath, outList);
	}
	
	@Override
	public void stat(String path) {
		StatService ss = new StatService();
		System.out.println(path.replace(".txt", "_hubecc.txt"));
		System.out.println(path.replace(".txt", "_ecc_sum.txt"));
		System.out.println(path.replace(".txt", "_hubval.txt"));
		ss.statFileByNum(path.replace(".txt", "_hubecc.txt"));
		ss.statFileByNum(path.replace(".txt", "_ecc_sum.txt"));
		ss.statFileByNum(path.replace(".txt", "_hubval.txt"));
	}
	
}
