package ThreeWaterJugProblem;

import java.util.*;
import java.util.stream.Collectors;
/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 */
public class UsingAstar {
    public static int MAX_JUG1, MAX_JUG2, MAX_JUG3;

    public static PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getFCost));
    public static Set<Vertex> visited = new HashSet<Vertex>() {
    };

    public static void main(String[] args) {
        MAX_JUG1 = 3;
        MAX_JUG2 = 5;
        MAX_JUG3 = 8;

        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2, MAX_JUG3);
        Vertex initialVertex = new Vertex(new State(0, 0, 0));
        initialVertex.getState().setType(Type.INIT);

        Vertex GOAL = new Vertex(new State(3, 4, 0));
        GOAL.getState().setType(Type.GOAL);

        queue.add(initialVertex);
        initialVertex.calculateHeuristicCost(GOAL);
        System.out.println("Cài đặt thuật toán A* để giải bài toán 3 bình nước (bình 1: 3 lít, bình 2: 5 lít, bình 3: 8 lít; yêu cầu đong: 4 lít).");
        System.out.println("Init : \t"+initialVertex);
        System.out.println("Goal : \t"+GOAL);

        while (!queue.isEmpty()) {
            System.out.println("Open : \t"+queue);
            Vertex currentVertex = queue.poll();
            currentVertex.addToPath()   ;

            System.out.println("Closed :"+visited);
            System.out.println("Trạng thái đang xét : "+currentVertex);
            visited.add(currentVertex);
            if (currentVertex.equals(GOAL)) {
                System.out.println("Da tim thay : "+"\u001B[32m");
                currentVertex.printPath();
                //currentVertex.tracePath().printPath();
                currentVertex.getPath().forEach((s) -> {
                    visited.remove(s);
                });
                break;
            }
            ArrayList<Vertex> newVertices = new ArrayList<>();

            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.full_jug3());
            newVertices.add(currentVertex.empty_jug1());
            newVertices.add(currentVertex.empty_jug2());
            newVertices.add(currentVertex.empty_jug3());
            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug1_jug3());
            newVertices.add(currentVertex.pour_jug2_jug1());
            newVertices.add(currentVertex.pour_jug2_jug3());
            newVertices.add(currentVertex.pour_jug3_jug1());
            newVertices.add(currentVertex.pour_jug3_jug2());

             // System.out.println("Tất cả các trạng thái : " + newVertices);
            // xoa cac state cung voi state current
            newVertices.removeIf(vertex -> vertex.equals(currentVertex));

            // xoa cac state bi trung
            List<Vertex> temporaryList = XulyMangTrung(newVertices);
            newVertices.clear();
            newVertices.addAll(temporaryList);


            // kiểm tra xem các state con đã trong open hay chưa
            // nếu có state con nào đã có trong open thì kiểm tra f
            // nếu f lớn hơn thì không duyện state đó nữa
            // nếu f lơn hơn thì xoá state có trong open và thêm state có f nhơ hơn vào open
            temporaryList = new ArrayList<>();
            for (Vertex newVertex : newVertices) {
                newVertex.calculateHeuristicCost(GOAL);
                newVertex.setGCost(currentVertex.getGCost() + 1);
                for (Vertex q : queue) {

                    if (q.equals(newVertex)) {
                        if (q.getFCost() > newVertex.getFCost()) {
                            queue.remove(q);
                            break;
                        } else {
                            temporaryList.add(newVertex);
                        }
                    }
                }
            }
            newVertices.removeAll(temporaryList);
            temporaryList.clear();

            // kiểm tra xem các state con đã closed hay chưa
            // nếu có state con nào đã có trong closed thì kiểm tra f
            // nếu f nhỏ hơn thì sẽ tiếp tục thêm vào open
            // nếu f lớn hơn thì không duyện state này nữa
            temporaryList = new ArrayList<>();
            for (Vertex newVertex : newVertices) {
                for (Vertex v : visited) {

                    if (v.equals(newVertex)) {
                        if (v.getFCost() > newVertex.getFCost()) {
                           // queue.add(newVertex);
                          //  break;
                        }else {
                            temporaryList.add(newVertex);
                        }
                    }

                }
            }
            newVertices.removeAll(temporaryList);
            temporaryList.clear();

            System.out.println("Cac trang thai hop le : "+ newVertices);


            for (Vertex newVertex : newVertices) {

                if(!currentVertex.getPath().contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());

                    if (!visited.contains(newVertex))
                        queue.add(newVertex);
                }
            }
            sortQueue();
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

    }

    public static void sortQueue() {
        List<Vertex> sortedList = queue.stream()
                .sorted(Comparator.comparingInt(Vertex::getFCost))
                .collect(Collectors.toList());

        queue.clear();
        queue.addAll(sortedList);
    }
    public static  List<Vertex> XulyMangTrung( List<Vertex> list) {
        List<Vertex> temporaryList = new ArrayList<>();
        Set<String> seenStates = new HashSet<>();
        for (Vertex vertex : list) {
            String state = vertex.getState().getJug1() + "-" + vertex.getState().getJug2() + "-" + vertex.getState().getJug3();
            if (!seenStates.contains(state)) {
                temporaryList.add(vertex);
                seenStates.add(state);
            }
        }
        return temporaryList;
    }
}
