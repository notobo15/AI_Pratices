package ThreeWaterJugProblem;

import java.util.*;

public class UsingAStar2 {
	public static int MAX_JUG1, MAX_JUG2, MAX_JUG3;
	public class VertexComparator implements Comparator<Vertex> {
		@Override
		public int compare(Vertex v1, Vertex v2) {
			// Compare based on fCost (gCost + hCost)
			return Integer.compare(v1.getFCost(), v2.getFCost());
		}
	}
	//public static Queue<Vertex> queue = new LinkedList<>();
   // public static PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.getFCost(), v2.getFCost()));
	public static PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getFCost));
	public static Set<Vertex> visited = new HashSet<Vertex>() {

	};

	public static void main(String[] args) {
		MAX_JUG1 = 8;
		MAX_JUG2 = 5;
		MAX_JUG3 = 3;
		Vertex GOAL = new Vertex(new State(4, 0, 0));
	
		int count = 1;

		Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2, MAX_JUG3);

		Vertex initialVertex = new Vertex(new State(0, 0, 0));
		initialVertex.calculateHeuristicCost(GOAL);
		queue.add(initialVertex);
		visited.add(initialVertex);
		int i = 0;
		while (!queue.isEmpty()) {
			i++;
			Vertex currentVertex = queue.poll();

			currentVertex.addToPath();

			if (currentVertex.getState().getJug1() == GOAL.getState().getJug1()
					&& currentVertex.getState().getJug2() == GOAL.getState().getJug2()
					&& currentVertex.getState().getJug3() == GOAL.getState().getJug3()) {
				System.out.print(count + " ");
				currentVertex.printPath();
				count++;
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

			// tạo ra các nút con
			// sao đó tính F
			//
			newVertices.removeIf(vertex -> vertex.equals(currentVertex));


			List<Vertex> temporaryList = new ArrayList<>();
			Set<String> seenStates = new HashSet<>();

			for (Vertex vertex : newVertices) {
				String state = vertex.getState().getJug1() + "-" + vertex.getState().getJug2() + "-" + vertex.getState().getJug3();
				if (!seenStates.contains(state)) {
					temporaryList.add(vertex);
					seenStates.add(state);
				}
			}

			newVertices.clear(); // Xóa tất cả các phần tử trong newVertices
			newVertices.addAll(temporaryList); // Thêm tất cả các phần tử từ temporaryList vào newVertices

			for (Vertex newVertex : newVertices) {
				newVertex.calculateHeuristicCost(GOAL);

				if (!currentVertex.getPath().contains(newVertex)) {
					newVertex.setPath(currentVertex.getPath());
					newVertex.setGCost(currentVertex.getGCost()+1);

					if (!visited.contains(newVertex))
						queue.add(newVertex);

				}
			}

			visited.add(currentVertex);
		}
		System.out.println(i);
	}

}