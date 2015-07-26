package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class DCTableStrategy implements Strategy {

	List<String> TSlist = null;
	List<String> NFlist = null;
	List<String> Slist = null;
	Map<String, Double> TSMap = null;
	Map<String, Double> NFMap = null;
	Map<String, Double> SMap = null;
	
	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> list = CommonUtils.getFilesInPath(dirpath);
		for (String filepath : list) {
			if (filepath.indexOf("/TS-PIN") != -1) {
				System.out.println("TS:" + filepath);
				TSlist = CommonUtils.getInputFileJustKey(filepath);
				TSMap = CommonUtils.getInputFileMap(filepath);
			}

			if (filepath.indexOf("/NF-APIN") != -1) {
				System.out.println("NF:" + filepath);
				NFlist = CommonUtils.getInputFileJustKey(filepath);
				NFMap = CommonUtils.getInputFileMap(filepath);
					
			}

			if (filepath.indexOf("/S-PIN") != -1) {
				System.out.println("S:" + filepath);
				Slist = CommonUtils.getInputFileJustKey(filepath);
				SMap = CommonUtils.getInputFileMap(filepath);
			}
		}
		pre(null);
		core(dirpath);
		post(null);
		stat(null);
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		List<String> outList = new ArrayList<String>();
		Set<String> essSet = EssUtils.getEssentialSet();
		for (int i = 0; i < Slist.size(); i++) {
			if (NFMap.containsKey(Slist.get(i)) && TSMap.containsKey(Slist.get(i))) {
				outList.add( Slist.get(i) + "	" + essSet.contains(Slist.get(i)) + "	" + SMap.get(Slist.get(i)) + "	" + NFMap.get(Slist.get(i)) + "	" + TSMap.get(Slist.get(i)));
			}
		}
		CommonUtils.outputFile(path + "/result.txt", outList);
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
