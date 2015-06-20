package com.cb.strategy.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class GenBrokeLineStrategy implements Strategy {

	List<String> TSlist = null;
	List<String> NFlist = null;
	List<String> Slist = null;
	Map<String, Double> TSMap = null;
	Map<String, Double> NFMap = null;
	Map<String, Double> SMap = null;

	String subDirPath = null;
	int round = 0;

	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> subPaths = CommonUtils.getSubDirInPath(dirpath);
		if (subPaths.size() <= 0)
			return;
		while (round < 2) {
			if (round == 0)
				System.out.println("round: TS vs NF");
			else 
				System.out.println("round: TS vs S");
			for (String subDirPath : subPaths) {
				String [] items = subDirPath.split("/");
				if (items.length > 0) {
					this.subDirPath = items[items.length - 1];
				}
				
				pre(subDirPath);
				core(null);
				post(null);
				stat(null);
			}
			round++;
		}
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stu
		List<String> filepaths = CommonUtils.getFilesInPath(path);
		if (filepaths.size() != 3) {
			return;
		}
		for (String filepath : filepaths) {
			if (filepath.indexOf("/TS-PIN") != -1) {
				//System.out.println("TS:" + filepath);
				TSlist = CommonUtils.getInputFileJustKey(filepath);
				TSMap = CommonUtils.getInputFileMap(filepath);
			}

			if (filepath.indexOf("/NF-APIN") != -1) {
				//System.out.println("NF:" + filepath);
				NFlist = CommonUtils.getInputFileJustKey(filepath);
				NFMap = CommonUtils.getInputFileMap(filepath);
					
			}

			if (filepath.indexOf("/S-PIN") != -1) {
				//System.out.println("S:" + filepath);
				Slist = CommonUtils.getInputFileJustKey(filepath);
				SMap = CommonUtils.getInputFileMap(filepath);
			}
		}
	}

	private int[] calDiffAndEss(List<String> list, List<String> otherList,
			Map<String, Double> nFMap2, double nfsize) {
		int[] ret = new int[3];
		int temp1 = 0, temp2 = 0;
		Set<String> set = new HashSet<String>();
		Set<String> essSet = EssUtils.getEssentialSet();
		for (int i = 0; i < 100; i++) {
			if (nFMap2.containsKey(list.get(i))
					&& nFMap2.get(list.get(i)) > nfsize) {
				set.add(list.get(i));
			}
		}
		ret[0] = 100 - set.size();
		for (int i = 0; i < 100; i++) {
			if (!set.contains(list.get(i))) {
				if (essSet.contains(list.get(i))) {
					temp1++;
				}
			}
			if (!set.contains(otherList.get(i))) {
				if (essSet.contains(otherList.get(i))) {
					temp2++;
				}
			}
		}
		ret[1] = temp1;
		ret[2] = temp2;
		return ret;
	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		if (!(TSlist.size() > 0 && NFlist.size() > 0 && Slist.size() > 0)) {
			return;
		}
		double tssize = TSMap.get(TSlist.get(100));
		double nfsize = NFMap.get(NFlist.get(100));
		double ssize = SMap.get(Slist.get(100));
		int ret[] = null;
		if (round == 0) {
			ret = calDiffAndEss(TSlist, NFlist, NFMap, nfsize);
		} else {
			ret = calDiffAndEss(TSlist, Slist, SMap, ssize);
		}
		System.out.println(this.subDirPath + "	" + ret[1] + "	" + ret[2] + "	" + ret[0]);
		//System.out.println(this.subDirPath + "	" + ret[1] * 1.0 / ret[0] + "	" + ret[2] * 1.0 / ret[0]);
	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		if (!(TSlist.size() > 0 && NFlist.size() > 0 && Slist.size() > 0)) {
			return;
		}
	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub

	}

}
