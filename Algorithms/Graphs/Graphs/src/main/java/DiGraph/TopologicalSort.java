package DiGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import Graphs.Vertex;

/**
 * This extension of DFS successfully outputs a topological ordering
 * as long as input graph is a DAG. If every vertex is reachable
 * from the starting vertex, the output will be a topological ordering
 * of the entire graph; if not, it will be a topological ordering
 * of all vertices in the graph that are reachable from the starting
 * vertex.  (As mentioned in the slides, this approach can be improved
 * so that all vertices are output in topologically sorted order.)
 * 
 * IMPLEMENT
 */
public class TopologicalSort extends DepthFirstSearch {
	private Vertex[] sortedVertices;
	private Vertex topSortStartVertex = null;
	public Vertex getTopSortStartVertex() {
		return topSortStartVertex;
	}
	
	/**
	 * Assumption: g is a DAG. If not, there is no guarantee concerning
	 * the nature of the output.
	 */
	public TopologicalSort(Digraph g) {
		super(g);
		computeTopStartVertex();
		//implement
		
	}
	public List<Vertex> getTopologicalOrdering() {
		//warning: will return null until this class has been implemented!
		return Arrays.asList(sortedVertices);
	}
	
	/**
	 * Finds, if possible, a vertex that has no in-vertices and sets this value
	 * in topSortStartVertex
	 * If not found, throws an IllegalStateException, indicating that
	 * the input graph is not a DAG. 
	 */
	public void computeTopStartVertex() {
		//implement
	}
	

}
