import java.io.*;
import java.util.*;

public class Dijkstra {
  // Method for increasing the Distance
  public static int[][] IncreaseDist(int[][] mtx) {
    int[][] matr = new int[mtx.length][mtx[0].length + 1];
    for(int i = 0; i < mtx.length; i++) {
      for(int j = 0; j < mtx[i].length; j++) {
        matr[i][j] = mtx[i][j];
      }
    }
    return matr;
  }

  // Method for increasing the source
  public static int[][] IncreaseSrc(int[][] mtx) {
    int[][] matr = new int[mtx[0].length][mtx[0].length];
    for(int i = 0; i < mtx.length; i++) {
      for(int j = 0; j < mtx[i].length; j++) {
        matr[i][j] = mtx[i][j];
      }
    }
    return matr;
  }

  // Method for decreasing the list
  public static int[] decreaseList(int[] mtx) {
    int[] matr = new int[mtx.length - 1];
    for(int i = 0; i < matr.length; i++) {
      matr[i] = mtx[i];
    }
    return matr;
  }

  // Method for increasing the list
  public static int[] IncreaseList(int[] mtx) {
    int[] matr = new int[mtx.length + 1];
    for(int i = 0; i < mtx.length; i++) {
      matr[i] = mtx[i];
    }
    return matr;
  }

  public static void main(String[] args) {
    int[][] mtx = new int[1][1];
    int countSRC = 0;
    int countDIST = 0;
    int src = 0;
    int dist = 0;

    try {
      // reading an input file
      Scanner scanner = new Scanner(new File(args[0]));
      Scanner line = new Scanner(scanner.nextLine());
      src = line.nextInt();
      dist = line.nextInt();

      while(scanner.hasNextLine()){
        Scanner scanner2 = new Scanner(scanner.nextLine());

        while(scanner2.hasNextLine()) {
          if(mtx[countSRC].length <= countDIST) {
            mtx = IncreaseDist(mtx);
          }
          int scn2 = scanner2.nextInt();
          mtx[countSRC][countDIST] = scn2;
          countDIST++;
        }
        if(mtx.length <= countDIST) {
          mtx = IncreaseSrc(mtx);
        }
        countSRC++;
        countDIST = 0;
      }
    } catch (FileNotFoundException e) { // Error when input file is not found
      System.out.println("No file \""+args[0]+"\" found!");
    }
    int count = mtx.length;
    int[] setVertex = new int[mtx.length];
    for(int i = 0; i < setVertex.length; i++) {
      setVertex[i] = i;
    }
    int[] setVertex2 = new int[mtx.length];
    for(int i = 0; i < setVertex2.length; i++) {
      setVertex2[i] = i * 100;
    }
    int[] vertexTree = new int[0];
    int[] vertexDist = new int[mtx.length];
    vertexDist[src] = 0;
    for(int i = 0; i < src; i++) {
      vertexDist[i] = 2000000;
    }
    for(int i = src + 1; i < vertexDist.length; i++) {
      vertexDist[i] = 2000000;
    }
    while(count != 0) {
      int shortestDistVert = setVertex[0];
      int v = 0;
      for(int i = 0; i < setVertex.length; i++) {
        if(vertexDist[setVertex[i]] < vertexDist[shortestDistVert]) {
          shortestDistVert = setVertex[i];
          v = i;
        }
      }
      setVertex[v] = setVertex[setVertex.length - 1];
      setVertex = decreaseList(setVertex);
      vertexTree = IncreaseList(vertexTree);
      vertexTree[vertexTree.length - 1] = setVertex2[shortestDistVert];
      for(int i = 0; i < mtx[shortestDistVert].length; i++) {
        if(i == shortestDistVert) {
          continue;
        } else if(mtx[shortestDistVert][i] == 2000000) {
          continue;
        } else {
          if(vertexDist[i] > vertexDist[shortestDistVert] + mtx[shortestDistVert][i]) {
            if(setVertex2[i] % 100 != 0) {
              setVertex2[i] = i * 100;
            }
            setVertex2[i] = setVertex2[i] + shortestDistVert;
            vertexDist[i] = vertexDist[shortestDistVert] + mtx[shortestDistVert][i];
          }
        }
      }
      count--;
    }
    count = dist;

    try {
      PrintWriter output = new PrintWriter(new File("output.txt"));
      output.print(vertexDist[dist] + ":");
      int[] path = new int[0];
      while(setVertex2[count] / 100 != src) {
        path = IncreaseList(path);
        path[path.length - 1] = count;
        count = setVertex2[count] % 100;
      }
      path = IncreaseList(path);
      path[path.length - 1] = count;
      for(int i = path.length - 1; i >= 0; i--) {
        output.print(" " + path[i]);
      }
      output.close();
    } catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
    System.out.println("output.txt created!");
  }
}
