package com.cb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.utils.CommonUtils;
import com.cb.utils.EdgeUtils;
import com.cb.utils.EssUtils;

public class HubService {
	public void calHubs(String inpath, int percent) {
		List<String> list = CommonUtils.getFilesInPath(inpath); 
		for (String path : list) {
			calHub(path, path.replaceAll("_dc.txt", "_hub.txt"), percent);
		}
	}
	
	public void calHub(String inpath, String outpath, int percent) {
		List<String> list = CommonUtils.getInputFile(inpath);
		List<String> outList = new ArrayList<String>();
		int count = (int) (list.size() * 1.0 * percent / 100);
		int minValue = 0;//Integer.parseInt(list.get(count).split("	")[1]);
		long sum = 0;
		for (String item : list) {
			String[] strs = item.split("	");
			int val = Integer.parseInt(strs[1]);
			sum += val;
		}
		minValue = (int) (sum * 1.0/ list.size());
		
		for (int i = 0; i < list.size(); i++) {
			String [] items = list.get(i).split("	");
			int value = Integer.parseInt(items[1]);
			if (value < minValue) break;
			outList.add(items[0] + "	" + items[1] );
		}
		CommonUtils.outputFile(outpath, outList);
		System.out.println("cal hub done....");
	}
	
	/**
	 * 
	 * @param hubfilepath:  node1 234
	 * @param netwithvalue：  node1 node2 0.24523621
	 */
	public static void calHubValue(String hubfilepath, String outfilepath, String netwithvalue) {
		Map<String, Double> mapCount = CommonUtils.getInputFileMapWithoutValue(hubfilepath);
		Map<String, Double> mapValue = CommonUtils.getInputFileMapWithoutValue(hubfilepath);
		List<String> list = EdgeUtils.getEdge(netwithvalue);
		List<String> outList = new ArrayList<String>();
		for (String item : list) {
			String items[] = item.split("	");
			double value = Double.parseDouble(items[2]);
			
			if (mapValue.containsKey(items[0])) {
				mapValue.put(items[0], mapValue.get(items[0]) + value);
				mapCount.put(items[0], mapCount.get(items[0]) + 1.0);
				System.out.println("value:" + value + ";" + "count" + mapCount.get(items[0]));
			} 
			if (mapValue.containsKey(items[1])) {
				mapValue.put(items[1], mapValue.get(items[1]) + value);
				mapCount.put(items[1], mapCount.get(items[1]) + 1.0);
				System.out.println("value:" + mapValue.get(items[1]) + ";" + "count" + mapCount.get(items[1]));
			}
		}
		List<Map.Entry<String, Double>> mapItems =
			    new ArrayList<Map.Entry<String, Double>>(mapCount.entrySet());
		for (Map.Entry<String,Double> e : mapItems) {
			//if (e.getValue() < 0.5) continue;
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
		List<String> outList = new ArrayList<String>();
		
		//Map<String, Double> map = CommonUtils.getInputFileMap("protein_allorthology.txt", 1, 3);
		
		int essSmall = 0, small = 0, essBig = 0, big = 0;
		double smallValue = 0.0, bigValue = 0.0;
		
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
				outList.add(str + "	"  + 1 + "	" + value);
				//bigValue += map.get(items[0]);
				if (flag == 1) {
					essBig++;
				}
			} else {
				smallList.add(str + "	" + flag);
				small++;
				outList.add(str + "	"  + 0 + "	" + value);
				//smallValue += map.get(items[0]);
				if (flag == 1) {
					essSmall++;
				}
			}
		}
		CommonUtils.outputFile(outpathsmall, smallList);
		CommonUtils.outputFile(outpathbig, bigList);
		CommonUtils.outputFile(filepath, outList);
		System.out.println(div + "-------------------" + filepath);
		System.out.println("small:" + smallValue / small);
		System.out.println("big:" + bigValue / big);
		//System.out.println("small:" + essSmall * 1.0 / small + " ess:" + essSmall + " all:" + small);
		//System.out.println("big:" + essBig * 1.0 / big + " ess:" + essBig + " all:" + big);
		//System.out.println("-------------------");
	}
}
