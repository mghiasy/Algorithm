package BFS;

import Graphs.Edge;
import Graphs.Graph;
import Graphs.Vertex;
import java.util.HashMap;

public class ShortestPathLength extends BreadthFirstSearch {
//for single graph shortest path = height in Spanning tree
    private HashMap<Vertex,Integer> levelsMap = new HashMap<>();
    public ShortestPathLength(Graph graph) {
        super(graph);
    }

    @Override
    //for first vertex == root
    protected void handleInitialVertex(Vertex v) {
        if(v != null){
            //call these method in parent
            setHasBeenVisited(v);
            processVertex(v);
            //first vertex level = 0
            levelsMap.put(v,0);
            makeVertexCurrent(v);
        }
    }

    @Override
    protected void processEdge(Edge e) {
        Vertex child=e.u;
        Vertex parent = e.v;
        int levelOfParent =levelsMap.get(parent);
       // every other vertex length = levelOfParent +1
        levelsMap.put(child,levelOfParent+1);
    }

    public int getLevel(Vertex u, Vertex v) {
        start();
        //Using hashMap can access to element with O(1)
        int vLevel =levelsMap.get(u);
        int uLevel = levelsMap.get(v);
        return Math.abs(uLevel-vLevel);
    }
}
