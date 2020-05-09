package BFS;

import Graphs.Graph;
import Graphs.Vertex;
import java.util.HashMap;

public class ShortestPathLength extends BreadthFirstSearch {
    
    private HashMap<Vertex,Integer> levelsMap = new HashMap<>();
    public ShortestPathLength(Graph graph) {
        super(graph);
    }
}
