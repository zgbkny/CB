package com.cb.algorithms;

import com.cb.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NC: ECC sum
 * @author wei
 *
 */
public class NC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inFilePath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\result\\TS-PIN_ecc.txt";
		String outFilePath = "E:\\金山网盘\\#共享#\\生物\\20160128要做的\\data\\result\\TS-PIN_nc.txt";
    	List<String> outList = new ArrayList<String>();
		List<String> list = CommonUtils.getInputFile(inFilePath);
    	Map<String, Double> map = new ECC().calSumEcc(list);
    	for (String item : map.keySet()) {
    		outList.add(item + "	" + map.get(item));
     	}
    	CommonUtils.outputFile(outFilePath, outList);
	}

}
