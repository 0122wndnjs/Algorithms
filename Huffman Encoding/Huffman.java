import java.lang.*;
import java.io.*;
import java.util.*;

public class Huffman {

	// sorting function that uses insertion sort to sort the nodes based on the frequency

	public static void sort(Node[] arr, int len) {
		for(int i = 1; i < len; i++) {
			Node temp = arr[i];
			int key = arr[i].getFreq();
			int j = i-1;
			while(j>=0 && key > arr[j].getFreq()) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}

	//doencoding() -> function that moves through the whole tree and updates their encoding

	public static void doencoding(Node root) {
		if(root.getLNode() == null && root.getRNode() == null) {

		} else if(root.getLNode() == null) {
			root.getRNode().addenc(root.getEnc()+"0");
		} else if(root.getRNode() == null) {
			root.getLNode().addenc(root.getEnc()+"1");
		} else {
			root.getRNode().addenc(root.getEnc()+"0");
			Huffman.doencoding(root.getRNode());
			root.getLNode().addenc(root.getEnc()+"1");
			Huffman.doencoding(root.getLNode());
		}
	}

	public static void print(Node root, Node[] arr, int index) {
		if(root.getLNode() == null && root.getRNode() == null) {
			arr[index] = new Node(root.getCh(),0);
			arr[index].addenc(root.getEnc());
		}
		else {
			Huffman.print(root.getRNode(), arr, 2*index+1);
			Huffman.print(root.getLNode(), arr, 2*index+2);
		}
	}

//***********************************************************************************//
//main function//
//***********************************************************************************//
	public static void main(String[] args) {
		/* Comment for Space
		@brief Inserting the code for the File handling*/

		String s1 = "";

		try {
			Scanner input =  new Scanner(new File(args[0]));
			while(input.hasNext()) {
				s1 = input.next();
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}

		//************************************************************** */
		char[] c = s1.toCharArray();

		Arrays.sort(c);

		char temp = c[0];
		int typecount = 1;

		for(int i = 1; i < c.length; i++) {
			if(c[i] != temp) {
				temp = c[i];
				typecount++;
			}
		}

		// the code below helps us to make an array of type nodes which only contain unique characters

		Node[] q = new Node[typecount];

		temp = c[0];
		int index = 0;
		int counter = 0;
		for(int i = 1; i < c.length; i++) {
			counter++;
			if(c[i] != temp) {
				q[index] = new Node(temp, counter);
				temp = c[i];
				counter = 0;
				index++;
			}
		}
		counter++;
		q[index] = new Node(temp, counter);


		// I then sort the whole array based on the frequency of the characters

		Huffman.sort(q, q.length);
		int helper = q.length-1;

		// this loops help me to build the tree through which I have to traverse to update the encodings

		while(helper>0) {
			Node t = new Node(' ', q[helper].getFreq() + q[helper-1].getFreq());
			t.setLNode(q[helper-1]);
			t.setRNode(q[helper]);
			q[helper-1] = t;
			Huffman.sort(q, helper);
			helper--;
		}

		Huffman.doencoding(q[0]);	// update the encodings on the root of the main tree that was the result of the previous while loop

		Node[] encoding = new Node[20*typecount];  //  encoding is a helper array that just stores all the encodings to help print it into the output.txt file
		// I was not sure what to have as the size of the array because of how I add the nodes using the print array. So I made it sufficiently large
		Huffman.print(q[0], encoding, 0);

		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			for(int i = 0; i < encoding.length; i++) {
				if(encoding[i] != null)
					output.print(encoding[i].getCh() + ":" + encoding[i].getEnc()+"\n");
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	}
}
