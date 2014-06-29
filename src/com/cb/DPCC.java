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
		File file = new File(filePath);
		File outFile = new File(outFilePath);
		if (file.exists() && file.isFile()
				&& (!outFile.exists() || outFile.isFile())) {
			try {
				fip = new FileInputStream(file);
				fop = new FileOutputStream(outFile);
				return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else return false;
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
				//System.out.println(d);
				map.put(Math.abs(d), strs[0] + "," + strs[1]);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public void getDPCC() {
		PrintStream out = new PrintStream(fop);
		Map<String, String> itemMap = initItem();
		Map<Double, String> map = initData();
		Set<Double> newSet = new TreeSet(map.keySet());
		Object[] items = newSet.toArray();
		for (int i = items.length - 1; i > -1; i--) {
			//System.out.println(map.get(item));
			String[] strs = map.get(items[i]).split(",");
			out.print(strs[0] + "	" + itemMap.get(strs[0])
					+ "	" + strs[1] + "	" + itemMap.get(strs[1]) 
					+ "	" + items[i].toString() + "\n");
		}
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
