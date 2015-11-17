package com.cb.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cb.utils.CommonUtils;

public class IDC {
	public static List<String> getKeys(String filepath) {
		List<String> list = CommonUtils.getInputFile(filepath);
		Set<String> l1Set = new HashSet<String>();
		List<String> outList = new ArrayList<String>();
		for (String item : list) {
			String items[] = item.split(",");
			if (!l1Set.contains(items[0])) {
				l1Set.add(items[0]);
				outList.add(items[0]);
			}
			if (!l1Set.contains(items[1])) {
				l1Set.add(items[1]);
				outList.add(items[1]);
			}
		}
		System.out.println(l1Set.size());
		
		return outList;
	}
	/**
	 * 在别人的基础上做的处理工作
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String netFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN.txt";
		String indegreeFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\indegree.txt";
		String outFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_indegree.txt";
		List<String> proteins = getKeys(netFilePath);
		List<String> indegrees = CommonUtils.getInputFile(indegreeFilePath);
		List<String> outList = new ArrayList<String>();
		if (proteins.size() != indegrees.size()) {
			System.out.println("error proteins size didn't match indegree's size");
			return;
		}
		for (int i = 0; i < proteins.size(); i++) {
			String item = proteins.get(i) + "	" + indegrees.get(i);
			outList.add(item);
		}
		CommonUtils.outputFile(outFilePath, outList);
	}

}
