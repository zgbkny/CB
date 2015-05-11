package com.cb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.algorithms.DC;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;

public class StatService {
	
	
	
	public void statFilesByPercent(String path) {
		List<String> list = CommonUtils.getFilesInPath(path);
		for (String inpath : list) {
			statFileByPercent(inpath);
		}
	}
	
	public void statFilesByNum(String path) {
		List<String> list = CommonUtils.getFilesInPath(path);
		for (String inpath : list) {
			statFileByNum(inpath);
		}
	}
	
	public void stat5CAndOrder(String inpath) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		for (String path : list) {
			if (path.indexOf("degree_ec_sc_bc_cc") != -1) {
				CommonService cs = new CommonService();
				
				cs.getAndOrder(path, path.replaceAll("_degree_ec_sc_bc_cc_out.txt", "_dc.txt"), 1);
				cs.getAndOrder(path, path.replaceAll("_degree_ec_sc_bc_cc_out.txt", "_ec.txt"), 2);
				cs.getAndOrder(path, path.replaceAll("_degree_ec_sc_bc_cc_out.txt", "_sc.txt"), 3);
				cs.getAndOrder(path, path.replaceAll("_degree_ec_sc_bc_cc_out.txt", "_bc.txt"), 4);
				cs.getAndOrder(path, path.replaceAll("_degree_ec_sc_bc_cc_out.txt", "_cc.txt"), 5);
				
			}
		}
		
	}
	
	public void statDC(String inpath) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		for (String path : list) {
			DCService dcs = new DCService();
			dcs.calDC(path, path.replaceAll(".txt", "_dc.txt"));
			
		}
		
	}
	
	public void statFileByPercent(String inpath) {
		System.out.println(inpath);
		List<String> list = CommonUtils.getFilesInPath(inpath);
		List<String> dataList = new ArrayList<String>();
		for (String path : list) {
			dataList.add(path);
			System.out.println(path+ "======================================");
			Statistics.statByKeyValueEss(path, null, 1, dataList);
			Statistics.statByKeyValueEss(path, null, 5, dataList);
			Statistics.statByKeyValueEss(path, null, 10, dataList);
			Statistics.statByKeyValueEss(path, null, 15, dataList);
			Statistics.statByKeyValueEss(path, null, 20, dataList);
			Statistics.statByKeyValueEss(path, null, 25, dataList);
		}
	}
	
	public void formatList(List<String> list) {
		
		for (String item : list) {
			if (item.contains(".txt")) { // file
				if (item.contains("subc_onedu")) {
					
				} else if (item.contains("onedu_subc")) {
					
				} else if (item.contains("subc")){
					
				} else if (item.contains("onedu")) {
					
				} else if (item.contains("SC_net")) {
					
				}
			} else {
				
			}
		}
	}
	
	public void statFileByNum(String path) {
		
		String inpath = path;
		
		System.out.println(inpath + "==================================");
		Statistics.statByKeyValueEssInNum(inpath, null, 20);
		Statistics.statByKeyValueEssInNum(inpath, null, 30);
		/*		Statistics.statByKeyValueEssInNum(inpath, null, 247);
		Statistics.statByKeyValueEssInNum(inpath, null, 495);
		Statistics.statByKeyValueEssInNum(inpath, null, 743);
		Statistics.statByKeyValueEssInNum(inpath, null, 990);
		Statistics.statByKeyValueEssInNum(inpath, null, 1237);
		//Statistics.statByKeyValueEssInNum(inpath, null, 1485);
		Statistics.statByKeyValueEssInNum(inpath, null, 100);
		
		Statistics.statByKeyValueEssInNum(inpath, null, 200);
		
		Statistics.statByKeyValueEssInNum(inpath, null, 300);
		
		Statistics.statByKeyValueEssInNum(inpath, null, 400);
		Statistics.statByKeyValueEssInNum(inpath, null, 500);
		
		Statistics.statByKeyValueEssInNum(inpath, null, 600);
		Statistics.statByKeyValueEssInNum(inpath, null, 700);
		Statistics.statByKeyValueEssInNum(inpath, null, 800);
		Statistics.statByKeyValueEssInNum(inpath, null, 900);*/
		//Statistics.statByKeyValueEssInNum(inpath, null, 1000);
		//Statistics.statByKeyValueEssInNum(inpath, null, 1500);
		//Statistics.statByKeyValueEssInNum(inpath, null, 2000);
		
	}
	
public void statFileByNumByKey(String path, String originfile, int min, int max) {
		
		List<String> list = CommonUtils.getFilesInPath(path);
		List<String> dataList = new ArrayList<String>();
		DC dc = new DC();
		Map<String, Integer> dcMap = dc.calDC(CommonUtils.getInputFile(originfile));
		
		for (String inpath : list) {
			System.out.println(inpath + "==================================");
			
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 100, dcMap, min, max);
			
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 200, dcMap, min, max);
			
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 300, dcMap, min, max);
			
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 400, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 500, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 600, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 700, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 800, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 900, dcMap, min, max);
			Statistics.statByKeyValueEssInNumByKey(inpath, null, 1000, dcMap, min, max);
			
			//Statistics.statByKeyValueEssInNum(inpath, null, 600);
			//Statistics.statByKeyValueEssInNum(inpath, null, 700);
			//Statistics.statByKeyValueEssInNum(inpath, null, 800);
			//Statistics.statByKeyValueEssInNum(inpath, null, 900);
			//Statistics.statByKeyValueEssInNum(inpath, null, 1000);
			//Statistics.statByKeyValueEssInNum(inpath, null, 1500);
			//Statistics.statByKeyValueEssInNum(inpath, null, 2000);
		}
	}

	public void comboData(String path) {
		// TODO Auto-generated method stub
		System.out.println("comboData");
		List<String> list = CommonUtils.getFilesInPath(path);
		List<String> sixcsList = CommonUtils.getInputFile(list.get(0));
		Map<String,Double> eccMap = CommonUtils.getInputFileMap(list.get(1));
		Map<String, Double> pccMap = CommonUtils.getInputFileMap(list.get(2));
		Map<String, Double> pecMap = CommonUtils.getInputFileMap(list.get(3));
		List<String> outList = new ArrayList<String>();
		
		
		System.out.println("check");
		for (String item : sixcsList) {
			String[]items = item.split("	");
			if (eccMap.containsKey(items[0])) {
				item = item + "	" + eccMap.get(items[0]);
				System.out.println(eccMap.get(items[0]));
			} 
			if (pccMap.containsKey(items[0])) {
				item = item + "	" + pccMap.get(items[0]);
				
				System.out.println(pccMap.get(items[0]));
			} else {
				item = item + "	" + "x";
			}
			
			if (pecMap.containsKey(items[0])) {
				item = item + "	" + pecMap.get(items[0]);
				
				System.out.println(pecMap.get(items[0]));
			}
			outList.add(item);
		}
		System.out.println(path + "combadata.txt");
		CommonUtils.outputFile(path + "\\combadata.txt", outList);
		
		
	}
}
