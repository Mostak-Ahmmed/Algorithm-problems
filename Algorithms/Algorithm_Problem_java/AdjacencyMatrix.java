
package Algorithm_Problem;

/**
 *
 * @author Mostak Ahmmed
 */

public class AdjacencyMatrix {
    int vertex;
    int[][] matrix;

    public AdjacencyMatrix(int vertex){
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    public void addEdge(int start,int destination){
        matrix[start][destination] = 1;
        matrix[destination][start] = 1;
    }

    public void printGraph(){
        System.out.println("Adjacency Matrix : ");
        for (int i = 0; i <vertex; i++) {
            for (int j = 0; j <vertex ; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrix adj = new AdjacencyMatrix(9);
        
        adj.addEdge(1,2);
        adj.addEdge(1,3);
        adj.addEdge(2,4);
        adj.addEdge(2,5);
        adj.addEdge(3,6);
        adj.addEdge(3,7);
        adj.addEdge(4,8);
        adj.addEdge(5,8);
        adj.addEdge(6,8);
        adj.addEdge(7,8);
        
        adj.printGraph();
    }
}