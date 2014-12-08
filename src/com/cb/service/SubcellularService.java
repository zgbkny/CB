package com.cb.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cb.algorithms.Subcellular;
import com.cb.utils.CommonUtils;

public class SubcellularService {
	
	private Subcellular subc;
	
	public SubcellularService() {
		subc = new Subcellular();
	}
	
	/**
	 * 输入：读入的路径，输出的路径
	 * 输出：无
	 * 功能：亚细胞定位
	 */
	public void locationFilter(String inpath, String subcFile,  String outpath) {
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, List<String>> map = getESLDBmap(subcFile);
		List<String> outList = subc.locationFilter(list, map);
		CommonUtils.outputFile(outpath, outList);
	}
	
	private Map<String, List<String>> getESLDBmap(String filepath) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String []strs = buf.split("	");
				String []items = strs[2].split(",");
				List<String> list = new LinkedList<String>();
				for (String item : items) {
					list.add(item);
					//System.out.println(strs[1] + ":" + item);
				}
				if (map.containsKey(strs[1])) {
					map.get(strs[1]).addAll(list);
				} else {
					map.put(strs[1], list);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
