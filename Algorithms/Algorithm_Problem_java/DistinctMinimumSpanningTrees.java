
import java.util.*;

class Graph {
    private int V; // Number of vertices
    private List<List<Edge>> adj; // Adjacency list

    public Graph(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Add an undirected edge to the graph
    public void addEdge(int src, int dest, int weight) {
        Edge edge1 = new Edge(src, dest, weight);
        adj.get(src).add(edge1);

        Edge edge2 = new Edge(dest, src, weight);
        adj.get(dest).add(edge2);
    }

    // Helper class to represent an edge
    private class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Helper class to represent a heap node
    private class HeapNode implements Comparable<HeapNode> {
        int vertex, key;

        HeapNode(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        public int compareTo(HeapNode node) {
            return this.key- node.key;
        }
    }

    // Count the number of distinct minimum spanning trees
    public int countDistinctMinimumSpanningTrees() {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        int distinctTrees = 0;

        // Initialize all keys to infinity
        Arrays.fill(key, Integer.MAX_VALUE);

        // Initialize the first vertex as the root
        key[0] = 0;
        parent[0] = -1;

        // Priority queue to store heap nodes
        PriorityQueue<HeapNode> pq = new PriorityQueue<>();
        pq.offer(new HeapNode(0, key[0]));

        while (!pq.isEmpty()) {
            HeapNode node = pq.poll();
            int u = node.vertex;

            mstSet[u] = true;

            for (Edge edge : adj.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!mstSet[v] && weight == key[v]) {
                    pq.offer(new HeapNode(v, key[v]));
                    parent[v] = u;
                } else if (!mstSet[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new HeapNode(v, key[v]));
                }
            }
        }

        // Count the number of distinct minimum spanning trees
        for (int i = 1; i < V; i++) {
            int u = parent[i];
            for (Edge edge : adj.get(i)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (u == v && weight < key[i]) {
                    distinctTrees++;
                    break;
                }
            }
        }

        return distinctTrees;
    }
}

public class DistinctMinimumSpanningTrees {
    public static void main(String[] args) {
        int V = 6; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.addEdge(2, 5, 5);
        graph.addEdge(3, 5, 1);

        int distinctTrees = graph.countDistinctMinimumSpanningTrees();
        System.out.println("Number of distinct minimum spanning trees: " + distinctTrees);
    }
}
