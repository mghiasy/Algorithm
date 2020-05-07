package BFS;


import Graphs.Edge;
import Graphs.Graph;

import java.util.*;

public class FindSpanningTree extends BreadthFirstSearch {
	private ArrayList<Edge> tree = new ArrayList<Edge>();
	public FindSpanningTree(Graph graph) {
		super(graph);
	}
	protected void processEdge(Edge e) {
			tree.add(e);
	}

	public Graph computeSpanningTree() {
		//do BFS
		start();
		//tree is loaded
		Edge[] edges = tree.toArray(new Edge[0]);
		Graph newG  = new Graph(edges);
		return newG;
	}

}
