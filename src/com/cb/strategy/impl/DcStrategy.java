package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

public class DcStrategy implements Strategy {

	// 将subdir中的文件单独求dc，然后再合并
	public static final int SUBDIR_IN_PATH = 1;
	// 将path中的文件单独求dc，然后再合并
	public static final int FILES_IN_PATH = 2;
	// 将path（path就是一个文件）求dc
	public static final int FILE_PATH = 3;
	// 将path中的文件合并以后，求dc
	public static final int MERGE_FILES_IN_PATH = 4;
	// 将subdir中的所有文件合并以后，求dc
	public static final int MERGE_SUBDIR_IN_PATH = 5;

	private Map<String, Integer> dcMap;
	private DC dc;
	private String outpath;
	private List<String> outList = new ArrayList<String>();
	private Set<String> mergeData = new HashSet<String>();
	private List<String> mergeList = new ArrayList<String>();
	private int actionType;
	private String filename;

	public DcStrategy(String outpath, int actionType) {
		dcMap = new HashMap<String, Integer>();
		dc = new DC();
		this.outpath = outpath;
		this.actionType = actionType;
	}

	public void setMergeFileName(String filename) {
		this.filename = filename;
	}

	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		List<String> files = null;
		switch (actionType) {
		case SUBDIR_IN_PATH:
			files = CommonUtils.getFilesInSubPath(dirpath);
			for (int i = 0; i < files.size(); i++) {
				pre(files.get(i));
				core(files.get(i));

			}
			post("");
			stat(outpath);
			break;
		case FILES_IN_PATH:
			files = CommonUtils.getFilesInPath(dirpath);
			for (int i = 0; i < files.size(); i++) {
				pre(files.get(i));
				core(files.get(i));
				post(files.get(i));
				stat(files.get(i));
			}
			
			break;
		case FILE_PATH:
			pre("");
			core("");
			post("");
			stat("");
			break;
		case MERGE_FILES_IN_PATH:
			files = CommonUtils.getFilesInPath(dirpath);
			for (int i = 0; i < files.size(); i++) {
				pre(files.get(i));
			}
			core("");
			post("");
			stat(outpath);
			break;
		case MERGE_SUBDIR_IN_PATH:
			files = CommonUtils.getFilesInSubPath(dirpath);
			for (int i = 0; i < files.size(); i++) {
				pre(files.get(i));
			}
			core("");
			post("");
			stat(outpath);
			break;
		default:
			return;
		}

	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		switch (actionType) {
		case SUBDIR_IN_PATH:

			break;
		case FILES_IN_PATH:
			
			break;
		case FILE_PATH:

			break;
		case MERGE_FILES_IN_PATH:
		case MERGE_SUBDIR_IN_PATH:
			List<String> list = CommonUtils.getInputFile(path);
			for (String ss : list) {
				if (!mergeData.contains(ss)) {
					mergeData.add(ss);
				}
			}
			break;
		default:
			return;
		}
	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		Map<String, Integer> map;
		switch (actionType) {
		case SUBDIR_IN_PATH:
			map = dc.calDC(CommonUtils.getInputFile(path));
			if (map.size() > 0) {
				for (String key : map.keySet()) {
					if (dcMap.containsKey(key)) {
						int value = dcMap.get(key) + map.get(key);
						dcMap.remove(key);
						dcMap.put(key, value);
					} else {
						dcMap.put(key, map.get(key));
					}
				}
			}
			break;
		case FILES_IN_PATH:
			map = dc.calDC(CommonUtils.getInputFile(path));
			dcMap = map;
			break;
		case FILE_PATH:
			break;
		case MERGE_FILES_IN_PATH:
		case MERGE_SUBDIR_IN_PATH:
			for (String ss : mergeData)
				mergeList.add(ss);
			CommonUtils.outputFile(filename, mergeList);
			dcMap = dc.calDC(mergeList);
			break;
		default:
			return;
		}

	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		List<Map.Entry<String, Integer>> infoIds;
		switch (actionType) {
		case SUBDIR_IN_PATH:
			infoIds = new ArrayList<Map.Entry<String, Integer>>(
					dcMap.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> o1,
								Map.Entry<String, Integer> o2) {
							return (o2.getValue() - o1.getValue());
						}
					});
			
			for (Map.Entry<String, Integer> e : infoIds) {
				outList.add(e.getKey() + "  " + e.getValue());
			}
			break;
		case FILES_IN_PATH:
			infoIds = new ArrayList<Map.Entry<String, Integer>>(
					dcMap.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> o1,
								Map.Entry<String, Integer> o2) {
							return (o2.getValue() - o1.getValue());
						}
					});
			outList = new ArrayList<String>();
			for (Map.Entry<String, Integer> e : infoIds) {
				outList.add(e.getKey() + "  " + e.getValue());
			}
			break;
		case FILE_PATH:
			break;
		case MERGE_FILES_IN_PATH:
		case MERGE_SUBDIR_IN_PATH:
			infoIds = new ArrayList<Map.Entry<String, Integer>>(
					dcMap.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> o1,
								Map.Entry<String, Integer> o2) {
							return (o2.getValue() - o1.getValue());
						}
					});
			for (Map.Entry<String, Integer> e : infoIds) {
				outList.add(e.getKey() + "  " + e.getValue());
			}
			break;
		default:
			return;
		}

	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub
		StatService ss = null;
		System.out.println(outList.size());
		switch (actionType) {
		case SUBDIR_IN_PATH:

			CommonUtils.outputFile(path, outList);
			ss = new StatService();
			ss.statFileByNum(path);
			break;
		case FILES_IN_PATH:
			CommonUtils.outputFile(path.replaceAll(".txt", "_dc.txt"), outList);
			
			break;
		case FILE_PATH:
			break;
		case MERGE_FILES_IN_PATH:
		case MERGE_SUBDIR_IN_PATH:
			CommonUtils.outputFile(path, outList);
			ss = new StatService();
			ss.statFileByNum(path);
			break;
		default:
			return;
		}

	}

}
