#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1005, INF = 0x3f3f3f3f;

int n, m;
vector<pair<int, int>> adj[MAXN]; // adjacency list

int dist[MAXN][MAXN]; // dist[i][j] stores the shortest path from i to j

void bellman_ford(int s)
{
    int h[MAXN];
    memset(h, INF, sizeof(h));
    h[s] = 0;

    for (int i = 1; i < n; i++)
    {
        for (int u = 1; u <= n; u++)
        {
            for (auto p : adj[u])
            {
                int v = p.first, w = p.second;
                if (h[u] + w < h[v])
                {
                    h[v] = h[u] + w;
                }
            }
        }
    }

    for (int u = 1; u <= n; u++)
    {
        for (auto p : adj[u])
        {
            int v = p.first, w = p.second;
            if (h[u] + w < h[v])
            {
                throw "Negative-weight cycle detected";
            }
        }
    }

    for (int u = 1; u <= n; u++)
    {
        for (auto p : adj[u])
        {
            int v = p.first, w = p.second;
            dist[u][v] = w + h[u] - h[v];
        }
    }
}

void dijkstra(int s)
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    for (int i = 1; i <= n; i++)
    {
        dist[s][i] = INF;
    }
    dist[s][s] = 0;
    pq.push({0, s});

    while (!pq.empty())
    {
        int u = pq.top().second;
        pq.pop();

        for (auto p : adj[u])
        {
            int v = p.first, w = p.second;
            if (dist[s][u] + w < dist[s][v])
            {
                dist[s][v] = dist[s][u] + w;
                pq.push({dist[s][v], v});
            }
        }
    }
}

void johnsons_algorithm()
{
    n++;                      // add a new vertex to the graph
    adj[n].push_back({1, 0}); // add edges from the new vertex to all other vertices with weight 0

    bellman_ford(n); // Step 2: Run Bellman-Ford algorithm to find shortest path from the new vertex to all vertices

    n--; // remove the new vertex from the graph

    for (int u = 1; u <= n; u++)
    {
        dijkstra(u); // Step 4: Run Dijkstra's algorithm for each vertex in the graph
    }
}

int main()
{
    cout << "Enter the number of nodes and edges:";
    cin >> n >> m;
    cout << "Enter the edges:" << endl;
    for (int i = 1; i <= m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
    }

    memset(dist, INF, sizeof(dist));

    johnsons_algorithm();

    // Output the shortest paths
    for (int u = 1; u <= n; u++)
    {
        for (int v = 1; v <= n; v++)
        {
            if (dist[u][v] == INF)
            {
                cout << "INF ";
            }
            else
            {
                cout << dist[u][v] << " ";
            }
        }
        cout << endl;
    }

    return 0;
}
