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
        visited = visitDFS(v,visited, vertices.get(b));
        //System.out.println(visited);                   //extra
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
                //System.out.println(i);                  //extra
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

    private ArrayList<RedBlackVertex<E>> visitDFS(RedBlackVertex<E>v,ArrayList<RedBlackVertex<E>>visited, RedBlackVertex<E>end){
        System.out.print("Current visited: ");                  //extra
        System.out.println(visited);                            //extra
        visited.add(v);

        Set <RedBlackVertex<E>> n = v.getNeighbors();
        System.out.print("current element: ");                  //extra
        System.out.print(v);                                    //extra
        System.out.println(n);                                  //extra
        System.out.print("Size: ");                             //extra
        System.out.println(n.size());                           //extra

        if (n.size() == 0){
            return visited;
        }
        else{
            ArrayList <RedBlackVertex<E>> list = new ArrayList<RedBlackVertex<E>>();
            for (RedBlackVertex<E> w : n){
                list.add(w);
            }
            for(RedBlackVertex<E>w:list){
                //RedBlackVertex<E> test = visited.get(visited.size()-1);
                if (w.getNeighbors().size() == 0){
                    if (!visited.contains(w) && true){
                        visited.add(w);
                        return visited;
                    }
                    else {
                        return visited;
                    }
                }
                else {
                    if (true){
                        if (!visited.contains(w)){
                            if (w != end){
                                visitDFS(w, visited, end);
                            }
                            else{
                                visited.add(w);
                                return visited;
                            }
                        }
                    }
                }
                
            }
        }
        return visited;
    }

 private ArrayList<RedBlackVertex<E>> visitDFS2(RedBlackVertex<E>x,ArrayList<RedBlackVertex<E>>visited){ //extra function to create non recursive visitDFS, ignore
    visited.add(x);
    Set <RedBlackVertex<E>> n = x.getNeighbors();
    RedBlackVertex<E>v = x;
    while (true){
        int s = n.size();
        if (s == 0){
            break;
        }
        else {
            for(RedBlackVertex<E>w:n){
                RedBlackVertex<E> test = visited.get(visited.size()-1);
                if(test.color() != w.color()){
                    if (!visited.contains(w)){
                        visited.add(w);
                        n = w.getNeighbors();
                        v = w;
                        break;
                        }
                    }
                }
            }
        
        if (v == x){
            break;
            }
    }
    return visited;
               
    }
}