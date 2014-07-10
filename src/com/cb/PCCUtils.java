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

public class PCCUtils {

	
	private FileInputStream fip;
	private FileInputStream dip;
	private FileOutputStream fop;
	
	public PCCUtils() {
	}
	
	
	private Set<String> initItem(String filepath) {
		HashSet<String> hashSet= new HashSet<String>();
		
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				/*String [] strs = buf.split("	");
				//System.out.println(strs[0] + ";" + strs[1] + ";");
				if (!hashSet.contains(strs[0]))
					hashSet.add(strs[0]);
				if (!hashSet.contains(strs[1]))
					hashSet.add(strs[1].toString());*/
				hashSet.add(buf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSet;
	}
	
	private Map<String, List<Double>> initData(String datapath) {
		HashMap<String, List<Double>> map= new HashMap<String, List<Double>>();
		
		try {
			FileReader ins = new FileReader(datapath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("  ");
				//System.out.println(strs[0] + ";" + strs[1] + ";");
				System.out.println(strs[1]);
				if (!strs[1].equals("non-annotated") && map.get(strs[1]) == null) {
					List<Double> list = new ArrayList<Double>();
					for (int i = 2; i < strs.length; i++) {
						Double d = Double.parseDouble(strs[i]);
						//System.out.println(d);
						list.add(d);
					}
					map.put(strs[1], list);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	private double pcc(List<Double> X, List<Double> Y) {
		double sumX = 0, sumY = 0, 
			   sumXSq = 0, sumYSq = 0,
			   pSum = 0;
		for (int i = 0; i < X.size(); i++) {
			sumX += X.get(i);
			sumY += Y.get(i);
		}
		for (int i = 0; i < X.size(); i++) {
			sumXSq += Math.pow(X.get(i), 2);
			sumYSq += Math.pow(Y.get(i), 2);
		}
		for (int i = 0; i < X.size(); i++) {
			pSum += Y.get(i) * X.get(i);
		}
		
		double num = pSum * X.size() - sumX * sumY;  
        double den = Math.sqrt(X.size() * sumXSq - sumX * sumX)
        			*Math.sqrt(Y.size() * sumYSq - sumY * sumY); 
        //System.out.println(sumX+";"+sumY+";"+sumXSq+";"+sumYSq+";"+pSum+";"+num+";"+den);
        //System.out.println((sumXSq - Math.pow(sumX, 2) / X.size()));
        //System.out.println((sumYSq - Math.pow(sumY, 2) / 2));
        return num/den;
	}
	
	public void calPcc(String filepath, String datapath) {
		File outFile = new File("pcc" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PrintStream out = new PrintStream(fop);
		Set<String> data = initItem(filepath);
		Map<String, List<Double>> map = initData(datapath);
		//Object[] strs = data.toArray();
		/*for (int i = 0; i < strs.length; i++) {
			for (int j = i + 1; j < strs.length; j++) {
				if (map.get(strs[i].toString()) != null
						&& map.get(strs[j].toString()) != null) {
					double ret = pcc(map.get(strs[i].toString()), map.get(strs[j].toString()));
					out.print(strs[i].toString() + "	" + strs[j].toString() + "	" + ret + "\n");
					//return;
				}
			}
		}*/
		Map<String, Double> mp = new HashMap<String, Double>();
		for (String item:data) {
			String [] items = item.split("	");
			if (map.get(items[0]) != null && map.get(items[1]) != null) {
				double ret = pcc(map.get(items[0]), map.get(items[1]));
				ret = Math.abs(ret);
				mp.put(items[0] + "	" + items[1], ret);
				//out.print(items[0] + "	" + items[1] + "	" + ret + "\n");
			}
			//return;	
		}
		
		List<Map.Entry<String, Double>> infoIds =
		    new ArrayList<Map.Entry<String, Double>>(mp.entrySet());
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
			//dip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
