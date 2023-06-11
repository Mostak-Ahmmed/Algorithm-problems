
package Algorithm_Problem;

/**
 *
 * @author HP
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphChar {
static class Edge{
   char src;
   char dest;
     
        
        public Edge( char s, char d){
           this.src=s;
           this.dest=d;
          

        }
    }
    public static void createGraph(ArrayList<Edge>graph[]) {
        for(char i=0; i<graph.length; i++){
            graph[i]= new ArrayList<Edge>();

        }
        graph[a].add(new Edge(a,b))
        graph[A].add( new Edge(A, C)); 
        
         graph[B].add( new Edge(B, A));
         graph[B].add( new Edge(B, D)); 
         graph[B].add( new Edge(B, E));
       
          graph[C].add( new Edge(C, A));
          graph[C].add( new Edge(C, F));
                   graph[C].add( new Edge(C, G));
                   
      graph[D].add( new Edge(D,B ));          
        graph[D].add( new Edge(D, H));    
        
          graph[E].add( new Edge(E,B ));    
          graph[E].add( new Edge(E,H )); 
          
          graph[F].add( new Edge(F,C )); 
           graph[F].add( new Edge(F,H )); 
           
            graph[G].add( new Edge(G,C )); 
             graph[G].add( new Edge(G,H )); 
             
              graph[H].add( new Edge(H,D )); 
               graph[H].add( new Edge(H,E ));
              graph[H].add( new Edge(H,F )); 
                graph[H].add( new Edge(H,G)); 
          
    }
    public static void bfs(ArrayList<Edge>graph[],char v){
    Queue<Integer> q =new LinkedList<>();
    boolean vis[]= new boolean[v];
    q.add(0);
    
    while(!q.isEmpty()){
        int curr= q.remove();
        if(vis[curr]==false){
            System.out.println(curr+" ");  
            
            vis[curr]=true;
            
            for(int i=0; i<graph[curr].size(); i++){
                
               GraphChar.Edge e = graph[curr].get(i);
                q.add(e.dest);
                
            }
                    
        }
        
    }
    }
public static void dfs(ArrayList<Edge>graph[],char curr,boolean vis[]){
    System.out.println(curr+ " ");
    vis [curr]=true;
    for(int i=0; i<graph[curr].size(); i++){
        Edge e=graph[curr].get(i);
        if(vis[e.dest]==false)
            dfs(graph,e.dest,vis);
    }
}
    public static void main(String[] args) {
        int v=8;
        
        ArrayList<Edge> graph[]= new ArrayList[v];

        createGraph(graph);
        boolean vis[]= new boolean[v];
       bfs(graph,v); 
                           
       
          System.out.println();
        }

    }
    
