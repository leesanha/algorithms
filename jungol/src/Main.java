import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int storeCnt = Integer.parseInt(br.readLine());

		ArrayList<Node>stores = new ArrayList<>();
		for (int i = 0; i < storeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int position = Integer.parseInt(st.nextToken());
			int row = 0;
			int col = 0;
			switch(dir) {
			case 1://북
				row = 0;
				col = position;
				break;
			case 2://남
				row = n - 1;
				col = position;
				break;
			case 3://서
				row = position;
				col = 0;
				break;
			case 4:
				break;
			}
//			stores.add(new Node(, , Integer.MAX_VALUE));
		}

		st = new StringTokenizer(br.readLine());
		int dongRow = Integer.parseInt(st.nextToken());
		int dongCol = Integer.parseInt(st.nextToken());

		
	}

	static class Node {
		int row;
		int col;
		int dis;

		public Node(int row, int col, int dis) {
			super();
			this.row = row;
			this.col = col;
			this.dis = dis;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getDis() {
			return dis;
		}

		public void setDis(int dis) {
			this.dis = dis;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", dis=" + dis + "]";
		}

	}
}
