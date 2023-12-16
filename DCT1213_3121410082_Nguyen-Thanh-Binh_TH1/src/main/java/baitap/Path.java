package baitap;

import java.util.List;
import java.util.ArrayList;

/*
 *   Ho Ten : Nguyen Thanh Binh
 *   MSSV : 3121410082
 *   Lop : DCT1213
 * */
public class Path<T> {
	private List<T> path;

	public Path() {
		path = new ArrayList<>();
	}

	public void addCross(T cross) {
		path.add(cross);
	}

	public List<T> getPath() {
		return path;
	}

	public void setPath(List<T> path) {
		this.path.addAll(path);
	}

	public void printPath() {
		for (int i = 0; i < path.size(); i++) {
			System.out.print(path.get(i).toString() + " ");
		}

		System.out.println();
	}
}
