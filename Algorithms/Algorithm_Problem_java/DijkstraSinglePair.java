

import java.util.*;

public class DijkstraSinglePair {

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

    public DijkstraSinglePair(int numVertices) {
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
        int source = 4;
        int destination = 5;

        DijkstraSinglePair graph = new DijkstraSinglePair(numVertices);

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
