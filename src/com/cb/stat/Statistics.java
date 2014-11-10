package com.cb.stat;

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
import java.util.List;

public class Statistics {
	
	public void statByCount(String filepath, String outpath, int size) {
		List<Double> list= new ArrayList<Double>();
		
		File outFile = new File("stat_" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PrintStream out = new PrintStream(fop);
		
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(Double.parseDouble(buf.split("	")[2]));
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list, new Comparator<Double>() {   
		    public int compare(Double o1, Double o2) {      
		        return (int)((o1 - o2) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		Double d = 1.0;
		int j = 0;
		
		int max = list.size();
		for (int i = 1; i <= size; i++) {
			int sum = 0;
			for ( ; j < list.size(); j++) {
				if (list.get(j)<=(d * i / size)) sum++;
				else break;
				
			}
			System.out.println(sum + " " + max);
			out.print(sum + "	" + (d * i / size) + "\n");
			//out.print(sum * 1.0 / max+ "	" + (d * i / size) + "\n");
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void statByValue(String filepath, String outpath, int size) {
		List<Double> list= new ArrayList<Double>();
		
		File outFile = new File("stat_" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PrintStream out = new PrintStream(fop);
		
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(Double.parseDouble(buf.split("	")[2]));
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list, new Comparator<Double>() {   
		    public int compare(Double o1, Double o2) {      
		        return (int)((o1 - o2) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		Double d = 1.0;
		int j = 0;
		
		int max = list.size();
		for (int i = 1; i <= size; i++) {
			int sum = 0;
			for ( ; j < list.size(); j++) {
				if (list.get(j)<=(d * i / size)) sum++;
				else break;
				
			}
			System.out.println(sum + " " + max);
			out.print(sum + "	" + (d * i / size) + "\n");
			//out.print(sum * 1.0 / max+ "	" + (d * i / size) + "\n");
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
