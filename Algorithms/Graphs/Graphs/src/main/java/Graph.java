import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/** 
 * A basic Graph class. Provides a constructor for creating a graph
 * by loading up a list of Pairs. The toString() method displays the
 * vertices and edges of a graph.
 *
 * This version goes with Lesson 9
 */
public class Graph {
	private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	HashMap<Vertex,LinkedList<Vertex>> adjList = new HashMap<Vertex,LinkedList<Vertex>>();

	/**
	 * Constructs a graph from a list of pairs. A pair (A,B)
	 * is transformed into vertices A and B together with an edge A-B.
	 */
	public Graph(List<Pair> pairs){
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();
		HashMap<Edge,Edge> dupedges = new HashMap<Edge,Edge>();
		for(Pair e : pairs){
			//handle the vertices and edges simultaneously
			Vertex v1 = new Vertex(e.ob1);
			Vertex v2 = new Vertex(e.ob2);
			Edge edge = new Edge(v1,v2);
			if(!dupverts.containsKey(v1)){
				dupverts.put(v1,v1);
				vertices.add(v1);
			}
			if(!dupverts.containsKey(v2)){
				dupverts.put(v2,v2);
				vertices.add(v2);
			}

			if(!dupedges.containsKey(edge)){
				dupedges.put(edge,edge);
				edges.add(edge);
			}

			LinkedList<Vertex> l = adjList.get(v1);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1,l);

			LinkedList<Vertex> l2 = adjList.get(v2);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2,l2);
		}
	}
	
	/**
	 * Constructs a Graph object from an array of Edges. Sample usage: After
	 * finding edges of a spanning tree, turn this collection of edges
	 * into a Graph object and return
	 */
	public Graph(Edge[] inputEdges) {
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();

		for(Object ob: inputEdges) {
			if(ob.getClass() != Edge.class) continue;
			Edge e = (Edge)ob;
			//Assume dup edges are not allowed
			edges.add(e);
			Vertex v1 = e.u;
			Vertex v2 = e.v;
			if(!dupverts.containsKey(v1)){
				dupverts.put(v1,v1);
				vertices.add(v1);
			}

			if(!dupverts.containsKey(v2)){
				dupverts.put(v2,v2);
				vertices.add(v2);

			}

			LinkedList<Vertex> l = adjList.get(v1);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1,l);

			LinkedList<Vertex> l2 = adjList.get(v2);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2,l2);
		}
	}
	
	///////// public operations
	public Graph computeSpanningTree() {
		FindSpanningTree fst = new FindSpanningTree(this);
		return fst.computeSpanningTree();
	}

	public boolean pathExists(Vertex u, Vertex v) {
		PathExists pe = new PathExists(this);
		return pe.pathExists(u, v);
	}

	public boolean isConnected() {
		IsConnected ic = new IsConnected(this);
		return ic.isConnected();
	}

	public boolean hasCycle() {
		HasCycle hc = new HasCycle(this);
		return hc.hasCycle();
	}

		
	//implement
	public int numConnectedComponents() {
		
		return -1;
	}
	public int shortestPathLength(Vertex u, Vertex v){ 
		return -1;
	}
	
	public List<Graph> connectedComponents() {
		return null;
	}

	

	
	
	/**
	 * Important to return only a copy of the adjacency list. Running time for making
	 * this copy: For each vertex v, the number of adjacent vertices to v that need to
	 * be copied into a new list (matched with v in the copy of the map) is deg v. Also, each
	 * vertex is processed (cloned and the clone is added to its list); processing is O(1) Therefore,
	 * it is the sum over v in V of 1 + deg(v), which is O(n+m).
	 */
	public HashMap<Vertex,LinkedList<Vertex>> getAdjacencyList() {
		HashMap<Vertex,LinkedList<Vertex>> copy 
		  = new HashMap<Vertex,LinkedList<Vertex>>();
		for(Vertex v : adjList.keySet()) {
			copy.put(v, getListOfAdjacentVerts(v));
		}
		return copy;
	}
	/**
	 * Returns a copy of the list of adjacent vertices
	 */
	public LinkedList<Vertex> getListOfAdjacentVerts(Vertex v) {
		List<Vertex> theList = adjList.get(v);
		LinkedList<Vertex> copy = new LinkedList<Vertex>();
		if (theList != null) {
			for(Vertex vert : theList) {
				copy.add(vert.clone());
			}
		}
		return copy;
	}
	
	public boolean areAdjacent(Vertex v, Vertex w) {
		//implement
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Vertices: \n"+" ");
		for(Vertex v : vertices) {
			sb.append(v+" ");
		}
		sb.append("\nEdges:\n"+" ");
		HashMap<String,String> dups = new HashMap<String,String>();
		for(Vertex v: vertices){
			LinkedList<Vertex> l  = adjList.get(v);

			for(Vertex w : l){
				String edge = v.toString()+"-"+w.toString();
				String edgeRev = reverse(edge);
				if(!dups.containsKey(edge) && !dups.containsKey(edgeRev)){
					sb.append(edge+" ");
					dups.put(edge,edge);
				}
			}
		}
		return sb.toString();
	}
	
	/* Support method for toString() */
	private String reverse(String edge) {
		String[] vals = edge.split("-");
		return vals[1]+"-"+vals[0];
	}
	
	public List<Vertex> vertices() {
		return vertices;
	}

	public List<Edge> edges() {
		return edges;
	}

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

		
	}
}
