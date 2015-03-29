package com.cb;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cb.service.DCService;
import com.cb.service.ECCService;
import com.cb.service.HubService;
import com.cb.service.PCCService;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.HubUtils;

public class Season7 {
	/**
	 * 验证是party 的保守性高还是date的保守性高
	 */
	public void checkHub() {
		List<String> list = CommonUtils.getFilesInPath("H:\\金山网盘\\data\\CB\\inpath");
		ECCService eccs = new ECCService();
		for (String path : list) {
			//action(path, 50);
			//HubService.divAndStat(path, path.replaceAll(".txt", "_big.txt"), path.replaceAll(".txt", "_small.txt"), 0.5);
			eccs.calSumEcc(path, path.replaceAll(".txt", "_sum.txt"), true);
		}
	}
	
	/**
	 * 根据计算新的权值
	 */
	public void calNewValueByPartyDateHub(double alpa, double percent, double pccval) {
		List<String> list = CommonUtils.getFilesInPath("H:\\金山网盘\\data\\CB\\inpath");
		double []a = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 0) {
				Map<String, Double> map = CommonUtils.getInputFileMap(list.get(i + 1), 0, 1);
				List<String> eccSumList = CommonUtils.getInputFile(list.get(i));
				List<String> outList = new ArrayList<String>();
				
				for (String item : eccSumList) {
					String []strs = item.split("	");
					double value = Double.parseDouble(strs[2]);
					System.out.println(strs[0]);
					
					double pccv = 0.0;
					if (map.containsKey(strs[0])) {
						pccv = map.get(strs[0]);
					} else {
						value = 0.0;
					}
					if (pccv > alpa) {
						pccv = 1;
					} else {
						pccv = pccval;
					}
					value = value * percent + pccv * (1 - percent);
					outList.add(strs[0] + "	" + value);
				}
				outList = Statistics.sortListAndNormal(outList, 1);
				DecimalFormat df = new DecimalFormat("#0.0");
				CommonUtils.outputFile(list.get(i).replaceAll("_ecc_sum.txt", "_"+df.format(pccval)+"_party"+df.format(percent)+"date"+df.format(1-percent)+".txt").replaceAll("inpath", "result"), outList);
				i++;
			}
		}
	}
	
	
	public void action(String originpath, int percent) {
		DCService dcs = new DCService();
		HubService hubs = new HubService();
		dcs.calDC(originpath, originpath.replaceAll(".txt", "_dc.txt"));
		hubs.calHub(originpath.replaceAll(".txt", "_dc.txt"), originpath.replaceAll(".txt", "_hub.txt"), percent);
		PCCService.calPccByLiMin(originpath, "result36.txt", originpath.replaceAll(".txt", "_pcclm.txt"));
		HubService.calHubValue(originpath.replaceAll(".txt", "_hub.txt"), originpath.replaceAll(".txt", "_hubval.txt"), originpath.replaceAll(".txt", "_pcclm.txt"));
		CommonUtils.stat(originpath.replaceAll(".txt", "_hubval.txt"), originpath.replaceAll(".txt", "_hub_stat.txt"), -1, 1, 100);
	}
}
