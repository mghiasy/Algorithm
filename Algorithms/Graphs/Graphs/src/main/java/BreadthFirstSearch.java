import java.util.*;


public class BreadthFirstSearch  {
	protected HashMap<Vertex, Vertex> visitedVertices = new HashMap<>();
	HashMap<Vertex,LinkedList<Vertex>> adjacencyList;
	Graph graph;
	Queue<Vertex> queue;
	Vertex current;

	List<Vertex> vertices = null;
	Iterator<Vertex> iterator = null;
	protected int numVertices;

	public BreadthFirstSearch(Graph graph){
		queue = new LinkedList<Vertex>();
		this.graph=graph;
		vertices =graph.vertices();
		iterator = vertices.iterator();
		numVertices = vertices.size();
		//this is a copy, so we can modify it - O(n+m) to acquire this
		adjacencyList = graph.getAdjacencyList();
	}
	
	public void start(){	
		while(someVertexUnvisited()) {	
			//picks an unvisited vertex and marks it
			handleInitialVertex();		
			
			//Starting from initial vertex s, marked as visited, 
			//adds one vertex at a time to the collection of visited vertices
			//by choosing (in unspecified way) next vertex that is adjacent
			//to some visited vertex
			singleComponentLoop();	
			
			//performs necessary processing (for subclasses) between completions 
			//of successive components
			additionalProcessing();
		}
	}
	public void start(Vertex s){
		do {
			if(!getHasBeenVisited(s)) {
				handleInitialVertex(s);
			} else handleInitialVertex();
			
			singleComponentLoop();	
			
			//performs necessary processing (for subclasses) between completions 
			//of successive components
			additionalProcessing();
		} while(someVertexUnvisited());
	}
	protected void handleInitialVertex(){
		Vertex v = nextUnvisited();
		handleInitialVertex(v);
	}
	//Gives option to set starting vertex
	protected void handleInitialVertex(Vertex v) {
		if(v != null){
			setHasBeenVisited(v);
			processVertex(v);
			makeVertexCurrent(v);
		}
	}
	
	protected void singleComponentLoop(){
		while(current != null){
			Vertex nextAdj = null;
			while((nextAdj = nextUnvisitedAdjacent(current)) != null) {
				setHasBeenVisited(nextAdj);
				processEdge(new Edge(nextAdj,current));
				processVertex(nextAdj);
				queue.add(nextAdj);

			}
			current = queue.poll();
		}
	}
	
	///// template methods
	protected void additionalProcessing() {
		//by default do nothing
	}

	protected void processEdge(Edge e) {
		//override for needed functionality
	}
	
	protected void processVertex(Vertex w){
		//should be overridden; by default, do nothing
	}

	/////// next unvisited and next unvisited adjacent code
	
	public Vertex nextUnvisited() {
		while(iterator.hasNext()){
			Vertex next = iterator.next();
			if(!visitedVertices.containsKey(next)){
				return next;

			}
		}
		return null;
	}
	public Vertex nextUnvisitedAdjacent(Vertex v) {
		List<Vertex> listOfAdjacent = adjacencyList.get(v);
		Iterator<Vertex> it = listOfAdjacent.iterator();
		Vertex retVert = null;
		//this loop will execute only once for each vertex v
		//since whenever a vertex is encountered, it is removed after processing
		while(it.hasNext()) {
			Vertex u = it.next();
			if(visitedVertices.containsKey(u)) {
				it.remove();
			}
			if(!visitedVertices.containsKey(u)) {
				retVert = u;
				it.remove();
				return retVert;
			}
		}
		//unvisited adjacent vertex not found
		return retVert;  //returning null
	}
	
	////////// support methods
	
	protected void resetVisitedVertices() {
		visitedVertices.clear();
	}

	protected void resetVertexIterator() {
		iterator = vertices.iterator();
	}

	protected void makeVertexCurrent(Vertex v) {
		current = v;
	}

	protected boolean someVertexUnvisited(){
		return visitedVertices.size() < numVertices;
	}

	public boolean getHasBeenVisited(Vertex v) {
		return visitedVertices.containsKey(v);
	}
	
	public void setHasBeenVisited(Vertex v) {
		visitedVertices.put(v,v);
	}

	//for any two vertices x, y in
	//the graph G, will return a shortest path from x to y
	public List<Vertex> shortestPath(Vertex u, Vertex v){
		start(u);

	}

	//
	public int shortestPathLength(Vertex u, Vertex v){
		start(u);
	}
}
