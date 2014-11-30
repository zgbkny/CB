package com.cb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cb.utils.CBUtils;
import com.cb.utils.DCUtils;
import com.cb.utils.PCCUtils;

public class cb {

	public static void calEcc() {
		DCUtils dcUtils = new DCUtils();
		//dcUtils.calDc("Y2k.txt", "Essential.txt");
		
		ECCUtils eccUtils = new ECCUtils();
		eccUtils.calEcc("DIP20101010.txt");
	}
	
	public static void calGo() {
		GOUtils goUtils = new GOUtils();
		List<String> list = new ArrayList<String>();
		list.add("GO_Function_annotation.txt");
		list.add("GO_Process_annotation.txt");
		list.add("GO_Component_annotation.txt");
		//goUtils.calProbability(list);
		//goUtils.calSim("go_Probability.txt", list);
		//goUtils.calFS("DIP20101010.txt", "go_sim.txt", list);
		goUtils.calCvw("go_FS_DIP20101010.txt");
		goUtils.normalize("cvw_go_FS_DIP20101010.txt");
	}
	
	public static void calPcc() {
		PCCUtils pccUtils = new PCCUtils();
		pccUtils.calPcc("SC_net.txt", null, "result36.txt");
	}
	
	public static void cl() {
		CBUtils cb = new CBUtils();
		cb.classify("go_FSs.txt", "Essential.txt");
		cb.statistic("c3_not_go_FSs.txt", 10);
		cb.statistic("c2_notall_go_FSs.txt", 10);
		cb.statistic("c1_all_go_FSs.txt", 10);
	}
	
	/**
	 * target: 计算数据集的ecc、 bc、pc等， 然后不用根据一个边包含几个关键蛋白分三类，直接根据计算的结果进行统计
	 */
	public static void the3() {
		Season3 s3 = new Season3();
		System.out.println("bc_DIP20101010:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_DIP20101010.txt", null, 99);
		System.out.println("bc_SC_net:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_SC_net.txt", null, 99);
		System.out.println("bc_Y2k:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\bc\\bc_normal_Y2k.txt", null, 99);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("ecc_DIP20101010:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_DIP20101010.txt", null, 99);
		System.out.println("ecc_SC_net:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_SC_net.txt", null, 99);
		System.out.println("ecc_Y2k:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\ecc\\ecc_normal_Y2k.txt", null, 99);
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("pcc_DIP20101010:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_DIP20101010.txt", null, 99);
		System.out.println("pcc_SC_net:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_SC_net.txt", null, 99);
		System.out.println("pcc_Y2k:-------------------------------------------------------------------------");
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 1);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 5);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 10);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 15);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 20);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 25);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 30);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 35);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 40);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 45);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 50);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 55);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 60);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 65);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 70);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 75);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 80);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 85);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 90);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 95);
		s3.statistic("F:\\金山网盘\\data\\CB\\不同数据集的权值\\pcc\\pcc_normal_Y2k.txt", null, 99);
	}
	
	public static void the4() {
		Season4 season = new Season4();
		season.core();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//calEcc();
		//calGo();
		//calPcc();
		//cl();
		the4();
	}
	
}
