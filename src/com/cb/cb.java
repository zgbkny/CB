package com.cb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class cb {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "DIP20101010.txt";
		String dataPath = "Essenntial(DIP20101010).txt";
		String outFilePath = "DIP_result.txt";
		DCUtils dcUtils = new DCUtils(filePath, dataPath, outFilePath);
		if (dcUtils.init()) {
			//dcUtils.calDc();
		}
		
		String filePath2 = "DIP_result.txt";
		String dataPath2 = "result36.txt";
		String outFilePath2 = "SC_net_pcc_result.txt";
		String outFilePath3 = "dpcc.txt";
		/*PCCUtils pccUtils = new PCCUtils(filePath, dataPath2, outFilePath2);
		pccUtils.init();
		pccUtils.calPcc();*/
		
		DPCC dpcc = new DPCC(outFilePath, outFilePath2, outFilePath3);
		/*dpcc.init();
		dpcc.getDPCC();
		dpcc.getFisher(); /*/
		/*DCUtils dcUtils = new DCUtils(filePath, dataPath, outFilePath);
		if (dcUtils.init()) {
			dcUtils.calDc();
		}*/
		
		//dpcc.goFilter();
		dpcc.statistics();
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
