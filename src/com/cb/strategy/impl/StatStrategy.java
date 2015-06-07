package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

public class StatStrategy implements Strategy {

    @Override
    public void action(String dirpath) {
        // TODO Auto-generated method stub
        List<String> list = CommonUtils.getFilesInPath(dirpath);
        for (String path : list) {
            pre(path);
            core(path);
            post(path);
            stat(path);
        }
    }

    @Override
    public void pre(String path) {
        // TODO Auto-generated method stub
    	List<String> list = CommonUtils.getInputFile(path);
    	List<String> outList = new ArrayList<String>();
    	for (String item : list) {
    		String ss[] = item.split("	");
    		double value = Double.parseDouble(ss[1]);
    		if (value < 0) {
    			value *= -1;
    		}
    		outList.add(ss[0] + "	" + value);
    	}
    	CommonUtils.outputFile(path, outList);
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
        StatService ss = new StatService();
        ss.statFileByNum(path);
    }

}
