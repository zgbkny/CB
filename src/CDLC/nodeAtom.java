package CDLC;

/**
 * <p>名称：Algorithms
 * <p>Package名称：ccc.hn.ml.LAC
 * 文件名称：nodeAtom.java 
 * 版本：1.00 
 * 创建日期：2013-6-14
 */

import java.util.ArrayList;

/**
 *<p>类说明：
 *@author：Administrator
 *@version 1.00
 *
 */
public class nodeAtom {
	public String proteinname;
	public int nodeId;//这个是统一的节点编号
	public int degree;
	public double nodeweight;//节点的权重，用来确定节点扩展的优先级的（谁做为种子）。
	public ArrayList<String> go_note;//每个节点有基因注释链表
}

