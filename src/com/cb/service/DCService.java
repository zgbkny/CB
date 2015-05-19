package com.cb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class DCService {
        private DC dc;

        public DCService() {
                dc = new DC();
        }

        public void calDCs(String path) {
                List<String> list = CommonUtils.getFilesInPath(path);
                for (String inpath : list) {
                        calDC(inpath, inpath.replaceAll(".txt", "_dc.txt"));
                }
        }

        public void calDC(String inpath, String outpath) {
                List<String> list = CommonUtils.getInputFile(inpath);
                List<String> outList = new ArrayList<String>();
                Map<String, Integer> map = dc.calDC(list);
                Set<String> essSet = EssUtils.getEssentialSet();

                List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(
                                map.entrySet());

                Collections.sort(infoIds,
                                new Comparator<Map.Entry<String, Integer>>() {
                                        public int compare(
                                                        Map.Entry<String, Integer> o1,
                                                        Map.Entry<String, Integer> o2) {
                                                return (o2.getValue() - o1
                                                                .getValue());
                                        }
                                });
                int i = 0;
                for (Map.Entry<String, Integer> e : infoIds) {
                        System.out.println(e.getKey() + "::::" + e.getValue());
                        if (essSet.contains(e.getKey())) {
                                System.out.println("check 1");
                                outList.add(e.getKey() + "	" + e.getValue()
                                                + "	" + "1");
                        } else {
                                System.out.println("check 0");
                                outList.add(e.getKey() + "	" + e.getValue()
                                                + "	" + "0");
                        }
                }

                CommonUtils.outputFile(outpath, outList);
        }

}
