package com.cb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DPCC {
	private String filePath;
	private String dataPath;
	private String outFilePath;
	
	private FileInputStream fip;
	private FileInputStream dip;
	private FileOutputStream fop;
	
	public DPCC(String filePath, String dataPath, String outFilePath) {
		this.filePath = filePath;
		this.dataPath = dataPath;
		this.outFilePath = outFilePath;
	}
	
	public boolean init() {
		return false;
	}
	
	private Map<String, String> initItem() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			FileReader ins = new FileReader(filePath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				//System.out.println(strs[0] + ";" + strs[1] + ";" + strs[2]);
				//System.out.println(d);
				map.put(strs[0], strs[1] + "," + strs[2]);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<Double, String> initData() {
		Map<Double, String> map = new HashMap<Double, String>();
		try {
			FileReader ins = new FileReader(dataPath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				//System.out.println(strs[0] + ";" + strs[1] + ";" + strs[2]);
				Double d = Double.parseDouble(strs[2]);
				System.out.println(strs[0] + "," + strs[1]);
				map.put(d, strs[0] + "," + strs[1]);
				//map.put(Math.abs(d), strs[0] + "," + strs[1]);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public void getDPCC() {
		File outFile = new File("dpcc.txt");
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		Map<String, String> itemMap = initItem();
		Map<Double, String> map = initData();
		Set<Double> newSet = new TreeSet(map.keySet());
		Object[] items = newSet.toArray();
		for (int i = items.length - 1; i > -1; i--) {
			//System.out.println(map.get(items[i]));
			String[] strs = map.get(items[i]).split(",");
			out.print(strs[0] + "	" + itemMap.get(strs[0]).split(",")[0] 
			        + "	" + itemMap.get(strs[0]).split(",")[1]
					+ "	" + strs[1] + "	" + itemMap.get(strs[1]).split(",")[0] 
					+ "	" + itemMap.get(strs[1]).split(",")[1] 
					+ "	" + items[i].toString() + "\n");
		}
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<String> readDpcc() {
		List<String> list = new ArrayList<String>();
		try {
			
			FileReader ins = new FileReader("dpcc.txt");
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				//System.out.println(buf);
				list.add(buf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private double calFisher(int N, double r) {
		double ret = (Math.log(1 + r)/Math.log(1 - r))
						* Math.sqrt(N - 3) * 0.5;
		System.out.println("fisher:" + ret);
		return ret;
	}
	public void getFisher() {
		List<String> list = readDpcc();
 		List<String> result = new ArrayList<String>();
		File outFile = new File("dpccFisher.txt");
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double sum = 0;
		int count = 0;
		PrintStream out = new PrintStream(fop);
		for (String item : list) {
			count++;
			//System.out.println(map.get(items[i]));
			String[] strs = item.split("	");
			double ret = calFisher(36, Double.parseDouble(strs[6]));
			sum += ret;
			out.print(item + "	" + ret + "\n");
			result.add(item + "	" + ret);
		}
		System.out.println("均值：" + sum/count);
		double up = sum/count + 1000;
		double down = sum/count - 1.64;
		System.out.println("上界：" + up);
		System.out.println("下界：" + down);
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		outFile = new File("fisherFilter.txt");
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		out = new PrintStream(fop);
		for (String item : result) {
			String[] strs = item.split("	");
			double data = Double.parseDouble(strs[7]);
			if (data > down && data < up) {
				out.print(strs[0] + "	" + strs[3] + "\n");
			}
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void statistics() {
		List<String> listOrigin = new ArrayList<String>();
		List<String> listPcc = new ArrayList<String>();
		try {
			FileReader ins = new FileReader("SC_net_result.txt");
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				listOrigin.add(buf);
			}
			readBuf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileReader ins = new FileReader("go_SC_net_result.txt");
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				listPcc.add(buf);
			}
			readBuf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 1; i < 6; i++) {
			int count1 = 0, count2 = 0;
			for (int j = 0; j < i * 5 * listPcc.size() / 100; j++) {
				String data = listOrigin.get(j).split("	")[2];
				if (Integer.parseInt(data) == 1) {
					count1++;
				}
			}
			for (int j = 0; j < i * 5 * listPcc.size() / 100; j++) {
				String data = listPcc.get(j).split("	")[2];
				if (Integer.parseInt(data) == 1) {
					count2++;
				}
			}
			//System.out.println(count1 * 1.0 / (i * 5 * listPcc.size() / 100) + " "  + count2 * 1.0 / (i * 5 * listPcc.size() / 100));
			System.out.println(count1 + " " + count2);
		}
		
	}
	private Map<String, List<String>> getGoMap(String path) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		try {
			
			FileReader ins = new FileReader(path);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				List<String> list1 = new ArrayList<String>();
				List<String> list2 = new ArrayList<String>();
				String [] strs = buf.split("	");
				for (int i = 1; i < strs.length; i++) {
					list1.add(strs[i]);
				}
				map.put(strs[0], list1);
				for (int i = 2; i < strs.length; i++) {
					list2.add(strs[i]);
				}
				map.put(strs[1], list2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	private boolean matchItem(String item1, String item2, Map<String, List<String>> map, Set<String> set) {
		List<String> list = map.get(item2);
		if (item1 == null || item2 == null) return false;
		if (set.contains(item2)) return false;
		if (item1.equals(item2)) return true;
		set.add(item2);
		if (list != null) {
			//System.out.println("  ");
			for (String item : list) {
				//System.out.println(item1+ item);
				if (matchItem(item1, item, map, set)) return true;
			}
		}
		return false;
	}
	
	private boolean match(String item1, String item2, Map<String, List<String>> map, Set<String> set) {
		List<String> list = map.get(item1);
		if (item1 == null || item2 == null) return false;
		Set<String> item2Set = new HashSet<String>();
		if (matchItem(item1, item2, map, item2Set)) return true;
		set.add(item1);
		if (list != null) {
			for (String item : list) {
				if (match(item, item2, map, set)) return true;
			}
		}
		return false;
	}
	
	public void goFilter() {
		List<String> list = readDpcc();
		Map<String, List<String>> map1 = getGoMap("GO_Component_annotation.txt");
		//Map<String, List<String>> map2 = getGoMap("GO_Function_annotation.txt");
		//Map<String, List<String>> map3 = getGoMap("GO_Process_annotation.txt");
		File outFile = new File("go_filter.txt");
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		double sum = 0;
		int count = 0;
		PrintStream out = new PrintStream(fop);
		Set<String> set1 = new HashSet<String>();
		//Set<String> set2 = new HashSet<String>();
		//Set<String> set3 = new HashSet<String>();
		for (String item : list) {
			String strs[] = item.split("	");
			set1.clear();
			//set2.clear();
			//set3.clear();
			if (match(strs[0], strs[3], map1, set1)
					//|| match(strs[0], strs[3], map2, set2)
					//|| match(strs[0], strs[3], map3, set3)
					) {
				out.print(strs[0] + "	" + strs[3] + "\n");
			}
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
