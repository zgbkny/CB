package com.cb.strategy.impl;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/***
 * 陈骁培论文最终版，将12个不同时刻的网络根据是否在同一个细胞器进行筛选。
 * @author ww
 *
 */
public class PaperFinalStrategy implements Strategy {

	Map<String, List<String>> mp;
	
	// 蛋白对应的细胞器文件
	public PaperFinalStrategy(String filepath) {
		List<String> list = CommonUtils.getInputFile(filepath);
		mp = new HashMap<String, List<String>>();
		for (String item : list) {
			String []strs = item.split("	");
			if (strs.length == 2) {
				/*if (!mp.containsKey(strs[1])) {
					mp.put(strs[1], null);
				}*/
			} else if (strs.length == 3) {
				if (!mp.containsKey(strs[1])) {
					List<String> tmplist = new ArrayList<String>();
					String []ss = strs[2].split(", ");
					//System.out.println(ss.length);
					for (String temp : ss) {
						//System.out.println(temp);
						tmplist.add(temp);
					}
					mp.put(strs[1], tmplist);
				}
			}
		}
	}
	
	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> list = CommonUtils.getFilesInPath(dirpath);
		List<String> dataList = new ArrayList<String>();
		List<String> outList = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		for (String filepath : list) {
			List<String> tempList = CommonUtils.getInputFile(filepath);
			for (String item : tempList) {
				if (!set.contains(item)) {
					String [] ss = item.split("	");
					String str = ss[1] + "	" + ss[0];
					if (!set.contains(str)) {
						dataList.add(item);
						set.add(item);
					}
				}
			}
		}
		System.out.println(set.size());
		///////  已经将网络合并，下边开始筛选
		for (String item : dataList) {
			List<String> list1, list2;
			String items[] = item.split("	");
			list1 = mp.get(items[0]);
			list2 = mp.get(items[1]);
			if (list1 != null && list2 != null) {
				for (String ss : list1) {
					for (String sss : list2) {
						if (ss.equals(sss)) {
							outList.add(item);
						}
					}
				}
			}
		}
		///////  网络已经筛选完成，下边是导出到文件
		CommonUtils.outputFile(dirpath + "/TS-PIN.txt", outList);
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub

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
