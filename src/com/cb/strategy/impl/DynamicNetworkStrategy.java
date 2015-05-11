package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

public class DynamicNetworkStrategy implements Strategy {

	private Map<String, List<String>> mp;
	private Map<String, List<String>> xbqMap;

	
	public DynamicNetworkStrategy(String filepath) {
		// TODO Auto-generated constructor stub
		mp = new HashMap<String, List<String>>();
		xbqMap = new HashMap<String, List<String>>();
		List<String> data = CommonUtils.getInputFile(filepath);
		for (String item : data) {
			String []strs = item.split("	");
			String []items = strs[1].split(",");
			if (mp.containsKey(strs[0])) {
				for (String ss : items) {
					mp.get(strs[0]).add(ss);
				}
			} else {
				List<String> list = new ArrayList<String>();
				for (String ss : items) {
					list.add(ss);
				}
				mp.put(strs[0], list);
			}
			
			for (String ss : items) {
				if (!xbqMap.containsKey(ss)) {
					xbqMap.put(ss, null);
				}
			}
		}
	}
	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> list = CommonUtils.getFilesInPath(dirpath);
		
		for (String path : list) {
			pre(path);
			core(path);
			post(path);
			stat(path);
		}
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		List<String> data = CommonUtils.getInputFile(path);
		List<String> list1, list2;
		List<String> outList = new ArrayList<String>();
		for (String item : data) {
			String items[] = item.split("	");
			list1 = mp.get(items[0]);
			list2 = mp.get(items[1]);
			//System.out.println(item);
			if ( list1 != null && list2 != null) {
				for (String ss : list1) {
					for (String sss : list2) {
						if (ss.equals(sss)) {
							if (xbqMap.get(ss) == null) {
								List<String> temp = new ArrayList<String>();
								temp.add(item);
								xbqMap.put(ss, temp);
							} else {
								xbqMap.get(ss).add(item);
							}
						}
					}
				}
			}
		}
		for (String ss : xbqMap.keySet()) {
			if (xbqMap.get(ss) != null) {
				CommonUtils.outputFile(path.replace(".txt", "\\" + ss + "_out.txt"), xbqMap.get(ss));
			}
			
		}
		
	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		// clear all xdcMap
		Set<String> set = xbqMap.keySet();
		for (String ss : set) {
			xbqMap.put(ss, null);
		}
	} 

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub

	}

}
