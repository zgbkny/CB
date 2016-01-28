package com.cb.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Node {
	public String nodename;
	public double value;			// 该节点的权值
	public double other;			// 扩展

	public List<Edge> neighbors;

	public List<Edge> minPathes;

	public Node(String nodename) {
		this.nodename = nodename;
		this.neighbors = null;
	}
	public Node(String nodename, double value) {
		this.nodename = nodename;
		this.value = value;
		this.neighbors = null;
	}



	////////////////////////////////// old
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
