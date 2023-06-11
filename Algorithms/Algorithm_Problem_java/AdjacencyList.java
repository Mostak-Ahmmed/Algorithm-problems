/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm_Problem;

/**
 *
 * @author HP
 */
import java.util.LinkedList;

public class AdjacencyList {
    int vertex;
    LinkedList<Integer> []list;

    public AdjacencyList(int vertex){
        this.vertex = vertex;
        list = new LinkedList[vertex];
        for(int i=0;i<vertex;i++){
            list[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int start,int destination){
        list[start].addFirst(destination);
        list[destination].addFirst(start); // un-directed graph
    }

    public void printGraph(){
            
        for (int i = 0; i < vertex ; i++) {
            if(list[i].size()>0) {
                System.out.print("Node " + i + " - ");
                for (int j = 0; j < list[i].size(); j++) {
                    System.out.print(list[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        AdjacencyList adl = new AdjacencyList(4);
        adl.addEdge(0,1);
        adl.addEdge(1,2);
        adl.addEdge(2,3);
        adl.printGraph();
    }

}

