package com.cb.utils;

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

		
	public static Map<String, Integer> getDc(String filepath) {
		Map<String, Integer> map= new HashMap<String, Integer>();
		
		try {
			FileReader ins = new FileReader(filepath);
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
		System.out.println("get dc done....");
		return map;
	}
	
	private static boolean checkDc(Map<String, Integer> map, Set<String> set, String filepath) {
		
		File outFile = new File(filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		

		List<Map.Entry<String, Integer>> infoIds =
			    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
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
			if (set.contains(e.getKey())) {
				out.print(e.getKey() + "	" + e.getValue() + "	" +"1\n");
			} else {
				out.print(e.getKey() + "	" + e.getValue() + "	" +"0\n");

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
	
	public static void calDc(String infilepath, String outfilepath) {
		checkDc(getDc(infilepath), EssUtils.getEssentialSet(), outfilepath);
	}
	
	
	
}
