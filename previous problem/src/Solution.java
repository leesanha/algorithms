import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
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
					dfs(i, j, i, j, 0, 0);
				}
			}
			System.out.format("#%d %d\n", t, (ans == 0) ? -1 : ans);
		}
	}

	private static void dfs(int sr, int sc, int row, int col, int depth, int turn) {
		dessert[map[row][col]] = true;
		if (turn == 3) {
			int nr = row;
			int nc = col;
			while (true) {
				nr = nr + dr[3];
				nc = nc + dc[3];

				if (nr == sr && nc == sc) {
					if (depth > ans)
						ans = depth;
					for (int i = 0; i <= 100; i++) {
						if (dessert[i])
							System.out.print(i + " ");
					}
					System.out.println();
					dessert[map[row][col]] = false;
					return;
				}

				if (nr < 0 || nc >= n) {
					dessert[map[row][col]] = false;
					return;
				}
			}
		}

		int cnt = 0;
		int nr = row;
		int nc = col;
		while (true) {
			cnt++;
			nr = nr + dr[turn];
			nc = nc + dc[turn];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				break;
			dfs(sr, sc, nr, nc, depth + cnt, turn + 1);
			if (cnt > 1)
				dessert[map[nr + dr[(turn + 2) % 4]][nc + dc[(turn + 2) % 4]]] = true;

		}
		dessert[map[row][col]] = false;
	}
}