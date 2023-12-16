package ThreeWaterJugProblem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class UsingAStar {
    public static int MAX_JUG1, MAX_JUG2, MAX_JUG3, GOAL;

    public static PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(new VertexComparator());
    public static Set<Vertex> visited = new HashSet<>();

    public static class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex v1, Vertex v2) {
            // So sánh các đỉnh dựa trên giá trị f(n)
            int f1 = v1.getFCost();
            int f2 = v2.getFCost();
            return Integer.compare(f1, f2);
        }
    }

    public static void main(String[] args) {
        MAX_JUG1 = 8;
        MAX_JUG2 = 5;
        MAX_JUG3 = 3;
        GOAL = 4;
        int count = 1;

        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2, MAX_JUG3);

        Vertex initialVertex = new Vertex(new State(0, 0, 0));
        initialVertex.setGCost(0);
        initialVertex.setHCost(manhattanDistance(initialVertex.getState()));
        priorityQueue.add(initialVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex currentVertex = priorityQueue.poll();
            visited.add(currentVertex);

            if (currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL || currentVertex.getState().getJug3() == GOAL) {
                System.out.print(count + " ");
                currentVertex.printPath();
                count++;
                continue;
            }

            ArrayList<Vertex> newVertices = new ArrayList<>();

            newVertices.add(createNewVertex(currentVertex, currentVertex.full_jug1()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.full_jug2()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.full_jug3()));

            newVertices.add(createNewVertex(currentVertex, currentVertex.empty_jug1()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.empty_jug2()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.empty_jug3()));

            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug1_jug2()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug1_jug3()));

            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug2_jug1()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug2_jug3()));

            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug3_jug1()));
            newVertices.add(createNewVertex(currentVertex, currentVertex.pour_jug3_jug2()));

            for (Vertex newVertex : newVertices) {
                if (!visited.contains(newVertex)) {
                    priorityQueue.add(newVertex);
                }
            }
        }
    }

    private static int manhattanDistance(State state) {
        int h1 = Math.abs(state.getJug1() - GOAL);
        int h2 = Math.abs(state.getJug2() - GOAL);
        int h3 = Math.abs(state.getJug3() - GOAL);
        return h1 + h2 + h3;
    }

    private static Vertex createNewVertex(Vertex currentVertex, Vertex newVertex) {
        newVertex.setGCost(currentVertex.getGCost() + 1);
        newVertex.setHCost(manhattanDistance(newVertex.getState()));
        newVertex.setPath(new ArrayList<>(currentVertex.getPath()));
        return newVertex;
    }
}
