package com.cb.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.entity.Edge;

public class PCC {
	
	
	/**
	 * 功能：求各个点的pcc和
	 * @param list List<String(点  点  pcc)>
	 * @return
	 */
	public Map<String, Double> calSumPcc(List<String> list) {
		Map<String, Double> map = new HashMap<String, Double>();
		
		for (String item : list) {
			String []items = item.split("	");
			double value = Double.parseDouble(items[2]);
			if (map.containsKey(items[0])) {
				map.put(items[0], map.get(items[0]) + value);
			} else {
				map.put(items[0], value);
			}
			
			if (map.containsKey(items[1])) {
				map.put(items[1], map.get(items[1]) + value);
			} else {
				map.put(items[1], value);
			}
		}
		
		return map;
	}
	
	
	private static double pcc(List<Double> X, List<Double> Y) {
		double sumX = 0, sumY = 0, 
			   sumXSq = 0, sumYSq = 0,
			   pSum = 0;
		for (int i = 0; i < X.size(); i++) {
			sumX += X.get(i);
			sumY += Y.get(i);
		}
		for (int i = 0; i < X.size(); i++) {
			sumXSq += Math.pow(X.get(i), 2);
			sumYSq += Math.pow(Y.get(i), 2);
		}
		for (int i = 0; i < X.size(); i++) {
			pSum += Y.get(i) * X.get(i);
		}
		
		double num = pSum * X.size() - sumX * sumY;  
        double den = Math.sqrt(X.size() * sumXSq - sumX * sumX)
        			*Math.sqrt(Y.size() * sumYSq - sumY * sumY); 
        return num/den;
	}
	/**
	 * 
	 * @param data:Set<String(边)>
	 * @param map:类似于result36.txt生成的图
	 */
	public static List<String> calPcc(Set<String> data, Map<String, List<Double>> map) {
		
		List<String> outList = new ArrayList<String>();
		Map<String, Double> mp = new HashMap<String, Double>();
		System.out.println(data.size());
		for (String item : data) {
			String [] items = item.split("	");
			if (map.get(items[0]) != null && map.get(items[1]) != null) {
				double ret = pcc(map.get(items[0]), map.get(items[1]));
				//ret = Math.abs(ret);
				mp.put(items[0] + "	" + items[1], ret);
				//out.print(items[0] + "	" + items[1] + "	" + ret + "\n");
			} else {
				System.out.println("check");
			}
			//return;	
		}
		
		List<Map.Entry<String, Double>> infoIds =
		    new ArrayList<Map.Entry<String, Double>>(mp.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        return (int)((o2.getValue()  - o1.getValue()) * 100000000); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});

		for(Map.Entry<String,Double> e : infoIds) {
			outList.add(e.getKey() + "	" + e.getValue());
		}
		return outList;
	}
	
	
	public static float Pcc(Edge edge)
	  {
	    float avg2;
	    float avg1 = avg2 = 0.0F;
	    int num = 0;
	    if (edge.nodeL.geneTime.size() == edge.nodeR.geneTime.size())
	    {
	      num = edge.nodeL.geneTime.size();
	      System.out.println("check " + num);
	    }
	    else
	    {
	      System.out.println("check");
	    }

	    for (int i = 0; i < num; i++) {
	      avg1 += ((Float)edge.nodeL.geneTime.get(i)).floatValue();
	      avg2 += ((Float)edge.nodeR.geneTime.get(i)).floatValue();
	    }
	    avg1 /= num;
	    avg2 /= num;
	    float o2;
	    float o1 = o2 = 0.0F;
	    for (int i = 0; i < num; i++) {
	      o1 += (((Float)edge.nodeL.geneTime.get(i)).floatValue() - avg1) * (((Float)edge.nodeL.geneTime.get(i)).floatValue() - avg1);
	      o2 += (((Float)edge.nodeR.geneTime.get(i)).floatValue() - avg2) * (((Float)edge.nodeR.geneTime.get(i)).floatValue() - avg2);
	    }
	    o1 = (float)Math.sqrt(o1 / (num - 1));
	    o2 = (float)Math.sqrt(o2 / (num - 1));
	    float pcc = 0.0F;
	    for (int i = 0; i < num; i++) {
	      pcc += (((Float)edge.nodeL.geneTime.get(i)).floatValue() - avg1) / o1 * ((((Float)edge.nodeR.geneTime.get(i)).floatValue() - avg2) / o2);
	    }
	    pcc /= (num - 1);
	    System.out.println(edge.nodeL.nodename + " " + edge.nodeR.nodename + " " + "相关系数" + pcc + "**");
	    return pcc;
	  }

}
