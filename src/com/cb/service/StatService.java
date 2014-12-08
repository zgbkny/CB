package com.cb.service;

import java.util.List;

import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;

public class StatService {
	public void statFiles(String path) {
		List<String> list = CommonUtils.getFilesInPath(path);
		for (String inpath : list) {
			statFile(inpath);
		}
	}
	public void statFile(String inpath) {
		System.out.println(inpath + "==================================");
		Statistics.statByKeyValueEss(inpath, null, 1);
		Statistics.statByKeyValueEss(inpath, null, 5);
		Statistics.statByKeyValueEss(inpath, null, 10);
		Statistics.statByKeyValueEss(inpath, null, 15);
		Statistics.statByKeyValueEss(inpath, null, 25);
	}
}
