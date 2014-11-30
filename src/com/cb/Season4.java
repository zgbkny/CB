package com.cb;

import com.cb.global.Global;
import com.cb.utils.CommonUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.HubUtils;
import com.cb.utils.PCCUtils;

public class Season4 {
	public static final String nucleusppihub = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\hub\\hub_Nucleus_PPI.txt";
	public static final String nucleusppipcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_Nucleus_PPI.txt";
	public static final String nucleusppiavgpcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\avgpcc\\avgpcc_Nucleus_PPI.txt";
	public static final String nucleusppihubstat = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\hub\\stat_avgpcc_Nucleus_PPI.txt";
	
	public static final String nucleusppihubstatbig = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\hub\\stat_big_avgpcc_Nucleus_PPI.txt";
	public static final String nucleusppihubstatsmall = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\hub\\stat_small_avgpcc_Nucleus_PPI.txt";
	
	
	public void core() {
		// TODO Auto-generated method stub
	/**/	DCUtils.calDc("F:\\金山网盘\\data\\CB\\数据集\\Nucleus_PPI.txt",
				"F:\\金山网盘\\data\\CB\\不同数据集的权值\\dc\\dc_Nucleus_PPI.txt");
		HubUtils.calHub("F:\\金山网盘\\data\\CB\\不同数据集的权值\\dc\\dc_Nucleus_PPI.txt", 
				"F:\\金山网盘\\data\\CB\\不同数据集的权值\\hub\\hub_Nucleus_PPI.txt");
		PCCUtils.calPcc(Global.nucleusppiFile, nucleusppipcc, "result36.txt");
		HubUtils.getHubValue(nucleusppihub, nucleusppiavgpcc, nucleusppipcc);
		CommonUtils.stat(nucleusppiavgpcc, nucleusppihubstat, -1, 1, 100);
		
		
		HubUtils.divAndStat(nucleusppiavgpcc, nucleusppihubstatbig, nucleusppihubstatsmall, 0.5);
		
	}
	
}
