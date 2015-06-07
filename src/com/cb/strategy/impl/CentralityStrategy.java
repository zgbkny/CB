package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.cb.service.StatService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;

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
        for (int i = 0; i < outPaths.size(); i++) {
            List<String> datas = CommonUtils.getInputFileByIndex(path, i + 1);
            CommonUtils.outputFile(outPaths.get(i), datas);
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
            ss.statFileByNum(outPaths.get(i));
        }
    }

}
