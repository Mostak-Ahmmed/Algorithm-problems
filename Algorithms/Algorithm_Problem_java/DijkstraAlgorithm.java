

import java.util.*;

public class DijkstraAlgorithm {

    public static void dijkstra(int[][] graph, int source) {
        int vertices = graph.length;
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && distance[u] + 1 < distance[v]) {
                    distance[v] = distance[u] + 1;
                    queue.add(v);
                }
            }
        }

        printSolution(distance);
    }

    private static void printSolution(int[] distance) {
        int length = distance.length;
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < length; i++) {
            System.out.println(i + " \t\t " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        int[][] graph = new int[vertices][vertices];

        System.out.println("Enter the edges (source and destination): ");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph[source][destination] = 1; // Assuming unweighted graph
        }

        System.out.print("Enter the source vertex: ");
        int sourceVertex = scanner.nextInt();

        scanner.close();

        dijkstra(graph, sourceVertex);
    }
}

