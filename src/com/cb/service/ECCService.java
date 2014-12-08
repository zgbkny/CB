package com.cb.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cb.algorithms.DC;
import com.cb.algorithms.ECC;
import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class ECCService {
	private ECC ecc;
	public ECCService() {
		ecc = new ECC();
	}
	
	public void calEcc(String inpath, String outpath) {
		Map<String, List<String>> graph = GraphUtils.getGraph(inpath);
		List<String> list = CommonUtils.getInputFile(inpath);
		DC dc = new DC();
		Map<String, Integer> dcMap = dc.calDC(list);
		List<String> outList = ecc.calEcc(list, dcMap, graph);
		CommonUtils.outputFile(outpath, outList);
	}
	
	
}
