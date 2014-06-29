package com.cb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	private Map<String, Integer> getDc() {
		Map<String, Integer> map= new HashMap<String, Integer>();
		
		try {
			FileReader ins = new FileReader(filePath);
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
		return map;
	}
	private HashSet<String> initData() {
		HashSet<String> hashSet= new HashSet<String>();
		
		try {
			FileReader ins = new FileReader(dataPath);
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
		return hashSet;
	}
	private boolean checkDc(Map<String, Integer> map, HashSet<String> hashSet) {
		PrintStream out = new PrintStream(fop);
		Set<String> set = map.keySet();
		for (String item:set) {
			//System.out.println(item + "	" + map.get(item));
		}
		
		for (String item : hashSet) {
			System.out.println(item + ";");
			if (map.get(item) != null) {
				out.print(item + "	" + map.get(item) + "\n");
			}
		}
		try {
			fop.close();
			fip.close();
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
