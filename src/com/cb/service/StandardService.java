package com.cb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.utils.CommonUtils;

public class StandardService {
	public static void process(String infilepath, String outfilepath) {
		List<String> list = CommonUtils.getInputFile(infilepath);
		Map<String, Double> map = new HashMap<String, Double>();
		List<String> outList = new ArrayList<String>();
		double MIN = Double.MAX_VALUE;
		double MAX = Double.MIN_VALUE;
		for (String item : list) {
			String items[] = item.split("	");
			double value = Double.parseDouble(items[1]);
			if (MIN > value) MIN = value;
			if (MAX < value) MAX = value;
		}
		for (String item : list) {
			String items[] = item.split("	");
			double value  = Double.parseDouble(items[1]);
			value = (value - MIN) * 1.0 / (MAX - MIN);
			map.put(items[0], value);
 		}
		
		
		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// int flag = o2.getValue().compareTo(o1.getValue());

				/*
				 * if (o2.getValue() > o1.getValue()) { return 1; } else return
				 * -1;
				 */
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		
		for (Map.Entry<String, Double> item : infoIds) {
			outList.add(item.getKey() + "	" + item.getValue());
		}
		
		CommonUtils.outputFile(outfilepath, outList);
	}
}
