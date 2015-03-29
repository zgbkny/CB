package com.cb.strategy;

public interface Strategy {
	// used to
	public void action(String dirpath);
	public void pre(String path);
	public void core(String path);
	public void post(String path);
	public void stat(String path);
}
