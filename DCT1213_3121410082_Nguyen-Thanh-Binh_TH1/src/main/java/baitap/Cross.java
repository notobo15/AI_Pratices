package baitap;

import java.util.ArrayList;
import java.util.List;
/*
*   Ho Ten : Nguyen Thanh Binh
*   MSSV : 3121410082
*   Lop : DCT1213
* */

public class Cross {
    private State currentState;

    private Path<Cross> path;

    public Cross() {
        this.currentState = new State();
        this.path = new Path<>();
    }

    public Cross(State state) {
        this.currentState = state;
        this.path = new Path<>();
    }

    public State getState() {
        return currentState;
    }

    public void addToPath() {
        this.path.addCross(this);
    }

    public List<Cross> getPath() {
        return path.getPath();
    }

    public void setPath(List<Cross> path) {
        this.path.setPath(path);
    }

    public Cross F_cross() {
        return new Cross(currentState.F_cross());
    }

    public Cross F_back() {
        return new Cross(currentState.F_back());
    }

    public Cross F_F_cross() {
        return new Cross(currentState.F_F_cross());
    }

    public Cross F_F_back() {
        return new Cross(currentState.F_F_back());
    }

    public Cross F_G_cross() {
        return new Cross(currentState.F_G_cross());
    }

    public Cross F_G_back() {
        return new Cross(currentState.F_G_back());
    }

    public Cross F_B_cross() {
        return new Cross(currentState.F_B_cross());
    }

    public Cross F_B_back() {
        return new Cross(currentState.F_B_back());
    }

    public void printPath() {
        path.printPath();
    }

    @Override
    public boolean equals(Object obj) {
        Cross c = (Cross) obj;

        if (!(c instanceof Cross))
            return false;
        return (this.getState().equals(c.getState()));
    }

    @Override
    public String toString() {
        return currentState.toString();
    }

    // list Cross co the duy chuyen duoc
    public ArrayList<Cross> getPossibleCrosses() {
        ArrayList<Cross> CanCross = new ArrayList<>();
        // Khi Farmer ben trai chi duy chuyen duoc qua ben phai
        if (currentState.getFarmer() == 1) {
            Cross F_cross = F_cross();
            Cross F_F_cross = F_F_cross();
            Cross F_G_cross = F_G_cross();
            Cross F_B_cross = F_B_cross();
            if (F_cross.getState().CanMove()) {
                CanCross.add(F_cross);
            }
            if (F_F_cross.getState().CanMove()) {
                CanCross.add(F_F_cross);
            }
            if (F_G_cross.getState().CanMove()) {
                CanCross.add(F_G_cross);
            }
            if (F_B_cross.getState().CanMove()) {
                CanCross.add(F_B_cross);
            }
            // Khi Farmer ben phai chi duy chuyen duoc qua ben trai
        } else if (currentState.getFarmer() == 0) {
            Cross F_back = F_back();
            Cross F_F_back = F_F_back();
            Cross F_G_back = F_G_back();
            Cross F_B_back = F_B_back();
            if (F_back.getState().CanMove()) {
                CanCross.add(F_back);
            }
            if (F_F_back.getState().CanMove()) {
                CanCross.add(F_F_back);
            }
            if (F_G_back.getState().CanMove()) {
                CanCross.add(F_G_back);
            }
            if (F_B_back.getState().CanMove()) {
                CanCross.add(F_B_back);
            }
        }
        return CanCross;
    }
}
