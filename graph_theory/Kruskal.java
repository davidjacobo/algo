import java.util.Scanner;
import java.util.Arrays;

class Kruskal{
	Trio[] graph;
	int[] cmp;
	int[] siz;

	public static void main(String[] args){
		Kruskal exe = new Kruskal();
		exe.begin();
	}
	
	void begin(){
		Scanner l = new Scanner(System.in);
		int V,E;
		
		int x,y;
		double w;
		
		
		System.out.println("Introduce la cantidad de V E: ");
		V = l.nextInt();
		E = l.nextInt();
		
		graph = new Trio[E];
		cmp = new int[V];
		siz = new int[V];
		
		for(int i=0;i<E;i++){
			x = l.nextInt();
			y = l.nextInt();
			w = l.nextDouble();
			
			graph[i] = new Trio(x,y,w);
 		}
		
		w = kruskal(graph, V, E);
		System.out.println("MST: "+w);
	}
	
	double kruskal(Trio[] graph,int V,int E){
		Trio temp;
		int x,y;
		double w, mst = 0.0;
		
		init(V);
		
		//do you even sort, bro?
		Arrays.sort(graph);
		
		for(int i=0;i<E;i++){
			temp = graph[i];
			x = temp.x;
			y = temp.y;
			w = temp.w;
			
			//Need to compare if they are in the same component , disjoint-set, attacks!
			mst+= (union(x,y)==1)?w:0;
		}
		
		return mst;
	}
	
	int find(int x){
		//Classic find, featuring path-compression
		return (x == cmp[x])?x:(cmp[x] = find(cmp[x]));
 	}
	
	int union(int x,int y){
		int cmpx, cmpy;
		
		cmpx = find(x);
		cmpy = find(y);
		if(cmpx == cmpy) return 0;
		else{
			if(siz[cmpx] > siz[cmpy]){
				//The tree at component X is clearly larger
				cmp[cmpx] = cmpy;
				siz[cmpy] += siz[cmpx];
			}
			else{
				cmp[cmpy] = cmpx;
				siz[cmpx] += siz[cmpy];
			}
		}
		return 1;
	}
	
	void init(int V){
		for(int i=0;i<V;i++){
			cmp[i] = i;
			siz[i] = 1;
		}
	}
}

class Trio implements Comparable<Trio>{
	Trio(int _x, int _y, double _w){
		x = _x;
		y = _y;
		w = _w;
	}
	
	public int compareTo(Trio otro){
		return (this.w > otro.w)?1:((this.w == otro.w)?0:-1);
	}
	int x,y;
	double w;
}