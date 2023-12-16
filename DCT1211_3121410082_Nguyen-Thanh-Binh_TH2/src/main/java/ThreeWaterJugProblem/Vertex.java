/*
    Nguyen Tuan Dang
    Faculty of Information Technology, Saigon University
    dangnt@sgu.edu.vn
*/

package ThreeWaterJugProblem;

import java.util.List;
/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 */
public class Vertex {
    private State state;
    //private Vertex parent;
    private int gCost; // Actual cost to reach this state
    private int hCost; // Heuristic cost
    private Path<Vertex> path;
    public Vertex(){
        this.state = new State();
       // this.parent = null;
        this.gCost = 0; // Initialize gCost to 0
        this.hCost = 0; // Initialize hCost to 0
        this.path = new Path<>();
    }

    public Vertex(State state){
        this.state = state;
       // this.parent = null;
        this.path = new Path<>();
    }
    public void addToPath() {
        this.path.addVertex(this);
    }
    public static void setMaxJugsCapacity(int maxJug1, int maxJug2, int maxJug3) {
        State.setMaxJugsCapacity(maxJug1, maxJug2, maxJug3);
    }
    public State getState(){
        return state;
    }

    public Vertex full_jug1(){
	return new Vertex(state.full_jug1());
    }

    public Vertex full_jug2(){
	return new Vertex(state.full_jug2());
    }
    public Vertex full_jug3() {
        return new Vertex(state.full_jug3());
    }

    public Vertex empty_jug1(){
	return new Vertex(state.empty_jug1());
    }

    public Vertex empty_jug2(){
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

//    public Path tracePath(){
//        Path<Vertex> path = new Path();
//        Vertex v = new Vertex();
//
//        v = this;
//
//        while (v != null){
//            path.addVertex(v);
//            v = v.getParent();
//        }
//
//        return path;
//    }

//    public void setParent(Vertex vertex){
//        this.parent = vertex;
//    }
    public void setPath(List<Vertex> path) {
        this.path.setPath(path);
    }
//    public Vertex getParent(){
//        return parent;
//    }

    @Override
    public boolean equals(Object obj) {
        Vertex v = (Vertex) obj;

	if (!(v instanceof Vertex))
            return false;

        return (this.state.equals(v.getState()));
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

    @Override
    public String toString(){
        return state.toString() + "f:" + getFCost();
//        return state.toString() + "f:" + getFCost() + "="+getHCost()+"+"+getGCost() ;
    }
    public void printPath() {
        path.printPath();
    }
    public List<Vertex> getPath() {
        return path.getPath();
    }
    public int SumFCost() {
        int sum = 0;
        for (Vertex vertex: getPath()) {
            Vertex tmp = (Vertex) vertex;
            sum += tmp.getFCost();
        }
        return sum;
    }
}
