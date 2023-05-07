#include<bits/stdc++.h>
using namespace std;

const int N=9999;
vector<int>parent(N);



void makeset(int v)
{
    parent[v]=v;
}


int findset(int v)
{
    if(v==parent[v])
        return v;
    return findset(parent[v]);
}


void unionset (int a,int b)
{
    int x=findset(a);
    int y=findset(b);
    if(x!=y)
        parent[b]=a;
}

int main()
{
    int n,m;
    int cost=0;
    bool flag=false;
    cin >>n>>m;

    vector<vector<int>>edges;

    for(int i=0; i<N; i++)
        makeset (i);

    for(int i=0; i<m; i++)
    {
        int u,v,w;

        cin>> u>>v>>w;

        edges.push_back({w,u,v});


}    sort(edges.begin(),edges.end());

    for(auto i:edges)
    {
        int w=i[0];
        int u=i[1];
         int v=i[2];

        int x=findset(u);
        int y=findset(v);
        if(x==y)

            continue;

        else{
            cost=cost+w;
            unionset(u,v);
        }

    }

        cout<<cost;


}

