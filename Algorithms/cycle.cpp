// C++ program to detect cycle
// in an undirected graph
// using BFS.
#include <bits/stdc++.h>
using namespace std;



bool isCyclicConntected(int src,unordered_map<int,bool>&vis,unordered_map<int,list<int>>&adj)
{
	unordered_map<int,int>parent;
	parent[src]=-1;
	vis[src]=1;
	queue<int>q;
	q.push(src);

	while (!q.empty()) {

		// Dequeue a vertex from queue and print it
		int front= q.front();
		q.pop();
		
		for (auto child : adj[front]) {
			if (!vis[child]==true&& child!=parent[front]){
				return true;
			} 
			else if(!vis[child]){
				
				q.push(child);
				vis[child]=1;
			parent[child] = front;
			}
			
		}
	}
	return false;
}

int main(){
bool Cyclicdetection(vector<vector<int>>& edges, int n,int m)
{

	
unordered_map<int, list<int> >adj;

	for (int i = 0; i <m; i++){
	int u=edges[i][0];
	int v=edges[i][1];	


	adj[u].push_back(v);
	adj[v].push_back(u);

}

unordered_map<int,bool>vis;

for(int i=0; i<m;i++){
	if(!vis[i]){
		bool ans=isCyclicConntected(i,vis,adj);
		if(ans==1)
		return "yes";
	}

}
return"NO";

}
}
