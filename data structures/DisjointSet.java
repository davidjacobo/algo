/*
		What does this shit do?
		
		This data structure is called disjoint-set
		
		It is used to solve the query "To which component this element belongs?"
		in almost constant time (amortized analysis needed) using path compression.
*/
import java.util.Scanner;

class DisjointSet{
	int[] cmp;
	int[] siz;
	public static void main(String[] args){
		DisjointSet exe = new DisjointSet();
		exe.begin();
	}
	
	void begin(){
		Scanner l = new Scanner(System.in);
		int V,E;
		int x,y;
		
		System.out.println("Introduce V, E: ");
		V = l.nextInt();
		E = l.nextInt();
		
		cmp = new int[V];
		siz = new int[V];
		
		init(V);
		
		for(int i=0;i<E;i++){
			x = l.nextInt();
			y = l.nextInt();
			
			union(x, y);
		}
	}
	
	//Try to mix components, unless they are already the same
	void union(int x, int y){
		int cmpx = find(cmp[x]);
		int cmpy = find(cmp[y]);
		
		if(cmpx == cmpy) return;
		else{
			if(siz[cmpx] > siz[cmpy]){
				siz[cmpx] += siz[cmpy];
				cmp[cmpy] = cmpx;
			}else{
				siz[cmpy] += siz[cmpx];
				cmp[cmpx] = cmpy;
			}
		}
	}
	
	//Let's find the root(component) of x
	int find(int x){
		if(x==cmp[x]) 
			return x;
		else
			return cmp[x] = find(cmp[x]);
 	}
	
	//Every vertex/element has his own component of size 1
	void init(int V){
		for(int i=0;i<V;i++){
			cmp[i] = i;
			siz[i] = 1;
		}
	}
}