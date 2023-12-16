package ThreeWaterJugProblem;

import java.util.List;

public class Vertex {
	private State state;
	private Path<Vertex> path;
	private int gCost; // Actual cost to reach this state
	private int hCost; // Heuristic cost
	private int fCost; // Heuristic cost

	public Vertex() {
		this.state = new State();
		this.path = new Path<>();
		this.gCost = 0; // Initialize gCost to 0
		this.hCost = 0; // Initialize hCost to 0
	}

	public Vertex(State state) {
		this.state = state;
		this.path = new Path<>();
	}

	public static void setMaxJugsCapacity(int maxJug1, int maxJug2, int maxJug3) {
		State.setMaxJugsCapacity(maxJug1, maxJug2, maxJug3);
	}

	public State getState() {
		return state;
	}

	public Vertex full_jug1() {
		return new Vertex(state.full_jug1());
	}

	public Vertex full_jug2() {
		return new Vertex(state.full_jug2());
	}

	public Vertex full_jug3() {
		return new Vertex(state.full_jug3());
	}

	public Vertex empty_jug1() {
		return new Vertex(state.empty_jug1());
	}

	public Vertex empty_jug2() {
		return new Vertex(state.empty_jug2());
	}

	public Vertex empty_jug3() {
		return new Vertex(state.empty_jug3());
	}

	public Vertex pour_jug1_jug2() {
		return new Vertex(state.pour_jug1_jug2());
	}

	public Vertex pour_jug1_jug3() {
		return new Vertex(state.pour_jug1_jug3());
	}

	public Vertex pour_jug2_jug1() {
		return new Vertex(state.pour_jug2_jug1());
	}

	public Vertex pour_jug2_jug3() {
		return new Vertex(state.pour_jug2_jug3());
	}

	public Vertex pour_jug3_jug1() {
		return new Vertex(state.pour_jug3_jug1());
	}

	public Vertex pour_jug3_jug2() {
		return new Vertex(state.pour_jug3_jug2());
	}

	public void addToPath() {
		this.path.addVertex(this);
	}

	public List<Vertex> getPath() {
		return path.getPath();
	}

	public void setPath(List<Vertex> path) {
		this.path.setPath(path);
	}

	public void printPath() {
		path.printPath();
	}

	@Override
	public boolean equals(Object obj) {
		Vertex v = (Vertex) obj;

		if (!(v instanceof Vertex))
			return false;
		return (this.getState().equals(v.getState()));
	}
	@Override
	public int hashCode() {
		return this.getState().hashCode();
	}
	@Override
	public String toString() {
		return state.toString() + "f:" + getFCost() ;
	}

	public int getGCost() {
		return gCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}

	public int getHCost() {
		return hCost;
	}

	public void setHCost(int hCost) {
		this.hCost = hCost;
	}

    public int getFCost() {
        return gCost + hCost; // Calculate f(n) = g(n) + h(n)
    }

	public int calculateHeuristicCost(Vertex goal) {
		State currentState = this.getState();
		State goalState = goal.getState();

		int hCost = Math.abs(currentState.getJug1() - goalState.getJug1()) +
				Math.abs(currentState.getJug2() - goalState.getJug2()) +
				Math.abs(currentState.getJug3() - goalState.getJug3());

		this.setHCost(hCost);
		return hCost;
	}


}
