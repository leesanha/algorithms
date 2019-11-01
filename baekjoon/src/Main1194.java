import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1194 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		boolean[][][] visit = new boolean[64][n][m];

		int ans = Integer.MAX_VALUE;
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '0') {
					q.add(new Node(i, j, 0, 0));
					visit[0][i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();
//			System.out.println("빼낸 놈");
//			System.out.println(cur);
			int row = cur.row;
			int col = cur.col;
			int keys = cur.keys;
			int dis = cur.dis;

//			System.out.println("들어가는 놈");
			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				int nextKeys = keys;

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == '#' || visit[keys][nr][nc])
					continue;
				if (map[nr][nc] == 'a' || map[nr][nc] == 'b' || map[nr][nc] == 'c' || map[nr][nc] == 'd'
						|| map[nr][nc] == 'e' || map[nr][nc] == 'f') {
					nextKeys |= (1 << (map[nr][nc] - 'a'));
				}
				if (map[nr][nc] == 'A' || map[nr][nc] == 'B' || map[nr][nc] == 'C' || map[nr][nc] == 'D'
						|| map[nr][nc] == 'E' || map[nr][nc] == 'F') {
					if ((nextKeys & (1 << (map[nr][nc] + "").toLowerCase().charAt(0) - 'a')) == 0) {
						continue;
					}
				}

				visit[nextKeys][nr][nc] = true;
				Node next = new Node(nr, nc, nextKeys, dis + 1);
//				System.out.println(next);
				q.add(next);
				if (map[nr][nc] == '1' && dis + 1 < ans) {
					ans = dis + 1;
				}
			}
		}
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}

	static class Node {
		int row;
		int col;
		int keys;
		int dis;

		public Node(int row, int col, int keys, int dis) {
			super();
			this.row = row;
			this.col = col;
			this.keys = keys;
			this.dis = dis;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", keys=" + keys + ", dis=" + dis + "]";
		}
	}
}
