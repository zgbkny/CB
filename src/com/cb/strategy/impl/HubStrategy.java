package com.cb.strategy.impl;

import java.util.List;

import com.cb.service.DCService;
import com.cb.service.HubService;
import com.cb.service.PCCService;
import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;


public class HubStrategy implements Strategy {

	//private Logger log = Logger.getLogger(HubStrategy.class);
	
	public HubService hubs = new HubService();
	public DCService dcs = new DCService();
	
	// get hub by dc
	protected void calHub(String inpath, String outpath, int percent) {
		dcs.calDC(inpath, inpath.replace(".txt", "_dc.txt"));
		hubs.calHub(inpath.replace(".txt", "_dc.txt"), outpath, 50);
	}
	
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

	}

	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		calHub(path, path.replaceAll(".txt", "_hub.txt"), 50);
		PCCService.calPcc(path, "result36.txt", path.replaceAll(".txt", "_pcc.txt"));
		HubService.calHubValue(path.replaceAll(".txt", "_hub.txt"), path.replaceAll(".txt", "_hubval.txt"), path.replaceAll(".txt", "_pcc.txt"));
	}
	
	@Override
	public void post(String path) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub
		
	}

	
}
