package com.cb;

import com.cb.service.StandardService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wei on 2015/12/16.
 */
public class AlfaCombine {

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

    public static void process(String filename1, String filename2, String outFilename, double alfa) {
        Map<String, Integer> f1IndexMap = CommonUtils.getIndexIdMap(filename1, 0);
        Map<String, Integer> f2IndexMap = CommonUtils.getIndexIdMap(filename2, 0);
        Map<String, Double> f1Map = CommonUtils.getKeyValueMap(filename1);
        Map<String, Double> f2Map = CommonUtils.getKeyValueMap(filename2);


        List<String> outList = new ArrayList<String>();
        int size = f1Map.size();
        for (String key : f1Map.keySet()) {
            if (!f2Map.containsKey(key)) continue;

			/*double value = 0;
			if (f1Map.get(key) * f1IndexMap.get(key) > f2Map.get(key) * f2IndexMap.get(key)) {
				value = f1Map.get(key) * 1.0 * (f1IndexMap.get(key) * 1.0 / size) +
						   f2Map.get(key) * 1.0 * (1 - f1IndexMap.get(key) * 1.0 / size);
			} else {
				value = f1Map.get(key) * 1.0 * (1 - f2IndexMap.get(key) * 1.0 / size) +
						   f2Map.get(key) * 1.0 * (f2IndexMap.get(key) * 1.0 / size);
			}*/
            double value = 0;
            value = (1 - alfa) * f1Map.get(key) + alfa * f2Map.get(key);


            outList.add(key + "	" + value);
        }
        CommonUtils.outputFile(outFilename.replaceAll("##", Double.toString(alfa)), outList);


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String filename1 = args[0];
        //String filename2 = args[1];
        //String outFilename = args[2];
        String filename1 = "E:\\金山网盘\\#共享#\\生物\\20151216实验\\data\\NC\\TS-PIN_NC.txt";
        String filename2 = "E:\\金山网盘\\#共享#\\生物\\ion实验\\orthology.txt";
        String outFilename = "E:\\金山网盘\\#共享#\\生物\\ion实验\\ion_alfa_combine_##.txt";
        StandardService.process(filename1, filename1);
        StandardService.process(filename2, filename2);
        for (double alfa = 0; alfa < 1.1; alfa += 0.1) {
            process(filename2, filename1, outFilename, alfa);
        }
        //new StatService().statFileByNum(outFilename);
    }
}
