package com.cb.entity;

public class Edge {
	public Node nodeL;
	public Node nodeR;
	public float ecc;
	public float pcc;
	float weight;

	public Edge(Node nodeL, Node nodeR) {
		this.nodeL = nodeL;
		this.nodeR = nodeR;
		this.ecc = 0.0F;
		this.pcc = 0.0F;
		this.weight = 0.0F;
	}
}
