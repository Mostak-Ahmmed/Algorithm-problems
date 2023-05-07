#include <bits/stdc++.h>
using namespace std;

const int N = 1000;
int visited[N], parent[N], flag = 0;
vector<int> adj[15];
vector<int> bfslist;

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
        for (int j : adj[i])
        {
            cout << "--> " << j;
        }
        cout << endl;
    }
}

void bfs(int n)
{
    queue<int> q;
    q.push(n);
    visited[n] = 1;
    parent[n] = -1; // set parent of the starting node as -1
    while (!q.empty())
    {
        int node = q.front();
        q.pop();
        bfslist.push_back(node);
        for (int i : adj[node])
        {
            if (!visited[i])
            {
                visited[i] = 1;
                parent[i] = node; // set parent of the adjacent node as current node
                q.push(i);
            }
            else if (parent[node] != i)
            {
                if (flag == 0)
                {
                    cout << "Cycle detected!" << endl;
                    flag = 1;
                    // return;
                }
            }
        }
    }
    if (flag == 0)
        cout << "No cycle detected!" << endl;
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
    printadjlist(n);

    bfs(1);
    cout << "BFS: ";
    for (int i : bfslist)
    {
        cout << i << " ";
    }
}
