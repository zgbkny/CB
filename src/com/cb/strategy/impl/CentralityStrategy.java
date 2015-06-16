package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

public class CentralityStrategy implements Strategy {

    List<String> getOutFileNames(String path) {
        List<String> list = new ArrayList<String>();
        String []name = path.split(".txt");
        String []strs = name[0].split(" ");
        String []items = strs[1].split("_");
        for (String str : items) {
            list.add(strs[0] + "_" + str + ".txt");
        }
        return list;
    }
    @Override
    public void action(String dirpath) {
        // TODO Auto-generated method stub
        List<String> files = CommonUtils.getFilesInPath(dirpath);
        for (String path : files) {
            pre(path);
            core(path);
            post(path);
            stat(path);
        }
    }

    @Override
    public void pre(String path) {
        // TODO Auto-generated method stub
        List<String> outPaths = getOutFileNames(path);
        Set<String> essSet = EssUtils.getEssentialSet();
        for (int i = 0; i < outPaths.size(); i++) {
            List<String> datas = CommonUtils.getInputFileByIndex(path, i + 1);
            Map<String, Double> map = new HashMap<String, Double>();
            for (String item : datas) {
            	String []strs = item.split("   ");
            	map.put(strs[0], Double.parseDouble(strs[1]));
            }
            List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(
    		        map.entrySet());
    		Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
    			public int compare(Map.Entry<String, Double> o1,
    			        Map.Entry<String, Double> o2) {
    				return (int) ((o2.getValue() - o1.getValue()) * 100000000);
    				// return (o1.getKey()).toString().compareTo(o2.getKey());
    			}
    		});
    		List<String> outList = new ArrayList<String>();
    		for (int k = 0; k < infoIds.size(); k++) {
    		
    			outList.add(infoIds.get(k).getKey() + "	" + infoIds.get(k).getValue() + "	" + essSet.contains(infoIds.get(k).getKey()));
    		}
            CommonUtils.outputFile(outPaths.get(i), outList);
        }
    }

    @Override
    public void core(String path) {
        // TODO Auto-generated method stub

    }

    @Override
    public void post(String path) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stat(String path) {
        // TODO Auto-generated method stub
        List<String> outPaths = getOutFileNames(path);
        for (int i = 0; i < outPaths.size(); i++) {
            StatService ss = new StatService();
            //ss.statFileByNum(outPaths.get(i));
        }
    }

}
