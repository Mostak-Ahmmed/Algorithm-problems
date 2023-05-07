#include <bits/stdc++.h>
#include <vector>
using namespace std;
const int INF = 1e9;

int main()
{
    int n, m;
    cout << "Enter the number of nodes and edges: ";
    cin >> n >> m;
    vector<int> dist(n + 1, INF);
    vector<vector<pair<int, int>>> g(n + 1);
    cout << "Enter the edges:\n";
    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        g[u].push_back({v, w});
        // g[v].push_back({u, w});
    }
    int s;
    cout << "Enter the source node: ";
    cin >> s;
    dist[s] = 0;

    for (int i = 1; i <= n - 1; i++)
    {
        for (int u = 1; u <= n; u++)
        {
            for (auto v : g[u])
            {
                int to = v.first;
                int weight = v.second;
                if (dist[u] != INF && dist[to] > dist[u] + weight)
                {
                    dist[to] = dist[u] + weight;
                }
            }
        }
    }

    for (int u = 1; u <= n; u++)
    {
        for (auto v : g[u])
        {
            int to = v.first;
            int weight = v.second;
            if (dist[u] != INF && dist[to] > dist[u] + weight)
            {
                cout << "Negative cycle detected\n";
                return 0;
            }
        }
    }

    cout << "Shortest distances from node " << s << ":\n";
    for (int i = 1; i <= n; i++)
    {
        cout << dist[i] << " ";
    }
    cout << endl;
}
/*
5 7
1 2 6
1 3 5
3 2 -2
2 4 -1
3 4 4
4 5 3
3 5 3
*/