#include<bits/stdc++.h>
using namespace std;

const int N=1e5+10;
vector<int>g[N];
bool vis[N];
int level[N];


void bfs(int source){

   queue<int>q;
   q.push(source);
   vis[source]=true;
   while (!q.empty()){
    int cur_v=q.front();
    q.pop();
    cout<<cur_v<<"-";
    for(int child:g[cur_v]){
        if(!vis[child]){
            q.push(child);
            vis[child]=true;
            level[child]=level[cur_v]+1;
        }
    }


   }
cout<<endl;
}
int main(){
    int n,m;
    cin>>n>>m;
    for(int i=0;i<m;++i){
        int v1,v2;
        cin>>v1>>v2;
        g[v1].push_back(v2);
        g[v2].push_back(v1);


    }
  bfs(1);
  for(int i=1;i<=n;++i){
    cout<< i <<"level:"<<level[i]<<endl;

  }

}

/*
6 5
1 2
1 3
3 4
2 5
4 6
*/

