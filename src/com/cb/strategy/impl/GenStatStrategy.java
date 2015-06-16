package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;
/***
 * 产生一个关键蛋白的统计文件
 * @author ww
 *
 */
public class GenStatStrategy implements Strategy {

	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> files = CommonUtils.getFilesInSubPath(dirpath);
		for (String filepath : files) {
			pre(filepath);
			core(filepath);
			post(filepath);
			stat(filepath);
		}
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		System.out.println(path);
		Map<String, Double> map = CommonUtils.getInputFileMap(path);
		List<String> inList = CommonUtils.getInputFile(path);
		List<String> outList = new ArrayList<String>();
		Set<String> essSet = EssUtils.getEssentialSet();
		int count = 0;
		
		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			if (essSet.contains(infoIds.get(i).getKey())) {
				count++;
			}
			
			int j = i + 1;
			outList.add(j + "	" + count);
		}
		CommonUtils.outputFile(path, outList);
	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub

	}

}
