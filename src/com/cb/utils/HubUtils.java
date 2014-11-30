package com.cb.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HubUtils {
	public static void calHub(String filepath1, String filepath2) {
		List<String> list = CommonUtils.getInputFile(filepath1);
		List<String> outList = new ArrayList<String>();
		int count = (int) (list.size() * 1.0 * 25 / 100);
		int minValue = Integer.parseInt(list.get(count).split("	")[1]);
		for (int i = 0; i < list.size(); i++) {
			String [] items = list.get(i).split("	");
			int value = Integer.parseInt(items[1]);
			if (value < minValue) break;
			outList.add(items[0] + "	" + items[1] );
		}
		CommonUtils.outputFile(filepath2, outList);
		System.out.println("cal hub done....");
	}
	/**
	 * 
	 * @param hubfilepath:  node1 234
	 * @param netwithvalue：  node1 node2 0.24523621
	 */
	public static void getHubValue(String hubfilepath, String outfilepath, String netwithvalue) {
		Map<String, Double> mapCount = CommonUtils.getInputFileMap(hubfilepath);
		Map<String, Double> mapValue = CommonUtils.getInputFileMap(hubfilepath);
		List<String> list = EdgeUtils.getEdge(netwithvalue);
		List<String> outList = new ArrayList<String>();
		for (String item : list) {
			String items[] = item.split("	");
			double value = Double.parseDouble(items[2]);
			
			if (mapValue.containsKey(items[0])) {
				mapValue.put(items[0], mapValue.get(items[0]) + value);
				mapCount.put(items[0], mapCount.get(items[0]) + 1.0);
				System.out.println("value:" + value + ";" + "count" + mapCount.get(items[0]));
			} else if (mapValue.containsKey(items[1])) {
				mapValue.put(items[1], mapValue.get(items[1]) + value);
				mapCount.put(items[1], mapCount.get(items[1]) + 1.0);
				System.out.println("value:" + mapValue.get(items[1]) + ";" + "count" + mapCount.get(items[1]));
			}
		}
		List<Map.Entry<String, Double>> mapItems =
			    new ArrayList<Map.Entry<String, Double>>(mapCount.entrySet());
		for (Map.Entry<String,Double> e : mapItems) {
			if (e.getValue() < 0.5) continue;
			Double ret = mapValue.get(e.getKey()) / e.getValue();
			mapValue.put(e.getKey(), ret);
			
			//System.out.println("ret:" + e.getValue());
		}
		mapItems = new ArrayList<Map.Entry<String, Double>>(mapValue.entrySet());
		
		Collections.sort(mapItems, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        return (int)((o1.getValue()  - o2.getValue()) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		
		for(Map.Entry<String,Double> e : mapItems) {
			outList.add(e.getKey() + "	" + e.getValue());
		}
		
		CommonUtils.outputFile(outfilepath, outList);
	}
	
	public static void divAndStat(String filepath, String outpathbig, String outpathsmall, double div) {
		Set<String> essSet = EssUtils.getEssentialSet();
		List<String> list = CommonUtils.getInputFile(filepath);
		List<String> bigList = new ArrayList<String>();
		List<String> smallList = new ArrayList<String>();
		
		int essSmall = 0, small = 0, essBig = 0, big = 0;
		
		for (String str : list) {
			String items[] = str.split("	");
			double value = Double.parseDouble(items[1]);
			
			int flag = 0;
			if (essSet.contains(items[0])) flag = 1;
			else flag = 0;
			
			//System.out.println(value + " " + div);
			if (value > div) {
				bigList.add(str + "	" + flag);
				big++;
				if (flag == 1) {
					essBig++;
				}
			} else {
				smallList.add(str + "	" + flag);
				small++;
				if (flag == 1) {
					essSmall++;
				}
			}
		}
		CommonUtils.outputFile(outpathsmall, smallList);
		CommonUtils.outputFile(outpathbig, bigList);
		System.out.println(div + "-------------------");
		
		System.out.println("small:" + essSmall * 1.0 / small + " ess:" + essSmall + " all:" + small);
		System.out.println("big:" + essBig * 1.0 / big + " ess:" + essBig + " all:" + big);
		System.out.println("-------------------");
	}
	
}
