import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2105 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans, n;
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };
	static boolean[] dessert;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dessert = new boolean[101];
					dessert[map[i][j]] = true;
					dfs(i, j, i, j, 0, 0);
					dessert[map[i][j]] = false;
				}
			}
			System.out.format("#%d %d\n", t, (ans == 0) ? -1 : ans);
		}
	}

	private static void dfs(int sr, int sc, int row, int col, int depth, int turn) {
		if (turn == 4) {
			if (row == sr && col == sc) {
				if (depth > ans)
					ans = depth;
//				for (int i = 0; i <= 100; i++) {
//					if (dessert[i])
//						System.out.print(i + " ");
//				}
//				System.out.println();
//				dessert[map[row][col]] = false;
				return;
			} else {
				return;
			}
		}

		int nr = row + dr[turn];
		int nc = col + dc[turn];

		if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			// 같은 방향
			if (!dessert[map[nr][nc]]) {
				dessert[map[nr][nc]] = true;
				dfs(sr, sc, nr, nc, depth + 1, turn);
				dessert[map[nr][nc]] = false;
			} else if (nr == sr && nc == sc) {
				if (depth + 1 > ans)
					ans = depth + 1;
			}
		}
		if (turn == 3)
			return;
		nr = row + dr[turn + 1];
		nc = col + dc[turn + 1];
		if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
			// 다른 방향
			if (!dessert[map[nr][nc]]) {
				dessert[map[nr][nc]] = true;
				dfs(sr, sc, nr, nc, depth + 1, turn + 1);
				dessert[map[nr][nc]] = false;
			} else if (nr == sr && nc == sc) {
				if (depth + 1 > ans)
					ans = depth + 1;
			}
		}
	}
}