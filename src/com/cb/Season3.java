package com.cb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ab.utils.EdgeUtils;
import com.ab.utils.FileUtils;

public class Season3 {
	/**
	 * 
	 * @param filepath:edge with value, and the data is already in order
	 * @param outpath
	 * @param size
	 */
	public void statistic(String filepath, String outpath, int size) {
		
		List<String> edges = EdgeUtils.getEdge(filepath);
		Set<String> set = new HashSet<String>();
		Set<String> essSet = null;
		int essCountInFile = 0, essCountInTopSize = 0;
		//List<String> data = new ArrayList<String>(); 
		essSet = FileUtils.getEssentialSet();
		essCountInFile = EdgeUtils.getTotalEssentialNodeCount(edges);
		
		for (int i = 0; i < (1.0 * size * edges.size() / 100); i++) {
			String []items = edges.get(i).split("	");
			if (!set.contains(items[0])) {
				set.add(items[0]);
				if (essSet.contains(items[0])) {
					essCountInTopSize++;
				}
			}
			
			if (!set.contains(items[1])) {
				set.add(items[1]);
				if (essSet.contains(items[1])) {
					essCountInTopSize++;
				}
			}
		}

		System.out.println("top " + size + "%: " + essCountInTopSize * 1.0 * 100/ essCountInFile + "%");
	}
	
}
