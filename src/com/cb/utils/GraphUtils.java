package com.cb.utils;

import com.cb.entity.Edge;
import com.cb.entity.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphUtils {

    public static Map<String, List<String>> getGraphFromList(
            List<String> dataList) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (int i = 0; i < dataList.size(); i++) {
            String item = dataList.get(i);
            int k = item.charAt(7);
            System.out.println(item);
            System.out.println(i);
            System.out.println(item.charAt(7) == k);
            System.out.println(item.split("   ").length);
            String[] strs = item.split("    ");
            if (map.get(strs[0]) == null) {
                List<String> list = new ArrayList<String>();
                list.add(strs[1]);
                map.put(strs[0], list);
            } else {
                List<String> list = map.get(strs[0]);
                list.add(strs[1]);
                map.put(strs[0], list);
            }
            if (map.get(strs[1]) == null) {
                List<String> list = new ArrayList<String>();
                list.add(strs[0]);
                map.put(strs[1], list);
            } else {
                List<String> list = map.get(strs[1]);
                list.add(strs[0]);
                map.put(strs[1], list);
            }
        }

        return map;
    }

    public static Map<String, List<String>> getGraph(String filepath) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        try {
            FileReader ins = new FileReader(filepath);
            BufferedReader readBuf = new BufferedReader(ins);
            String buf = null;
            while ((buf = readBuf.readLine()) != null) {
                String[] strs = buf.split("	");
                if (map.get(strs[0]) == null) {
                    List<String> list = new ArrayList<String>();
                    list.add(strs[1]);
                    map.put(strs[0], list);
                } else {
                    List<String> list = map.get(strs[0]);
                    list.add(strs[1]);
                    map.put(strs[0], list);
                }
                if (map.get(strs[1]) == null) {
                    List<String> list = new ArrayList<String>();
                    list.add(strs[0]);
                    map.put(strs[1], list);
                } else {
                    List<String> list = map.get(strs[1]);
                    list.add(strs[0]);
                    map.put(strs[1], list);
                }
                // System.out.println(strs[0]+map.get(strs[0]));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Node> getStaticGraph(String filepath) {
        Map<String, Node> map = new HashMap<String, Node>();
        try {
            FileReader ins = new FileReader(filepath);
            BufferedReader readBuf = new BufferedReader(ins);
            String buf = null;
            while ((buf = readBuf.readLine()) != null) {
                String[] strs = buf.split("\t");
                if (strs.length != 2) {
                    System.out.println("error!");
                    break;
                }
                Node node1 = null;
                Node node2 = null;
                if (!map.containsKey(strs[0])) {
                    node1 = new Node(strs[0]);
                    map.put(strs[0], node1);
                } else {
                    node1 = map.get(strs[0]);
                }
                if (node1.neighbors == null) {
                    node1.neighbors = new ArrayList<Edge>();
                }

                if (!map.containsKey(strs[1])) {
                    node2 = new Node(strs[1]);
                    map.put(strs[1], node2);
                } else {
                    node2 = map.get(strs[1]);
                }
                if (node2.neighbors == null) {
                    node2.neighbors = new ArrayList<Edge>();
                }
                Edge edge1 = new Edge(node1, node2, 1);
                Edge edge2 = new Edge(node2, node1, 1);
                node1.neighbors.add(edge1);
                node2.neighbors.add(edge2);
            }
        } catch (IOException e) {
            map = null;
        }
        if (map.size() == 0) map = null;
        return map;
    }

    public static void getAllMinPath(Map<String, Node> map) {
        for (String item : map.keySet()) {
            Dijkstra(map, item);
        }
    }

    public static void Dijkstra(Map<String, Node> map, String item) {
        int degree = 1;
        Set<String> visited = new HashSet<String>();
        List<String> nowNodes = new ArrayList<String>();
        List<String> nextNodes = null;
        if (map.get(item).minPathes != null) return;
        map.get(item).minPathes = new ArrayList<Edge>();
        List<Edge> minPathes = map.get(item).minPathes;
        Node node  = map.get(item);
        for (Edge edge : map.get(item).neighbors) {
            nowNodes.add(edge.nodeR.nodename);
        }

        visited.add(item);
        while (nowNodes != null && nowNodes.size() > 0) {
            nextNodes = new ArrayList<String>();
            for (String nodename : nowNodes) {
                if (visited.contains(nodename)) continue;
                else visited.add(nodename);
                Node tmpNode = map.get(nodename);
                Edge pathEdge = new Edge(node, tmpNode, degree);
                node.minPathes.add(pathEdge);
                for (Edge nextEdge : tmpNode.neighbors) {
                    if (visited.contains(nextEdge.nodeR.nodename)) continue;
                    nextNodes.add(nextEdge.nodeR.nodename);
                }
            }
            nowNodes = nextNodes;
            nextNodes = null;
            degree++;
        }
    }
}
