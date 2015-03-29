package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cb.service.ECCService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

public class HubUc extends HubStrategy {
	
	String ucFile = "H:\\金山网盘\\data\\CB\\inpath\\temp2\\result-uc-y_normal.txt";
	
	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		
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

		ss.statFileByNum(path.replace(".txt", "_hubuc.txt"));
		ss.statFileByNum(ucFile);
		ss.statFileByNum(path.replace(".txt", "_hubval.txt"));
	}
}
