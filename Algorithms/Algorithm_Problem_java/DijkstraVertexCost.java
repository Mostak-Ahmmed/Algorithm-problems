

import java.util.*;

public class DijkstraVertexCost {
    
    // Represents a node in the graph
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
    
    // Represents an edge in the graph
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
    
    public DijkstraVertexCost(int numVertices) {
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
    
    public void dijkstra(int source, int[] vertexCosts) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        pq.add(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int currentVertex = node.id;
            
            if (distances[currentVertex] < node.distance) {
                continue;
            }
            
            for (Edge edge : graph.get(currentVertex)) {
                int newDistance = distances[currentVertex] + edge.cost + vertexCosts[edge.destination];
                
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance;
                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }
        
        // Print the shortest distances from the source vertex
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Shortest distance from source to vertex " + i + ": " + distances[i]);
        }
    }
    
    public static void main(String[] args) {
        int numVertices = 5;
        int source = 0;
        
       DijkstraVertexCost graph = new DijkstraVertexCost(numVertices);
        
        // Adding edges to the graph
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        
        int[] vertexCosts = {0, 2, 3, 1, 4};
        
        // Running Dijkstra's algorithm
        graph.dijkstra(source, vertexCosts);
    }
}

/*In this program, the DijkstraAlgorithm class represents the graph and includes methods to add edges and run Dijkstra's algorithm. The Node class represents a node in the graph with its ID and distance. The Edge class represents an edge with its source, destination, and cost.

In the dijkstra method, the algorithm maintains a priority queue pq to select the node with the smallest distance. It calculates the new distance for each neighbor by adding the edge cost and the vertex cost. If the new distance is smaller than the current distance, it updates the distance and adds the neighbor node to the priority queue.

In the main method, an example graph is created with five vertices and edges. The vertex costs are provided as an array. The Dijkstra's algorithm is then executed with the given source vertex and vertex costs, and the shortest distances from the source vertex to each vertex are printed.

You can run this program to see the output. It should give you the shortest distances from the source vertex to each vertex in the graph, considering both edge costs and vertex costs.
*/