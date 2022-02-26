public interface RedBlackGraph<E> {
    public boolean contains(E value);
    public void connectDirected(E a, E b);
    public void connectUndirected(E a, E b);
    public int size();

    public void add(E value, boolean color); // color=false if black, color=true of red
    public boolean isRed(E item);
    public boolean isBlack(E item);
    public boolean hasRedBlackPath(E a, E b);  
}
