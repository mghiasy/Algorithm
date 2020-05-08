package DiGraph;

import java.util.*;

import Graphs.Vertex;
import Graphs.Pair;


/**
 *Implementation of a directed graph
 */
public class Digraph {
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	LinkedList<DirectedEdge> directedEdges = new LinkedList<DirectedEdge>();
	
	/* For any vertex v, lists the vertices w for which (v,w) is an edge */ 
	HashMap<Vertex,LinkedList<Vertex>> outList = new HashMap<Vertex,LinkedList<Vertex>>();
	/* For any vertex v, lists the vertices w for which (w,v) is an edge */
	
	HashMap<Vertex,LinkedList<Vertex>> inList = new HashMap<Vertex,LinkedList<Vertex>>();
	
	////constructors
	/**
	 * Constructs a digraph from a list of pairs. A pair (A,B) 
	 * is transformed into vertices A and B together with a directed edge A->B.
	 */
	public Digraph(List<Pair> pairs){
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();
		HashMap<DirectedEdge,DirectedEdge> dupedges = new HashMap<DirectedEdge,DirectedEdge>();
		for(Pair e : pairs){
			//handle the vertices and edges simultaneously
			Vertex source = new Vertex(e.ob1);
			Vertex head = new Vertex(e.ob2);
			DirectedEdge edge = new DirectedEdge(source, head);
			if(!dupverts.containsKey(source)){
				dupverts.put(source,source);
				vertices.add(source);
				
			}
			if(!dupverts.containsKey(head)){
				dupverts.put(head,head);
				vertices.add(head);
				
			}
			
			if(!dupedges.containsKey(edge)){
				dupedges.put(edge,edge);
				directedEdges.add(edge);
			}
			
			LinkedList<Vertex> l = outList.get(source);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(head);
			outList.put(source,l);
			
			LinkedList<Vertex> l2 = inList.get(head);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(source);
			inList.put(head,l2);
		}	
	}
	
	/**
	 * Constructs a digraph object from an array of directed edges. 
	 */
	public Digraph(DirectedEdge[] inputEdges) {
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();
		
		for(DirectedEdge e: inputEdges) {
			//Assume dup edges are not allowed
			directedEdges.add(e);
			Vertex source = e.u;
			Vertex head = e.v;
			if(!dupverts.containsKey(source)){
				dupverts.put(source,source);
				vertices.add(source);			
			}
			
			if(!dupverts.containsKey(head)){
				dupverts.put(head,head);
				vertices.add(head);
				
			}
			
			LinkedList<Vertex> l = outList.get(source);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(head);
			outList.put(source,l);
			
			LinkedList<Vertex> l2 = inList.get(head);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(source);
			outList.put(head,l2);				
		}	
	}
		
	/**
	 * Returns a copy of the list of out-vertices for v
	 */
	public LinkedList<Vertex> getOutVertices(Vertex v) {
		List<Vertex> theList = outList.get(v);
		LinkedList<Vertex> copy = new LinkedList<Vertex>();
		if (theList != null) {
			for(Vertex vert : theList) {
				copy.add(vert.clone());
			}
		}
		return copy;
	}
	
	/**
	 * Returns a copy of the list of in-vertices for v
	 */
	public LinkedList<Vertex> getInVertices(Vertex v) {
		List<Vertex> theList = inList.get(v);
		LinkedList<Vertex> copy = new LinkedList<Vertex>();
		if (theList != null) {
			for(Vertex vert : theList) {
				copy.add(vert.clone());
			}
		}
		return copy;
	}
	
	public LinkedList<Vertex> getVertices() {
		return vertices;
	}
	
	public LinkedList<DirectedEdge> getDirectedEdges() {
		return directedEdges;
	}
	
	////////// Methods to implement
	
	/**
	 * Returns true iff Vertex v is reachable from Vertex u
	 */
	public boolean reaches(Vertex u, Vertex v) {
		List<Vertex> listOfAdjacent = getOutVertices(u);
		if (listOfAdjacent == null || listOfAdjacent.isEmpty()) return false;
		Iterator<Vertex> iterator = listOfAdjacent.iterator();
			while(iterator.hasNext()){
				Vertex vertex = iterator.next();
				if(vertex ==v) return true;
				reaches(vertex,v);
			}
		return false;
	}
	
	/**
	 * Returns a list of vertices reachable from a given vertex
	 */
	public List<Vertex> reachable(Vertex u) {
		//implement
		ArrayList<Vertex> reachableArrayList= new ArrayList<Vertex>();
		return getReachable(u,reachableArrayList);

	}
	
	/**
	 * Returns true if G is strongly connected
	 */
	public boolean isStronglyConnected() {
		//implement (optional)
		return false;
		
	}
	
	/**
	 * Returns a List of vertices in topological order.
	 * Precondition: this G is a DAG (i.e. no directed cycles)
	 * and all vertices are reachable from the start vertex.
	 * If not all are reachable, then returns list of all
	 * vertices reachable from start vertex, in topological order.
	 * Otherwise, throws IllegalStateException.
	 */
	public List<Vertex> topologicalSort() {
		TopologicalSort ts = new TopologicalSort(this);
		ts.start(ts.getTopSortStartVertex());
		return ts.getTopologicalOrdering();
	}
	 
	public String toString() {
		StringBuilder sb = new StringBuilder("Vertices: \n"+" ");
		for(Vertex v : vertices) {
			sb.append(v+" ");
		}
		sb.append("\nDirected Edges:\n"+" ");
	
		for(Vertex v: vertices){
			LinkedList<Vertex> l  = outList.get(v);
			if(l != null && !l.isEmpty()) {
				for(Vertex w : l){
					String edge = v.toString()+"->"+w.toString();
					sb.append(edge+" ");
				}
			}
		}
		return sb.toString();
	}

	public List<Vertex> getReachable(Vertex vertex,List<Vertex> reachableArrayList){
		List<Vertex> listOfAdjacent = getOutVertices(vertex);
		if (listOfAdjacent == null || listOfAdjacent.isEmpty()) {
			return null;
		}
			Iterator<Vertex> iterator = listOfAdjacent.iterator();
			while (iterator.hasNext()) {
				Vertex v = iterator.next();
				reachableArrayList.add(v);
				getReachable(v,reachableArrayList);
			}
		return reachableArrayList;
	}
	
}
