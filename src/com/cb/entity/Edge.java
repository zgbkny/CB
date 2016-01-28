package com.cb.entity;

public class Edge {
	public Node nodeL;
	public Node nodeR;
	public double weight;

	public Edge(Node nodeL, Node nodeR, double weight) {
		this.nodeL = nodeL;
		this.nodeR = nodeR;
		this.weight = weight;
	}





	public float ecc;
	public float pcc;
	public Edge(Node nodeL, Node nodeR) {
		this.nodeL = nodeL;
		this.nodeR = nodeR;
		this.ecc = 0.0F;
		this.pcc = 0.0F;
		this.weight = 0.0F;
	}
}
