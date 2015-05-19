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

import com.cb.algorithms.DC;
import com.cb.algorithms.ECC;
import com.cb.entity.Edge;
import com.cb.entity.Node;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class ECCService {
        private ECC ecc;

        public ECCService() {
                ecc = new ECC();
        }

        public void calEcc(String inpath, String outpath) {
                Map<String, List<String>> graph = GraphUtils.getGraph(inpath);
                List<String> list = CommonUtils.getInputFile(inpath);
                DC dc = new DC();
                Map<String, Integer> dcMap = dc.calDC(list);
                List<String> outList = ecc.calEcc(list, dcMap, graph);
                CommonUtils.outputFile(outpath, outList);
        }

        public void calSumEcc(String inpath, String outpath, boolean normal) {
                List<String> list = CommonUtils.getInputFile(inpath);
                Map<String, Double> map = ecc.calSumEcc(list);
                List<String> outList = null;
                outList = Statistics.sortMap(map);
                if (normal) {
                        outList = Statistics.sortListAndNormal(outList, 1);
                }
                CommonUtils.outputFile(outpath, outList);
        }

        public static void calEccByLiMin(String inpath, String outpath) {
                List<String> outList = new ArrayList<String>();
                try {
                        AddNode(inpath);
                        Iterator it_edge = set_edge.iterator();
                        while (it_edge.hasNext()) {
                                Edge edge = (Edge) it_edge.next();
                                edge.ecc = ECC.Ecc(edge);
                                outList.add(edge.nodeL.nodename + "	"
                                                + edge.nodeR.nodename + "	"
                                                + edge.ecc);

                        }

                        CommonUtils.outputFile(outpath, outList);

                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public static HashSet<Node> set_node = new HashSet();
        public static HashSet<Edge> set_edge = new HashSet();

        public static void AddNode(String filestr1) throws IOException {
                int flag = 0;
                BufferedReader br = new BufferedReader(new FileReader(filestr1));
                String str = null;
                String str1 = null;
                String str2 = null;
                while ((str = br.readLine()) != null) {
                        Scanner scan = new Scanner(str);
                        str1 = scan.next();
                        str2 = scan.next();
                        System.out.println(str1);
                        System.out.println(str2);
                        if (flag == 0) {
                                Node node1 = new Node(str1, 1);
                                Node node2 = new Node(str2, 1);
                                node1.nodelist.add(node2);

                                node2.nodelist.add(node1);

                                set_node.add(node1);
                                set_node.add(node2);
                                // System.out.println("加入的节点" + node1.nodename);
                                // System.out.println("加入的节点" + node2.nodename);

                                Edge edge = new Edge(node1, node2);
                                set_edge.add(edge);
                                node1.edgelist.add(edge);
                                node2.edgelist.add(edge);
                                flag = 1;
                                // System.out.println("链表为空，加入节点");
                        } else {
                                Node nodeemp1 = null;
                                Node node11 = null;
                                Node node22 = null;
                                Iterator it_node = set_node.iterator();
                                while (it_node.hasNext()) {
                                        nodeemp1 = (Node) it_node.next();
                                        if (str1.equals(nodeemp1.nodename)) {
                                                node11 = nodeemp1;
                                        } else {
                                                if (!str2.equals(nodeemp1.nodename))
                                                        continue;
                                                node22 = nodeemp1;
                                        }
                                }
                                if (node11 == null) {
                                        node11 = new Node(str1, 1);
                                        set_node.add(node11);
                                }
                                if (node22 == null) {
                                        node22 = new Node(str2, 1);
                                        set_node.add(node22);
                                }
                                node11.nodelist.add(node22);
                                node22.nodelist.add(node11);
                                Edge edge = new Edge(node11, node22);

                                set_edge.add(edge);
                                node11.edgelist.add(edge);
                                node22.edgelist.add(edge);
                                // System.out.println("链表不空，判断");
                        }
                }
        }

}
