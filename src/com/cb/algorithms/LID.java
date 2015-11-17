package com.cb.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class LID {
	
	public static boolean checkNeighbor(String protein1, String protein2, Map<String, List<String>> map){
		for (String neiNode1 : map.get(protein1)) {
			if (neiNode1.equals(protein2)) {
				return true;
			}
		}
		for (String neiNode2 : map.get(protein2)) {
			if (neiNode2.equals(protein1)) {
				return true;
			}
		}
		return false;
	}
	
	public static double getLIDValue(String protein, Map<String, List<String>> map) {
		List<String> neighbors = map.get(protein);
		Set<String> nodes = new HashSet<String>();
		Set<String> edges = new HashSet<String>();
		for (String protein1 : neighbors) {
			for (String protein2 : neighbors) {
				if (!protein1.equals(protein2)) {
					if (checkNeighbor(protein1, protein2, map)) {
						if (!nodes.contains(protein1)) {
							nodes.add(protein1);
						}
						if (!nodes.contains(protein2)) {
							nodes.add(protein2);
						}
						if (!edges.contains(protein1 + "," + protein2) 
								&& !edges.contains(protein2 + "," + protein1)) {
							edges.add(protein1 + "," + protein2);
						}
					}
				}
			}
		}
		
		double value = edges.size() * 1.0 / nodes.size();
		if (nodes.size() == 0) {
			value = 0;
		}
		return value;
	}

	// 某个节点相邻的闭合三角形的边数除以点数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String netFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN.txt";
		String outFilePath = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_LID.txt";
		Map<String, List<String>> map = GraphUtils.getGraph(netFilePath);
		List<String> outList = new ArrayList<String>();
		for (String protein : map.keySet()) {
			double value = getLIDValue(protein, map);
			outList.add(protein + "	" + value);
		}
		CommonUtils.outputFile(outFilePath, outList);
	}

}
