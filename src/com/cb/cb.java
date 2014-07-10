package com.cb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cb {

	public static void calEcc() {
		DCUtils dcUtils = new DCUtils();
		dcUtils.calDc("DIP20101010.txt", "Essential.txt");
		
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
		//goUtils.calFS("SC_net.txt", "go_sim.txt", list);
		goUtils.normalize("go_FS.txt");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//calEcc();
		calGo();
	}
	


	

	
		public static void main1(String[] args)
		{
			try
			{
				Statement stmt=null;
				ResultSet rs=null;
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://182.92.79.94:3309/dong8?user=superadmin&password=AdminSuper&useUnicode=true&characterEncoding=utf-8"); 
				stmt = (Statement)conn.createStatement(); 
				String data = "测试";
				String newData = new String(data.getBytes("ISO-8859-1"), "ISO-8859-1");
				stmt.executeUpdate("update Club set club_name='" + data + "' where id=52"); 

				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	



}
