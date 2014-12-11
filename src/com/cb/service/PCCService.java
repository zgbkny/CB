package com.cb.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.PCC;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;

public class PCCService {
	private PCC pcc;
	public PCCService() {
		pcc = new PCC();
	}
	
	public void calPcc(String inpath, String datapath, String outpath) {
		Map<String, List<Double>> map = initData(datapath);
		Set<String> set = CommonUtils.getInputFileSet(inpath);
		List<String> outList = pcc.calPcc(set, map);
		CommonUtils.outputFile(outpath, outList);
	}
	
	private Map<String, List<Double>> initData(String datapath) {
		HashMap<String, List<Double>> map= new HashMap<String, List<Double>>();
		
		try {
			FileReader ins = new FileReader(datapath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("  ");
				//System.out.println(strs[0] + ";" + strs[1] + ";");
				//System.out.println(strs[1]);
				if (!strs[1].equals("non-annotated") && map.get(strs[1]) == null) {
					List<Double> list = new ArrayList<Double>();
					for (int i = 2; i < strs.length; i++) {
						Double d = Double.parseDouble(strs[i]);
						//System.out.println(d);
						list.add(d);
					}
					map.put(strs[1], list);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	public void calSumPcc(String inpath, String outpath) {
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, Double> map = pcc.calSumPcc(list);
		List<String> outList = Statistics.sortMap(map);
		CommonUtils.outputFile(outpath, outList);
	}
}
