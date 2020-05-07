package BFS;

import Graphs.Edge;
import Graphs.Graph;
import Graphs.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class BreadthFirstSearch  {
	//It is easier to set a map for Visited instead of Array
	//On O(1) we can check an specific Vertex is visited or not
	protected HashMap<Vertex, Vertex> visitedVertices = new HashMap<>();
	HashMap<Vertex,LinkedList<Vertex>> adjacencyList;
	Graph graph;
	//use Java collection for Queue
	Queue<Vertex> queue;
	Vertex current;

	List<Vertex> vertices = null;
	Iterator<Vertex> iterator = null;
	protected int numVertices;

	public BreadthFirstSearch(Graph graph){
		queue = new LinkedList<Vertex>();
		this.graph=graph;
		//this is a copy
		vertices =graph.vertices();
		//we can call method of Iterator on any collection Type
		iterator = vertices.iterator();
		numVertices = vertices.size();
		//this is a copy, so we can modify it - O(n+m) to acquire this
		adjacencyList = graph.getAdjacencyList();
	}

	//Do BFS on Graph from a random Vertex
	public void start(){
		//Do in a loop foe all the vertexes
		while(someVertexUnvisited()) {	

			//Choose a random Vertex and handle it
			Vertex v = nextUnvisited();
			//Starting from initial vertex s, marked as visited
			handleInitialVertex(v);
			

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
			} else {
				Vertex v = nextUnvisited();
				handleInitialVertex(v);
			}
			//process on adjacent of vertex
			singleComponentLoop();	
			
			//performs necessary processing (for subclasses) between completions 
			//of successive components
			additionalProcessing();
		} while(someVertexUnvisited());
	}

	//Set as Visited and make it as current Vertex
	protected void handleInitialVertex(Vertex v) {
		if(v != null){
			setHasBeenVisited(v);
			processVertex(v);
			makeVertexCurrent(v);
		}
	}

	protected void singleComponentLoop(){
		//get current from Queue
		while(current != null){
			Vertex nextAdj = null;
			//add all unvisited adjacent to queue and mark them as visited
			while((nextAdj = nextUnvisitedAdjacent(current)) != null) {
				setHasBeenVisited(nextAdj);
				processEdge(new Edge(nextAdj,current));
				processVertex(nextAdj);
				queue.add(nextAdj);

			}
			//returns and removes the element at the front the container
			//when the Queue is empty, it returns null.
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
}
