import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1103 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int storeCnt = Integer.parseInt(br.readLine());

		ArrayList<Node> stores = new ArrayList<>();
		int[][] map = new int[n + 1][m + 1];
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		for (int i = 0; i <= n; i++) {
			map[i][0] = 1;
			map[i][m] = 1;
		}
		for (int i = 0; i <= m; i++) {
			map[0][i] = 1;
			map[n][i] = 1;
		}
		int row = 0;
		int col = 0;
		int dir = 0;
		int position = 0;
		int dongRow = 0;
		int dongCol = 0;
		for (int i = 0; i <= storeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			dir = Integer.parseInt(st.nextToken());
			position = Integer.parseInt(st.nextToken());
			switch (dir) {
			case 1:// 북
				row = 0;
				col = position;
				break;
			case 2:// 남
				row = n;
				col = position;
				break;
			case 3:// 서
				row = position;
				col = 0;
				break;
			case 4:// 동
				row = position;
				col = m;
				break;
			}
			if (i == storeCnt) {
				dongRow = row;
				dongCol = col;
			} else {
				map[row][col] = 2;
				stores.add(new Node(row, col, 0));
			}
		}

		boolean[][] visit = new boolean[n + 1][m + 1];
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(dongRow, dongCol, 0));
		visit[dongRow][dongCol] = true;
		int[][] distance = new int[n + 1][m + 1];

		while (!q.isEmpty()) {
			Node temp = q.poll();
			row = temp.row;
			col = temp.col;
			int dis = temp.dis;

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr > n || nc < 0 || nc > m || map[nr][nc] == 0 || visit[nr][nc])
					continue;
				if (map[nr][nc] == 1) {
					q.add(new Node(nr, nc, dis + 1));
					visit[nr][nc] = true;
				}
				if (map[nr][nc] == 2) {
					distance[nr][nc] = dis + 1;
					visit[nr][nc] = true;
					q.add(new Node(nr, nc, dis + 1));
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < stores.size(); i++) {
			ans += distance[stores.get(i).row][stores.get(i).col];
		}
		System.out.println(ans);
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
