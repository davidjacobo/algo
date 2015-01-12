/*
	What does this shit do?
	
	It's called Dijkstra algorithm and it's used in order to 
	solve the SSSP (Single Source Shortest Path) problem.
	
	Restriction: The edges must have a non-negative value
*/

import java.util.PriorityQueue;
import java.util.Scanner;

class Dijkstra{
	int[] parent;
	boolean[] intree;
	double[] distance;
	double INF = 2000000000000000.0;
	
	public static void main(String[] args){
		Dijkstra exe = new Dijkstra();
		exe.begin();
	}
	
	void begin(){
		Scanner l = new Scanner(System.in);
		int V,E;
		int x,y;
		double w;
		
		Nodo[] graph = new Nodo[V];
		intree = new boolean[V];
		parent = new int[V];
		distance = new double[V];
		
		for(int i=0;i<E;i++){
			x = l.nextInt();
			y = l.nextInt();
			w = l.nextDouble();
			
			insert(graph, x, y, w);
			insert(graph, y, x, w);
		}
		
		System.out.println("Source = ");
		source = l.nextInt();
		dijkstra(graph, source, V);
	}
	
	void dijkstra(Nodo[] graph, int source, int V){
		PriorityQueue<Pair> pq = new PriorityQueue();
		double w;
		Pair temp;
		
		for(int i=0;i<V;i++){
			parent[i] = -1;
			distance[i] = INF;
			intree[i] = false;
		}
		
		distance[source] = 0.0;
		
		while(!intree[source]){
			intree[source] = true;
			temp = graph[source];
			
			while(temp != null){
				w = distance[source] + temp.w;
				if(w < distance[temp.y]){
					distance[temp.y] = w;
					parent[temp.y] = source;
					pq.add(new Pair(temp.y, w));
				}
				temp = temp.next;
			}
			
			while(!pq.isEmpty()){
				source = pq.poll().y;
				if(!intree[source])
					break;
			}
		}
	}
}

class Pair implements Comparable<Pair>{
	Pair(int _y, double _w){
		y = _y;
		w = _w;
	}
	
	public int compareTo(Pair otro){
		return (this.w > otro.w)?1:((this.w == otro.w)?0:-1);
	}
	int y;
	double w;
}

class Nodo{
	Nodo(int _y, double _w){
		y = _y;
		w = _w;
		next = null;
	}
	Nodo next;
	int y;
	double w;
}