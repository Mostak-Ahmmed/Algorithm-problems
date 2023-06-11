
package Algorithm_Problem;

import java.util.ArrayList;



/**
 *
 * @author Mostak Ahmmed
 */
public class Onepath {
    
 static class Edge{
        int src;
        int dest;
     
        
        public Edge(int s,int d){
            this.src=s;
            this.dest=d;
          

        }
    }
 
    public static void createGraph(ArrayList<Edge>graph[]) {
        for(int i=0; i<graph.length; i++){
            graph[i]= new ArrayList<Edge>();

        }
       
        graph[1].add( new Edge(1, 2)); 
        graph[1].add( new Edge(1, 3)); 

        graph[2].add( new Edge(2, 1)); 
        graph[2].add( new Edge(2, 4)); 
         graph[2].add( new Edge(2, 5)); 
        
        graph[3].add( new Edge(3, 1)); 
        graph[3].add( new Edge(3, 7));
        graph[3].add( new Edge(3, 5));

        graph[4].add( new Edge(4, 2)); 
        graph[4].add( new Edge(4, 8)); 
     
        
        
        graph[5].add( new Edge(5, 2));
        graph[5].add( new Edge(5, 8));
        
         
      graph[6].add( new Edge(6, 3));
        graph[6].add( new Edge(6, 8));
        
         graph[7].add( new Edge(7, 3));
         graph[7].add( new Edge(7, 8));
          
      graph[8].add( new Edge(8, 4));   
      graph[8].add( new Edge(8, 5)); 
      graph[8].add( new Edge(8, 6)); 
      graph[8].add( new Edge(8, 7)); 
        
    }
    
public static void printAllPath(ArrayList<Edge>graph[],boolean vis[],int curr,String path,int tar){
   
    if(curr==tar){
    System.out.println("Path 1 node to 4 :" + path);  //base case
       return;
    }
   
     for(int i=0; i<graph[curr].size(); i++){ //DFS case
        Edge e=graph[curr].get(i);
        if(!vis[e.dest]){
            
           vis[curr] = true;
       
            printAllPath(graph ,vis ,e.dest, path+e.dest ,tar);
         
    
    }
    }
}


    public static void main(String[] args) {
        int v=9;
        
        ArrayList<Edge> graph[]= new ArrayList[v];

        createGraph(graph);
         int src=1, tar=4;
         
           printAllPath(graph, new boolean[v] ,src ,"1" ,tar);
          
       

    }
}

