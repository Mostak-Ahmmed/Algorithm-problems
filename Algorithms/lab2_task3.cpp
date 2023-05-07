#include <bits/stdc++.h>
using namespace std;
const int N = 1000;
int inDegree[N];
vector<int> adj[N];

void addEdge(int u, int v)
{
    adj[u].push_back(v);
    inDegree[v]++;
}

void topologicalSort(int n)
{
    queue<int> q; // create a queue
    for (int i = 1; i <= n; i++)
    {
        if (inDegree[i] == 0)
        {
            q.push(i); // add nodes with in-degree 0 to the queue
        }
    }
    while (!q.empty())
    {
        int node = q.front(); // get the node from the queue
        q.pop();              // remove the node from the queue
        cout << node << " ";
        for (int adjNode : adj[node])
        {
            inDegree[adjNode]--; // decrement the in-degree of adjacent nodes
            if (inDegree[adjNode] == 0)
            {
                q.push(adjNode); // add adjacent nodes with in-degree 0 to the queue
            }
        }
    }
}

int main()
{
    int n, m;
    cout << "Enter the number of nodes and edges: ";
    cin >> n >> m;
    cout << "Enter the edges: ";
    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        addEdge(u, v);
    }
    cout << "Topological Sort: ";
    topologicalSort(n);
    return 0;
}
