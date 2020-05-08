package DiGraph;


import java.util.ArrayList;
import java.util.List;
import Graphs.Vertex;
import Graphs.Pair;

public class Main {

	public static void main(String[] args) {
		List<Pair> l = new ArrayList<Pair>();
		l.add(new Pair("A","B"));
		l.add(new Pair("B","C"));
		l.add(new Pair("A","D"));
		//l.add(new Pair("B","D"));
		Digraph g = new Digraph(l);
		Vertex a = new Vertex("A");
		Vertex b  = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		String s = g.toString();
		System.out.println(s + "\n");
		
		System.out.println("Is B reachable from A? " + g.reaches(a, b));
		System.out.println("Is B reachable from C? " + g.reaches(c, b));
		System.out.println("Is D reachable from D? " + g.reaches(d, d));
		System.out.println("All vertices reachable from B: " + g.reachable(b));
		System.out.println("Graph is strongly connected? " + g.isStronglyConnected());
		System.out.println("\n======================\n");
		
		System.out.println("\nTopological sort: ");
		System.out.println(g.topologicalSort()+"\n");
		
		
		l.clear();
		l.add(new Pair("A","B"));
		l.add(new Pair("B","C"));
		l.add(new Pair("C","D"));
		l.add(new Pair("D","A"));
		g = new Digraph(l);
		s = g.toString();
		System.out.println(s + "\n");
		System.out.println("Graph is strongly connected? " + g.isStronglyConnected());
		
		
	}

}
