

import java.util.*;

public class JohnsonAlgorithm {

    public static int[][] johnson(int[][] graph) {
        int vertices = graph.length;

        // Step 1: Add a new vertex with edges to all other vertices
        int[][] augmentedGraph = augmentGraph(graph);

        // Step 2: Run Bellman-Ford algorithm from the new vertex
        int[] h = bellmanFord(augmentedGraph, vertices);

        // Step 3: Remove the added vertex and adjust edge weights
        int[][] modifiedGraph = modifyGraph(graph, h);

        // Step 4: Run Dijkstra's algorithm for each vertex
        int[][] shortestPaths = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            int[] distances = dijkstra(modifiedGraph, i);
            shortestPaths[i] = adjustDistances(distances, h, i);
        }

        return shortestPaths;
    }

    private static int[][] augmentGraph(int[][] graph) {
        int vertices = graph.length;
        int[][] augmentedGraph = new int[vertices + 1][vertices + 1];

        // Copy the original graph
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                augmentedGraph[i][j] = graph[i][j];
            }
        }

        // Add edges from the new vertex to all other vertices
        for (int i = 0; i < vertices; i++) {
            augmentedGraph[vertices][i] = 0;
        }

        return augmentedGraph;
    }

    private static int[] bellmanFord(int[][] graph, int source) {
        int vertices = graph.length;
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax all edges V-1 times
        for (int count = 0; count < vertices - 1; count++) {
            for (int u = 0; u < vertices; u++) {
                for (int v = 0; v < vertices; v++) {
                    if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                        distance[v] = distance[u] + graph[u][v];
                    }
                }
            }
        }

        return distance;
    }

    private static int[][] modifyGraph(int[][] graph, int[] h) {
        int vertices = graph.length;

        int[][] modifiedGraph = new int[vertices][vertices];

        for (int u = 0; u < vertices; u++) {
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0) {
                    modifiedGraph[u][v] = graph[u][v] + h[u] - h[v];
                } else {
                    modifiedGraph[u][v] = 0;
                }
            }
        }

        return modifiedGraph;
    }

    private static int[] dijkstra(int[][] graph, int source) {
        int vertices = graph.length;
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        return distance;
    }

    private static int[] adjustDistances(int[] distances, int[] h, int source) {
        int vertices = distances.length;

        int[] adjustedDistances = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            adjustedDistances[i] = distances[i] - h[source] + h[i];
        }

        return adjustedDistances;
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }

    private static void printSolution(int[][] shortestPaths) {
        int vertices = shortestPaths.length;

        System.out.println("Shortest Paths:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(shortestPaths[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        int[][] graph = new int[vertices][vertices];

        System.out.println("Enter the edges and their weights (0 if no edge exists):");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        int[][] shortestPaths = johnson(graph);
        printSolution(shortestPaths);
    }
}
