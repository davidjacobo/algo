/*
	What does this shit do?
	
	Its called Prim's algorithm and it is used
	in order to find the MST (Minimum Spanning Tree)
	
	The implementation can be reaaaaally similar to Dijkstra
	that's why you want to learn one or the other.
	The algorithm is based on the greedy selection of the closest
	vertex using a priority queue. 
	
	Havne't compiled this shit, that is up to you Prof. Cypress Hill
*/
import java.util.Scanner;
import java.util.PriorityQueue;

class Prim{
	double INF = 2000000000L;
	boolean[] intree;
	double[] distance;
	
	public static void main(String[] args){
		Prim exe = new Prim();
		exe.begin();
	}
	
	public begin(){
		Scanner l = new Scanner(System.in);
		int V,E;
		int x,y;
		double w, totalDistance;
		
		System.out.println("Introduce V E: ");
		V = l.nextInt();
		E = l.nextInt();
		
		Nodo[] graph = new Nodo[V];
		tree = new boolean[V];
		distance = new double[V];
		
		//We obtain all the edges from the user
		for(int i=0;i<E;i++){
			x = l.nextInt();
			y = l.nextInt();
			w = l.nextDouble();
			
			insert(graph, x, y, w);
			insert(graph, y, x, w);
		}
		
		init(V);
		totalDistance = prim(graph);
		System.out.println("Sum of optimal edges: "+totalDistance);
	}
	
	double prim(Nodo[] graph){
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		double totalDistance;
		Pair temp;
		Nodo list;
		//Arbitrary number, doesn't matter
		int source = 0;
		
		totalDistance = 0.0;
		distance[source] = 0;
		pq.add(new Pair(source, 0));
		
		while(!intree[source]){
			intree[source] = true;
			list = graph[source];
			totalDistance += distance[source];
			
			while(list != null){
				if(list.w < distance[list.y]){
					distance[list.y] = list.w;
					pq.add(new Pair(list.y, list.w));
				}
				
				list = list.next;
			}
			
			while(!pq.isEmpty()){
				source = pq.poll().y;
				if(!intree[source])
					break;
			}
		}
	}
	
	void init(int V){
		for(int i=0;i<V;i++){
			intree[i] = false;
			distance[i] = INF;
		}
	}
	
	void insert(Nodo[] graph, int x, int y, double w){
		Nodo temp = graph[x];
		
		graph[x] = new Nodo(y, w);
		if(temp != null) 
			graph[x].next = temp;
	}
}

class Nodo{
	Nodo(int _y,double _d){
		next = null;
		y = _y;
		w = _d;
	}
	Nodo next;
	int y;
	double w;
}

class Pair implements Comparable<Pair>{
	Pair(int _y,double _d){
		y = _y;
		w = _d;
	}
	
	public int compareTo(Pair otro){
		return (this.w > otro.w)?1:((this.w == otro.w)?0:-1);
	}
	int y;
	double w;
}