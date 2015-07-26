package com.cb.strategy.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

/***
 * @author ww
 * @function 根据网络中每条边是否对应相同细胞器来分组，将网络分到不同的细胞器中
 */
public class DynamicNetworkStrategy implements Strategy {
	// 蛋白对应的细胞器map
	private Map<String, List<String>> mp;
	// 细胞器map
	private Map<String, List<String>> xbqMap;

	// 蛋白对应的细胞器文件夹
	public DynamicNetworkStrategy(String path, boolean dirFlag) {
		mp = new HashMap<String, List<String>>();
		xbqMap = new HashMap<String, List<String>>();
		List<String> files = null;
		if (dirFlag) {
			files = CommonUtils.getFilesInPath(path);
		} else {
			files = new ArrayList<String>();
			files.add(path);
		}
		for (String filepath : files) {
			List<String> data = CommonUtils.getInputFile(filepath);
			for (String item : data) {
				String[] strs = item.split("	");
				if (strs.length == 2)
					continue;
				String[] items = strs[2].split(", ");
				if (mp.containsKey(strs[1])) {
					for (String ss : items) {
						mp.get(strs[1]).add(ss);
					}
				} else {
					List<String> list = new ArrayList<String>();
					for (String ss : items) {
						list.add(ss);
					}
					mp.put(strs[1], list);
				}

				for (String ss : items) {
					if (ss.indexOf("/") != -1) {
						System.out.println(ss);
					}
					if (!xbqMap.containsKey(ss)) {
						xbqMap.put(ss, null);
					}
				}
			}
		}
	}

	// 蛋白对应的细胞器文件
	public DynamicNetworkStrategy(String filepath) {
		// TODO Auto-generated constructor stub
		mp = new HashMap<String, List<String>>();
		xbqMap = new HashMap<String, List<String>>();
		List<String> data = CommonUtils.getInputFile(filepath);
		for (String item : data) {
			String[] strs = item.split("	");
			
			if (strs.length == 1)
				continue;
			String[] items = strs[2].split(", ");
			if (mp.containsKey(strs[1])) {
				for (String ss : items) {
					mp.get(strs[1]).add(ss);
				}
			} else {
				List<String> list = new ArrayList<String>();
				for (String ss : items) {
					list.add(ss);
				}
				mp.put(strs[1], list);
			}

			for (String ss : items) {
				if (!xbqMap.containsKey(ss)) {
					
					xbqMap.put(ss, null);
				}
			}
		}
	}

	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> list = CommonUtils.getFilesInPath(dirpath);

		for (String path : list) {
			pre(path);
			core(path);
			post(path);
			stat(path);
		}
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		List<String> data = CommonUtils.getInputFile(path);
		List<String> list1, list2;
		List<String> outList = new ArrayList<String>();
		boolean flag = true;
		for (String item : data) {
			String items[] = item.split("	");
			
			list1 = mp.get(items[0]);
			list2 = mp.get(items[1]);
			if (list1 != null && list2 != null) {
				flag = true;
				for (String ss : list1) {
					for (String sss : list2) {
						if (ss.equals(sss)) {
							if (xbqMap.get(ss) == null) {
								List<String> temp = new ArrayList<String>();
								temp.add(item);
								xbqMap.put(ss, temp);
							} else {
								xbqMap.get(ss).add(item);
							}
						}

					}
				}
			}
		}

		for (String ss : xbqMap.keySet()) {
			if (xbqMap.get(ss) != null) {
				File file = new File(path.replace(".txt", ""));
				file.mkdirs();
				CommonUtils.outputFile(
				        path.replace(".txt", "/" + ss + "_out.txt"),
				        xbqMap.get(ss));
			}

		}

	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		// clear all xdcMap
		Set<String> set = xbqMap.keySet();
		for (String ss : set) {
			xbqMap.put(ss, null);
		}
	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub

	}

}
