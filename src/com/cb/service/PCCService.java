package com.cb.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.cb.algorithms.ECC;
import com.cb.algorithms.PCC;
import com.cb.entity.Edge;
import com.cb.entity.Node;
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
	
	public static void calPccsByLiMin(String inpath, String genedata) {
		List<String> list = CommonUtils.getFilesInPath(inpath);
		for (String path : list) {
			set_node.removeAll(set_node);
			set_edge.removeAll(set_edge);
			calPccByLiMin(path, genedata, path.replaceAll(".txt", "_pcc.txt"));
		}
	}
	
	public static void calPccByLiMin(String inpath, String genedata, String outpath) {
		List<String> outList = new ArrayList<String>();
		try {
			set_node.removeAll(set_node);
			set_edge.removeAll(set_edge);
			AddNode(inpath);
			readExpression(genedata);
			Iterator it_edge = set_edge.iterator();
		    while (it_edge.hasNext())
		    {
		      Edge edge = (Edge)it_edge.next();
		      edge.pcc = PCC.Pcc(edge);
		      outList.add(edge.nodeL.nodename + "	" + edge.nodeR.nodename + "	" + edge.pcc);
		    
		    }
		    
		    CommonUtils.outputFile(outpath, outList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readExpression(String filestr)
		    throws IOException
		  {
		    BufferedReader br = new BufferedReader(new FileReader(filestr));
		    String str = null;

		    System.out.println("长度 " + set_node.size());
		    while ((str = br.readLine()) != null)
		    {
		      Scanner scan = new Scanner(str);
		      String str1 = scan.next();
		      String str2 = scan.next();
		      Iterator it_node = set_node.iterator();
		      while (it_node.hasNext())
		      {
		        Node nodeemp = (Node)it_node.next();
		        if (!str2.equalsIgnoreCase(nodeemp.nodename))
		          continue;
		        while (scan.hasNext())
		        {
		          nodeemp.geneTime.add(Float.valueOf(scan.nextFloat()));
		          //System.out.println("加入" + nodeemp.geneTime);
		        }
		        break;
		      }

		    }

		    br.close();

		    System.out.print("哈哈哈哈哈哈哈哈哈哈哈");
		  }

	
	public static HashSet<Node> set_node = new HashSet();
	public static HashSet<Edge> set_edge = new HashSet();
	public static void AddNode(String filestr1)
		    throws IOException
		  {
		    int flag = 0;
		    BufferedReader br = new BufferedReader(new FileReader(filestr1));
		    String str = null;
		    String str1 = null;
		    String str2 = null;
		    while ((str = br.readLine()) != null)
		    {
		      Scanner scan = new Scanner(str);
		      str1 = scan.next();
		      str2 = scan.next();
		      System.out.println(str1);
		      System.out.println(str2);
		      if (flag == 0)
		      {
		        Node node1 = new Node(str1, 1);
		        Node node2 = new Node(str2, 1);
		        node1.nodelist.add(node2);

		        node2.nodelist.add(node1);

		        set_node.add(node1);
		        set_node.add(node2);
		        //System.out.println("加入的节点" + node1.nodename);
		        //System.out.println("加入的节点" + node2.nodename);

		        Edge edge = new Edge(node1, node2);
		        set_edge.add(edge);
		        node1.edgelist.add(edge);
		        node2.edgelist.add(edge);
		        flag = 1;
		        //System.out.println("链表为空，加入节点");
		      }
		      else
		      {
		        Node nodeemp1 = null;
		        Node node11 = null;
		        Node node22 = null;
		        Iterator it_node = set_node.iterator();
		        while (it_node.hasNext())
		        {
		          nodeemp1 = (Node)it_node.next();
		          if (str1.equals(nodeemp1.nodename))
		          {
		            node11 = nodeemp1;
		          } else {
		            if (!str2.equals(nodeemp1.nodename))
		              continue;
		            node22 = nodeemp1;
		          }
		        }
		        if (node11 == null)
		        {
		          node11 = new Node(str1, 1);
		          set_node.add(node11);
		        }
		        if (node22 == null)
		        {
		          node22 = new Node(str2, 1);
		          set_node.add(node22);
		        }
		        node11.nodelist.add(node22);
		        node22.nodelist.add(node11);
		        Edge edge = new Edge(node11, node22);

		        set_edge.add(edge);
		        node11.edgelist.add(edge);
		        node22.edgelist.add(edge);
		        //System.out.println("链表不空，判断");
		      }
		    }
		  }
}
