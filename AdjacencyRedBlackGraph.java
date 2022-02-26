import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyRedBlackGraph<E> implements RedBlackGraph<E> {

    private Map<E,RedBlackVertex<E>> vertices;

    public AdjacencyRedBlackGraph(){
        vertices = new HashMap<E,RedBlackVertex<E>>();
    }

    @Override
    public boolean contains(E item) { 
        return vertices.containsKey(item);
    }

    @Override
    public void connectDirected(E a, E b) {
        RedBlackVertex<E> va = vertices.get(a);
        RedBlackVertex<E> vb = vertices.get(b);
        va.connect(vb);
    }

    @Override
    public void connectUndirected(E a, E b) {
        RedBlackVertex<E> va = vertices.get(a);
        RedBlackVertex<E> vb = vertices.get(b);
        va.connect(vb);
        vb.connect(va);
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public void add(E item, boolean color) {
        vertices.put(item,new RedBlackVertex<E>(item,color));
    }

    @Override
    public boolean isRed(E item) {
        RedBlackVertex<E> vr = vertices.get(item);
        if (vr == null){
            throw new IllegalArgumentException("Item DNE");
        }
        return vr.color();
       
    }

    @Override
    public boolean isBlack(E item) {
        RedBlackVertex<E> vb = vertices.get(item);
        if (vb == null){
            throw new IllegalArgumentException("Item DNE");
        }
        return !vb.color();
    }

    @Override
    public boolean hasRedBlackPath(E a, E b) {
        Set<RedBlackVertex<E>>visited=new HashSet<RedBlackVertex<E>>();
        RedBlackVertex<E>v=vertices.get(a);
        visitDFS(v,visited);
        return visited.contains(vertices.get(b));
    }
    private void visitDFS(RedBlackVertex<E>v,Set<RedBlackVertex<E>>visited){
        visited.add(v);
        for(RedBlackVertex<E>w:v.getNeighbors()){
            if(!visited.contains(w))
                visitDFS(w,visited);
        }
    }

}