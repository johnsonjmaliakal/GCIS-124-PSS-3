import java.util.HashSet;
import java.util.Set;

public class RedBlackVertex<E> {
    private E value;
    private Set<RedBlackVertex<E>> neighbors;
    private boolean color;

    public RedBlackVertex(E value, boolean color){
        this.value = value;
        this.color = color;
        neighbors = new HashSet<RedBlackVertex<E>>();
    }

    public boolean color(){
        return color;
    }

    public E getValue() {
        return value;
    }

    public Set<RedBlackVertex<E>> getNeighbors() {
        return neighbors;
    }

    public void connect(RedBlackVertex<E> e){
        neighbors.add(e);
    }

    public boolean connected(RedBlackVertex<E> e){
        return neighbors.contains(e);
    }

    @Override
    public String toString() {
        return "Vertex [value=" + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
           return true;
        if(obj==null || !(obj instanceof RedBlackVertex))
           return false;
        RedBlackVertex<E> other = (RedBlackVertex<E>) obj;
        return this.value.equals(other.equals(obj));      
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
