package com.cb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;

public class CommonService {
	public void getAndOrderDc(String inpath, String outpath, int index) {
		List<String> list = CommonUtils.getInputFile(inpath);
		List<String> outList = null;
		Map<String, Double> map = new HashMap<String, Double>();
		
		for (String str : list) {
			String []items = str.split(" ");
			Double value = Double.parseDouble(items[index]);
			map.put(items[0], value);
		}
		outList = Statistics.sortMap(map);
		CommonUtils.outputFile(outpath, outList);
	}
}
