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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DCUtils {
	private String filePath;
	private String dataPath;
	private String outFilePath;
	private FileInputStream fip;
	private FileInputStream dip;
	private FileOutputStream fop;
	
	
	public DCUtils (String filePath, String dataPath, String outFilePath) {
		this.filePath = filePath;
		this.outFilePath = outFilePath;
		this.dataPath = dataPath;
	}
	public boolean init() {
		return true;
	}
	private Map<String, Integer> getDc() {
		Map<String, Integer> map= new HashMap<String, Integer>();
		
		try {
			FileReader ins = new FileReader("go_sc_net_filter.txt");
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				if (map.get(strs[0]) == null) {
					map.put(strs[0], 1);
				} else {
					map.put(strs[0], map.get(strs[0]) + 1);
				}
				if (map.get(strs[1]) == null) {
					map.put(strs[1], 1);
				} else {
					map.put(strs[1], map.get(strs[1]) + 1);
				}
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map.get("YHR110W"));
		return map;
	}
	private HashSet<String> initData() {
		HashSet<String> hashSet= new HashSet<String>();
		
		try {
			FileReader ins = new FileReader("Essential.txt");
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				//String [] strs = buf.split("	");
				hashSet.add(buf);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hashSet.contains("YHR110W"));
		return hashSet;
	}
	private boolean checkDc(Map<String, Integer> map, HashSet<String> hashSet) {
		
		File outFile = new File("go_SC_net_result.txt");
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		

		List<Map.Entry<String, Integer>> infoIds =
			    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		//����
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getValue() - o1.getValue()); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		//}); 
		int i = 0;
		for(Map.Entry<String,Integer> e : infoIds) {
			System.out.println(e.getKey() + "::::" + e.getValue());
			//System.out.println(item + ";");
			if (hashSet.contains(e.getKey())) {
				out.print(e.getKey() + "	" + e.getValue() + "	" +"1\n");
			} else {
				out.print(e.getKey() + "	" + e.getValue() + "	" +"0\n");
				//System.out.println("0");
			}
		}
		
		
		
		try {
			fop.close();
			//dip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void calDc() {
		checkDc(getDc(), initData());
	}
	
	
	
}
