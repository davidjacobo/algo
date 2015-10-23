import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Stack;

public class Graph{
	public Graph(int V,int E){
		this.V = V;
		this.E = E;
		graph = new Node[V];
		back_graph = new Node[V];
		parent = new int[V];
		color = new int[V];
		distance = new double[V];		
		cmp = new int[V];
		indegree = new int[V];
		st = new int[V];
		ft = new int[V];
		for(Node temp:graph)
			temp = null;
		for(Node temp:back_graph)
			temp = null;
	}

	public void insert(int u,int v,double w){
		Node t = graph[u];
		graph[u] = new Node(v,w);
		graph[u].next = t;

		t = back_graph[v];
		back_graph[v] = new Node(u,w);
		back_graph[v].next = t;

		++indegree[v];
	}

	public void insert(int u,int v){
		insert(u,v,0.0);
	}

	private void clearGraph() {
		for(int i=0;i<V;++i) {
			color[i] = BLACK;
			parent[i] = INV;
			distance[i] = INF;
			cmp[i] = INV;
			indegree[i] = 0;
			st[i] = ft[i] = 0;
		}
	}

	public void dfs(int u,int comp) {
		color[u] = GRAY;
		cmp[u] = comp;

		Node temp = graph[u];
		while(null!=temp) {
			int v = temp.v;
			if(BLACK==color[v]) {
				parent[v] = u;
				dfs(v, comp);
			}

			temp = temp.next;
		}

		color[u] = WHITE;
	}

	private void dfsScc(int u) {
		st[u] = TIME++;

		Node ne = graph[u];
		while(null!=ne) {
			int v = ne.v;
			if(0==st[v]) {
				parent[v] = u;
				dfsScc(v);
			}
			ne = ne.next;
		}

		ft[u] = TIME++;
		stack.add(u);
	}

	private void dfsBack(int u,int comp) {
		color[u] = GRAY;
		cmp[u] = comp;

		Node ne = back_graph[u];
		while(null!=ne) {
			int v = ne.v;
			if(BLACK==color[v]) {
				dfsBack(v, comp);
			}
			ne = ne.next;
		}

		color[u] = WHITE;
	}

	public void scc() {
		int component = 0;
		TIME = 1;
		clearGraph();
		stack = new Stack<Integer>();

		for(int i=0;i<V;++i) {
			if(st[i]==0) {
				dfsScc(i);
			}
		}

		while(!stack.empty()) {
			int u = stack.pop();
			if(BLACK==color[u]) {
				dfsBack(u,component++);
			}
		}

		System.out.println("Components per vertex");
		for(int i=0;i<V;++i) {
			System.out.print(cmp[i]+" ");
		}
		System.out.println();
	}

	public void dijkstra(int u){
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		double w;
		clearGraph();

		//we only know the distance to the very start of the path, which is 0.0
		distance[u] = 0.0;
		pq.add(new Pair(u, 0.0));

		while(!pq.isEmpty()) {
			Pair currentNode = pq.poll();
			u = currentNode.v;
			w = currentNode.w;

			//As we are not deleting duplicates on the PQ it could potentially
			//have entries for the same vertex with different proposed distances
			if(distance[u]!=w) continue;

			Node ne = graph[u];
			while(null!=ne){
				double total = w + ne.w;
				int v = ne.v;

				if(distance[v] > total) {
					distance[v] = total;
					parent[v] = u;
					pq.add(new Pair(v, total));
				}
				ne = ne.next;
			}
		}
	}

	public boolean bellmanFord(int u) {
		double w;
		clearGraph();

		distance[u] = 0.0;

		for(int i=0;i<V-1;++i) {
			for(int j=0;j<V;++j) {
				Node ne = graph[j];
				u = j;
				while(null!=ne) {
					int v = ne.v;
					w = distance[u] + ne.w;
					if(distance[v] > w) {
						distance[v] = w;
						parent[v] = u;
					}
					ne = ne.next;
				}
			}
		}

		//Now every distance is computed correcly, unless there is 
		//a negative cycle
		boolean negCycle = false;
		for(int j=0;j<V;++j) {
			Node ne = graph[j];
			u = j;
			while(null!=ne) {
				int v = ne.v;
				w = distance[u] + ne.w;
				if(distance[v] > w) {
					distance[v] = w;
					parent[v] = u;
					negCycle = true;
				}
				ne = ne.next;
			}
		}

		return negCycle;
	}

	private ArrayList<Integer> zeroIndegree() {
		ArrayList<Integer> nextVertex = new ArrayList<Integer>();

		for(int i=0;i<V;++i) {
			if(indegree[i]==0) {
				nextVertex.add(i);
			}
		}

		return nextVertex;
	}

	public ArrayList<Integer> topSort(){
		ArrayList<Integer> nextVertex;
		nextVertex = zeroIndegree();

		for(int i=0;i<nextVertex.size();++i) {
			int u = nextVertex.get(i);

			Node ne = graph[u];
			while(null!=ne) {
				int v = ne.v;
				--indegree[v];
				if(indegree[v]==0) {
					nextVertex.add(v);
				}

				ne = ne.next;
			}
		}

		System.out.println("Linearized size: "+nextVertex.size());

		return nextVertex;
	}

	public void showArrayListContent(ArrayList<Integer> lst) {
		for(int i=0;i<lst.size();++i) {
			System.out.print(lst.get(i)+" ");
		}
		System.out.println();
	}

	public void floodFill(){
		int currentColor = 0;

		clearGraph();
		for(int i=0;i<V;++i){
			if(BLACK==color[i]) {
				dfs(i, currentColor++);
			}
		}
	}

	public void showComponents(){
		for(int i=0;i<V;++i) {
			System.out.print(cmp[i]+" ");
		}
		System.out.println();
	}
	
	private int color[], parent[], cmp[], indegree[], st[], ft[];
	private double distance[];
	public Node[] graph, back_graph;
	public int V,E;
	private Stack<Integer> stack;
	private static int BLACK = 0;
	private static int GRAY = 1;
	private static int WHITE = 2;
	private static int INV = -1;
	private static int INF = (1<<28);
	private int TIME;
}

class Node{
	public Node(int v,double w){
		this.v = v;
		this.w = w;
		this.next = null;
	}

	public Node(int v){
		this(v,0.0);
	}
	public int v;
	public Node next;
	public double w;
}

class Pair implements Comparable<Pair>{
	public Pair(int v){
		this(v, 0.0);
	}

	public Pair(int v,double w){
		this.v = v;
		this.w = w;
	}

	public int compareTo(Pair otro){
		if(this.w > otro.w) return 1;
		if(this.w < otro.w) return -1;
		return 0;
	}

	public int v;
	public double w;
}