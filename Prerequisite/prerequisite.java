import java.util.*;
import java.io.*;

/* class to list all prerequisite courses for each class. */
public class prerequisite {
  public static void prereq(String[] classes, String [][] classConnection, int[] classPriority,
  int count, String classname) {
    int k = 0;
    int j = 0;

    for (k = 0; k < count; k++) {
      if(classes[k].equals(classname)) {
        classPriority[k]++;

        for(j = 0; classConnection[k][j] != null; j++) {
          prereq(classes,classConnection,classPriority,count,classConnection[k][j]);
        }
      }
    }
  }

  public static void main (String args[]) throws IOException, ArrayIndexOutOfBoundsException {
    String classes[] = new String[100];
    int classPriority[] = new int [100];
    String classConnection[][] = new String[100][100];
    String classname = "",temp="";
    int count = 0;
    int i = 0;
    int j = 0;
    int k = 0;
    int countCon = 0;

    for(i = 0; i < 100; i++) {
      classPriority[i] = 1;
    }
    // Using BufferedReader to read an input file.
    try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

      String line;

      while((line = br.readLine()) != null) {
        i = 0;
        countCon = 0;
        classes[count] = line.substring(0,line.indexOf(':'));
        line = line.substring(line.indexOf(':')+1);

        if(line.length() > 1) {
          while(line.indexOf(' ') != -1) {
            classname = line.substring(0, line.indexOf(' '));
            classConnection[count][countCon] = classname;
            line = line.substring(line.indexOf(' ')+1);
            countCon++;
          }

          classConnection[count][countCon] = line;
          countCon++;
        }

        count++;
      }
    } catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}
    // Using PrintWriter to create an output.txt file.
    try (PrintWriter output = new PrintWriter(new File("output.txt"))) {

      for(i = 0; i < count; i++) {
        for(j = 0; classConnection[i][j] != null; j++) {
          prereq(classes,classConnection,classPriority,count,classConnection[i][j]);
        }
      }

      for(i = 0; i < count; i++) {
        for(j = 1; j < count; j++) {
          if(classPriority[j-1] <= classPriority[j]) {
            k = classPriority[j-1];
            classPriority[j-1] = classPriority[j];
            classPriority[j] = k;
            temp = classes[j];
            classes[j] = classes[j-1];
            classes[j-1] = temp;
          }
        }
      }

      for(i = 0; i < count - 1; i++) {
        output.print(classes[i] + " ");
      }
      output.print(classes[count - 1]); // erasing the spacing after the last class is listed
    }  catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!"); // Throwing error when hainv trouble creating output.txt
		}
		System.out.println("output.txt created!");
  }
}
