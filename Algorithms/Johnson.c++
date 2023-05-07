#include <bits/stdc++.h>
using namespace std;
/*
@Pankaj Mahanto @ ID: 213902002 Section:PC-213-DA
*/
const int INF = 1e9;
vector<vector<pair<int, int>>> adj;
vector<int> h;
void dijkstra(int s, vector<int>& d) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    d.assign(adj.size(), INF);
    d[s] = 0;
    pq.emplace(0, s);
    while (!pq.empty()) {
        int u = pq.top().second;
        int dist = pq.top().first;
        pq.pop();
        if (dist > d[u]) continue;
        for (auto [v, w] : adj[u]) {
            if (d[v] > d[u] + w) {
                d[v] = d[u] + w;
                pq.emplace(d[v], v);
            }
        }
    }
}
vector<vector<int>> johnson() {
    int n = adj.size();
    vector<vector<int>> dist(n, vector<int>(n, INF));
    vector<pair<int, int>> edges;
    for (int u = 0; u < n; ++u) {
        for (auto [v, w] : adj[u]) {
            edges.emplace_back(u, v);
        }
    }
    edges.emplace_back(n, 0);
    h.assign(n + 1, INF);
    h[n] = 0;
    dijkstra(n, h);
    for (auto& [u, v] : edges) {
        u = u == n ? n - 1 : u;
        v = v == n ? n - 1 : v;
    }
    for (int u = 0; u < n; ++u) {
        vector<int> d;
        dijkstra(u, d);
        for (int v = 0; v < n; ++v) {
            if (d[v] != INF) {
                dist[u][v] = d[v] - h[u] + h[v];
            }
        }
    }
    return dist;
}
int main() {
    int n, m;
    cin >> n >> m;
    adj.resize(n);
    for (int i = 0; i < m; ++i) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].emplace_back(v, w);
    }
    auto dist = johnson();
    for (int u = 0; u < n; ++u) {
        for (int v = 0; v < n; ++v) {
            if (dist[u][v] == INF) {
                cout << "INF ";
            } else {
                cout << dist[u][v] << " ";
            }
        }
        cout << endl;
    }
    return 0;
}
/*

6 8
1 2 2
1 3 4
2 4 7
3 5 3
4 6 1
5 4 2
5 6 5
2 3 1
*/