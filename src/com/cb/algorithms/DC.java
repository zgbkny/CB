package com.cb.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DC {
        /**
         * 输入：List<String(边)> 输出：map，表示各个节点的度数 功能：求网络中各个节点的度数
         */
        public Map<String, Integer> calDC(List<String> list) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                for (String str : list) {
                        String[] strs = str.split("	");
                        if (map.get(strs[0]) == null) {
                                map.put(strs[0], 1);
                        } else {
                                map.put(strs[0], map.get(strs[0]) + 1);
                        }
                        if (map.get(strs[1]) == null) {
                                map.put(strs[1], 1);
                        } else {
                                map.put(strs[1], map.get(strs[1]) + 1);
                        }
                        // System.out.println(strs[0]+map.get(strs[0]));
                }

                return map;
        }
}
