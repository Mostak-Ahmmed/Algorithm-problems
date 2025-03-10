/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algorithms;

/**
 *
 * @author Abid
 */
import java.util.*;

class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

class Graph {
    int V;
    List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();

        // Step 1: Sort the edges in ascending order of weights
        Collections.sort(edges);

        // Create a disjoint set to track components
        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        int edgeCount = 0;
        int index = 0;

        // Step 2: Process each edge in ascending order of weights
        while (edgeCount < V - 1) {
            Edge edge = edges.get(index++);
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            // Check if including the edge forms a cycle
            if (srcParent != destParent) {
                result.add(edge);
                union(parent, srcParent, destParent);
                edgeCount++;
            }
        }

        return result;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex)
            parent[vertex] = find(parent, parent[vertex]);
        return parent[vertex];
    }

    private void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[yRoot] = xRoot;
    }
}

public class Krushkal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();

        Graph graph = new Graph(V);
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        List<Edge> result = graph.kruskalMST();
        for (Edge edge : result)
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
    }
}