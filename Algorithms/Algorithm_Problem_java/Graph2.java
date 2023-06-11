
package Algorithm_Problem;

/**
 *
 * @author Mostak Ahmmed
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph2 {
    
    private int numVertices;
    private ArrayList<Character> vertices;
    private boolean[][] adjMatrix;

    public Graph2(int numVertices) {
        this.numVertices = numVertices;
        this.vertices = new ArrayList<Character>();
        this.adjMatrix = new boolean[numVertices][numVertices];
    }

    public void addVertex(char vertex) {
        vertices.add(vertex);
    }

    public void addEdge(char src, char dest) {
        int srcIndex = vertices.indexOf(src);
        int destIndex = vertices.indexOf(dest);
        adjMatrix[srcIndex][destIndex] = true;
        adjMatrix[destIndex][srcIndex] = true;
    }



    public void BFS(char startVertex) {
        boolean[] vis = new boolean[numVertices];
        Queue<Integer> q = new LinkedList<Integer>();
        int start = vertices.indexOf(startVertex);
        vis[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int vertex = q.poll();
            System.out.print(vertices.get(vertex) + "->");
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[vertex][i] && !vis[i]) {
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
        System.out.println();
    }

    public void DFS(char startVertex) {
        boolean[] vis = new boolean[numVertices];
        int start = vertices.indexOf(startVertex);
        DFSUtil(start, vis);
        System.out.println();
    }

    private void DFSUtil(int vertex, boolean[] vis) {
        vis[vertex] = true;
        System.out.print(vertices.get(vertex) + "->");
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertex][i] && !vis[i]) {
                DFSUtil(i, vis);
            }
        }
    }

    public static void main(String[] args) {
            Graph2 graph = new Graph2(8);
            
            graph.addVertex('A');
            graph.addVertex('B');
            graph.addVertex('C');
            graph.addVertex('D');
            graph.addVertex('E');
            graph.addVertex('F');
            graph.addVertex('G');
            graph.addVertex('H');

            graph.addEdge('A', 'B');
            graph.addEdge('A', 'C');

            graph.addEdge('B', 'A');
            graph.addEdge('B', 'D');
             graph.addEdge('B', 'E');


            graph.addEdge('C', 'A');
             graph.addEdge('C', 'F');
              graph.addEdge('C', 'G');

            graph.addEdge('D', 'B');
            graph.addEdge('D', 'H');

            graph.addEdge('E', 'B');
            graph.addEdge('E', 'H');

            graph.addEdge('F', 'C');
            graph.addEdge('F', 'H');


            graph.addEdge('G', 'C');
            graph.addEdge('G', 'H');


            graph.addEdge('H', 'D');
            graph.addEdge('H', 'E');
            graph.addEdge('H', 'F');
            graph.addEdge('H', 'G');





        System.out.println("BFS traversal: ");
        graph.BFS('A');

        System.out.println();

        System.out.println("DFS traversal: ");
        graph.DFS('A');
        System.out.println();
    }
}





