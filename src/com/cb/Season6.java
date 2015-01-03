package com.cb;

import com.cb.service.GroupService;

public class Season6 {
	/**
	 * 根据团来进行统计
	 * 
	 * 
	 */
	public void divideByGroupAndStat() {
		GroupService gs = new GroupService();
		//gs.calSidesDc("H:\\金山网盘\\data\\CB\\inpath");
		gs.statEss("H:\\金山网盘\\data\\CB\\inpath");
	}
	
	public void core() {
		divideByGroupAndStat();
	}
}
