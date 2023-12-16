package baitap;
/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 * */


public class State {
	// 1 = Present, ben trai song
	// 0 = Absent, ben phai song
	// -1 = khong hop le
	private int Farmer, Fox, Goose, Beans;
	private Type type;

	public State() {
		Farmer = 1;
		Fox = 1;
		Goose = 1;
		Beans = 1;
	}

	public State(int farmer, int fox, int goose, int beans) {
		super();
		Farmer = farmer;
		Fox = fox;
		Goose = goose;
		Beans = beans;
	}
	// Farmer co the di chuyen 1 minh qua song
	public State F_cross() {
		if (Farmer == 1) {
			State newState = new State(0, Fox, Goose, Beans);
			newState.setType(Type.FARMER_CROSS);
			return newState;
		}
		// khong hop le
		return new State(-1, -1, -1, -1);
	}
	// Farmer 1 minh quay tro lai
	public State F_back() {
		if (Farmer == 0) {
			State newState = new State(1, Fox, Goose, Beans);
			newState.setType(Type.FARMER_BACK);
			return newState;
		} else {
			return new State(-1, -1, -1, -1);
		}
	}
	// Farmer va Fox qua ben kia song
	public State F_F_cross() {
		if (Farmer == 1 && Fox == 1) {
			State newState = new State(0, 0, Goose, Beans);
			newState.setType(Type.FARMER_FOX_CROSS);
			return newState;
		} else {
			return new State(-1, -1, -1, -1);

		}
	}
	// Farmer va Fox quay tro lai
	public State F_F_back() {
		if (Farmer == 0 && Fox == 0) {
			State newState = new State(1, 1, Goose, Beans);
			newState.setType(Type.FARMER_FOX_BACK);
			return newState;
		} else {
			return new State(-1, -1, -1, -1);
		}
	}
	// Farmer va Goose di qua ben kia song
	public State F_G_cross() {
		if (Farmer == 1 && Goose == 1) {
			State newState = new State(0, Fox, 0, Beans);
			newState.setType(Type.FARMER_GOOSE_CROSS);
			return newState;
		}
		return new State(-1, -1, -1, -1);
	}
	// Farmer va Goose quay tro lai
	public State F_G_back() {
		if (Farmer == 0 && Goose == 0) {
			State newState = new State(1, Fox, 1, Beans);
			newState.setType(Type.FARMER_GOOSE_BACK);
			return newState;
		}
		return new State(-1, -1, -1, -1);
	}
	//Farmer va Beans qua ben kia song
	public State F_B_cross() {
		if (Farmer == 1 && Beans == 1) {
			State newState = new State(0, Fox, Goose, 0);
			newState.setType(Type.FARMER_BEANS_CROSS);
			return newState;
		}
		return new State(-1, -1, -1, -1);
	}
	// Farmer va Beans quay tro lai
	public State F_B_back() {
		if (Farmer == 0 && Beans == 0) {
			State newState = new State(1, Fox, Goose, 1);
			newState.setType(Type.FARMER_BEANS_BACK);
			return newState;
		}
		return new State(-1, -1, -1, -1);
	}

	// kiem tra xem state co hop le hay khong
	public boolean checkAllow() {
		// Fox and Goose khong the o cung voi nhau ma Farmer o ben kia song
		if ((Fox == 1 && Goose == 1) && (Farmer == 0))
			return false;
		// Fox and Goose khong the o cung voi nhau ma Farmer o ben kia song
		if ((Fox == 0 && Goose == 0) && (Farmer == 1))
			return false;
		// Beans and Goose khong the o cung voi nhau ma Farmer o ben kia song
		if ((Beans == 1 && Goose == 1) && (Farmer == 0))
			return false;
		// Beans and Goose khong the o cung voi nhau ma Farmer o ben kia song
		if ((Beans == 0 && Goose == 0) && (Farmer == 1))
			return false;
		// State khong the duy chuyen duoc
		if (Farmer == -1 && Fox == -1 && Goose == -1 && Beans == -1)
			return false;
		// co the duy chuyen duoc
		return true;
	}
	// So sanh state
	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		if (!(other instanceof State))
			return false;
		return (Farmer == other.Farmer && Fox == other.Fox && Goose == other.Goose && Beans == other.Beans);

	}
	// return false neu state khong hop le
	// return true neu state no the duy chuyen
	public boolean CanMove() {
		if (Farmer == -1 && Fox == -1 && Goose == -1 && Beans == -1)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + Farmer + "," + Fox + "," + Goose + "," + Beans + ")";
	}

	public int getFarmer() {
		return Farmer;
	}

	public void setFarmer(int farmer) {
		Farmer = farmer;
	}

	public int getFox() {
		return Fox;
	}

	public void setFox(int fox) {
		Fox = fox;
	}

	public int getGoose() {
		return Goose;
	}

	public void setGoose(int goose) {
		Goose = goose;
	}

	public int getBeans() {
		return Beans;
	}

	public void setBeans(int beans) {
		Beans = beans;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
