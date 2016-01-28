package com.cb.algorithms;

import com.cb.entity.Node;
import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;
import com.cb.utils.LOG;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CC {

	/**
	 * 接近度中心性(closeness centrality, CC)
	 * 算法：网络中的节点总数减一，除以（网络中该点到其他所有点的最短路径长度之和）
	 * 结果：每个点的cc值
	 */
	public static void calCC(String filepath, String outpath) {
		Map<String, Node> map  = GraphUtils.getStaticGraph(filepath);
		GraphUtils.getAllMinPath(map);
		List<String> outList = new ArrayList<String>();
		int count = map.size() - 1;
		for (String key : map.keySet()) {
			Node node = map.get(key);
			double value = 0;
			for (int i = 0; i < node.minPathes.size(); i++) {
				value += node.minPathes.get(i).weight;
			}
			LOG.info(key + " count:" + node.minPathes.size() + " value:" + value);
			value = node.minPathes.size() * 1.0 / count * 1.0 / value;
			outList.add(key + "	" + value);
		}
		CommonUtils.outputFile(outpath, outList);
	}

	public static void main(String []args) {
		String filepath = "E:\\金山网盘\\#共享#\\生物\\TS-PIN.txt";
		String outpath = "E:\\金山网盘\\#共享#\\生物\\TS-PIN_cc.txt";
		calCC(filepath, outpath);
	}
}
