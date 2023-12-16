package ThreeWaterJugProblem;

import java.util.PriorityQueue;

public class Test {
    public static PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.getFCost(), v2.getFCost()));

    public static void main(String[] args) {
        Vertex goal = new Vertex(new State(4,0,0));
        Vertex v1 = new Vertex(new State(0,0,0));
        v1.calculateHeuristicCost(goal);
        Vertex v2 = new Vertex(new State(0,4,0));
        v2.calculateHeuristicCost(goal);

        Vertex v3 = new Vertex(new State(1,2,0));
        v3.calculateHeuristicCost(goal);

        Vertex v4 = new Vertex(new State(4,0,0));
        v4.calculateHeuristicCost(goal);


        queue.add(v1);
        queue.add(v2);
        queue.add(v3);
        queue.add(v4);
    }
}
