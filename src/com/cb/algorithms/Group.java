package com.cb.algorithms;

import java.util.*;

public class Group {
	
	public static void bfs(String root, Set<String> set, Map<String, List<String>> map, List<List<String>> lists) {
		Queue<String> queue = new LinkedList<String>();
		Set<String> check = new HashSet<String>(); 
		List<String> groupList = new ArrayList<String>();
		queue.add(root);
		check.add(root);
		while (!queue.isEmpty()) {
			String node = queue.poll();
			check.add(node);
			List<String> list = map.get(node);
			for (String item : list) {
				if (!check.contains(item)) {
					check.add(item);
					queue.add(item);
				}
			}
		}
		groupList.addAll(check);
		lists.add(groupList);
		set.removeAll(check);
		Iterator<String> it = set.iterator();
		if (it.hasNext()) {
			bfs(it.next(), set, map, lists);
		}
	}
	public static List<List<String>> divideGroup(Map<String, List<String>> map) {
		List<List<String>> ret = new ArrayList<List<String>>();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		String root = it.next();
		bfs(root, set, map, ret);
		
		return ret;
	}
}
