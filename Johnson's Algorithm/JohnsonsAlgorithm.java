import java.util.*;
import java.io.*;

public class JohnsonsAlgorithm {
  private int srcNode;
  private int numNode;
  private int twoDMat[][];
  private int potential[];
  private BellmanFord bellmanFord;
  private Dijkstra dijkstra;
  private int[][] shortestPath;

  public static final int MAX_VALUE = 2000000;

  public JohnsonsAlgorithm(int numNode) {
    this.numNode = numNode;
    twoDMat = new int[numNode + 2][numNode + 2];
    srcNode = numNode + 1;
    potential = new int[numNode + 2];
    bellmanFord = new BellmanFord(numNode + 1);
    dijkstra = new Dijkstra(numNode);
    shortestPath = new int[numNode + 1][numNode + 1];
  }

  public void johns(int adjMatrix[][]) {
    compute(adjMatrix);

    bellmanFord.BellmanFordEval(srcNode, twoDMat);
    potential = bellmanFord.getDistances();

    int reweighted[][] = reweightGraph(adjMatrix);

    for (int src = 1; src <= numNode; src++) {
      dijkstra.dijkShorteset(src, reweighted);
      int[] result = dijkstra.getDistances();
      for (int dest = 1; dest <= numNode; dest++) {
        shortestPath[src][dest] = result[dest] + potential[dest] - potential[src];
      }
    }

    System.out.println();
    for (int src = 1; src <= numNode; src++) {
      for (int dest = 1; dest <= numNode; dest++) {
        System.out.print(shortestPath[src][dest] + " ");
      }
      System.out.println();
    }
  }

  private void compute(int adjMatrix[][]) {
    for (int src = 1; src <= numNode; src++) {
      for (int dest = 1; dest <= numNode; dest++) {
        twoDMat[src][dest] = adjMatrix[src][dest];
      }
    }
    for (int dest = 1; dest <= numNode; dest++) {
      twoDMat[srcNode][dest] = 0;
    }
  }

  private int[][] reweightGraph(int adjMatrix[][]) {
    int[][] result = new int[numNode + 1][numNode + 1];
    for (int src = 1; src <= numNode; src++) {
      for (int dest = 1; dest <= numNode; dest++) {
        result[src][dest] = adjMatrix[src][dest] + potential[src] - potential[dest];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int adjacency_matrix[][];
    int numVer;

    Scanner scanner = new Scanner(System.in);
    try {
    //  Scanner scanner = new Scanner(new File(args[0]));
      System.out.println("Number of Verticies: ");
      numVer = scanner.nextInt();
      adjacency_matrix = new int[numVer + 1][numVer + 1];

      System.out.println("Write an adjacency matrix: ");
      for (int i = 1; i <= numVer; i++) {
        for (int j = 1; j <= numVer; j++) {
          adjacency_matrix[i][j] = scanner.nextInt();
          if (i == j) {
            adjacency_matrix[i][j] = 0;
            continue;
          }
          if (adjacency_matrix[i][j] == 0) {
            adjacency_matrix[i][j] = MAX_VALUE;
          }
        }
      }

      JohnsonsAlgorithm johnsons = new JohnsonsAlgorithm(numVer);
      johnsons.johns(adjacency_matrix);
    } catch(InputMismatchException e) {
      System.out.println("Wrong input");
    }
    scanner.close();
  //
  //   try {
  //     PrintWriter output = new PrintWriter(new File("output.txt"));
  //     for(int i = 1; i <= numVer; i++) {
  //       for(int j = 1; j <= numVer; j++) {
  //         output.print(adjacency_matrix[i][j]);
  //       }
  //     }
  //     output.close();
  //   } catch (FileNotFoundException e) {
	// 		System.out.println("Error creating \"output.txt\"!");
	// 	}
  //   System.out.println("output.txt created!");
  // }
  }
}

class BellmanFord {
  private int distances[];
  private int numVertex;

  public static final int MAX_VALUE = 2000000;

  public BellmanFord(int numVertex) {
    this.numVertex = numVertex;
    distances = new int[numVertex + 1];
  }

  public void BellmanFordEval(int src, int adjMatrix[][]) {
    for (int node = 1; node <= numVertex; node++) {
      distances[node] = MAX_VALUE;
    }

    distances[src] = 0;

    for (int node = 1; node <= numVertex - 1; node++) {
      for (int srcNode = 1; srcNode <= numVertex; srcNode++) {
        for (int destNode = 1; destNode <= numVertex; destNode++) {
          if (adjMatrix[srcNode][destNode] != MAX_VALUE) {
            if (distances[destNode] > distances[srcNode] + adjMatrix[srcNode][destNode]) {
              distances[destNode] = distances[srcNode] + adjMatrix[srcNode][destNode];
            }
          }
        }
      }
    }

    for (int srcNode = 1; srcNode <= numVertex; srcNode++) {
      for (int destNode = 1; destNode <= numVertex; destNode++) {
        if (adjMatrix[srcNode][destNode] != MAX_VALUE) {
          if (distances[destNode] > distances[srcNode] + adjMatrix[srcNode][destNode]) {
            System.out.println("Negative cycle!");
          }
	      }
      }
    }
  }

  public int[] getDistances() {
    return distances;
  }
}

class Dijkstra {
  private boolean settled[];
  private boolean unsettled[];
  private int distances[];
  private int adjMatrix[][];
  private int numVertex;

  public static final int MAX_VALUE = 2000000;

  public Dijkstra(int numVertex) {
    this.numVertex = numVertex;
  }

  public void dijkShorteset(int src, int adjMatrix[][]) {
    this.settled = new boolean[numVertex + 1];
    this.unsettled = new boolean[numVertex + 1];
    this.distances = new int[numVertex + 1];
    this.adjMatrix = new int[numVertex + 1][numVertex + 1];

    int evalNode;
    for (int vertex = 1; vertex <= numVertex; vertex++) {
      distances[vertex] = MAX_VALUE;
    }

    for (int srcVertex = 1; srcVertex <= numVertex; srcVertex++) {
      for (int destVertex = 1; destVertex <= numVertex; destVertex++) {
        this.adjMatrix[srcVertex][destVertex] = adjMatrix[srcVertex][destVertex];
      }
    }

    unsettled[src] = true;
    distances[src] = 0;
    while (getCount(unsettled) != 0) {
      evalNode = getNode(unsettled);
      unsettled[evalNode] = false;
      settled[evalNode] = true;
      evalNeighb(evalNode);
    }
  }

  public int getCount(boolean unsettled[]) {
    int count = 0;
    for (int vertex = 1; vertex <= numVertex; vertex++) {
      if (unsettled[vertex] == true) {
        count++;
      }
    }
    return count;
  }

  public int getNode(boolean unsettled[]) {
    int min = MAX_VALUE;
    int node = 0;
    for (int vertex = 1; vertex <= numVertex; vertex++) {
      if (unsettled[vertex] == true && distances[vertex] < min) {
        node = vertex;
        min = distances[vertex];
      }
    }
    return node;
  }

  public void evalNeighb(int evalNode) {
    int edgeDist = -1;
    int newDist = -1;

    for (int destNode = 1; destNode <= numVertex; destNode++) {
      if (settled[destNode] == false) {
        if (adjMatrix[evalNode][destNode] != MAX_VALUE) {
          edgeDist = adjMatrix[evalNode][destNode];
          newDist = distances[evalNode] + edgeDist;
          if (newDist < distances[destNode]) {
            distances[destNode] = newDist;
          }
          unsettled[destNode] = true;
        }
      }
    }
  }

  public int[] getDistances() {
    return distances;
  }
}
