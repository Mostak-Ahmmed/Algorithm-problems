
import java.util.*;

public class DijkstraSingleDestination {

    static class Node implements Comparable<Node> {
        int id;
        int distance;

        public Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }

    static class Edge {
        int source;
        int destination;
        int cost;

        public Edge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    private int numVertices;
    private List<List<Edge>> graph;

    public DijkstraSingleDestination(int numVertices) {
        this.numVertices = numVertices;
        graph = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int cost) {
        Edge edge = new Edge(source, destination, cost);
        graph.get(source).add(edge);
    }

    public int dijkstra(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int currentVertex = node.id;

            if (currentVertex == destination) {
                return distances[currentVertex];
            }

            if (distances[currentVertex] < node.distance) {
                continue;
            }

            for (Edge edge : graph.get(currentVertex)) {
                int newDistance = distances[currentVertex] + edge.cost;

                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance;
                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }

        return -1; // Destination not reachable
    }

    public static void main(String[] args) {
        int numVertices = 6;
        int source = 3;
        int destination = 5;

        DijkstraSingleDestination graph = new DijkstraSingleDestination(numVertices);

        // Adding edges to the graph
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 3);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 1);

        int shortestDistance = graph.dijkstra(source, destination);

        if (shortestDistance != -1) {
            System.out.println("Shortest distance from source to destination: " + shortestDistance);
        } else {
            System.out.println("Destination not reachable from the source.");
        }
    }
}

/*In this program, the DijkstraSingleDestination class represents the graph and includes methods to add edges and run Dijkstra's algorithm. The Node class represents a node in the graph with its ID and distance. The Edge class represents an edge with its source, destination, and cost.

In the dijkstra method, the algorithm is similar to the previous implementation, but it checks if the current vertex is the destination vertex. If it is, it returns the distance to the destination. If the destination vertex is not reached after processing all nodes, it returns -1 to indicate that the destination is not reachable from the source.

In the main method, an example graph is created with six vertices and edges. The source and destination vertices are specified. The Dijkstra's algorithm is then executed with the given source and destination, and the shortest distance from the source to the destination is printed.

You can run this program to see the output. It should give you the shortest distance from the source vertex to the destination vertex in the graph. If the destination is not reachable, it will indicate that as well. 
*/