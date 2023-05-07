#include <bits/stdc++.h>
using namespace std;
const int INF = 1e9;
/*
@Pankaj Mahanto @ ID: 213902002 Section:PC-213-DA
*/
int main() {
    int n, m;
    cin >> n >> m;
    vector<vector<pair<int, int>>> adj(n); // adjacency list
    vector<int> node_cost(n); // cost of selecting each node
    for (int i = 0; i < n; i++) {
        cin >> node_cost[i];
    }
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        u--; v--;
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }
    // Dijkstra's algorithm
    vector<int> dist(n, INF);
    vector<bool> visited(n, false);
    dist[0] = node_cost[0];
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({dist[0], 0});
    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();
        if (visited[u]) continue;
        visited[u] = true;
        for (auto e : adj[u]) {
            int v = e.first;
            int w = e.second;

            if (dist[u] + w + node_cost[v] < dist[v]) {
                dist[v] = dist[u] + w + node_cost[v];
                pq.push({dist[v], v});
            }
        }
    }
    // output the shortest distance from node 1 to node n
    cout<<endl;
    for (int i = 0; i <n; i++){
        cout << dist[i] << " ";
    }
    cout << endl;
   // cout << dist[n-1] << endl;
    return 0;
}
/*
6 8
1
2
3
4
5
6
1 2 2
1 3 4
2 4 7
3 5 3
4 6 1
5 4 2
5 6 5
2 3 1


*/