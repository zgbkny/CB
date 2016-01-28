package com.cb;

import com.cb.service.StandardService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wei on 2015/12/16.
 */
public class RankCombineAll {

    public static void to1(Map<String, Double> mp) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (String key : mp.keySet()) {
            if (mp.get(key) > max) {
                max = mp.get(key);
            }
            if (mp.get(key) < min) {
                min = mp.get(key);
            }
        }

        for (String key : mp.keySet()) {
            double value = (mp.get(key) - min) / (max - min);
            mp.put(key, value);
        }
    }

    public static void process(String filename1, String filename2, String outFilename) {
        Map<String, Integer> f1IndexMap = CommonUtils.getIndexIdMap(filename1, 0);
        Map<String, Integer> f2IndexMap = CommonUtils.getIndexIdMap(filename2, 0);
        Map<String, Double> f1Map = CommonUtils.getKeyValueMap(filename1);
        Map<String, Double> f2Map = CommonUtils.getKeyValueMap(filename2);


        List<String> outList = new ArrayList<String>();
        int size = f1Map.size();
        for (String key : f1Map.keySet()) {
            if (!f2Map.containsKey(key)) continue;

            double value = 0;
            if (f1Map.get(key) * f1IndexMap.get(key) > f2Map.get(key) * f2IndexMap.get(key)) {
                value = f1Map.get(key) * 1.0 * (f1IndexMap.get(key) * 1.0 / size) +
                        f2Map.get(key) * 1.0 * (1 - f1IndexMap.get(key) * 1.0 / size);
            } else {
                value = f1Map.get(key) * 1.0 * (1 - f2IndexMap.get(key) * 1.0 / size) +
                        f2Map.get(key) * 1.0 * (f2IndexMap.get(key) * 1.0 / size);
            }/*
            double alfa = 0.1;
            double value = 0;
            value = alfa * f1Map.get(key) + (1 - alfa)* f2Map.get(key);*/


            outList.add(key + "	" + value);
        }
        CommonUtils.outputFile(outFilename, outList);


    }

    public static String generateOutFileName(String name1, String name2) {
        String []items1 = name1.split("_");
        String []items2 = name2.split("_");
        if (items1.length != 2 && items2.length != 2) {
            System.out.println("error!!");
            return "";
        }

        String newName = name1.replaceAll(".txt", "_" + items2[1]).replaceAll("data", "out");
        return newName;
    }

    public static void processDirs(String dirpath1, String dirpath2) {
        List<String> dirpathes1 = CommonUtils.getFilesInPath(dirpath1);
        List<String> dirpathes2 = CommonUtils.getFilesInPath(dirpath2);
        for (int i = 0; i < dirpathes1.size(); i++) {
            String filename1 = dirpathes1.get(i);
            String filename2 = dirpathes2.get(i);
            String outFilename = generateOutFileName(filename1, filename2);
            StandardService.process(filename1, filename1);
            StandardService.process(filename2, filename2);
            process(filename2, filename1, outFilename);
            new StatService().statFileByNum(filename1);
            new StatService().statFileByNum(filename2);
            new StatService().statFileByNum(outFilename);
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    public static void main(String []args) {
        String dirpath = "E:\\金山网盘\\#共享#\\生物\\20151216实验\\data";
        List<String> dirpathes = CommonUtils.getSubDirInPath(dirpath);
        for (int i = 0; i < dirpathes.size(); i++) {
            for (int j = i + 1; j < dirpathes.size(); j++) {
                processDirs(dirpathes.get(i), dirpathes.get(j));
            }
        }
    }
}
