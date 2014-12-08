package com.cb.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Subcellular {
	/**
	 * 亚细胞定位
	 * 输入：List<String(边)>，Map<String(点), List<String>(亚细胞器)>
	 * 输出：过滤后的边List<String(边)>
	 * 功能：根据亚细胞定位策略过滤
	 */
	public List<String> locationFilter(List<String> list, Map<String, List<String>> map) {
		List<String> outList = new ArrayList<String>();
		for (String str : list) {
			String []strs = str.split("	");
			if (check(map.get(strs[0]), map.get(strs[1]))) {
				outList.add(str);
			}
		}
		return outList;
	}
	
	public boolean check(List<String> list1, List<String> list2) {
		if (list1 == null || list2 == null) return true;
		for (String str1 : list1) {
			for (String str2 : list2) {
				if (str1.equals("None") || str2.equals("None") || str1.equals(str2)) return true;
			}
		}
		return false;
	}
}
