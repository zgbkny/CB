package com.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.algorithms.OneDegree;
import com.cb.algorithms.Subcellular;
import com.cb.global.Global;
import com.cb.service.BCService;
import com.cb.service.CCService;
import com.cb.service.CommonService;
import com.cb.service.DCService;
import com.cb.service.ECCService;
import com.cb.service.ECService;
import com.cb.service.OneDegreeService;
import com.cb.service.PCCService;
import com.cb.service.SCService;
import com.cb.service.SeasonService;
import com.cb.service.StatService;
import com.cb.service.SubcellularService;
import com.cb.stat.Statistics;
import com.cb.utils.CommonUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.EdgeUtils;

public class Season5 {
	
	public static final String subc = "H:\\金山网盘\\data\\CB\\数据集\\eSLDB_Saccharomyces_cerevisiae.txt";
	public static final String nucleusppi_esldb = "F:\\金山网盘\\data\\CB\\数据集\\eSLDB_nucleusppi.txt";
	public static final String dc_nucleusppi_esldb = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\dc\\dc_esldb_Nucleus_PPI.txt";
	public static final String dc_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\dc\\dc_Nucleus_PPI.txt";
	public static final String dc_no_1du_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\dc\\dc_no_1du_Nucleus_PPI.txt";
	
	public static final String PeC_nucleusppi_esldb = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\PeC\\PeC_esldb_nuleusppi.txt";
	public static final String PeC_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\PeC\\PeC_nuleusppi.txt";
	
	
	public static final String onedu_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu\\nucleusppi.txt";
	public static final String subc_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc\\nucleusppi.txt";
	public static final String onedu_subc_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu_subc\\nucleusppi.txt";
	public static final String subc_onedu_nucleusppi = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc_onedu\\nucleusppi.txt";
	
	public static final String nucleusppi_ecc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\nucleusppi_ecc.txt";
	public static final String onedu_nucleusppi_ecc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu\\nucleusppi_ecc.txt";
	public static final String subc_nucleusppi_ecc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc\\nucleusppi_ecc.txt";
	public static final String onedu_subc_nucleusppi_ecc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu_subc\\nucleusppi_ecc.txt";
	public static final String subc_onedu_nucleusppi_ecc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc_onedu\\nucleusppi_ecc.txt";
	
	public static final String nucleusppi_pcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\nucleusppi_pcc.txt";
	public static final String onedu_nucleusppi_pcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu\\nucleusppi_pcc.txt";
	public static final String subc_nucleusppi_pcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc\\nucleusppi_pcc.txt";
	public static final String onedu_subc_nucleusppi_pcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu_subc\\nucleusppi_pcc.txt";
	public static final String subc_onedu_nucleusppi_pcc = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc_onedu\\nucleusppi_pcc.txt";
	
	
	public static final String nucleusppi_ecc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\nucleusppi_ecc_sum.txt";
	public static final String onedu_nucleusppi_ecc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu\\nucleusppi_ecc_sum.txt";
	public static final String subc_nucleusppi_ecc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc\\nucleusppi_ecc_sum.txt";
	public static final String onedu_subc_nucleusppi_ecc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu_subc\\nucleusppi_ecc_sum.txt";
	public static final String subc_onedu_nucleusppi_ecc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc_onedu\\nucleusppi_ecc_sum.txt";
	
	public static final String nucleusppi_pcc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\nucleusppi_pcc_sum.txt";
	public static final String onedu_nucleusppi_pcc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu\\nucleusppi_pcc_sum.txt";
	public static final String subc_nucleusppi_pcc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc\\nucleusppi_pcc_sum.txt";
	public static final String onedu_subc_nucleusppi_pcc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\onedu_subc\\nucleusppi_pcc_sum.txt";
	public static final String subc_onedu_nucleusppi_pcc_sum = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\subc_onedu\\nucleusppi_pcc_sum.txt";
	
	
	public static final String season5_temp = "F:\\金山网盘\\data\\CB\\不同数据集的权值\\temp\\temp.txt";
	
	public static final String np_origin = "F:\\金山网盘\\data\\Nucleus_PPI_degree_ec_sc_bc_cc_out.txt";
	public static final String np_1du = "F:\\金山网盘\\data\\Nucleus_PPI_1du_degree_ec_sc_bc_cc_out.txt";
	public static final String np_subc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_degree_ec_sc_bc_cc_out.txt";
	public static final String np_1du_subc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_degree_ec_sc_bc_cc_out.txt";
	public static final String np_subc_1du = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_degree_ec_sc_bc_cc_out.txt";
	
