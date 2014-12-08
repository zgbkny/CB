package com.cb.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECC {
	/**
	 * 
	 * @param list :网络中的边
	 * @param dcMap:网络中每个节点的度数
	 * @param graph:整个图的邻接表
	 */
	public List<String> calEcc(List<String> list, Map<String, Integer> dcMap, Map<String, List<String>> graph) {
		List<String> outList = new ArrayList<String>();
		Map<String, Double> map = new HashMap<String, Double>();
		
		for (String str : list) {
			System.out.println(str);
			String [] strs = str.split("	");
			int num1 = get(graph, strs[0], strs[1]);
			int num2 = dcMap.get(strs[0]) < dcMap.get(strs[1]) ? 
						dcMap.get(strs[0]) : dcMap.get(strs[1]);
			if (num2 == 1) {
				num2++;
				//num1++;
			}
			System.out.println(num1 + " " + num2);
			double ret = num1 * 1.0 / (num2 - 1);
			map.put(str, ret);
		}
		List<Map.Entry<String, Double>> infoIds =
		    new ArrayList<Map.Entry<String, Double>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        return (int)((o2.getValue()  - o1.getValue()) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});

		for(Map.Entry<String,Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue()  + "\n");
		}
		return outList;
	}
	
	int get(Map<String, List<String>> map, String item1, String item2) {
		int num = 0;
		List<String> list = map.get(item1);
		for (String item : list) {
			if (item.equals(item2)) continue;
			List<String> subList = map.get(item);
			System.out.println("item: " + item);
			for (String subItem : subList) {
				//System.out.println("subitem: " + subItem);
				if (subItem.equals(item2)) {
					//System.out.println("match: " + subItem);
					num++;
				}
			}
		}
		return num;
	}
}
