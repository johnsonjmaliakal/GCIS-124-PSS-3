import java.util.ArrayList;
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
        ArrayList<RedBlackVertex<E>>visited = new ArrayList<RedBlackVertex<E>>();
        RedBlackVertex<E>v=vertices.get(a);
        visitDFS(v,visited);
        System.out.println(visited);                //extra
        if (visited.contains(vertices.get(b))){
            RedBlackVertex<E> first = vertices.get(a);
            boolean temp = first.color();
            int flag = 0; 
            int starter = 0;
            for (RedBlackVertex<E>i:visited){
                if (starter == 0) {
                    starter = 1;
                    continue;
                }
                System.out.println(i);                  //extra
                if (i.color() == !temp){
                    temp = i.color();
                    flag = 0;
                }
                else {
                    flag = 1;
                    break;
                }
                if (i == vertices.get(b)){
                    break;
                }
            }
            if (flag == 0){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    private void visitDFS(RedBlackVertex<E>v,ArrayList<RedBlackVertex<E>>visited){
        visited.add(v);
        for(RedBlackVertex<E>w:v.getNeighbors()){
            RedBlackVertex<E> test = visited.get(visited.size()-1);
            if(!visited.contains(w) && test.color() != w.color())
                visitDFS(w,visited);
        }
    }

}