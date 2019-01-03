import java.lang.*;
import java.io.*;
import java.util.*;

public class PQueue {
	Node[] QueueHeap;
	int count;	//  counter for the size of the heap

	public PQueue() {
		QueueHeap = new Node[0];
		count = 0;
	}

	public int getLength() {
		return count;
	}

	public Node getIndex(int x) {
		if(x<count)
			return QueueHeap[x];
		else
			return null;
	}

	public int arg_max(int left, int right, int i) {
		if(left < count && right < count) {
			if(QueueHeap[i].getPriority() >= QueueHeap[left].getPriority() && QueueHeap[i].getPriority() >= QueueHeap[right].getPriority())
				return i;
			else if(QueueHeap[left].getPriority() >= QueueHeap[i].getPriority() && QueueHeap[left].getPriority() >= QueueHeap[right].getPriority())
				return left;
			else
				return right;
		}
		else if(left < count) {
			if(QueueHeap[i].getPriority() >= QueueHeap[left].getPriority())
				return i;
			else
				return left;
		}
		else if(right < count) {
			if(QueueHeap[i].getPriority() >= QueueHeap[right].getPriority())
				return i;
			else
				return right;
		}
		else
			return i;
	}

	public void max_heapify(int i) {
		if(i<count) {
			int left = (2*i)+1;
			int right = 2*(i+1);
			int l = arg_max(left, right, i);

			if(l != i) {
				Node temp = QueueHeap[i];
				QueueHeap[i] = QueueHeap[l];
				QueueHeap[l] = temp;
				max_heapify(l);
			}
		}
	}

	public void build_max_heap() {
		for(int i = (int) (Math.floor(count/2)); i>=0; i--) {
			max_heapify(i);
		}
	}

//***********************************************************************************//
//insert helper function//
//***********************************************************************************//
	public void insert(Node x) {
		if(count == QueueHeap.length) {
			Node[] temp = new Node[2*QueueHeap.length+5];	//  +5 for dynamic increase in length of the array
			for(int i = 0; i<count; i++) {
				temp[i] = QueueHeap[i];
			}
			QueueHeap = temp;
		}
		QueueHeap[count] = x;
		count++;
		build_max_heap();
	}

//***********************************************************************************//
//extract_max helper function//
//***********************************************************************************//

	public Node extract_max() {
		if(count >0) {
			Node max = QueueHeap[0];
			QueueHeap[0] = QueueHeap[count-1];
			QueueHeap[count-1] = null;
			count--;
			max_heapify(0);
			return max;
		}
		else
			return null;
	}

//***********************************************************************************//
//Maximum function
//***********************************************************************************//

	public static Node Maximum(PQueue q) {
		return q.getIndex(0);
	}

//******************************************************************** */
//Insert function//
//******************************************************************** */
	public static void Insert(PQueue q, Node x) {
		q.insert(x);
	}

//******************************************************************** */
//Extract-Max function//
//******************************************************************** */

	public static Node Extract_Max(PQueue q) {
		return q.extract_max();
	}


//***********************************************************************************//
//main function//
//***********************************************************************************//
	public static void main(String[] args) {
		// System.out.println("Hello World");
		/* Comment for Space
		@brief Inserting the code for the File handling*/

		PQueue myqueue = new PQueue();

		try {
			Scanner input=  new Scanner(new File(args[0]));
			while(input.hasNext()) {
				String name = input.next();
				input.next();
				String pr = input.next();
				int priority = Integer.parseInt(pr);
				PQueue.Insert(myqueue, new Node(name, priority));
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}

		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			while(myqueue.getLength()>1) {
				output.print(PQueue.Extract_Max(myqueue).getName() + " ");
			}
			output.print(PQueue.Extract_Max(myqueue).getName());
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
//***************************************************************************************************//
	}//end main
}//end class
