public class RedBlackGraphTest {

    public static void main(String[] args){
        RedBlackGraph<Character> graph = new AdjacencyRedBlackGraph<Character>();
        graph.add('A',true); 
        graph.add('B',true);
        graph.add('C',false);
        graph.add('D',true);
        graph.add('E',false);
        graph.add('F',false);
        graph.add('G',true);
        graph.add('H',false);
        graph.add('I',false);
        graph.add('J',true);
        graph.add('K',true);

        graph.connectDirected('A', 'C');
        graph.connectDirected('C', 'D');
        graph.connectDirected('H', 'C');
        graph.connectDirected('E', 'F');
        graph.connectDirected('F', 'G');
        
        graph.connectUndirected('A', 'B');
        graph.connectUndirected('B', 'E');
        graph.connectUndirected('C', 'E');
        graph.connectUndirected('B', 'D');
        graph.connectUndirected('D', 'F');
        graph.connectUndirected('J', 'K');
        graph.connectUndirected('K', 'I');
        graph.connectUndirected('I', 'J');
        
        System.out.println(graph.isBlack('A')); // false   
        System.out.println(graph.isRed('H')); // false
        System.out.println(graph.isBlack('E')); // true
        System.out.println(graph.isRed('J')); // true

        System.out.println(graph.hasRedBlackPath('A', 'G')); // true
        System.out.println(graph.hasRedBlackPath('A', 'B')); // false
        System.out.println(graph.hasRedBlackPath('B', 'F')); // false
        System.out.println(graph.hasRedBlackPath('J', 'K')); // true
        System.out.println(graph.hasRedBlackPath('I', 'K')); // true

    }
}
