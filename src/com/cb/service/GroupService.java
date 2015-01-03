package com.cb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.algorithms.Group;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;
import com.cb.utils.GraphUtils;

public class GroupService {
	
	public void calSidesDc(String inpath) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		Set<String> essSet = EssUtils.getEssentialSet();
		for (String path : list) {
			Map<String, Integer> dcMap = new DC().calDC(CommonUtils.getInputFile(path));
			Map<String, List<String>> map = GraphUtils.getGraph(path);
			Map<String, Integer> sdcMap = new HashMap<String, Integer>();
			List<Map.Entry<String, List<String>>> infoIds =
				    new ArrayList<Map.Entry<String, List<String>>>(map.entrySet());
			
			for(Map.Entry<String, List<String>> e : infoIds) {
				
				Integer key = dcMap.get(e.getKey());
				List<String> nodeList = e.getValue();
				int sum = 0;
				for (String item : nodeList) {
					sum += Math.abs(key - dcMap.get(item));
				}
				sdcMap.put(e.getKey(), sum);
			}
			
			CommonUtils.outputFile(path.replaceAll(".txt", "_sdc.txt"), Statistics.sortMapByAsc(sdcMap));
		}
	}
	
	
	public void divideGroup(String inpath) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		Set<String> essSet = EssUtils.getEssentialSet();
		for (String path : list) {
			Map<String, List<String>> map = GraphUtils.getGraph(path);
			List<List<String>> lists = Group.divideGroup(map);
			
			for (int i = 0; i < lists.size(); i++) {
				/*List<String> outList = new ArrayList<String>();
				for (String item : lists.get(i)) {
					if (essSet.contains(item)) {
						outList.add(item + "	1");
					} 
				}*/
				
			
				CommonUtils.outputFile(path.replaceAll(".txt", "_" + i + "group.txt"), lists.get(i));
			}
		}
	}
	
	public void statEss(String inpath) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		Set<String> essSet = EssUtils.getEssentialSet();
		for (String path : list) {
			List<String> datas = CommonUtils.getInputFile(path);
			List<String> outList = new ArrayList<String>();
			for (String tmp_item : datas) {
				String items[] = tmp_item.split("	");
				String item = items[0];
				if (essSet.contains(item)) {
					outList.add(item + "	1");
				} else {
					outList.add(item + "	0");
				}
			}
			CommonUtils.outputFile(path.replaceAll(".txt", "_val.txt"), outList);
			
		}

	}
}
