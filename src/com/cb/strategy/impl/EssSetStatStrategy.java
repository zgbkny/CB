package com.cb.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.strategy.Strategy;
import com.cb.utils.CommonUtils;
import com.cb.utils.EssUtils;

/***
 * 功能：统计每个子文件夹下的三个文件的关键蛋白的集合，然后用前两个文件分别与第三个文件做运算。
 * @author ww
 *
 */
public class EssSetStatStrategy implements Strategy {

	Map<String, Integer> s1Map = null;
	Map<String, Integer> s2Map = null;
	Map<String, Integer> s3Map = null;
	List<String> s1List = null;
	List<String> s2List = null;
	List<String> s3List = null;
	
	int topSize = 100;
	
	Set<String> essSet;
	
	public void setSize(int topSize) {
		this.topSize = topSize;
	}
	
	@Override
	public void action(String dirpath) {
		// TODO Auto-generated method stub
		// 获取目录下面的所有子目录
		System.out.println("topSize:" + topSize);
		List<String> subDirPaths = CommonUtils.getSubDirInPath(dirpath);
		essSet = EssUtils.getEssentialSet();
		for (String subDir : subDirPaths) {
			List<String> filepaths = CommonUtils.getFilesInPath(subDir);
			if (filepaths.size() == 3) {
				System.out.println("s1:" + filepaths.get(0));
				System.out.println("s2:" + filepaths.get(1));
				System.out.println("s3:" + filepaths.get(2));
				s1Map = CommonUtils.getInputFileMapWithIndex(filepaths.get(0));
				s1List = CommonUtils.getInputFileJustKey(filepaths.get(0));
				s2Map = CommonUtils.getInputFileMapWithIndex(filepaths.get(1));
				s2List = CommonUtils.getInputFileJustKey(filepaths.get(1));
				s3Map = CommonUtils.getInputFileMapWithIndex(filepaths.get(2));
				s3List = CommonUtils.getInputFileJustKey(filepaths.get(2));
			}
			pre(null);
			core(null);
			post(null);
			stat(null);
		}
	}

	@Override
	public void pre(String path) {
		// TODO Auto-generated method stub
		int count1 = 0, count2 = 0, count3 = 0;
		for (int i = 0; i < topSize; i++) {
			if (essSet.contains(s1List.get(i))) {
				count1++;
			}
			if (essSet.contains(s2List.get(i))) {
				count2++;
			}
			if (essSet.contains(s3List.get(i))) {
				count3++;
			}
 		}
		System.out.println("s1 ess count:" + count1);
		System.out.println("s2 ess count:" + count2);
		System.out.println("s3 ess count:" + count3);
	}

	public int getS1AndS2(List<String> s1List, Map<String, Integer> s2Map) {
		int count = 0;
		for (int i = 0; i < topSize; i++) {
			if (essSet.contains(s1List.get(i)) 
					&& s2Map.containsKey(s1List.get(i)) 
					&& s2Map.get(s1List.get(i)) < topSize) {
				// s1中, 且在s2中，且在s2中的index小于topsize
				count++;
			}
		}
		return count;
	}
	
	public int getS1MinusS2(List<String> s1List, Map<String, Integer> s2Map) {
		int count = 0;
		for (int i = 0; i < topSize; i++) {
			if (essSet.contains(s1List.get(i))) { 
				
				if (!s2Map.containsKey(s1List.get(i))) { // s2不包含，直接++
					count++;
				} else {
					if (s2Map.get(s1List.get(i)) > topSize) { // s2包含，但是在topSize之外，++
						count++;
					}
				}
			}
		}
		return count;
	}
	
	@Override
	public void core(String path) {
		// TODO Auto-generated method stub
		System.out.println("s1 & s3:" + getS1AndS2(s1List, s3Map));
		System.out.println("s2 & s3:" + getS1AndS2(s2List, s3Map));
		
		System.out.println("s3 - s1:" + getS1MinusS2(s3List, s1Map));
		System.out.println("s3 - s2:" + getS1MinusS2(s3List, s2Map));
	}

	@Override
	public void post(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stat(String path) {
		// TODO Auto-generated method stub

	}

}
