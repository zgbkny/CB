package CDLC;

public class ProteinNode {

	//蛋白质名
	private String name;
	//蛋白质出现次数
	private int count;
	//蛋白质得分总和
	private double score;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
