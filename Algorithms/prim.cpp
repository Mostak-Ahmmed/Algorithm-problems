#include <bits/stdc++.h>

using namespace std;

int n, m;
int cost = 0;
const int N = 999;
const int INF = 1e9;

vector<vector<int>> g[N];
vector<int> dist(N);
vector<int> parent(N);
vector<bool> vis(N);

void primsMST(int source)
{
    // initially all distance infinity.
    for (int i = 0; i <= n; i++)
        dist[i] = INF;

    dist[source] = 0;


    set<vector<int>> s;

    // set amra distance r node ta rakhbo. INC order a thakbe
    s.insert({0, source}); //{w,vertex}

    while (!s.empty())
    {
        auto x = *(s.begin()); // pointer a thake.
        s.erase(x);            // pop kore deyar moto

        
        int w = x[0];
        int u = x[1];
        //int v = parent[x[1]];

        vis[x[1]] = true;
        

        //cout << u << " " << v << " " << w << "\n";
        cost = cost + w;

        for (auto i : g[x[1]])
        {
            if (vis[i[0]])
                continue;
            if (dist[i[0]] > i[1])
            {
                s.erase({dist[i[0]], i[0]});
                dist[i[0]] = i[1];
                s.insert({dist[i[0]], i[0]});
                //parent[i[0]] = x[1];
            }
        }
    }
}

int main()
{

    cin >> n >> m;

    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;

        // adj list with 2 values in each unit.
        g[u].push_back({v, w});
        g[v].push_back({u, w});
    }
    cout << "\n";
    primsMST(0);
    cout << cost;
}

/*
4 5
0 1 10
1 2 15
0 2 5
3 1 2
3 2 40
*/