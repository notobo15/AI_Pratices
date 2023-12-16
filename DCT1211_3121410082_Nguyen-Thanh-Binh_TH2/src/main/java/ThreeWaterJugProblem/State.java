package ThreeWaterJugProblem;

/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 */
public class State {

    private int jug1, jug2,jug3;
    private static int MAX_JUG1 = 0;
    private static int MAX_JUG2 = 0;
    private static int MAX_JUG3 = 0;
    private Type type;

    public State(){
        this.jug1 = 0;
        this.jug2 = 0; this.jug3 = 0;
    }
    
    public State(int jug1, int jug2){
        this.jug1 = jug1;
        this.jug2 = jug2;        
    }
    public State(int jug1, int jug2, int jug3){
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.jug3 = jug3;
    }
    public static void setMaxJugsCapacity(int maxJug1, int maxJug2){
        MAX_JUG1 = maxJug1;
        MAX_JUG2 = maxJug2;        
    }
    public static void setMaxJugsCapacity(int maxJug1, int maxJug2, int maxJug3){
        MAX_JUG1 = maxJug1;
        MAX_JUG2 = maxJug2;
        MAX_JUG3 = maxJug3;
    }
    public int getJug1(){
        return jug1;
    } 
    
    public int getJug2(){
        return jug2;
    }
    public int getJug3(){
        return jug3;
    }
    public State full_jug1(){
        State tmp = new State(MAX_JUG1, jug2, jug3);
        tmp.type = Type.FULL_JUG_1;
        return tmp;
    }

    public State full_jug2(){
        State tmp = new State(jug1, MAX_JUG2, jug3);
        tmp.type = Type.FULL_JUG_2;
        return tmp;
    }
    public State full_jug3(){
        State tmp = new State(jug1, jug2, MAX_JUG3);
        tmp.type = Type.FULL_JUG_3;
        return tmp;
    }

    public State empty_jug1(){
        State tmp = new State(0, jug2, jug3);
        tmp.type = Type.EMPTY_JUG_1;
        return tmp;
    }

    public State empty_jug2(){
        State tmp = new State(jug1, 0, jug3);
        tmp.type = Type.EMPTY_JUG_2;
        return tmp;
    }


    public State empty_jug3(){
        State tmp = new State(jug1, jug2, 0);
        tmp.type = Type.EMPTY_JUG_3;
        return tmp;
    }


    // 1 -> 2
    public State pour_jug1_jug2(){
        State tmp = new State(-1, -1, -1);

        if(jug2 == MAX_JUG2) {
            tmp = new State(0, jug2, jug3);
            return tmp;
        }
        if ((jug1 + jug2) >= MAX_JUG2)
            tmp = new State((jug1 + jug2 - MAX_JUG2), MAX_JUG2, jug3);
        else
            tmp = new State(0, (jug1 + jug2), jug3);
        tmp.type = Type.POUR_JUG_1_2;
        return tmp;
    }
    // 1-> 3
    public State pour_jug1_jug3(){
        State tmp = new State(-1, -1, -1);

        if(jug3 == MAX_JUG3) {
            tmp =  new State(0, jug2, jug3);
            return tmp;
        }
        if ((jug1 + jug3) >= MAX_JUG3)
            tmp = new State((jug1 + jug3 - MAX_JUG3), jug2, MAX_JUG3);
        else
            tmp = new State(0,  jug2, (jug1 + jug3));
        tmp.type = Type.POUR_JUG_1_3;
        return tmp;
    }


    // 2 -> 1
    public State pour_jug2_jug1(){
        State tmp = new State(-1, -1, -1);

        if(jug1 == MAX_JUG1) {
            tmp = new State(jug1, 0, jug3);
            return tmp;
        }
        if ((jug1 + jug2) >= MAX_JUG1)
            tmp = new State(MAX_JUG1, (jug1 + jug2 - MAX_JUG1), jug3);
        else
            tmp = new State((jug1 + jug2), 0, jug3);
        tmp.type = Type.POUR_JUG_2_1;
        return tmp;
    }

    // 2 -> 3
    public State pour_jug2_jug3(){
        State tmp = new State(-1, -1, -1);

        if(jug3 == MAX_JUG3) {
            tmp = new State(jug1, 0, jug3);
            return tmp;
        }
        if ((jug2 + jug3) >= MAX_JUG3)
            tmp = new State(jug1, (jug3 + jug2 - MAX_JUG3), MAX_JUG3);
        else
            tmp = new State(jug1, 0, (jug3 + jug2));
        tmp.type = Type.POUR_JUG_2_3;
        return tmp;
    }

    // 3 -> 1
    public State pour_jug3_jug1(){
        State tmp = new State(-1, -1, -1);
        if(jug1 == MAX_JUG1) {
            tmp = new State(jug1, jug2, 0); return tmp;
        }
        if ((jug1 + jug3) >= MAX_JUG1)
            tmp = new State( MAX_JUG1, jug2, (jug1 + jug3 - MAX_JUG1));
        else
            tmp = new State((jug1 + jug3), jug2 , 0);
        tmp.type = Type.POUR_JUG_3_1;
        return tmp;
    }

    // 3 ->  2
    public State pour_jug3_jug2(){
        State tmp = new State(-1, -1, -1);
        if(jug2 == MAX_JUG2) {
            tmp = new State(jug1, jug2, 0);
            return tmp;
        }
        if ((jug3 + jug2) >= MAX_JUG2)
            tmp = new State( jug1, MAX_JUG2, (jug3 + jug2 - MAX_JUG2));
        else
            tmp = new State(jug1, (jug3 + jug2), 0);

        tmp.type = Type.POUR_JUG_3_2;
        return tmp;
    }



    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;
        
	if (!(s instanceof State))
            return false;	

        return ((s.getJug1() == this.getJug1()) && (s.getJug2() == this.getJug2()) && (s.getJug3() == this.getJug3()));
    }
    
    @Override
    public String toString(){
        return getType() + ":(" + jug1 + "-" + jug2 + "-" + jug3+")";
    }

    public Type getType() {
        return type;
    }
    public String getTypeName() {
        if(type != null)
            return type.name();
        else
            return "";
    }

    public void setType(Type type) {
        this.type = type;
    }
}
