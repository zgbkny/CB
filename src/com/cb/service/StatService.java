package com.cb.service;

import java.util.List;

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
	
	public void statFileByPercent(String inpath) {
		System.out.println(inpath + "==================================");
		Statistics.statByKeyValueEss(inpath, null, 1);
		Statistics.statByKeyValueEss(inpath, null, 5);
		Statistics.statByKeyValueEss(inpath, null, 10);
		Statistics.statByKeyValueEss(inpath, null, 15);
		Statistics.statByKeyValueEss(inpath, null, 20);
		Statistics.statByKeyValueEss(inpath, null, 25);
	}
	
	public void statFileByNum(String inpath) {
		System.out.println(inpath + "==================================");
		Statistics.statByKeyValueEssInNum(inpath, null, 50);
		Statistics.statByKeyValueEssInNum(inpath, null, 100);
		Statistics.statByKeyValueEssInNum(inpath, null, 150);
		Statistics.statByKeyValueEssInNum(inpath, null, 200);
		Statistics.statByKeyValueEssInNum(inpath, null, 250);
		Statistics.statByKeyValueEssInNum(inpath, null, 300);
		Statistics.statByKeyValueEssInNum(inpath, null, 350);
		Statistics.statByKeyValueEssInNum(inpath, null, 400);
		Statistics.statByKeyValueEssInNum(inpath, null, 500);
	}
}
