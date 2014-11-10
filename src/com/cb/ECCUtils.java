package com.cb;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECCUtils {
	
	private List<String> getEdge(String filepath) {
		List<String> list = new ArrayList<String>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(buf);
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private Map<String, Integer> getDcMap(String filepath) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			FileReader ins = new FileReader("dc_" + filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				map.put(strs[0], Integer.parseInt(strs[1]));
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String, List<String>> getGraph(String filepath) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				if (map.get(strs[0]) == null) {
					List<String> list = new ArrayList<String>();
					list.add(strs[1]);
					map.put(strs[0], list);
				} else {
					List<String> list = map.get(strs[0]);
					list.add(strs[1]);
					map.put(strs[0], list);
				}
				if (map.get(strs[1]) == null) {
					List<String> list = new ArrayList<String>();
					list.add(strs[0]);
					map.put(strs[1], list);
				} else {
					List<String> list = map.get(strs[1]);
					list.add(strs[0]);
					map.put(strs[1], list);
				}
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
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
	
	public void calEcc(String filepath) {
		List<String> list = getEdge(filepath);
		Map<String, Integer> dcMap = getDcMap(filepath);
		Map<String, List<String>> graph = getGraph(filepath);
		
		File outFile = new File("ecc_" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		
		
	
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
			out.print(e.getKey() + "	" + e.getValue()  + "\n");
		}

		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	                      
}
