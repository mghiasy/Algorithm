package Graphs;


public class Edge {
	//contains of two Vertex
	Vertex u;
	Vertex v;
	public Edge(Vertex u, Vertex v) {
		this.u=u;
		this.v = v;
	}
	
	public boolean equals(Object ob) {
		if(ob ==null) return false;
		if(ob.getClass() != getClass()) return false;
		Edge other = (Edge)ob;
		return (u.equals(other.u) && v.equals(other.v)) || (u.equals(other.v) 
				&& v.equals(other.u));
	}
	public int hashCode() {
		int result = 17;
		result += 31 * result + u.hashCode();
		result += 31 * result + v.hashCode();
		return result;
	}
	@Override
	//for represent : (u,v) => An edge from u--v
	public String toString() {
		return "(" + u.toString() + ", " + v.toString() + ")";
	}
}
