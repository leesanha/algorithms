import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2174 {
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());// 가로
		int b = Integer.parseInt(st.nextToken());// 세로
		Node[][] map = new Node[b][a];

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 로봇 개수
		int m = Integer.parseInt(st.nextToken());// 명령 개수

		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int tx = Integer.parseInt(st.nextToken()) - 1;// x좌표
			int ty = Integer.parseInt(st.nextToken()) - 1;// y좌표
			String dir = st.nextToken();
			switch (dir) {
			case "N":
				list.add(new Node(ty, tx, 0, i + 1));
				break;
			case "E":
				list.add(new Node(ty, tx, 1, i + 1));
				break;
			case "S":
				list.add(new Node(ty, tx, 2, i + 1));
				break;
			case "W":
				list.add(new Node(ty, tx, 3, i + 1));
				break;
			}
			map[ty][tx] = list.get(i);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			String construction = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				Node cur = list.get(num);
				int ty = cur.ty;
				int tx = cur.tx;
				int dir = cur.dir;
				switch (construction) {
				case "L":
					cur.dir = (dir + 3) % 4;
					break;
				case "R":
					cur.dir = (dir + 1) % 4;
					break;
				case "F":
					int nty = ty + dy[dir];
					int ntx = tx + dx[dir];

					if (nty < 0 || nty >= b || ntx < 0 || ntx >= a) {
						//남은 입력 다 받는다.
						for (int k = i; k < m - i - 1; k++) {
							st = new StringTokenizer(br.readLine());
							int tnum = Integer.parseInt(st.nextToken());
							String tconstrunction = st.nextToken();
							int tcnt = Integer.parseInt(st.nextToken());
						}
						System.out.println("Robot " + (num + 1) + " crashes into the wall");
						return;
					}
					if (map[nty][ntx] != cur && map[nty][ntx] != null) {
						for (int k = i; k < m - i - 1; k++) {
							st = new StringTokenizer(br.readLine());
							int tnum = Integer.parseInt(st.nextToken());
							String tconstrunction = st.nextToken();
							int tcnt = Integer.parseInt(st.nextToken());
						}
						System.out.println("Robot " + (num + 1) + " crashes into robot " + map[nty][ntx].num);
						return;
					}
					map[ty][tx] = null;
					map[nty][ntx] = cur;
					cur.ty = nty;
					cur.tx = ntx;
					break;
				}
			}
		}
		System.out.println("OK");
	}

	static class Node {
		int ty;
		int tx;
		int dir;
		int num;

		public Node(int ty, int tx, int dir, int num) {
			super();
			this.ty = ty;
			this.tx = tx;
			this.dir = dir;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [ty=" + ty + ", tx=" + tx + ", dir=" + dir + ", num=" + num + "]";
		}
	}
}
