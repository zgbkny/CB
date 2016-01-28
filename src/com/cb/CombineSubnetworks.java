package com.cb;

import com.cb.utils.CommonUtils;

import java.util.*;

/**
 * Created by wei on 2015/12/16.
 *
 * function：将多个不同时刻的子网合并为同一个网络
 */
public class CombineSubnetworks {

    public static void combine(String dirpath, String outpath, String split) {
        List<String> filepathes = CommonUtils.getFilesInPath(dirpath);
        Set<String> set = new HashSet<>();
        List<String> outList = new ArrayList<>();
        for (String filepath : filepathes) {
            List<String> list = CommonUtils.getInputFile(filepath);
            for (String item : list) {
                if (set.contains(item)) {
                    continue;
                }

                String []items = item.split(split);
                if (items.length != 2) {
                    System.out.println("error! there no 2 items after split with" + split);
                    break;
                }
                if (set.contains(items[1] + split + items[0])) {
                    continue;
                }
                outList.add(items[0] + "    " + items[1]);
                set.add(item);
                set.add(items[1] + split + items[0]);
            }
        }
        CommonUtils.outputFile(outpath, outList);
    }

    public static void CombineWithValue(String dirpath, String outpath, String split) {
        List<String> filepathes = CommonUtils.getFilesInPath(dirpath);
        Set<String> set = new HashSet<>();
        List<String> outList = new ArrayList<>();
        Map<String, List<Double>> map = new HashMap<String, List<Double>>();
        for (String filepath : filepathes) {
            List<String> list = CommonUtils.getInputFile(filepath);
            for (String item : list) {
                String items[] = item.split(split);
                Double value = Double.parseDouble(items[1]);
                if (map.containsKey(items[0])) {
                    map.get(items[0]).add(value);
                } else {
                    List<Double> tmpList = new ArrayList<>();
                    tmpList.add(value);
                    map.put(items[0], tmpList);
                }
            }
        }

        for (String key : map.keySet()) {
            List<Double> list = map.get(key);
            Double count = 0.0;
            for (Double value : list) {
                count += value;
            }
            double value = count * 1.0 / list.size();
            outList.add(key + " " + value);
        }
        CommonUtils.outputFile(outpath, outList);
    }

    public static void main(String []args) {
        String dirpath = "E:\\金山网盘\\#共享#\\生物\\20151217实验\\subnetworks-LAC";
        String outpath = "E:\\金山网盘\\#共享#\\生物\\20151217实验\\subnetworks-LAC_combine.txt";
        CombineWithValue(dirpath, outpath, "\t");
    }
}
