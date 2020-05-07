package DFS;

import Graphs.Graph;
import Graphs.Vertex;

public class PathExists extends DepthFirstSearch {
	private Vertex target;
	private boolean pathFound = false;
	private int numComponents = 0;
	public PathExists(Graph graph) {
		super(graph);
	}

	@Override
	public void processVertex(Vertex w) {
		//Change value of pathFound only if we are working in
		//the 0th component.
		if(w.equals(target) && numComponents == 0) pathFound = true;
	}

	@Override
	public void additionalProcessing() {
		numComponents++;
	}

	public boolean pathExists(Vertex u, Vertex v) {
		target = v;
		start(u);
		return pathFound;
	}
}
