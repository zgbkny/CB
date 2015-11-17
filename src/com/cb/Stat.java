package com.cb;

import com.cb.service.StatService;

public class Stat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String outFilename = args[0];
		String outFilename = "E:\\金山网盘\\项目\\生物\\实验数据\\1025实验\\NC\\S-PIN_NC.txt";
		new StatService().statFileByNum(outFilename);
	}

}
