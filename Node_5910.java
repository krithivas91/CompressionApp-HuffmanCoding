//SWAMINATHAN  KRITHIVAS 		PP 		CS610	5910

public class Node_5910 implements Comparable<Node_5910> {

	int symbol;
	int frequency;
	Node_5910 left;
	Node_5910 right;
	String code ="";
	
	
	public Node_5910 getRight() {
		return right;
	}
	public void setRight_5910(Node_5910 right) {
		this.right = right;
	}
	public Node_5910 getLeft() {
		return left;
	}
	public void setLeft_5910(Node_5910 left) {
		this.left = left;
	}
	
	public  Node_5910 (int symbol, int frequency)
	{
		this.symbol = symbol;
		this.frequency =frequency;
		
	}
	public  Node_5910 (int frequency)
	{
		this.frequency =frequency;
		
	}
	
	public int getSymbol_5910() {
		return symbol;
	}
	public void setSymbol_5910(int symbol) {
		this.symbol = symbol;
	}
	public int getFrequency_5910() {
		return frequency;
	}
	public void setFrequency_5910(int frequency) {
		this.frequency = frequency;
	}
	public boolean equals_5910(Node_5910 other)
	{
		return this.frequency==other.frequency;
	}
	
	@Override
	public int compareTo(Node_5910 other) {
		if (this.equals(other))
			return 1;
		else if (getFrequency_5910() > other.frequency)
			return 1;
		else 
			return -1;
		
	}

}
