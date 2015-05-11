package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.service.ECCService;
import com.cb.service.StatService;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class HubUc extends HubStrategy {
	
	String originUcFile = "H:\\金山网盘\\data\\CB\\inpath\\temp2\\result-0.8-y.txt";
	
	String ucFile = "H:\\金山网盘\\data\\CB\\inpath\\temp2\\result-0.8-y-normal.txt";
	
	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		List<String> ucList = CommonUtils.getInputFile(originUcFile);
		System.out.println(ucList.size());
		String items[] = ucList.get(0).split("	");
		System.out.println(items.length);
		List<String> ucListNormal = Statistics.sortListAndNormal(ucList, 1);
		CommonUtils.outputFile(ucFile, ucListNormal);
	}
	
	@Override
	public void post(String path) {
		List<String> hubList = CommonUtils.getInputFile(path.replace(".txt", "_hubval.txt"));
		Map<String, Double> ucMap = CommonUtils.getInputFileMap(ucFile);
		String outpath = path.replace(".txt", "_hubuc.txt");
		List<String> outList = new ArrayList<String>();
		System.out.println(path.replace(".txt", "_hubval.txt") + hubList.size());
		for (String item : hubList) {
			String []items = item.split("	");
			double value = 0, alfa = 0.5;
			if (items[1].indexOf("NaN") == -1) {
				value = Double.parseDouble(items[1]);
				if (value > 0.5) {
					value = ucMap.get(items[0]) * alfa + (1 - alfa);
				} else {
					value = ucMap.get(items[0]) * alfa + (1 - alfa) * 0.5;
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

	//	ss.statFileByNum(path.replace(".txt", "_hubuc.txt"));
	//	ss.statFileByNum(ucFile);
		ss.statFileByNum(originUcFile);
	//	ss.statFileByNum(path.replace(".txt", "_hubval.txt"));
	}
	
	void statFileByNum(String path) {
		List<String> data = CommonUtils.getInputFile(path);
		Set<String> essSet = EssUtils.getEssentialSet();
		int essNum = 0, essNumInFile = 0;
		for (String str : data) {
			String []strs = str.split("	");
			if (essSet.contains(strs[0])) {
				essNum++;
			}
			int value = Integer.parseInt(strs[2]);
			if (value == 1) {
				essNumInFile++;
			}
			
			if (!essSet.contains(strs[0]) && value == 1) {
				System.out.println(str + " err");
			}
			if (essSet.contains(strs[0]) && value == 0) {
				System.out.println(str + " err");
			}
		}
		System.out.println(essNum + " " + essNumInFile);
	}
}
