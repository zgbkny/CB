package com.cb;

import java.io.BufferedReader;
import java.io.File;
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

public class CBUtils {
	
	private List<String> getFile(String filepath) {
		List<String> list= new ArrayList<String>();
		
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
	
	private HashSet<String> initData(String datapath) {
		HashSet<String> hashSet= new HashSet<String>();
		
		try {
			FileReader ins = new FileReader(datapath);
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
	
	public void classify(String filepath, String datapath) {
		List<String> list = getFile(filepath);
		Set<String> set = initData(datapath);
		File outFile1 = new File("c1_all_" + filepath);
		File outFile2 = new File("c2_notall_" + filepath);
		File outFile3 = new File("c3_not_" + filepath);
		FileOutputStream fop1 = null;
		FileOutputStream fop2 = null;
		FileOutputStream fop3 = null;
		try {
			fop1 = new FileOutputStream(outFile1);
			fop2 = new FileOutputStream(outFile2);
			fop3 = new FileOutputStream(outFile3);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out1 = new PrintStream(fop1);
		PrintStream out2 = new PrintStream(fop2);
		PrintStream out3 = new PrintStream(fop3);
		
		for (String item : list) {
			String strs[] = item.split("	");
			if (set.contains(strs[0]) && set.contains(strs[1])) {
				out1.print(item + "\n");
			} else if (set.contains(strs[0]) || set.contains(strs[1])) {
				out2.print(item + "\n");
			} else {
				out3.print(item + "\n");
			}
		}
		
		try {
			fop1.close();
			fop2.close();
			fop3.close();
			//dip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
