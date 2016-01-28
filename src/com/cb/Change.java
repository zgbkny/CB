package com.cb;

import com.cb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2016/1/6.
 */
public class Change {
    public static void main(String []args) {
        String filepath = "E:\\金山网盘\\项目\\503\\文档\\data.txt";
        List<String> list = CommonUtils.getInputFile(filepath);
        List<String> outList = new ArrayList<String>();
        for (String item : list) {
            String []items = item.split("\t");
            String out = "";
            for (String subitem : items) {
                double value = Double.parseDouble(subitem);
                value = value * 1.2;
                out += "    " + String.format("%.2f", value);
            }
            outList.add(out);
        }
        CommonUtils.outputFile(filepath, outList);
    }
}
