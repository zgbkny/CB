package com.cb;

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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GOUtils {
	public void calProbability(List<String> list) {
		int sum = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String filepath : list) {
			try {
				FileReader ins = new FileReader(filepath);
				BufferedReader readBuf = new BufferedReader(ins);
				String buf = null;
				while ((buf = readBuf.readLine()) != null) {
					String [] strs = buf.split("	");
					for (int i = 1; i < strs.length; i++) {
						sum++;
						if (map.get(strs[i]) == null) {
							map.put(strs[i], 1);
						} else {
							map.put(strs[i], map.get(strs[i]) + 1);
						}
					}
					//System.out.println(strs[0]+map.get(strs[0]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		File outFile = new File("go_Probability.txt");
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		

		List<Map.Entry<String, Integer>> infoIds =
			    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getValue() - o1.getValue()); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		//}); 
		int i = 0;
		for(Map.Entry<String,Integer> e : infoIds) {
			System.out.println(e.getKey() + "::::" + e.getValue());
			//System.out.println(item + ";");
			double pro = map.get(e.getKey()) * 1.0 / sum;
			out.print(e.getKey() + "	" + pro + "\n");
		}
		
		
		
		try {
			fop.close();
			//dip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private Map<String, Double> getGoMap(String filepath) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				map.put(strs[0], Double.parseDouble(strs[1]));
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String, List<String>> genGoGraph(List<String> list) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String filepath : list) {
			try {
				FileReader ins = new FileReader(filepath);
				BufferedReader readBuf = new BufferedReader(ins);
				String buf = null;
				while ((buf = readBuf.readLine()) != null) {
					String [] strs = buf.split("	");
					if (strs.length > 1) {
						List<String> nodes = new ArrayList<String>();
						for (int i = 2; i < strs.length; i++) {
							nodes.add(strs[i]);
						}
						System.out.println(nodes.size());
						map.put(strs[1], nodes);
					}
					//System.out.println(strs[0]+map.get(strs[0]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	private Set<String> getAncestor(Map<String, List<String>> graph,
			String item1) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(item1);
		
		while (!queue.isEmpty()) {
			String str = queue.poll();
			List<String> list = graph.get(str);
			if (list != null) {
				for (String s : list) {
					if (!set.contains(s)) {
						set.add(s);
						queue.add(s);
					}
				}
			}
			
		}
		return set;
	}
	
	List<String> getCommonGo(Map<String, List<String>> graph, String item1, String item2) {
		List<String> retList = new ArrayList<String>();
		Set<String> set1 = getAncestor(graph, item1);

		Set<String> set = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(item2);	
		while (!queue.isEmpty()) {
			String str = queue.poll();
			List<String> list = graph.get(str);
			if (list != null) {
				for (String s : list) {
					if (!set.contains(s)) {
						set.add(s);
						queue.add(s);
						if (set1.contains(s)) retList.add(s);
					}
				}
			}
		}
		return retList;
	}
	double getMaxLog(Map<String, Double> goMap, List<String> list) {
		double ret = Math.log(goMap.get(list.get(0)));
		for (String s : list) {
			ret = Math.log(goMap.get(s)) > ret ? Math.log(goMap.get(s)) : ret;
		}
		return ret;
	}
	
	public void calSim(String filepath, List<String> list) {
		Map<String, Double> goMap = getGoMap(filepath);
		Map<String, List<String>> graph = genGoGraph(list);
		List<Map.Entry<String, Double>> infoIds =
			    new ArrayList<Map.Entry<String, Double>>(goMap.entrySet());
		
		
		File outFile = new File("go_sim.txt");
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		
		
		for (int i = 0; i < infoIds.size(); i++) {
			for (int j = i + 1; j < infoIds.size(); j++) {
				if (infoIds.get(i).getKey().equals(infoIds.get(j).getKey())) {
					System.out.println( infoIds.get(i).getKey()+ " " +infoIds.get(j).getKey());
					return;
				}
				//System.out.println( infoIds.get(i).getKey()+ " " +infoIds.get(j).getKey());
				List<String> commGo = getCommonGo(graph, infoIds.get(i).getKey(), infoIds.get(j).getKey());
				if (commGo.size() > 0) {
					double ret = getMaxLog(goMap, commGo);
					ret = ret / (Math.log(infoIds.get(i).getValue()) + Math.log(infoIds.get(j).getValue())); 
					out.print(infoIds.get(i).getKey() + "	" + infoIds.get(j).getKey() + 
							"	" + ret + "\n");
				} else {
					/*out.print(infoIds.get(i).getKey() + "	" + infoIds.get(j).getKey() + 
							"	0\n");
				*/}
				//System.out.println(i + " " + j);
			}
		}
		
		try {
			fop.close();
			//dip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Map<String, Double> genSim(String filepath) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String [] strs = buf.split("	");
				map.put(strs[0] + "	" + strs[1], Double.parseDouble(strs[2]));
				//System.out.println(Double.parseDouble(strs[2]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	private Map<String, List<String>> genGraph(List<String> list) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String filepath : list) {
			try {
				System.out.println(filepath);
				FileReader ins = new FileReader(filepath);
				BufferedReader readBuf = new BufferedReader(ins);
				String buf = null;
				while ((buf = readBuf.readLine()) != null) {
					//System.out.println(buf);
					String [] strs = buf.split("	");
					if (strs.length > 1) {
						List<String> nodes = new ArrayList<String>();
						for (int i = 1; i < strs.length; i++) {
							nodes.add(strs[i]);
						}
						map.put(strs[0], nodes);
						nodes = new ArrayList<String>();
						for (int i = 2; i < strs.length; i++) {
							nodes.add(strs[i]);
						}
						map.put(strs[1], nodes);
					}
					//System.out.println(strs[0]+map.get(strs[0]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	private List<String> getDan(String filepath) {
		List<String> list = new ArrayList<String>();
		
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				System.out.println(buf);
				list.add(buf);
				//System.out.println(strs[0]+map.get(strs[0]));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(map.get("YHR110W"));
		return list;
	}
	
	private Set<String> getGoSet(String item, Map<String, List<String>> map) {
		Set<String> set = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		q.add(item);
		while (!q.isEmpty()) {
			String str = q.poll();
			
			List<String> list = map.get(str);
			if (list != null && list.size() > 0) {
				for (String ss : list) {
					//System.out.println(ss);
					if (!set.contains(ss)) {
						set.add(ss);
						q.add(ss);
						
					}
				}
			}
		}
		return set;
	}
	
	public void calFS(String filepath, String datapath, List<String> list) {
		Map<String, List<String>> graph = genGraph(list);
		Map<String, Double> map = genSim(datapath);
		List<String> danList = getDan(filepath);
		
		File outFile = new File("go_FS.txt");
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		
		for (String item : danList) {
			System.out.println(item);
			String strs[] = item.split("	");
			System.out.println(strs[0] + " " + strs[1]);
			Set<String> set1 = getGoSet(strs[0], graph);
			Set<String> set2 = getGoSet(strs[1], graph);
			System.out.println(set1.size()+ " " +set2.size());
			Object objs1[] = set1.toArray();
			Object objs2[] = set2.toArray();
			StringBuffer sb = new StringBuffer();
			double max = 0;
			for (Object o1 : objs1) {
				for (Object o2 : objs2) {
					String s1 = (String)o1;
					String s2 = (String)o2;
					
					sb.delete(0, sb.length());
					sb.append(s1 + "	" + s2);
					//System.out.println(sb);
					double ret = 0;
					if (map.get(s1 + "	" + s2) != null) {
						ret = map.get(s1 + "	" + s2);
						System.out.println(ret);
					} else {
						sb.delete(0, sb.length());
						//sb.append(s2 + "	" + s1);
						System.out.println(sb);
						if (map.get(s2 + "	" + s1) != null) {
							ret = map.get(s2 + "	" + s1);
							System.out.println(ret);
						}
					}
					if (ret > max) max = ret;
					//if (max < 0.001) return;
				}
			}
			System.out.println(strs[0] + "	" + strs[1] + "	" + max);
			out.print(strs[0] + "	" + strs[1] + "	" + max + "\n");
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void normalize(String filepath) {
		double min = 10, max = 0;
		List<String> list = getDan(filepath);
		for (String str : list) {
			String strs[] = str.split("	");
			double ret = Double.parseDouble(strs[2]);
			if (ret > max) max = ret;
			if (ret < min) min = ret;
		}
		
		File outFile = new File("go_FSs.txt");
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(outFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintStream out = new PrintStream(fop);
		Map<String, Double> map = new HashMap<String, Double>();
		for (String str : list) {
			String strs[] = str.split("	");
			double ret = Double.parseDouble(strs[2]);
			ret = (ret - min) / (max - min);
			map.put(strs[0] + "	" + strs[1],  ret);
		}
		
		List<Map.Entry<String, Double>> infoIds =
		    new ArrayList<Map.Entry<String, Double>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        return (int)((o2.getValue()  - o1.getValue()) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});

		for(Map.Entry<String,Double> e : infoIds) {
			out.print(e.getKey() + "	" + e.getValue()  + "\n");
		}
		
		try {
			fop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
