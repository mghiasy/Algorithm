package DFS;

import java.util.ArrayList;
import Graphs.Edge;
import Graphs.Graph;

public class HasCycle extends DepthFirstSearch {
	private ArrayList<Edge> tree = new ArrayList<Edge>();
	private int numTreeEdges = 0;
	private int numGraphEdges = 0;
	public HasCycle(Graph graph) {
		super(graph);
		numGraphEdges = graph.edges().size();
	}
	protected void processEdge(Edge e) {
			tree.add(e);
			++numTreeEdges;
	}

	public boolean hasCycle() {
		start();
		return numGraphEdges > numTreeEdges;
	}



}
