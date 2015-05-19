package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.DC;
import com.cb.algorithms.ECC;
import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class EccSumStrategy implements Strategy {

        // 将subdir中的文件单独求ecc，然后再合并
        public static final  int SUBDIR_IN_PATH = 1; 
        // 将path中的文件单独求ecc，然后再合并
        public static final int FILES_IN_PATH = 2;
        // 将path（path就是一个文件）求ecc
        public static final int FILE_PATH = 3;
        // 将path中的文件合并以后，求ecc
        public static final int MERGE_FILES_IN_PATH = 4;
        // 将subdir中的所有文件合并以后，求ecc
        public static final  int MERGE_SUBDIR_IN_PATH = 5;
        
        private int actionType;
        private ECC ecc;
        Map<String, Double> eccSumMap = null;
        List<String> outList = null;
        private String outpath;
        
        private Set<String> mergeData;
        private List<String> mergeList;
        
        public EccSumStrategy(String outpath, int actionType ) {
                this.actionType = actionType;
                ecc = new ECC();
                eccSumMap = new HashMap<String, Double>();
                outList = new ArrayList<String>();
                this.outpath = outpath;
                mergeData = new HashSet<String>();
                mergeList = new ArrayList<String>();
        }
        
        @Override
        public void action(String dirpath) {
                // TODO Auto-generated method stub
                List<String>  files = null;
                switch(actionType) {
                case SUBDIR_IN_PATH:
                        files  =  CommonUtils.getFilesInSubPath(dirpath);
                        for (int i = 0; i < files.size(); i++) {
                                pre(files.get(i));
                                core(files.get(i));
                                
                        }
                        post("");
                        stat(outpath);
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                        files  =  CommonUtils.getFilesInPath(dirpath);
                        for (int i = 0; i < files.size(); i++) {
                                pre(files.get(i));
                        }
                        core("");
                        post("");
                        stat(outpath);
                        break;
                case MERGE_SUBDIR_IN_PATH:
                        files  =  CommonUtils.getFilesInSubPath(dirpath);
                        for (int i = 0; i < files.size(); i++) {
                                pre(files.get(i));
                        }
                        core("");
                        post("");
                        stat(outpath);
                        break;
                default:
                        return;
                }
        }

        @Override
        public void pre(String path) {
                // TODO Auto-generated method stub
                switch(actionType) {
                case SUBDIR_IN_PATH:
                      
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                case MERGE_SUBDIR_IN_PATH:
                        List<String> list = CommonUtils.getInputFile(path);
                        for (String ss : list) {
                                if (!mergeData.contains(ss)) {
                                        mergeData.add(ss);
                                }
                        }
                        break;
                default:
                        return;
                }
        }

        @Override
        public void core(String path) {
                // TODO Auto-generated method stub
                Map<String, List<String>> graph ;
                List<String> list;
                DC dc = new DC();
                Map<String, Integer> dcMap;
                Map<String, Double> map;
                switch(actionType) {
                case SUBDIR_IN_PATH:
                        graph = GraphUtils.getGraph(path);
                        list = CommonUtils.getInputFile(path);
                        dcMap = dc.calDC(list);
                        List<String> outList = ecc.calEcc(list, dcMap, graph);
                         map = ecc.calSumEcc(outList);
                        for (String key : map.keySet()) {
                                if (eccSumMap.containsKey(key)) {
                                        eccSumMap.put(key, map.get(key) + eccSumMap.get(key));
                                } else {
                                        eccSumMap.put(key, map.get(key));
                                }
                        }
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                case MERGE_SUBDIR_IN_PATH:
                        for (String ss : mergeData) mergeList.add(ss);
                        CommonUtils.outputFile(outpath, mergeList);
                        //graph = GraphUtils.getGraphFromList(mergeList);
                        graph = GraphUtils.getGraph(outpath);
                        list = mergeList;
                        dc = new DC();
                        dcMap = dc.calDC(list);
                        List<String> tempList = ecc.calEcc(list, dcMap, graph);
                        eccSumMap = ecc.calSumEcc(tempList);
                        break;
                default:
                        return;
                }
        }

        @Override
        public void post(String path) {
                // TODO Auto-generated method stub
                List<Map.Entry<String, Double>> infoIds ;
                switch(actionType) {
                case SUBDIR_IN_PATH:
                        infoIds  = new ArrayList<Map.Entry<String, Double>>(
                                        eccSumMap.entrySet());

                        Collections.sort(infoIds,
                                        new Comparator<Map.Entry<String, Double>>() {
                                                public int compare(
                                                                Map.Entry<String, Double> o1,
                                                                Map.Entry<String, Double> o2) {
                                                        if (o2.getValue() > o1.getValue()) return 1;
                                                        else return 0;
                                                }
                                        });
                        for (Map.Entry<String, Double> e : infoIds) {
                                outList.add(e.getKey() + "  " + e.getValue());
                        }
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                case MERGE_SUBDIR_IN_PATH:
                    infoIds = new ArrayList<Map.Entry<String, Double>>(
                            eccSumMap.entrySet());

                        Collections.sort(infoIds,
                                        new Comparator<Map.Entry<String, Double>>() {
                                                public int compare(
                                                                Map.Entry<String, Double> o1,
                                                                Map.Entry<String, Double> o2) {
                                                        if (o2.getValue() > o1.getValue()) return 1;
                                                        else return 0;
                                                }
                                        });
                        for (Map.Entry<String, Double> e : infoIds) {
                                outList.add(e.getKey() + "  " + e.getValue());
                        }
                        break;
                default:
                        return;
                }
        }

        @Override
        public void stat(String path) {
                // TODO Auto-generated method stub
                StatService ss = null;
                System.out.println(outList.size());
                switch(actionType) {
                case SUBDIR_IN_PATH:
                        CommonUtils.outputFile(path, outList);
                        ss = new StatService();
                        ss.statFileByNum(path);
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                case MERGE_SUBDIR_IN_PATH:
                        CommonUtils.outputFile(path, outList);
                        ss = new StatService();
                        ss.statFileByNum(path);
                        break;
                default:
                        return;
                }
        }

        
}
