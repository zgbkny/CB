package com.cb;

import com.cb.service.StandardService;
import com.cb.service.StatService;
import com.cb.utils.CommonUtils;

import java.util.List;

/**
 * Created by wei on 2015/12/16.
 */
public class StatAll {
    public static void main(String []args) {
        String dirpath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\result";
        List<String> list = CommonUtils.getFilesInPath(dirpath);
        for (String filepath : list) {
            StandardService.process(filepath, filepath);
            new StatService().statFileByNum(filepath);
        }
    }
}
