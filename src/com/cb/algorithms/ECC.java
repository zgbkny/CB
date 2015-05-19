package com.cb.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cb.entity.*;

public class ECC {
        /**
         * 功能：求各个点的ecc和
         * 
         * @param list
         *                List<String(点 点 ecc)>
         * @return
         */
        public Map<String, Double> calSumEcc(List<String> list) {
                Map<String, Double> map = new HashMap<String, Double>();

                for (String item : list) {
                        String[] items = item.split("	");
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

        /**
         * 
         * @param list
         *                :网络中的边
         * @param dcMap
         *                :网络中每个节点的度数
         * @param graph
         *                :整个图的邻接表
         */
        public List<String> calEcc(List<String> list,
                        Map<String, Integer> dcMap,
                        Map<String, List<String>> graph) {
                List<String> outList = new ArrayList<String>();
                Map<String, Double> map = new HashMap<String, Double>();

                for (String str : list) {
                        // System.out.println(str);
                        String[] strs = str.split("	");
                        int num1 = get(graph, strs[0], strs[1]);
                        int num2 = dcMap.get(strs[0]) < dcMap.get(strs[1]) ? dcMap
                                        .get(strs[0]) : dcMap.get(strs[1]);
                        if (num2 == 1) {
                                num2++;
                                // num1++;
                        }
                        // System.out.println(num1 + " " + num2);
                        double ret = num1 * 1.0 / (num2 - 1);

                        // double ret = num1 / (num2 - 1);
                        map.put(str, ret);
                }
                List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
                                map.entrySet());
                Collections.sort(infoIds,
                                new Comparator<Map.Entry<String, Double>>() {
                                        public int compare(
                                                        Map.Entry<String, Double> o1,
                                                        Map.Entry<String, Double> o2) {
                                                return (int) ((o2.getValue() - o1
                                                                .getValue()) * 100000000);
                                                // return
                                                // (o1.getKey()).toString().compareTo(o2.getKey());
                                        }
                                });

                for (Map.Entry<String, Double> e : infoIds) {
                        outList.add(e.getKey() + "	" + e.getValue());
                }
                return outList;
        }

        int get(Map<String, List<String>> map, String item1, String item2) {
                int num = 0;
                List<String> list = map.get(item1);
                for (String item : list) {
                        if (item.equals(item2))
                                continue;
                        List<String> subList = map.get(item);
                        // System.out.println("item: " + item);
                        for (String subItem : subList) {
                                // System.out.println("subitem: " + subItem);
                                if (subItem.equals(item2)) {
                                        // System.out.println("match: " +
                                        // subItem);
                                        num++;
                                }
                        }
                }
                return num;
        }

        public static float Ecc(Edge edge) {
                int num = 0;
                float xishuemp = 0.0F;
                ArrayList list_xishu = new ArrayList();
                ArrayList list_xishu2 = new ArrayList();
                list_xishu.addAll(edge.nodeL.nodelist);
                list_xishu2.addAll(edge.nodeR.nodelist);
                list_xishu.retainAll(list_xishu2);
                for (int i = 0; i < list_xishu.size(); i++) {
                        if (((Node) list_xishu.get(i)).essential != 1)
                                continue;
                        num++;
                }

                num++;
                num = list_xishu.size() + 1;
                System.out.println("共同的节点" + num);
                if (edge.nodeL.nodelist.size() >= edge.nodeR.nodelist.size()) {
                        xishuemp = (float) (num * 1.0 / edge.nodeR.nodelist
                                        .size());
                        System.out.println(edge.nodeL.nodename + " "
                                        + edge.nodeR.nodename + " " + "边的聚集系数"
                                        + xishuemp + "**");
                        return xishuemp;
                }

                xishuemp = (float) (num * 1.0 / edge.nodeL.nodelist.size());
                System.out.println(edge.nodeL.nodename + " "
                                + edge.nodeR.nodename + " " + "边的聚集系数"
                                + xishuemp + "**");
                return xishuemp;
        }
}
