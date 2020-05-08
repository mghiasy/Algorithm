package DiGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import Graphs.Vertex;

/**
 * This version of DFS is for digraphs. It requires, in addition
 * to an input digraph, an input starting vertex. It locates all
 * vertices reachable from the starting vertex.
 */
public class DepthFirstSearch {

    protected HashMap<Vertex, Vertex> visitedVertices =
            new HashMap<Vertex, Vertex>();
    Digraph graph;
    Stack<Vertex> stack;
    List<Vertex> vertices = null;
    Iterator<Vertex> iterator = null;
    List<DirectedEdge> edges = null;
    HashMap<DirectedEdge, DirectedEdge> edgesVisitedDuringDFS =
            new HashMap<DirectedEdge, DirectedEdge>();
    protected int numVertices;
    protected int numEdges;
    protected Vertex startVertex;

    public DepthFirstSearch(Digraph graph) {
        stack = new Stack<Vertex>();
        this.graph = graph;
        vertices = graph.vertices;
        iterator = vertices.iterator();
        edges = graph.directedEdges;
        numVertices = vertices.size();
        numEdges = edges.size();
    }

    protected void resetVisitedVertices() {
        visitedVertices.clear();
    }

    protected void resetVertexIterator() {
        iterator = vertices.iterator();
    }

    public void reset() {
        resetVisitedVertices();
        resetVertexIterator();
    }

    public List<Vertex> getVisitedVerticesAsList() {
        ArrayList<Vertex> retval = new ArrayList<>();
        retval.addAll(Arrays.asList(
                visitedVertices.keySet().toArray(new Vertex[0])));
        return retval;
    }

    public HashMap<Vertex, Vertex> getVisitedVertices() {
        return visitedVertices;
    }

    protected boolean someVertexUnvisited() {
        return visitedVertices.size() < numVertices;
    }

    /**
     * This version of start is for traversing all nodes reachable from
     * a starting input vertex s
     *
     * @param useReversedEdges - consider the graph consisting of all (w,v)
     *                         for which (v,w) is in E.
     */
    public void start(Vertex s) {
        startVertex = s;

        //process vertex and place on stack
        handleInitialVertex();

        //search for deeper nodes and backtrack, in a loop
        mainLoop();
    }

    /**
     * This version of start is for performing traversing all vertices --
     * useful for obtaining a directed tree or determining if there is a directed
     * cycle
     */
    public void start() {
        while (someVertexUnvisited()) {
            startVertex = nextUnvisited();

            //process vertex and place on stack
            handleInitialVertex();

            //search for deeper nodes and backtrack as necessary
            //starts on  a new component after a component has been completely visited
            mainLoop();

            additionalProcessing();

        }
    }

    protected void additionalProcessing() {
        //do nothing
    }


    //inserts an initial vertex into the stack as preparation for
    //dfs for this component
    protected void handleInitialVertex() {
        if (startVertex != null) {
            setHasBeenVisited(startVertex);
            stack.push(startVertex);
        }
    }

    protected void mainLoop() {

        while (!stack.isEmpty()) {
            Vertex v = null;
            v = nextUnvisitedAdjacent(stack.peek());
            if (v == null) {
                //no unvisited vertices adjacent to v
                processVertex(stack.pop());
            } else {
                setHasBeenVisited(v);
                processEdge(new DirectedEdge(stack.peek(), v));
                stack.push(v);
            }
        }
    }

    protected void processEdge(DirectedEdge e) {
        //override for needed functionality
    }

    public boolean getHasBeenVisited(Vertex v) {
        return visitedVertices.containsKey(v);
    }

    public void setHasBeenVisited(Vertex v) {
        visitedVertices.put(v, v);
    }

    protected void processVertex(Vertex w) {
        //should be overridden; by default, do nothing
    }

    /**
     * Not used by DFS directly but is useful sometimes
     * for subclasses.
     */
    public Vertex nextUnvisited() {
        while (iterator.hasNext()) {
            Vertex next = iterator.next();
            if (!visitedVertices.containsKey(next)) {
                return next;

            }
        }
        return null;
    }

    public Vertex nextUnvisitedAdjacent(Vertex v) {

        List<Vertex> listOfAdjacent = null;
        listOfAdjacent = graph.getOutVertices(v);

        if (listOfAdjacent == null || listOfAdjacent.isEmpty()) return null;
        Iterator<Vertex> it = listOfAdjacent.iterator();
        Vertex retVert = null;
        boolean found = false;
        while (it.hasNext() && !found) {
            Vertex u = it.next();
            if (visitedVertices.containsKey(u)) {
                it.remove();
            }
            if (!visitedVertices.containsKey(u)) {
                retVert = u;
                it.remove();
                return retVert;
            }
        }
        return retVert;
    }
}
