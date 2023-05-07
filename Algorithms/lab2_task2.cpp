#include <bits/stdc++.h>
using namespace std;
const int N = 1000;
int visited[N], level[N];
vector<int> adj[15];
vector<int> dfslist;

void adjacentlist(int u, int v)
{
    adj[u].push_back(v);
    adj[v].push_back(u);
}

void printadjlist(int n)
{
    for (int i = 1; i <= n; i++)
    {
        cout << i << " : ";
        for (int i : adj[i])
        {
            cout << "--> " << i;
        }
        cout << endl;
    }
}

void bfs(int n)
{
    queue<pair<int, int>> q; // create a queue of pairs (node, level)
    q.push({n, 0});          // add the starting node with level 0
    visited[n] = 1;
    while (!q.empty())
    {
        int node = q.front().first; // get the node from the queue
        int lev = q.front().second; // get the level of the node from the queue
        q.pop();                    // remove the node from the queue
        level[node] = lev;          // update the level of the current node
        for (int i : adj[node])
        {
            if (visited[i] == 0)
            {
                q.push({i, lev + 1}); // add the adjacent node with level incremented by 1
                visited[i] = 1;
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
        adjacentlist(u, v);
    }
    cout << "Adjacent List :" << endl;
    printadjlist(n);
    bfs(1);
    cout << "Level of each node: " << endl;
    for (int i = 1; i <= n; i++)
    {
        cout << "Node " << i << " : " << level[i] << endl;
    }
    return 0;
}
