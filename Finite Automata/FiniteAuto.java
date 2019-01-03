import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class FiniteAuto {
  public static final int NUMCHARS = 256;

  /* getNext function. This will */
  public static int getNext(char [] pat, int n, char c) {

    int a;
    int b;

    if(n < pat.length && c == pat[n]) {
      return n + 1;
    }

    for(a = n; a > 0; a--) {
      if(pat[a - 1] == c) {
        for(b = 0; b < a - 1; b++) {
          if(pat[b] != pat[n - a + 1 + b]) {
            break;
          }
        }
        if(b == a - 1) {
          return a;
        }
      }
    }
    return 0;
  }

  /* search function. This will make a table that shows a finite automata pattern */
  public static int[] search(char[] str, int[][] table, int n) {
    int[] result = new int [10000];
    int a = 0;
    int count = 0;

    for(int i = 0; i < str.length; i++) {
      a = table[a][str[i] - 97];

      if(a == n) {
        result[count] = i - n + 1;
        count++;
      }
    }
    return Arrays.copyOf(result,count); // using copyof to return the results
  }

  public static void main(String[] args) {

    int num = 0;
    String[] lines = new String[2];
    int count = 0;
    int[] fR;
    int[] bR;

    try {
      // reading an input file
      Scanner scanner = new Scanner(new File(args[0]));
      while(scanner.hasNext()) {
        lines[count] = scanner.next();
        count++;
      }
      scanner.close();
    } catch (FileNotFoundException e) { // Error when input file is not found
			System.out.println("No file \""+args[0]+"\" found!");
		}
      char[] pattern = lines[0].toCharArray(); // changing to charArray
      char[] str = lines[1].toCharArray(); // chaging to charArray
      char[] backwards = lines[0].toCharArray(); // changing to charArray

      // In case of finding the pattern backwards
      for(int i = 0; i < pattern.length; i++) {
        backwards[i] = pattern[pattern.length - i - 1];
      }

      // In case of finding the pattern forward way
      int[][] forwardArr = new int[pattern.length + 1][25];
      for(int i = 0; i < forwardArr.length; i++) {
        for(int j = 0; j < forwardArr[0].length; j++) {
          forwardArr[i][j] = getNext(pattern, i, (char)(j + 97));
        }
      }

      int[][] backwardArr = new int[backwards.length+1][25];
      for(int i = 0; i < backwardArr.length; i++) {
        for(int j = 0; j < backwardArr[0].length; j++) {
          backwardArr[i][j] = getNext(backwards, i, (char)(j + 97));
        }
      }

      // showing the forward seraching result
      fR = search(str, forwardArr, pattern.length);
      // shoing the backward searching result
      bR = search(str, backwardArr, backwards.length);

      try {
        // Using PrintWriter to create an output.txt file.
        PrintWriter output = new PrintWriter(new File("output.txt"));

        // Dividing cases for printing the outputs
        if(fR.length > 0 && bR.length > 0) {
          for(int i = 0; i < fR.length; i++) {
            output.print(fR[i] + " ");
          }

          for(int i = 0; i < bR.length - 1; i++) {
            output.print(bR[i] + "\n");
          }
          output.print(bR[bR.length - 1]);
        } else if (fR.length > 0) {
          for(int i = 0; i < fR.length - 1; i++) {
            output.print(fR[i] + " ");
          }
          output.print(fR[fR.length - 1]);
        } else if (bR.length > 0) {
          for(int i = 0; i < bR.length - 1; i++) {
            output.print(bR[i] + "\n");
          }
          output.print(bR[bR.length - 1]);
        }
        output.close();
      } catch (FileNotFoundException e) { // Throwing an error when unable to create an output.txt file
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
  }
}