	public static final String sc_net_5 = "F:\\金山网盘\\data\\CB\\inpath\\SC_net_degree_ec_sc_bc_cc_out.txt";
	public static final String Y2k_5 = "F:\\金山网盘\\data\\CB\\inpath\\Y2k_degree_ec_sc_bc_cc_out.txt";
	
	
	public static final String np_origin_dc = "F:\\金山网盘\\data\\Nucleus_PPI_origin_dc.txt";
	public static final String np_1du_dc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_dc.txt";
	public static final String np_subc_dc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_dc.txt";
	public static final String np_1du_subc_dc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_dc.txt";
	public static final String np_subc_1du_dc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_dc.txt";
	
	public static final String np_origin_ec = "F:\\金山网盘\\data\\Nucleus_PPI_origin_ec.txt";
	public static final String np_1du_ec = "F:\\金山网盘\\data\\Nucleus_PPI_1du_ec.txt";
	public static final String np_subc_ec = "F:\\金山网盘\\data\\Nucleus_PPI_subc_ec.txt";
	public static final String np_1du_subc_ec = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_ec.txt";
	public static final String np_subc_1du_ec = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_ec.txt";
	
	public static final String np_origin_sc = "F:\\金山网盘\\data\\Nucleus_PPI_origin_sc.txt";
	public static final String np_1du_sc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_sc.txt";
	public static final String np_subc_sc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_sc.txt";
	public static final String np_1du_subc_sc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_sc.txt";
	public static final String np_subc_1du_sc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_sc.txt";
	
	public static final String np_origin_bc = "F:\\金山网盘\\data\\Nucleus_PPI_origin_bc.txt";
	public static final String np_1du_bc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_bc.txt";
	public static final String np_subc_bc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_bc.txt";
	public static final String np_1du_subc_bc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_bc.txt";
	public static final String np_subc_1du_bc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_bc.txt";
	
	public static final String np_origin_cc = "F:\\金山网盘\\data\\Nucleus_PPI_origin_cc.txt";
	public static final String np_1du_cc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_cc.txt";
	public static final String np_subc_cc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_cc.txt";
	public static final String np_1du_subc_cc = "F:\\金山网盘\\data\\Nucleus_PPI_1du_subc_cc.txt";
	public static final String np_subc_1du_cc = "F:\\金山网盘\\data\\Nucleus_PPI_subc_1du_cc.txt";
	
