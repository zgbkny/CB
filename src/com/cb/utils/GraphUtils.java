package com.cb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
