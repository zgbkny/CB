package com.cb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.service.StandardService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

public class RankCombine {

	public static void to1(Map<String, Double> mp) {
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		for (String key : mp.keySet()) {
			if (mp.get(key) > max) {
				max = mp.get(key);
			}
			if (mp.get(key) < min) {
				min = mp.get(key);
			}
		}
		
		for (String key : mp.keySet()) {
			double value = (mp.get(key) - min) / (max - min);
			mp.put(key, value);
		}
	}
	
	public static void process(String filename1, String filename2, String outFilename) {
		Map<String, Integer> f1IndexMap = CommonUtils.getIndexIdMap(filename1, 0);
		Map<String, Integer> f2IndexMap = CommonUtils.getIndexIdMap(filename2, 0);
		Map<String, Double> f1Map = CommonUtils.getKeyValueMap(filename1);
		Map<String, Double> f2Map = CommonUtils.getKeyValueMap(filename2);
		
		//to1(f1Map);
		//to1(f2Map);
		
		List<String> outList = new ArrayList<String>();
		int size = f1Map.size();
		for (String key : f1Map.keySet()) {
			if (!f2Map.containsKey(key)) continue;
			double value = f1Map.get(key) * 1.0 * (1 - f1IndexMap.get(key) * 1.0 / size) +
						   f2Map.get(key) * 1.0 * (f1IndexMap.get(key) * 1.0 / size);
			outList.add(key + "	" + value);
		}
		CommonUtils.outputFile(outFilename, outList);
		///////////
		Map<String, Double> map = new HashMap<String, Double>();
		int k = 0;
		for (String str :outList) {

			String[] strs = str.split("	");
			
			Double d = Double.parseDouble(strs[1]);
			map.put(strs[0], d);
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
		List<String> outList2 = new ArrayList<String>();
		for (Map.Entry<String, Double> item : infoIds) {
			outList2.add(item.getKey() + "	" + item.getValue());
		}
		CommonUtils.outputFile(outFilename, outList2);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///String filename1 = args[0];
		//String filename2 = args[1];
		//String outFilename = args[2];
		
		String filename1 = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_LID.txt";
		String filename2 = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_indegree.txt";
		String outFilename = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_lid_indegree.txt";
		StandardService.process(filename1, filename1);
		StandardService.process(filename2, filename2);
		process(filename1, filename2, outFilename);
		new StatService().statFileByNum(outFilename);
	}

}
