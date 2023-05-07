#include <bits/stdc++.h>
using namespace std;

struct Edge
{
    int v, weight;
    Edge(int vv, int ww) : v(vv), weight(ww) {}
};

struct Graph
{
    int V;
    vector<vector<Edge>> adj;
};

int count_msts(Graph& g)
{
    int count = 0;
    vector<int> parent(g.V, -1);
    vector<int> dist(g.V, INT_MAX);
    vector<bool> visited(g.V, false);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    for (int s = 0; s < g.V; s++)
    {
        dist.assign(g.V, INT_MAX);
        visited.assign(g.V, false);
        pq.push(make_pair(0, s));
        dist[s] = 0;

        while (!pq.empty())
        {
            int u = pq.top().second;
            pq.pop();
            if (visited[u])
            {
                continue;
            }
            visited[u] = true;
            for (Edge& e : g.adj[u])
            {
                int v = e.v;
                int weight = e.weight;
                if (!visited[v] && weight < dist[v])
                {
                    dist[v] = weight;
                    parent[v] = u;
                    pq.push(make_pair(dist[v], v));
                }
            }
        }

        vector<Edge> edges;
        for (int i = 1; i < g.V; i++)
        {
            int u = parent[i];
            int v = i;
            int weight = 0;
            for (Edge& e : g.adj[u])
            {
                if (e.v == v)
                {
                    weight = e.weight;
                    break;
                }
            }
            edges.push_back(Edge(v, weight));
        }

        sort(edges.begin(), edges.end(), [](Edge& a, Edge& b)
        {
            return a.weight < b.weight;
        });

        vector<int> rank(g.V, 0);
        vector<int> parent(g.V, -1);
        for (int i = 0; i < g.V; i++)
        {
            parent[i] = i;
        }
        for (int i = 0; i < edges.size(); i++)
        {
            int u = edges[i].v;
            int v = parent[u];
            while (u != v)
            {
                parent[u] = v;
                u = parent[u];
                v = parent[v];
            }
            if (parent[edges[i].v] != parent[edges[i-1].v] || i == 0)
            {
                count++;
            }
        }
    }
    return count;
}

int main()
{
    int V = 5;
    Graph g;
    g.V = V;
    g.adj = vector<vector<Edge>>(V);

    g.adj[0].push_back(Edge(1, 2));
    g.adj[1].push_back(Edge(0, 2));

    g.adj[0].push_back(Edge(2, 3));
    g.adj[2].push_back(Edge(0, 3));

    g.adj[1].push_back(Edge(2, 1));
    g.adj[2].push_back(Edge(1, 1));

    g.adj[1].push_back(Edge(3, 4));
    g.adj[3].push_back(Edge(1, 4));

    g.adj[2].push_back(Edge(3,5));
    g.adj[2].push_back(Edge(4, 6));
    g.adj[4].push_back(Edge(2, 6));

    g.adj[3].push_back(Edge(4, 7));
    g.adj[4].push_back(Edge(3, 7));

    int msts = count_msts(g);
    cout << "Number of distinct minimum spanning trees: " << msts << endl;

    return 0;
}
