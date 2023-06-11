
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

    private List<Edge> kruskalMST() {
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

    public List<Edge> findSecondBestMinimumSpanningTree() {
        List<Edge> mst = kruskalMST();
        List<Edge> secondBestMST = new ArrayList<>();
        int minWeight = Integer.MAX_VALUE;

        // Exclude one edge at a time and find alternative MSTs
        for (int i = 0; i < mst.size(); i++) {
            Edge excludedEdge = mst.get(i);
            int[] parent = new int[vertices];
            for (int j = 0; j < vertices; j++) {
                parent[j] = j;
            }

            int edgesAdded = 0;
            int totalWeight = 0;

            for (Edge edge : edgeList) {
                if (edgesAdded == vertices - 1)
                    break;

                if (edge == excludedEdge)
                    continue;

                int root1 = find(parent, edge.source);
                int root2 = find(parent, edge.destination);

                if (root1 != root2) {
                    totalWeight += edge.weight;
                    union(parent, root1, root2);
                    edgesAdded++;
                }
            }

            if (edgesAdded == vertices -1 && totalWeight < minWeight) {
                minWeight = totalWeight;
                secondBestMST.clear();
                secondBestMST.addAll(mst);
                secondBestMST.add(excludedEdge);
            }
        }

        return secondBestMST;
    }
}

public class Kruskal_second_min {
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
        graph.addEdge(2, 5, 5);
        graph.addEdge(3, 5, 1);

        List<Edge> secondBestMST = graph.findSecondBestMinimumSpanningTree();

        System.out.println("Edges in the Second Best Minimum Spanning Tree:");
        for (Edge edge : secondBestMST) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
