

import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class Graph {
    private int vertices;
    private List<Edge> edgeList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edgeList = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }

    private List<Edge> kruskalMST(boolean findMaxSpanningTree) {
        // Sort the edges based on weight
        Collections.sort(edgeList);

        // Initialize disjoint sets for vertices
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        List<Edge> mst = new ArrayList<>();
        int edgesAdded = 0;

        for (Edge edge : edgeList) {
            if (edgesAdded == vertices - 1)
                break;

            int root1 = find(parent, edge.source);
            int root2 = find(parent, edge.destination);

            if (root1 != root2) {
                mst.add(edge);
                union(parent, root1, root2);
                edgesAdded++;
            }
        }

        if (findMaxSpanningTree) {
            // Reverse the edges in the maximum spanning tree
            for (Edge edge : mst) {
                int temp = edge.source;
                edge.source = edge.destination;
                edge.destination = temp;
            }
        }

        return mst;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex)
            parent[vertex] = find(parent, parent[vertex]);
        return parent[vertex];
    }

    private void union(int[] parent, int root1, int root2) {
        parent[root2] = root1;
    }

    public List<Edge> findMinimumSpanningTree() {
        return kruskalMST(false);
    }

    public List<Edge> findMaximumSpanningTree() {
        return kruskalMST(true);
    }
}

public class Kruskal_min_max {
    public static void main(String[] args) {
        int vertices = 6;

        Graph graph = new Graph(vertices);

        // Adding edges to the graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);

        List<Edge> minimumSpanningTree = graph.findMinimumSpanningTree();
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }

        List<Edge> maximumSpanningTree = graph.findMaximumSpanningTree();
        System.out.println("Maximum Spanning Tree:");
        for (Edge edge : maximumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}

