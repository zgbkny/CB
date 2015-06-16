package com.cb.stat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class Statistics {

	public static List<String> sortListAndNormalWithAll(List<String> list,
	        int index) {
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		List<String> outList = new ArrayList<String>();
		for (String str : list) {
			String strs[] = str.split("	");
			// System.out.println(strs[index]);
			// System.out.println(str);
			double ret = Double.parseDouble(strs[index]);
			if (ret > max)
				max = ret;
			if (ret < min)
				min = ret;
		}
		Map<String, Double> map = new HashMap<String, Double>();
		for (String str : list) {
			String strs[] = str.split("	");
			double ret = Double.parseDouble(strs[index]);
			ret = (ret - min) / (max - min);
			map.put(strs[0], ret);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (Map.Entry<String, Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}

		return outList;
	}

	public static List<String> sortListAndNormal(List<String> list, int index) {
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		List<String> outList = new ArrayList<String>();
		for (String str : list) {
			String strs[] = str.split("	");
			// System.out.println(strs[index]);
			// System.out.println(str);
			double ret = Double.parseDouble(strs[index]);
			if (ret > max)
				max = ret;
			if (ret < min)
				min = ret;
		}
		Map<String, Double> map = new HashMap<String, Double>();
		for (String str : list) {
			String strs[] = str.split("	");
			double ret = Double.parseDouble(strs[index]);
			ret = (ret - min) / (max - min);
			map.put(strs[0], ret);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (Map.Entry<String, Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}

		return outList;
	}

	public static List<String> sortMap(Map<String, Double> map) {
		List<String> outList = new ArrayList<String>();
		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (Map.Entry<String, Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}

		return outList;

	}

	public static List<String> sortMapAndNormal(Map<String, Double> map) {
		List<String> outList = new ArrayList<String>();
		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (Map.Entry<String, Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}

		return outList;

	}

	public static List<String> sortMapByAsc(Map<String, Integer> sdcMap) {
		List<String> outList = new ArrayList<String>();
		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(
		        sdcMap.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
			        Map.Entry<String, Integer> o2) {
				return (int) ((o1.getValue() - o2.getValue()) * 1);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (Map.Entry<String, Integer> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}

		return outList;

	}

	public static void statByKeyValueEss(String inpath, String outpath,
	        int size, List<String> dataList) {
		Set<String> essSet = EssUtils.getEssentialSet();
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, Double> map = new HashMap<String, Double>();

		for (String str : list) {
			String[] strs = str.split("	");
			Double d = Double.parseDouble(strs[1]);
			map.put(strs[0], d);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		int countAll = infoIds.size();
		int essCount = 0, count = 0;

		for (int i = 0; i < countAll * 1.0 * size / 100; i++) {

			Map.Entry<String, Double> e = infoIds.get(i);
			count++;
			if (essSet.contains(e.getKey())) {
				essCount++;
			}

			// System.out.println(e.getValue());
		}

		System.out.println("top" + size + "%:	" + essCount * 1.0 * 100 / count
		        + "%	essCountInTop:" + essCount + "	countInTop:" + count);

		dataList.add("top" + size + "%:	" + essCount * 1.0 * 100 / count
		        + "%	essCountInTop:" + essCount + "	countInTop:" + count);

	}

	public static void statByKeyValueEssInNum(String inpath, String outpath,
	        int size) {
		Set<String> essSet = EssUtils.getEssentialSet();
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, Double> map = new HashMap<String, Double>();
		int k = 0;
		for (String str : list) {

			String[] strs = str.split("   ");
			Double d = Double.parseDouble(strs[1]);
			map.put(strs[0], d);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// int flag = o2.getValue().compareTo(o1.getValue());

				/*
				 * if (o2.getValue() > o1.getValue()) { return 1; } else return
				 * -1;
				 */
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		int countAll = infoIds.size();
		int essCount = 0, count = 0;

		for (int i = 0; i < size; i++) {
			Map.Entry<String, Double> e = infoIds.get(i);

			// System.out.println(e.getKey() + " " + e.getValue() + " " +
			// essSet.contains(e.getKey()));

			count++;
			if (essSet.contains(e.getKey())) {
				essCount++;
			}
		}

		System.out.println("top" + size + ":	" + essCount * 1.0 * 100 / count
		        + "%	essCountInTop:	" + essCount + "	countInTop:	" + count);
	}

	public static void statByKeyValueEssInNumByKey(String inpath,
	        String outpath, int size, Map<String, Integer> dcMap, int min,
	        int max) {
		Set<String> essSet = EssUtils.getEssentialSet();
		List<String> list = CommonUtils.getInputFile(inpath);
		Map<String, Double> map = new HashMap<String, Double>();

		for (String str : list) {
			String[] strs = str.split("	");
			Double d = Double.parseDouble(strs[1]);
			map.put(strs[0], d);
		}

		List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
		        map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
			        Map.Entry<String, Double> o2) {
				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		int countAll = infoIds.size();
		int essCount = 0, count = 0;

		for (int i = 0; i < size; i++) {

			Map.Entry<String, Double> e = infoIds.get(i);
			count++;
			if (essSet.contains(e.getKey()) && dcMap.get(e.getKey()) >= min
			        && dcMap.get(e.getKey()) < max) {
				essCount++;
			}

		}

		System.out.println("top" + size + ":[" + min + "," + max + ") 	"
		        + essCount * 1.0 * 100 / count + "%	essCountInTop:	" + essCount
		        + "	countInTop:	" + count);

	}

	public void statByCount(String filepath, String outpath, int size) {
		List<Double> list = new ArrayList<Double>();

		File outFile = new File("stat_" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintStream out = new PrintStream(fop);

		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(Double.parseDouble(buf.split("	")[2]));
				// System.out.println(strs[0]+map.get(strs[0]));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list, new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return (int) ((o1 - o2) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		Double d = 1.0;
		int j = 0;

		int max = list.size();
		for (int i = 1; i <= size; i++) {
			int sum = 0;
			for (; j < list.size(); j++) {
				if (list.get(j) <= (d * i / size))
					sum++;
				else
					break;

			}
			System.out.println(sum + " " + max);
			out.print(sum + "	" + (d * i / size) + "\n");
			// out.print(sum * 1.0 / max+ "	" + (d * i / size) + "\n");
		}

		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void statByValue(String filepath, String outpath, int size) {
		List<Double> list = new ArrayList<Double>();

		File outFile = new File("stat_" + filepath);
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintStream out = new PrintStream(fop);

		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				list.add(Double.parseDouble(buf.split("	")[2]));
				// System.out.println(strs[0]+map.get(strs[0]));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(list, new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return (int) ((o1 - o2) * 100000000);
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		Double d = 1.0;
		int j = 0;

		int max = list.size();
		for (int i = 1; i <= size; i++) {
			int sum = 0;
			for (; j < list.size(); j++) {
				if (list.get(j) <= (d * i / size))
					sum++;
				else
					break;

			}
			System.out.println(sum + " " + max);
			out.print(sum + "	" + (d * i / size) + "\n");
			// out.print(sum * 1.0 / max+ "	" + (d * i / size) + "\n");
		}

		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
