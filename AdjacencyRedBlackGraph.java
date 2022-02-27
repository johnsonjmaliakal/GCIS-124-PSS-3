import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
        ArrayList<RedBlackVertex<E>> visited = new ArrayList<RedBlackVertex<E>>();
        RedBlackVertex<E> v = vertices.get(a);
        visitDFS(v,visited);
        int flag = 0;
        for (RedBlackVertex<E> i: visited){
            if (i == vertices.get(b)){
                flag = 1;
                break;
            }
            else {
                flag = 0;
            }
        }
        if (flag == 1){
            return true;
        }
        else {
            return false;
        }
    }
    

    private void visitDFS (RedBlackVertex<E> v, ArrayList<RedBlackVertex<E>> visited){
        visited.add(v);
        for (RedBlackVertex<E>w : v.getNeighbors()){
            if(!visited.contains(w) && v.color() != w.color())
                visitDFS(w,visited);
        }
    }
}