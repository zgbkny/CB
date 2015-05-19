package com.cb.strategy.impl;

import java.util.List;
import java.util.Map;

import com.cb.algorithms.DC;
import com.cb.algorithms.ECC;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class EccStrategy implements Strategy{
        
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
        
        public EccStrategy(int actionType ) {
                this.actionType = actionType;
                ecc = new ECC();
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
                                post(files.get(i));
                               
                        }
                        stat("");
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                        
                        break;
                case MERGE_SUBDIR_IN_PATH:
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
                        break;
                case MERGE_SUBDIR_IN_PATH:
                        break;
                default:
                        return;
                }
        }

        @Override
        public void core(String path) {
                // TODO Auto-generated method stub
                switch(actionType) {
                case SUBDIR_IN_PATH:
                        Map<String, List<String>> graph = GraphUtils.getGraph(path);
                        List<String> list = CommonUtils.getInputFile(path);
                        DC dc = new DC();
                        Map<String, Integer> dcMap = dc.calDC(list);
                        List<String> outList = ecc.calEcc(list, dcMap, graph);
                        
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                        break;
                case MERGE_SUBDIR_IN_PATH:
                        break;
                default:
                        return;
                }
        }

        @Override
        public void post(String path) {
                // TODO Auto-generated method stub
                switch(actionType) {
                case SUBDIR_IN_PATH:
                       
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                        break;
                case MERGE_SUBDIR_IN_PATH:
                        break;
                default:
                        return;
                }
        }

        @Override
        public void stat(String path) {
                // TODO Auto-generated method stub
                switch(actionType) {
                case SUBDIR_IN_PATH:
                       
                        break;
                case FILES_IN_PATH:
                        break;
                case FILE_PATH:
                        break;
                case MERGE_FILES_IN_PATH:
                        break;
                case MERGE_SUBDIR_IN_PATH:
                        break;
                default:
                        return;
                }
        }

}
