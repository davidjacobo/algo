import java.util.Scanner;

public class Dfs{
    public void dfs(Nodo[] graph,boolean[] seen,int[] parent,int V){
        seen[V] = true;
        Nodo list = graph[V];

        while(null!=list){
            if(!seen[list.y]){ 
                parent[list.y] = V;
                dfs(graph, seen, parent, list.y);
            }
            list = list.next;
        }
    }
}

class Nodo{
    Nodo(int _y){
        y = _y;
	next = null;
    }
    int y;
    Nodo next;
}
