import Graphs.Graph;
import Graphs.Pair;
import Graphs.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Pair> l = new ArrayList<Pair>();
        l.add(new Pair("A","B"));
        l.add(new Pair("B","C"));
        l.add(new Pair("A", "C"));
		l.add(new Pair("C","D"));
		l.add(new Pair("F","E"));
		l.add(new Pair("E","A"));
		l.add(new Pair("F","G"));
		l.add(new Pair("G","H"));
		l.add(new Pair("H","F"));

        Graph g = new Graph(l);
        System.out.println(g);

        Graph tree = g.computeSpanningTree();
        System.out.println("spanning tree:");
        System.out.println(tree);


		boolean found1 = g.pathExists(new Vertex("A"), new Vertex("F"));
		boolean found2 = g.pathExists(new Vertex("A"), new Vertex("C"));
		System.out.println(found1);
		System.out.println(found2);
		System.out.println("*******************");
		boolean isConnected = g.isConnected();
		System.out.println(isConnected);

		boolean hasCycle = g.hasCycle();
		System.out.println(hasCycle);

		System.out.println(g);
		System.out.println("Shortest path length"+g.shortestPathLength(new Vertex("A"), new Vertex("G")));

//		boolean isSimpleCycle = g.isSimpleCycle();
//		System.out.println(isSimpleCycle);

    }
}
