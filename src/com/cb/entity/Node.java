package com.cb.entity;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {
	public String nodename;
	public int essential;
	int degree;
	float p;
	public HashSet<Node> nodelist;
	public HashSet<Edge> edgelist;
	public ArrayList<Float> geneTime;

	public Node(String nodename, int degree) {
		this.nodename = nodename;
	    this.degree = degree;
	    this.essential = 0;
	    this.p = 0.0F;
	    this.nodelist = new HashSet();
	    this.edgelist = new HashSet();
	    this.geneTime = new ArrayList();
	}

	public Node() {
		
	}
}
