package com.cb.entity;

import java.util.ArrayList;
import java.util.List;

public class Complex {
	public int num = 0;
	public List<String> proteins = null;
	public String key = null;
	
	public Complex next = null;
	
	public Complex() {
		proteins = new ArrayList<String>();
	}
	
	public boolean getKey() {
		boolean ret = false;
		if (num != proteins.size() && num != 0) {
			ret = false;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < proteins.size(); i++) {
				sb.append(proteins.get(i));
			}
			key = sb.toString();
			ret = true;
		}
		return ret;
	}

	@Override
	public String toString() {
		return key;
	}
	
	
}