	public void core() {
		OneDegreeService ods = new OneDegreeService();
//		SubcellularService scs = new SubcellularService();
		/*ods.process(Global.nucleusppiFile, onedu_nucleusppi);
		scs.locationFilter(onedu_nucleusppi, subc, onedu_subc_nucleusppi);
		
		scs.locationFilter(Global.nucleusppiFile, subc, subc_nucleusppi);
		ods.process(subc_nucleusppi, subc_onedu_nucleusppi);
		*/
		/*CommonService cs = new CommonService();
		
		cs.getAndOrder(np_origin, np_origin_dc, 1);
		cs.getAndOrder(np_origin, np_origin_ec, 2);
		cs.getAndOrder(np_origin, np_origin_sc, 3);
		cs.getAndOrder(np_origin, np_origin_bc, 4);
		cs.getAndOrder(np_origin, np_origin_cc, 5);
		
		cs.getAndOrder(np_origin, np_origin_dc, 1);
		cs.getAndOrder(np_origin, np_origin_ec, 2);
		cs.getAndOrder(np_origin, np_origin_sc, 3);
		cs.getAndOrder(np_origin, np_origin_bc, 4);
		cs.getAndOrder(np_origin, np_origin_cc, 5);
		
		cs.getAndOrder(np_1du, np_1du_dc, 1);
		cs.getAndOrder(np_1du, np_1du_ec, 2);
		cs.getAndOrder(np_1du, np_1du_sc, 3);
		cs.getAndOrder(np_1du, np_1du_bc, 4);
		cs.getAndOrder(np_1du, np_1du_cc, 5);
		
		cs.getAndOrder(np_subc, np_subc_dc, 1);
		cs.getAndOrder(np_subc, np_subc_ec, 2);
		cs.getAndOrder(np_subc, np_subc_sc, 3);
		cs.getAndOrder(np_subc, np_subc_bc, 4);
		cs.getAndOrder(np_subc, np_subc_cc, 5);
		
		cs.getAndOrder(np_subc_1du, np_subc_1du_dc, 1);
		cs.getAndOrder(np_subc_1du, np_subc_1du_ec, 2);
		cs.getAndOrder(np_subc_1du, np_subc_1du_sc, 3);
		cs.getAndOrder(np_subc_1du, np_subc_1du_bc, 4);
		cs.getAndOrder(np_subc_1du, np_subc_1du_cc, 5);
		
		cs.getAndOrder(np_1du_subc, np_1du_subc_dc, 1);
		cs.getAndOrder(np_1du_subc, np_1du_subc_ec, 2);
		cs.getAndOrder(np_1du_subc, np_1du_subc_sc, 3);
		cs.getAndOrder(np_1du_subc, np_1du_subc_bc, 4);
		cs.getAndOrder(np_1du_subc, np_1du_subc_cc, 5);*/
		
		StatService statService = new StatService();
		
		//statService.statDC("F:\\金山网盘\\data\\CB\\inpath");
		
		//statService.stat5CAndOrder("F:\\金山网盘\\data\\CB\\inpath");
		statService.statFileByPercent("H:\\金山网盘\\data\\CB\\inpath");
		//statService.statFiles("F:\\金山网盘\\data");
		
		ECCService eccService = new ECCService();
		/*PCCService pccService = new PCCService();
		eccService.calEcc(Global.nucleusppiFile, nucleusppi_ecc);
		eccService.calEcc(subc_nucleusppi, subc_nucleusppi_ecc);
		eccService.calEcc(onedu_nucleusppi, onedu_nucleusppi_ecc);
		eccService.calEcc(subc_onedu_nucleusppi, subc_onedu_nucleusppi_ecc);
		eccService.calEcc(onedu_subc_nucleusppi, onedu_subc_nucleusppi_ecc);
		
		pccService.calPcc(Global.nucleusppiFile, "result36.txt", nucleusppi_pcc);
		pccService.calPcc(subc_nucleusppi, "result36.txt", subc_nucleusppi_pcc);
		pccService.calPcc(onedu_nucleusppi, "result36.txt", onedu_nucleusppi_pcc);
		pccService.calPcc(subc_onedu_nucleusppi, "result36.txt", subc_onedu_nucleusppi_pcc);
		pccService.calPcc(onedu_subc_nucleusppi, "result36.txt", onedu_subc_nucleusppi_pcc);
		

		eccService.calSumEcc(nucleusppi_ecc, nucleusppi_ecc_sum);
		eccService.calSumEcc(subc_nucleusppi_ecc, subc_nucleusppi_ecc_sum);
		eccService.calSumEcc(onedu_nucleusppi_ecc, onedu_nucleusppi_ecc_sum);
		eccService.calSumEcc(subc_onedu_nucleusppi_ecc, subc_onedu_nucleusppi_ecc_sum);
		eccService.calSumEcc(onedu_subc_nucleusppi_ecc, onedu_subc_nucleusppi_ecc_sum);
		
		pccService.calSumPcc(nucleusppi_pcc, nucleusppi_pcc_sum);
		pccService.calSumPcc(subc_nucleusppi_pcc, subc_nucleusppi_pcc_sum);
		pccService.calSumPcc(onedu_nucleusppi_pcc, onedu_nucleusppi_pcc_sum);
		pccService.calSumPcc(subc_onedu_nucleusppi_pcc, subc_onedu_nucleusppi_pcc_sum);
		pccService.calSumPcc(onedu_subc_nucleusppi_pcc, onedu_subc_nucleusppi_pcc_sum);
		*/
		//eccService.calEcc("test.txt", "test_out.txt");
		//ECCService.calEccByLiMin(Global.nucleusppiFile, nucleusppi_ecc);
		
		//eccService.calSumEcc("test_out.txt", "test_out_sum.txt");
		//StatService statService = new StatService();
		//statService.statFilesByNum("F:\\金山网盘\\data\\CB\\inpath");
		
		
		/*
		OneDegree od = new OneDegree();
		Subcellular subc = new Subcellular();
		Map<String, List<String>> map = getESLDBmap(yaxibaodingwei);
		List<String> list = EdgeUtils.getEdge(Global.nucleusppiFile);
		List<String> degreeList = CommonUtils.getInputFile(dc_nucleusppi);
		Set<String> set1Du = od.process(degreeList);
		List<String> subcList = subc.locationFilter(list, map);
		
		

		
		DCUtils.calDc(nucleusppi_esldb, dc_nucleusppi_esldb);
		
		DCUtils.calDc(Global.nucleusppiFile, dc_nucleusppi);
		
		
		
		
		
		list = CommonUtils.getInputFile(dc_nucleusppi);
		outList = new ArrayList<String>();
		for (String str : list) {
			String[] strs = str.split("	");
			int i = Integer.parseInt(strs[1]);
			if (i != 1) {
				outList.add(str);
			}
		}
		CommonUtils.outputFile(dc_no_1du_nucleusppi, outList);
		
		System.out.println("----------------------------PeC esldb:");
		Statistics.statByKeyValueEss(PeC_nucleusppi_esldb, null, 1);
		Statistics.statByKeyValueEss(PeC_nucleusppi_esldb, null, 5);
		Statistics.statByKeyValueEss(PeC_nucleusppi_esldb, null, 10);
		Statistics.statByKeyValueEss(PeC_nucleusppi_esldb, null, 15);
		Statistics.statByKeyValueEss(PeC_nucleusppi_esldb, null, 25);
		System.out.println("----------------------------PeC origin:");
		Statistics.statByKeyValueEss(PeC_nucleusppi, null, 1);
		Statistics.statByKeyValueEss(PeC_nucleusppi, null, 5);
		Statistics.statByKeyValueEss(PeC_nucleusppi, null, 10);
		Statistics.statByKeyValueEss(PeC_nucleusppi, null, 15);
		Statistics.statByKeyValueEss(PeC_nucleusppi, null, 25);
		
		
		System.out.println("----------------------------dc esldb:");
		Statistics.statByKeyValueEss(dc_nucleusppi_esldb, null, 1);
		Statistics.statByKeyValueEss(dc_nucleusppi_esldb, null, 5);
		Statistics.statByKeyValueEss(dc_nucleusppi_esldb, null, 10);
		Statistics.statByKeyValueEss(dc_nucleusppi_esldb, null, 15);
		Statistics.statByKeyValueEss(dc_nucleusppi_esldb, null, 25);
		System.out.println("----------------------------dc origin:");
		Statistics.statByKeyValueEss(dc_nucleusppi, null, 1);
		Statistics.statByKeyValueEss(dc_nucleusppi, null, 5);
		Statistics.statByKeyValueEss(dc_nucleusppi, null, 10);
		Statistics.statByKeyValueEss(dc_nucleusppi, null, 15);
		Statistics.statByKeyValueEss(dc_nucleusppi, null, 25);
		
		System.out.println("----------------------------dc no 1du:");
		Statistics.statByKeyValueEss(dc_no_1du_nucleusppi, null, 1);
		Statistics.statByKeyValueEss(dc_no_1du_nucleusppi, null, 5);
		Statistics.statByKeyValueEss(dc_no_1du_nucleusppi, null, 10);
		Statistics.statByKeyValueEss(dc_no_1du_nucleusppi, null, 15);
		Statistics.statByKeyValueEss(dc_no_1du_nucleusppi, null, 25);*/
		
	}
	public Map<String, List<String>> getESLDBmap(String filepath) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			FileReader ins = new FileReader(filepath);
			BufferedReader readBuf = new BufferedReader(ins);
			String buf = null;
			while ((buf = readBuf.readLine()) != null) {
				String []strs = buf.split("	");
				String []items = strs[2].split(",");
				List<String> list = new LinkedList<String>();
				for (String item : items) {
					list.add(item);
					System.out.println(strs[1] + ":" + item);
				}
				if (map.containsKey(strs[1])) {
					map.get(strs[1]).addAll(list);
				} else {
					map.put(strs[1], list);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	public void core2() {
		// TODO Auto-generated method stub
		//SeasonService.season5FilterService("H:\\金山网盘\\data\\CB\\inpath", subc);
		
		PCCService pccs = new PCCService();
		//pccs.calPcc("H:\\金山网盘\\data\\CB\\inpath\\SC_net.txt", "result36.txt", "H:\\金山网盘\\data\\CB\\inpath\\SC_net_pcc.txt");
		
		DCService dcs = new DCService();
		//dcs.calDCs("H:\\金山网盘\\data\\CB\\inpath");
	
		//SeasonService.season5CalEccPccSum("H:\\金山网盘\\data\\CB\\inpath");
		
		StatService statService = new StatService();
		//statService.stat5CAndOrder("H:\\金山网盘\\data\\CB\\inpath");
		
		//statService.comboData("H:\\金山网盘\\data\\CB\\inpath");
		
		//statService.statFileByNum("H:\\金山网盘\\data\\CB\\inpath");
		
		statService.statFileByNumByKey("H:\\金山网盘\\data\\CB\\inpath", "SC_net.txt", 0, 5);
		statService.statFileByNumByKey("H:\\金山网盘\\data\\CB\\inpath", "SC_net.txt", 5, 10);
		statService.statFileByNumByKey("H:\\金山网盘\\data\\CB\\inpath", "SC_net.txt", 10, 20);
		statService.statFileByNumByKey("H:\\金山网盘\\data\\CB\\inpath", "SC_net.txt", 20, Integer.MAX_VALUE);
		
		//statService.getClass();
	}
}
