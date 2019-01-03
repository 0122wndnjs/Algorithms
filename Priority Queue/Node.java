public class Node {
	String name;
	int priority;

	public Node(String in_name, int x) {
		name = in_name;
		priority = x;
	}

	public String toString() {
		String ret = "Name is: "+ name + "\nPriority is: " + priority;
		return ret;
	}

	public void setName(String in_name) {
		name = in_name;
	}

	public void setPriority(int in_priority) {
		priority = in_priority;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}
}
