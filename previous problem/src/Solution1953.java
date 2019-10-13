import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visit;
	static int[][] map;
	static int ans, n, m;
	static Queue<Integer> q;
	static int[][][] possible = { { {}, { 1, 2, 5, 6 }, { 1, 2, 5, 6 }, {}, { 1, 2, 5, 6 }, {}, {}, { 1, 2, 5, 6 } },
			{ {}, { 1, 3, 6, 7 }, {}, { 1, 3, 6, 7 }, { 1, 3, 6, 7 }, { 1, 3, 6, 7 }, {}, {} },
			{ {}, { 1, 2, 4, 7 }, { 1, 2, 4, 7 }, {}, {}, { 1, 2, 4, 7 }, { 1, 2, 4, 7 }, {} },
			{ {}, { 1, 3, 4, 5 }, {}, { 1, 3, 4, 5 }, {}, {}, { 1, 3, 4, 5 }, { 1, 3, 4, 5 } } };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			map = new int[n][m];
			visit = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			q = new LinkedList<Integer>();
			q.add(sr);
			q.add(sc);
			q.add(1);
			visit[sr][sc] = true;

			while (!q.isEmpty()) {
				int row = q.poll();
				int col = q.poll();
				int cnt = q.poll();
				ans++;
//				System.out.println(row + " " + col + " " + ans);
//				for (int i = 0; i < n; i++) {
//					for (int j = 0; j < m; j++) {
//						System.out.print(visit[i][j] ? 1 : 0);
//					}
//					System.out.println();
//				}
//				System.out.println();

				if (cnt >= time)
					continue;

				switch (map[row][col]) {
				case 1:
					up(row, col, cnt, 1);
					down(row, col, cnt, 1);
					left(row, col, cnt, 1);
					right(row, col, cnt, 1);
					break;
				case 2:
					up(row, col, cnt, 2);
					down(row, col, cnt, 2);
					break;
				case 3:
					left(row, col, cnt, 3);
					right(row, col, cnt, 3);
					break;
				case 4:
					up(row, col, cnt, 4);
					right(row, col, cnt, 4);
					break;
				case 5:
					right(row, col, cnt, 5);
					down(row, col, cnt, 5);
					break;
				case 6:
					left(row, col, cnt, 6);
					down(row, col, cnt, 6);
					break;
				case 7:
					up(row, col, cnt, 7);
					left(row, col, cnt, 7);
					break;
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void up(int row, int col, int cnt, int before) {
		int nr = row + dr[0];
		int nc = col + dc[0];

		check(row, col, nr, nc, cnt, before, 0);
	}

	static void down(int row, int col, int cnt, int before) {
		int nr = row + dr[2];
		int nc = col + dc[2];

		check(row, col, nr, nc, cnt, before, 2);
	}

	static void left(int row, int col, int cnt, int before) {
		int nr = row + dr[3];
		int nc = col + dc[3];

		check(row, col, nr, nc, cnt, before, 3);
	}

	static void right(int row, int col, int cnt, int before) {
		int nr = row + dr[1];
		int nc = col + dc[1];

		check(row, col, nr, nc, cnt, before, 1);
	}

	private static void check(int row, int col, int nr, int nc, int cnt, int before, int dir) {
		if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
			return;
		for (int i = 0; i < possible[dir][before].length; i++) {
			if(map[nr][nc] == possible[dir][before][i]) {
				visit[nr][nc] = true;
				q.add(nr);
				q.add(nc);
				q.add(cnt + 1);
				return;
			}
		}
		
	}
}