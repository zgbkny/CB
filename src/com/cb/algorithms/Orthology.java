package com.cb.algorithms;

import com.cb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wei on 2016/1/27.
 */
public class Orthology {



    public static Map<String, Double> calOrthology(List<String> list) {
        List<String> outList = new ArrayList<>();
        Map<String, Double> map = new HashMap<String, Double>();
        double max = -1;
        for (String item : list) {
            String []items = item.split("\t");
            if (items.length == 4) {
                Double value = Double.parseDouble(items[3]);
                map.put(items[1], value);
                if (value > max) max = value;
            }
        }

        for (String key : map.keySet()) {
            double value = map.get(key) / max;
            map.put(key, value);
        }
        return map;
    }

    public static void calFilesOrthology(String dirpath, String datapath) {
        List<String> filepathes = CommonUtils.getFilesInPath(dirpath);
        Map<String, Double> map = calOrthology(CommonUtils.getInputFile(datapath));
        for (String filepath : filepathes) {
            List<String> list = CommonUtils.getInputFileJustKey(filepath);
            List<String> outList = new ArrayList<>();

            for (String item : list) {
                outList.add(item + "\t" + map.get(item));
            }
            CommonUtils.outputFile(filepath.replace("_dc.txt", "_ty.txt"), outList);
        }
    }

    public static void main(String []args) {
        String filepath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\protein_allorthology.txt";
        String outpath = "E:\\金山网盘\\#共享#\\生物\\ion实验\\orthology.txt";
        //calOrthology(filepath, outpath);

        String dirpath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\dc";
        calFilesOrthology(dirpath, filepath);
    }
}
