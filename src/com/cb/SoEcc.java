package com.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class SoEcc {
	public static void main(String[] args) {
		SoEcc so = new SoEcc();
		String path = "SC_net.txt";
		Vector<String[]> edges = new Vector<String[]>();
		String[] vertex = so.read(path, edges);
		int[][] data = so.makeMatrix(edges, vertex);
		try {
			so.figure(data, vertex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void figure(int[][] data, String[] vertex) throws IOException {
		FileOutputStream outputStream = new FileOutputStream("test_out.txt");
		int i, j, k, du, m;
		float score;
		String str = "";
		int[] degree = new int[data.length];
		for (i = 0; i < degree.length; i++) {
			for (j = 0; j < data.length; j++) {
				if (data[i][j] == 1) {
					degree[i]++;
				}
			}
		}
		for (i = 0; i < data.length; i++) {
			score = 0;
			for (j = 0; j < data[0].length; j++) {
				m = 0;
				if (data[i][j] == 1) {
					for (k = 0; k < data[0].length; k++) {
						if (data[i][k] == 1 && data[k][j] == 1) {
							m++;
						}
					}
				}
				du = degree[i] < degree[j] ? (degree[i] - 1) : (degree[j] - 1);
				if(du!=0)
				score += (float) m / du;
			}
			System.out.println(score);
			str = vertex[i] + "	" + score + "\r\n";
			outputStream.write(str.getBytes());
		}
		outputStream.flush();
		outputStream.close();
	}

	public String[] read(String path, Vector<String[]> vector) {
		File file = new File(path);
		HashSet<String> hashSet = new HashSet<String>();
		try {
			String line;
			Scanner lineScan;
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String[] data = new String[2];
				line = scan.nextLine();
				lineScan = new Scanner(line);
				data[0] = lineScan.next().trim();
				data[1] = lineScan.next().trim();
				vector.add(data);
				hashSet.add(data[0]);
				hashSet.add(data[1]);
				lineScan.close();
			}
			scan.close();
			hashSet.toArray(new String[2]);
			return hashSet.toArray(new String[2]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * create adjacent matrix
	 * 
	 * @param edges
	 * @param vertex
	 * @return: adjacent matrix,and default data[i][j]=0
	 */
	public int[][] makeMatrix(Vector<String[]> edges, String[] vertex) {
		int[][] data = new int[vertex.length][vertex.length];
		String[] edge;
		int m, n;
		for (int i = 0; i < edges.size(); i++) {
			edge = edges.get(i);
			for (m = 0; m < vertex.length; m++) {
				if (vertex[m].equals(edge[0]))
					break;
			}
			for (n = 0; n < vertex.length; n++) {
				if (vertex[n].equals(edge[1]))
					break;
			}
			if (n < vertex.length) {
				data[n][m] = 1;
				data[m][n] = 1;
			}
		}
		return data;
	}
}
