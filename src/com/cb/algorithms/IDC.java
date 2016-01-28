package com.cb.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	public static void proOther() {
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
	
/**********************************************************************************/
	public static Set<String> getProteins(List<String> list) {
		Set<String> set = new HashSet<String>();
		for (String item : list) {
			String [] items = item.split(",");
			if (!set.contains(items[0])) {
				set.add(items[0]);
			}
			if (!set.contains(items[1])) {
				set.add(items[1]);
			}
		}
		return set;
	}
	
	/**
	 * 
	 * @param indatas 每个复合物中包含的边
	 */
	public static void calIDC(List<String> indatas, String netfilepath, String outfilepath) {

		List<String> netList = CommonUtils.getInputFile(netfilepath);
		Set<String> proteins = getProteins(netList);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> outList = new ArrayList<String>();
		for (String item : indatas) {
			String []items = item.split(",");
			List<String> list = null;
			if (!map.containsKey(items[0])) {
				list = new ArrayList<String>();
				list.add(items[1]);
			} else {
				list = map.get(items[0]);
				list.add(items[1]);
			}
			map.put(items[0], list);
			
			
			if (!map.containsKey(items[1])) {
				list = new ArrayList<String>();
				list.add(items[0]);
			} else {
				list = map.get(items[1]);
				list.add(items[0]);
			}
			map.put(items[1], list);
		}
		
		for (String item : map.keySet()) {
			outList.add(item + "	" + map.get(item).size());
			if (proteins.contains(item)) {
				proteins.remove(item);
			}
		}
		System.out.println(proteins.size());
		for (String item : proteins) {
			outList.add(item + "	" + 0);
		}
		CommonUtils.outputFile(outfilepath, outList);
	}
	
/****************************************************************************/
	public static void calIDCFromOriginFile(String netFilePath, String complexFilePath, String outfilepath) {

		List<String> complexList = CommonUtils.getInputFile(complexFilePath);
		List<String> netList = CommonUtils.getInputFile(netFilePath);
		List<String> outList = new ArrayList<String>();
		Map<String, String> proteinEdgeMap = new HashMap<String, String>();
		for (String item : netList) {
			String []items = item.split(",");
			if (!proteinEdgeMap.containsKey(items[0])) {
				proteinEdgeMap.put(items[0], item);
			}
			if (!proteinEdgeMap.containsKey(items[1])) {
				proteinEdgeMap.put(items[1], item);
			}
		}
		Set<String> set = new HashSet<String>();
		for (String item : complexList) {
			String []items = item.split("	");
			
			for (String subitem : items) {
				if (proteinEdgeMap.containsKey(subitem) && !set.contains(proteinEdgeMap.get(subitem))) {
					set.add(proteinEdgeMap.get(subitem));
				}
			}
			outList.addAll(set);
			set.clear();
		}
		System.out.println("complex edge:" + outList.size());
		calIDC(outList, netFilePath, outfilepath);
	}
	/**
	 * 
	 * 从复合网络中处理数据
	 * @param args
	 */
	public static void calIDCFromComplexNetwork(String[] args) {
		String complexFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\complexinteraction.txt";
		List<String> inList = CommonUtils.getInputFile(complexFilePath);
		List<String> inList2 = new ArrayList<String>();
		
		for (String item : inList) {
			if (item.length() < 8) {
				continue;
			}
			inList2.add(item);
		}
		String netfilepath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN.txt";
		String outfilepath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_indegree.txt";
		calIDC(inList2, netfilepath, outfilepath);
	}

	public static void main(String[] args) {
		String dirpath = "E:\\金山网盘\\#共享#\\生物\\20151217实验\\subnetworks";
		List<String> list = CommonUtils.getFilesInPath(dirpath);
		String complexFilePath = "E:\\金山网盘\\#共享#\\生物\\实验数据\\别人的实验\\关于LIDC\\complex_425.txt";
		//String netFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN.txt";

		for (String netFilePath : list) {
			calIDCFromOriginFile(netFilePath, complexFilePath, netFilePath.replaceAll(".txt", "_indegree.txt"));
		}

	}

}
