package DiGraph;

import Graphs.Vertex;


public class DirectedEdge {
	Vertex u;
	Vertex v;
	public DirectedEdge(Vertex u, Vertex v) {
		this.u=u;
		this.v = v;
	}

	//the condition for being equal is different with Edge
	public boolean equals(Object ob) {
		if(ob ==null) return false;
		if(ob.getClass() != getClass()) return false;
		DirectedEdge other = (DirectedEdge)ob;
		return (u.equals(other.u) && v.equals(other.v));
	}
	
	public int hashCode() {
		int result = 17;
		result += 31 * result + u.hashCode();
		result += 31 * result + v.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return "(" + u.toString() + ", " + v.toString() + ")";
	}
}
