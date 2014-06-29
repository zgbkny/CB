package com.cb;

public class cb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "f:\\data\\data\\cb\\DIP20101010.txt";
		String dataPath = "f:\\data\\data\\cb\\Essenntial(DIP20101010).txt";
		String outFilePath = "f:\\data\\data\\cb\\DIP_result.txt";
		/*	DCUtils dcUtils = new DCUtils(filePath, dataPath, outFilePath);
		if (dcUtils.init()) {
			dcUtils.calDc();
		}
		*/
		//String filePath2 = "f:\\data\\data\\cb\\DIP_result.txt";
		String dataPath2 = "f:\\data\\data\\cb\\result36.txt";
		String outFilePath2 = "f:\\data\\data\\cb\\pccResult.txt";
		PCCUtils pccUtils = new PCCUtils(filePath, dataPath2, outFilePath2);
		pccUtils.init();
		pccUtils.calPcc();
		//*/
	}

}
