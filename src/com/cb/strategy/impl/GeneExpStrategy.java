package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

public class GeneExpStrategy implements Strategy{

	String outpath;
	List<String> outList = new ArrayList<String>();
	
	public GeneExpStrategy(String outpath) {
		this.outpath = outpath;
	}
	
	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		pre(dirpath);
		core(dirpath);
		post(dirpath);
		stat(outpath);
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		List<String> inList = CommonUtils.getInputFile(path);
		List<String> mapList = CommonUtils.getInputFile("result36.txt");
		Map<String, List<Double>> map = new HashMap<String, List<Double>>();
		for (String item : mapList) {
			String []strs = item.split("  ");
			if (!strs[1].equals("non-annotated") && map.get(strs[1]) == null) {
				List<Double> list = new ArrayList<Double>();
				for (int i = 2; i < strs.length; i++) {
					Double d = Double.parseDouble(strs[i]);
					list.add(d);
				}
				map.put(strs[1], list);
			}
		}
		List<Double> list1 = null;
		List<Double> list2 = null;
		// here we get map
		for (String item : inList) {
			String strs[] = item.split("	");
			list1 = map.get(strs[0]);
			list2 = map.get(strs[1]);
			if (list1 != null && list2 != null) {
				for (int i = 0; i < list1.size(); i++) {
					if (list1.get(i) > 0.7 && list2.get(i) > 0.7) {
						System.out.println(item);
						outList.add(item);
						break;
					}
				}
			}
		}
	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub
		CommonUtils.outputFile(path, outList);
		
	}
	
}
