package com.cb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EdgeUtils {
	/**
	 * 
	 * @param filepath: 
	 * @function: 读取每一行数据加入list
	 * @return
	 */
	
	public static List<String> getEdge(String filepath) {
		List<String> list = new ArrayList<String>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(buf);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param edges:edges中的每个条目代表一条边，包含两个点，点之间用tab隔开
	 * @return
	 */
	public static int getTotalNodeCount(List<String> edges) {
		int sum = 0;
		Set<String> set = new HashSet<String>();
		try {
			for (String edge : edges) {
				String []items = edge.split("	");
				if (!set.contains(items[0])) {
					set.add(items[0]);
					sum++;
				}
				
				if (!set.contains(items[1])) {
					set.add(items[1]);
					sum++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return sum;
	}
	/**
	 * 
	 * @param edges:edges中的每个条目代表一条边，包含两个点，点之间用tab隔开
	 * @return
	 */
	public static int getTotalEssentialNodeCount(List<String> edges) {
		int sum = 0;
		Set<String> set = new HashSet<String>();
		Set<String> essSet = FileUtils.getEssentialSet();
		
		try {
			for (String edge : edges) {
				String []items = edge.split("	");
				if (!set.contains(items[0])) {
					set.add(items[0]);
					if (essSet.contains(items[0])) {
						sum++;
					}
				}
				if (!set.contains(items[1])) {
					set.add(items[1]);
					if (essSet.contains(items[1])) {
						sum++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return sum;
	}
}
