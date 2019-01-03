import java.lang.*;
import java.io.*;
import java.util.*;

public class Selection {

	// partition function that helps me put the last element(pivot) at its right place once we iterate though the whole array.

	public static int partition(int[] arr, int startindex, int endindex) {
		int pind = endindex;	// pivot index is the last index
		int j = startindex-1;
		for(int i = startindex; i < pind; i++) {
			if(arr[i] <= arr[pind]) {
				j++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
		}
		j++;
		int temp2 = arr[j];
		arr[j] = arr[pind];
		arr[pind] = temp2;

		return j;
	}

//***********************************************************************************//
//main function//
//***********************************************************************************//
	public static void main(String[] args) {
		/* Comment for Space
		@brief Inserting the code for the File handling*/

		int[] nums = new int[1000000];
		int index = 0;
		int count = 0;
		try {
			Scanner input=  new Scanner(new File(args[0]));
			index = Integer.parseInt(input.next())-1;	// minus one because of how indexing starts at 0 for languages
			while(input.hasNextInt()) {
				nums[count] = input.nextInt();
				count++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}

		if(index<0 || index>= count) {
			System.out.println("output.txt not created because index out of range");
			return;
		}

		// creating an array of length equal to the number of elements actually present.

		int[] arr = new int[count];
		for(int k = 0; k < count; k++) {
			arr[k] = nums[k];
		}

		int i = 0;
		int j = arr.length - 1;
		int result = Selection.partition(arr, i, j);


		// This loops helps me to keep on decreasing the length of the array on which I have to apply the partition till
		// the pivot lands on the index as specified

		while(index != result) {
			if(index<result)
				j = result - 1;
			else
				i = result + 1;
			result = Selection.partition(arr, i, j);
		}

		// System.out.println("The element is " + arr[index]);


		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			output.print(arr[index]);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	}
}
