package com.cb.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OneDegree {
	/**
	 * 输入：List<String(点	度)>
	 * 输出：Set<String(点)>
	 * 功能：将度数不为1点的放入set中
	 */
	public Set<String> process(Map<String, Integer> map) {
		Set<String> set = new HashSet<String>();
		int sum = 0;
		List<Map.Entry<String, Integer>> mapList =
			    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		for(Map.Entry<String, Integer> e : mapList) {
	//		System.out.println("value:" + e.getValue()); && !set.contains(e.getKey())
			sum += e.getValue();
			if (e.getValue() == 1) {
				set.add(e.getKey());
			}
		}
		System.out.println(set.size());
		return set;
	}
}
