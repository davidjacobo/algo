import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GraphUser{
	public static void main(String[] args) throws IOException{
		GraphUser exe = new GraphUser();
		exe.begin();
	}

	private Graph graphReader(boolean directed,boolean weighted) throws IOException{
		Scanner stdin = new Scanner(System.in);
		String filePath = stdin.next();

		Scanner l = new Scanner(new File(filePath));
		int V,E;
		int x,y;
		double w;
		V = l.nextInt();
		E = l.nextInt();
		Graph G = new Graph(V,E);

		for(int i=0;i<E;++i) {
			x = l.nextInt();
			y = l.nextInt();
			if(weighted) {
				w = l.nextDouble();
				G.insert(x,y,w);
				if(!directed) G.insert(y,x,w);		
			} else {
				G.insert(x,y);
				if(!directed) G.insert(y,x);
			}
		}

		return G;
	}			

	public void begin() throws IOException {
		ArrayList<Integer> lst;
		Graph G; 
		G = graphReader(true, false);
		//G.floodFill();
		//G.showComponents();

		//lst = G.topSort();
		//G.showArrayListContent(lst);

		G.scc();
	}
}