package DFS;


import Graphs.Edge;
import Graphs.Graph;
import java.util.ArrayList;

public class FindSpanningTree extends DepthFirstSearch {
	private ArrayList<Edge> tree = new ArrayList<Edge>();
	public FindSpanningTree(Graph graph) {
		super(graph);
	}
	protected void processEdge(Edge e) {
		tree.add(e);
	}

	public Graph computeSpanningTree() {
		start();
		//tree is loaded
		Edge[] edges = tree.toArray(new Edge[0]);
		Graph newG  = new Graph(edges);
		return newG;
	}



}
