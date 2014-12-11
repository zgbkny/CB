package com.cb.utils;

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

import com.cb.global.Global;

public class CommonUtils {
	
	public static List<String> getFilesInPath(String path) {
		File file = new File(path);
		if (!file.isDirectory()) return null;
		List<String> outList = new ArrayList<String>();
		File[] t = file.listFiles();
		System.out.println(t);
        for(int i = 0; i < t.length; i++){
            //判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
            if(t[i].isDirectory()){

            }
            else{

                //if (t[i].getName().length() < 30) {
                	outList.add(t[i].getAbsolutePath());
                	System.out.println(t[i].getAbsolutePath());
                //}
            }
        }
        return outList;
	}
	
	public static Map<String, Double> getInputFileMap(String filepath) {
		Map<String, Double> mp = new HashMap<String, Double>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				mp.put(buf.split("	")[0], 0.0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mp;
	}
	
	public static Set<String> getInputFileSet(String filepath) {
		HashSet<String> hashSet= new HashSet<String>();
		
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {

				hashSet.add(buf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSet;
	}
	
	public static List<String> getInputFile(String filepath) {
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
		//System.out.println("get inputFile done....");
		return list;
	}
	
	public static void outputFile(String filepath, List<String> data) {
		
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(filepath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		for (String line : data) {
			out.print(line + "\n");
		}
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stat(String filepath, String outfilepath, double minValue, double maxValue, int size) {
		List<String> list = getInputFile(filepath);
		List<String> outList = new ArrayList<String>();
		double count = 0;
		double inter = (maxValue - minValue) * 1.0 / size, index = minValue;
		
		for (int i = 0, j = 0; i < size; i++) {
			index = inter * i + minValue;
			count = 0;
			for ( ; j < list.size(); j++) {
				String items[] = list.get(j).split("	");
				double value = Double.parseDouble(items[1]);
				if (value >= index && value < index + inter) {
					count += 1;
				} else {
					break;
				}
			}
			outList.add(index + "	" + count);
			
		}
		outputFile(outfilepath, outList);
	}
	
	public static List<String> mapValue(String filepath, int maxValue, int minValue) {
		List<String> list = new ArrayList<String>();
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(buf);
				double value = Double.parseDouble(buf.split("	")[1]);
				if (value > max) max = value;
				if (value < min) min = value;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
