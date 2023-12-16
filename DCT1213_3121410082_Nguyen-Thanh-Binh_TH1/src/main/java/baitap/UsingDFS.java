package baitap;

import java.util.*;

/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 *
 *
 *	ACTIONS
 * 1. F cross : Farmer 1 minh di qua ben kia song
 * 2. F back alone : Farmer 1 minh quay tro lai
 * 3. F and W cross : Farmer va Fox qua ben kia song
 * 4. F and W back : Farmer va Fox quay tro lai
 * 5. F and G cross : Farmer va Goose di qua ben kia song
 * 6. F and G back : Farmer va Goose quay tro lai
 * 7. F and C cross : Farmer va Beans qua ben kia song
 * 8. F and C cross : Farmer va Beans quay tro lai
 *
 * */

    /// CAI DAT THUAT TOAN DFS
public class UsingDFS {

    public static Stack<Cross> stack = new Stack<>();
    public static Set<Cross> closed = new HashSet<Cross>() {
        public boolean contains(Object obj) {
            Cross cross = (Cross) obj;
            for (Cross cr : this) {
                if((cross.equals(cr))  && (cross.getPath().equals(cr.getPath()))) {
                    return true;
                }
            }
            return false;
        }
    };
    public static void main(String[] args) {
        System.out.println("BAI TOAN: Farmer Crosses River Puzzle");
        System.out.println("- - - - - - - - - - - THUAT TOAN DFS - - - - - - - - - - -");

        ArrayList<Cross> result = new ArrayList<>(); // list ket qua
        Cross GOAL = new Cross(new State(0, 0, 0, 0)); // Diem muc tieu
        Cross initialCross = new Cross(new State(1, 1, 1, 1)); // Gia tri khoi tao
        initialCross.getState().setType(Type.INIT);
        stack.add(initialCross);

        System.out.println("Init : " + initialCross);
        System.out.println("Goal : " + GOAL);

        while (!stack.isEmpty()) {
            System.out.println("OPEN   : " + stack);
            Cross currentCross = stack.pop();

            System.out.println("CLOSED : " + closed);


            System.out.println("Current : "  + currentCross.getState().getType() + ":"+ currentCross);
            closed.add(currentCross);
            currentCross.addToPath();
            if (currentCross.getState().equals(GOAL.getState())) {
                result.add(currentCross);
//                currentCross.getPath().forEach((s) -> {
//                    closed.remove(s);
//                });
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                continue;
            }

            ArrayList<Cross> newCrosses = new ArrayList<>(); // list cross(state) dang xet
            ArrayList<Cross> InvalidCrosses = new ArrayList<>(); // list Cross vi pham
            ArrayList<Cross> CanCross = currentCross.getPossibleCrosses(); // list cross co the duy chuyen

            CanCross.forEach(cross -> {
                if (cross.getState().checkAllow()) {
                    newCrosses.add(cross);
                } else {
                    InvalidCrosses.add(cross);
                }
            });
            System.out.print("Cac Trang Thai Duoc Sinh Ra : ");
            CanCross.forEach(cross -> {
                System.out.print("[" + cross.getState().getType().name() + ":" + cross.getState() + ",");
            });
            System.out.println("]");
            System.out.println("Cac Trang Thai Khong Hop Le : " + InvalidCrosses);

            InvalidCrosses.clear();
            CanCross.clear();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (Cross cross : newCrosses) {
                if (!currentCross.getPath().contains(cross)) {
                    cross.setPath(currentCross.getPath());

                    if (!closed.contains(cross)) {
                        stack.push(cross);
                    }

                }
            }


        }
        System.out.println("OPEN   : " + stack);
        System.out.println("CLOSED : " + closed);

        System.out.println("- - - - - - - - - - - KET QUA - - - - - - - - - - - ");
        System.out.println("Tong So Cach Giai: " + result.size());
        result.forEach(Cross::printPath);
    }
}
