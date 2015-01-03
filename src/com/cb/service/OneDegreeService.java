package com.cb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.algorithms.OneDegree;
import com.cb.utils.CommonUtils;

public class OneDegreeService {
	
	private OneDegree od;
	public OneDegreeService() {
		od = new OneDegree();
	}
	
	/**
	 * 输入：读入的路径，输出的路径
	 * 输出：无
	 * 功能：从一个文件读入
	 */
	public void process(String inpath, String outpath) {
		DC dc = new DC();
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, Integer> map = dc.calDC(list);
		Set<String> set = od.process(map);
		List<String> outList = new ArrayList<String>();
		System.out.println("size:" + set.size());
		int count = 0;
		for (String str : list) {
			String []strs = str.split("	");
			
			if (set.contains(strs[0]) && set.contains(strs[1])) {
				System.out.println(str);
				count++;
			}
			
			if (set.contains(strs[0]) || set.contains(strs[1])) {
				//System.out.println("onedu :" + str);
			} else {
				outList.add(str);
			}
		}
		System.out.println("check" + count);
		CommonUtils.outputFile(outpath, outList);
	}
}
