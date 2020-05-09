package BFS;

import Graphs.Edge;
import Graphs.Graph;
import Graphs.Vertex;
import java.util.HashMap;

public class ShortestPathLength extends BreadthFirstSearch {

    private HashMap<Vertex,Integer> levelsMap = new HashMap<>();
    public ShortestPathLength(Graph graph) {
        super(graph);
    }

    @Override
    //for first vertex
    protected void handleInitialVertex(Vertex v) {
        if(v != null){
            //call these method in parent
            setHasBeenVisited(v);
            processVertex(v);
            levelsMap.put(v,0);
            makeVertexCurrent(v);
        }
    }

    @Override
    protected void processEdge(Edge e) {
        Vertex child=e.u;
        Vertex parent = e.v;
        int levelOfParent =levelsMap.get(parent);
        levelsMap.put(child,levelOfParent+1);
    }
}
