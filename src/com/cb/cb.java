package com.cb;

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
			dcUtils.calDc();
		}
		
		String filePath2 = "DIP_result.txt";
		String dataPath2 = "result36.txt";
		String outFilePath2 = "pccResult.txt";
		String outFilePath3 = "dpcc.txt";
		PCCUtils pccUtils = new PCCUtils(filePath, dataPath2, outFilePath2);
		pccUtils.init();
		pccUtils.calPcc();
		
		DPCC dpcc = new DPCC(outFilePath, outFilePath2, outFilePath3);
		dpcc.init();
		dpcc.getDPCC();
	}

}
