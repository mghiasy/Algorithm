import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("test graphs");
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


    }
}
