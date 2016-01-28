package com.cb.algorithms;

import com.cb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subcellular {
	/**
	 * 亚细胞定位
	 * 输入：List<String(边)>，Map<String(点), List<String>(亚细胞器)>
	 * 输出：过滤后的边List<String(边)>
	 * 功能：根据亚细胞定位策略过滤
	 */
	public static List<String> locationFilter(List<String> list, Map<String, List<String>> map) {
		List<String> outList = new ArrayList<String>();
		for (String str : list) {
			String []strs = str.split("\t");
			if (check(map.get(strs[0]), map.get(strs[1]))) {
				outList.add(str);
			}
		}
		return outList;
	}
	
	public static boolean check(List<String> list1, List<String> list2) {
		if (list1 == null || list2 == null) return false;
		for (String str1 : list1) {
			for (String str2 : list2) {
				if (str1.equals(str2)) return true;
			}
		}
		return false;
	}

	/* 过滤网路 */
	public static void calSubcellular(String filepath, String datapath, String outpath) {
		List<String> netlist = CommonUtils.getInputFile(filepath);
		List<String> list = CommonUtils.getInputFile(datapath);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String item : list) {
			String []items = item.split("\t");
			if (items.length == 1) {
				map.put(items[0], null);
			} else if (items.length == 2) {
				List<String> tmpList = new ArrayList<>();
				String []subitems = items[1].split(", ");
				for (String subitem : subitems) {
					tmpList.add(subitem);
				}
				map.put(items[0], tmpList);
			}
		}

		List<String> outlist = locationFilter(netlist, map);
		CommonUtils.outputFile(outpath, outlist);
	}

	public static void main(String []args) {
		String filepath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\S-PIN.txt";
		String datapath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\subcellular.txt";
		String outpath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\Sub-PIN.txt";

		calSubcellular(filepath, datapath, outpath);
	}
}
