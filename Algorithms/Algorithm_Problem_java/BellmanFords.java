import java.util.ArrayList;
import java.util.Arrays;

class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class BellmanFord {
    private int vertice, edges;
    private ArrayList<Edge>edgeList;

    public BellmanFord(int vertices, int edges) {
        this.vertice = vertices;
        this.edges = edges;
        this.edgeList = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }

    public void bellmanFordAlgorithm(int source) {
        int[] distances = new int[vertice];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 1; i < vertice; i++) {
            for (int j = 0; j < edges; j++) {
                int u = edgeList.get(j).source;
                int v = edgeList.get(j).destination;
                int weight = edgeList.get(j).weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
            printIteration(i, distances);
        }

        // Check for negative cycles
        for (int j = 0; j < edges; j++) {
            int u = edgeList.get(j).source;
            int v = edgeList.get(j).destination;
            int weight = edgeList.get(j).weight;

            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Negative cycle detected!");
                return;
            }
        }

        System.out.println("No negative cycle detected.");
    }

    public void printIteration(int iteration, int[] distances) {
        System.out.println("Iteration: " + iteration);
        for (int i = 0; i < vertice; i++) {
            System.out.println("Vertex " + i + " Distance: " + distances[i]);
        }
        System.out.println();
    }
}

 class BellmanFords {
    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;

        BellmanFord bellmanFord = new BellmanFord(vertices, edges);

        bellmanFord.addEdge(0, 1, -1);
        bellmanFord.addEdge(0, 2, 4);
        bellmanFord.addEdge(1, 2, 3);
        bellmanFord.addEdge(1, 3, 2);
        bellmanFord.addEdge(1, 4, 2);
        bellmanFord.addEdge(3, 2, 5);
        bellmanFord.addEdge(3, 1, 1);
        bellmanFord.addEdge(4, 3, -3);

        bellmanFord.bellmanFordAlgorithm(0);
    }
}
