// the node class is very much similar to the node class for P1. However it also has left and right child.

public class Node {
	char ch;
	int freq;
	String enc;
	Node left;
	Node right;

	public void addenc(String s) {
		enc += s;
	}

	public String getEnc() {
		return enc;
	}

	public void setLNode(Node p) {
		left = p;
	}

	public void setRNode(Node q) {
		right = q;
	}

	public Node getLNode() {
		return left;
	}

	public Node getRNode() {
		return right;
	}

	public Node(char in_ch, int x) {
		ch = in_ch;
		freq = x;
		enc = "";
		left = null;
		right = null;
	}

	public String toString() {
		String ret = "Character is: "+ ch + "\nNo. of Appearances " + freq;
		return ret;
	}

	public void setCh(char in_ch) {
		ch = in_ch;
	}

	public void setFreq(int in_freq) {
		freq = in_freq;
	}

	public char getCh() {
		return ch;
	}

	public int getFreq() {
		return freq;
	}
}
