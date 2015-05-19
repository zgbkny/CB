package com.cb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.entity.Complex;
import com.cb.service.DCService;
import com.cb.service.ECCService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

public class Season9 {

        public static void pre() {

                String dir = "H:\\金山网盘\\data\\CB\\数据集";
                List<String> list = CommonUtils.getFilesInPath(dir);
                ECCService eccs = new ECCService();
                DCService dcs = new DCService();
                for (String filepath : list) {
                        eccs.calEcc(filepath,
                                        filepath.replaceAll(".txt", "_ecc.txt")
                                                        .replaceAll("数据集",
                                                                        "不同数据集的权值\\\\ecc"));
                }
        }

        public static void stat() {
                String dir = "H:\\金山网盘\\data\\CB\\不同数据集的权值\\uc";

                new StatService().statFileByPercent(dir);

        }

        public static void core() {

                Map<String, Complex> map = new HashMap<String, Complex>();

                // 统计每个蛋白质出现的次数
                Map<String, Integer> countMap = new HashMap<String, Integer>();

                int max = Integer.MIN_VALUE;

                // 任意个复合元素进行合并
                String path = "H:\\金山网盘\\data\\CB\\复合物数据集";
                List<String> files = CommonUtils.getFilesInPath(path);
                for (String file : files) {
                        FileReader ins;
                        System.out.println(file);
                        try {
                                ins = new FileReader(file);
                                BufferedReader readBuf = new BufferedReader(ins);
                                String buf = null;
                                int index = 0;
                                boolean flag = false;
                                Complex c = null;
                                while ((buf = readBuf.readLine()) != null) {

                                        if (flag) {
                                                buf = buf.toUpperCase();
                                                if (countMap.containsKey(buf)) {

                                                        countMap.put(buf,
                                                                        countMap.get(buf) + 1);
                                                        if (countMap.get(buf) + 1 > max) {
                                                                max = countMap.get(buf) + 1;
                                                        }
                                                } else {
                                                        countMap.put(buf, 1);
                                                        if (1 > max) {
                                                                max = 1;
                                                        }
                                                }
                                                // System.out.println(buf);
                                                if (buf.equals("YHR114w")) {
                                                        System.out.println("---------"
                                                                        + buf);
                                                }

                                                index--;
                                                c.proteins.add(buf);
                                                if (index == 0) {
                                                        flag = false;
                                                        if (!map.containsKey(c
                                                                        .getKey())) {
                                                                map.put(c.toString(),
                                                                                c);
                                                        } else {
                                                                System.out.println("error");
                                                        }
                                                }
                                        } else {
                                                // System.out.println(buf);
                                                String[] s = buf.split("	");

                                                if (s.length == 1) {
                                                        s = buf.split("    ");
                                                }

                                                if (s.length > 1) {

                                                        int i = Integer.parseInt(s[s.length - 1]);

                                                        if (i > 0) {
                                                                c = new Complex();
                                                                c.num = i;
                                                                index = i;
                                                                flag = true;
                                                        }

                                                } else {
                                                        System.out.println("error");
                                                }
                                        }
                                }
                        } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                        System.out.println(map.size());
                        System.out.println(countMap.containsKey("YHR114W"));

                }
                files = null;
                files = CommonUtils
                                .getFilesInPath("H:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc");

                for (String filepath : files) {
                        List<String> eccData = CommonUtils
                                        .getInputFile(filepath);
                        Map<String, Double> eccMap = new HashMap<String, Double>();
                        Map<String, List<String>> netMap = new HashMap<String, List<String>>();
                        for (String data : eccData) {
                                // System.out.println(data);
                                data = data.toUpperCase();
                                String[] strs = data.split("	");
                                double value = Double.parseDouble(strs[2]);
                                eccMap.put(strs[0] + strs[1], value);
                                eccMap.put(strs[1] + strs[0], value);

                                if (netMap.containsKey(strs[0])) {
                                        netMap.get(strs[0]).add(strs[1]);
                                } else {
                                        List<String> temp = new ArrayList<String>();
                                        temp.add(strs[1]);
                                        netMap.put(strs[0], temp);
                                }
                                if (netMap.containsKey(strs[1])) {
                                        netMap.get(strs[1]).add(strs[0]);
                                } else {
                                        List<String> temp = new ArrayList<String>();
                                        temp.add(strs[0]);
                                        netMap.put(strs[1], temp);
                                }
                        }
                        List<String> outList = new ArrayList<String>();

                        for (Map.Entry<String, List<String>> entry : netMap
                                        .entrySet()) {

                                double sum = 0;
                                for (int i = 0; i < entry.getValue().size(); i++) {
                                        double value = 0;
                                        String key = entry.getKey()
                                                        + entry.getValue().get(
                                                                        i);
                                        System.out.println(entry.getValue()
                                                        .get(i));
                                        if (countMap.containsKey(entry
                                                        .getValue().get(i))) {
                                                value = (countMap.get(entry
                                                                .getValue()
                                                                .get(i)) + 1)
                                                                * 1.0
                                                                / (max + 1);
                                        } else {
                                                value = 1.0 / (max + 1);
                                        }

                                        System.out.println(value + " ;max:"
                                                        + max);
                                        value *= eccMap.get(key);
                                        sum += value;
                                }
                                outList.add(entry.getKey() + "	" + sum);
                        }
                        System.out.println(outList.size());
                        CommonUtils.outputFile(
                                        filepath.replaceAll("ecc", "uc"),
                                        outList);
                }
        }

        public static void eccSum() {
                // TODO Auto-generated method stub
                List<String> list = CommonUtils
                                .getFilesInPath("H:\\金山网盘\\data\\CB\\inpath");
                ECCService eccs = new ECCService();
                for (String path : list) {
                        // action(path, 50);
                        // HubService.divAndStat(path, path.replaceAll(".txt",
                        // "_big.txt"), path.replaceAll(".txt", "_small.txt"),
                        // 0.5);
                        eccs.calSumEcc(path,
                                        path.replaceAll(".txt", "_sum.txt"),
                                        true);
                }
        }
}
