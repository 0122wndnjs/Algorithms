import java.lang.*;
import java.io.*;
import java.util.*;

public class Bucket {
	ArrayList<ArrayList<String>> dict;

	public Bucket() { // initializing my dictionary
		dict = new ArrayList<ArrayList<String>>(26);
		for(int i = 0; i < 26; i++)
			dict.add(i, new ArrayList<String>());
	}

	public ArrayList<ArrayList<String>> getDict() {
		return dict;
	}

	public void addElement(String n, int i) {
		if(i<n.length()) {
			char c = n.charAt(i);
			int ind = (int) c;	// getting the ascii value
			ind -= 97;	// geting the index from the ascii value
			// System.out.println("Adding the element : "+ n+ " at index : "+ind);
			dict.get(ind).add(n);
		} else
			dict.get(0).add(n);
	}

	public void addElement(String n) {
		char c = n.charAt(0);
		int ind = (int) c;	// getting the ascii value
		ind -= 65;	// geting the index from the ascii value
		// System.out.println("Adding the element : "+ n+ " at index : "+ind);
		dict.get(ind).add(n);
	}

	public void sort(int index) {
		for(int i = 0; i < 26; i++) {
			if(dict.get(i).size()>10) {
				// System.out.println("Inside if Condition");
				Bucket temp = new Bucket();
				for(int j = 0; j < dict.get(i).size(); j++) {
					temp.addElement(dict.get(i).get(j), index+1);	//index+1 because I need to insert the elements based on their index+1 th Character
				}
				temp.sort(index+1);	// I have to pass index+1 again to ensure that if I again need to create sub buckets the character at index+2 get checked because of the recursion.

				// adding all the elements in the sorted bucket in same order to the arraylist.
				ArrayList<ArrayList<String>> dictlist = temp.getDict();
				int counter = 0;

				for(int j = 0; j < dictlist.size(); j++) {
					ArrayList<String> templist = dictlist.get(j);
					for(int k = 0; k < templist.size(); k++) {
						dict.get(i).set(counter, templist.get(k));
						counter++;
					}
				}
			} else {
				insertionSort(dict.get(i));
			}
		}
	}

	public void insertionSort(ArrayList<String> l) {
		int len = l.size();
		for(int i = 1; i < len; i++) {
			String key = l.get(i);
			// System.out.println("The key is : "+ key);
			int j = i-1;
			while(j>=0 && key.compareTo(l.get(j)) < 0) {
				//insertion sort algo
				//arr[j+1] = arr[j]
				// j--
				l.set(j+1, l.get(j));
				j--;
			}
			l.set(j+1, key);
		}
	}

	public String print() {
		String ret = "";
		ArrayList<ArrayList<String>> temp = this.getDict();
		for(int i = 0; i < temp.size(); i++) {
			ArrayList<String> templist = temp.get(i);
			for(int j = 0; j < templist.size(); j++) {
				ret = ret + templist.get(j) + " ";
				// System.out.println(templist.get(j));
			}
		}
		return ret;
	}


	//***********************************************************************************//
	//main function//
	//***********************************************************************************//
	public static void main(String[] args) {
		/* Comment for Space
		@brief Inserting the code for the File handling*/

		Bucket myBuckets = new Bucket();

		try {
			Scanner input=  new Scanner(new File(args[0]));
			while(input.hasNext()) {
				String name = input.next();
				myBuckets.addElement(name);
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}

		myBuckets.sort(0);

		String out = myBuckets.print();
		// System.out.println("*****************************");
		// myBuckets.print();

		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			output.print(out.substring(0,out.length()-1));
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	}
}
