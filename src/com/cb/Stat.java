package com.cb;

import com.cb.service.StandardService;
import com.cb.service.StatService;

public class Stat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String outFilename = args[0];
		String outFilename = "E:\\金山网盘\\#共享#\\生物\\TS-PIN_cc.txt";
		//String outFilename = "E:\\金山网盘\\项目\\生物\\别人的实验\\关于LIDC\\S-PIN_LID.txt";
		
		StandardService.process(outFilename, outFilename);
		new StatService().statFileByNum(outFilename);
	}

}
