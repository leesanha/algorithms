import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1861_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static int[][] dis;

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

			dis = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dfs(i, j, i, j, 1);
				}
			}

			int arow = 0;
			int acol = 0;
			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dis[i][j] > ans) {
						arow = i;
						acol = j;
						ans = dis[i][j];
					} else if (dis[i][j] == ans) {
						if (map[i][j] < map[arow][acol]) {
							arow = i;
							acol = j;
						}
					}
				}
			}

			System.out.println("#" + t + " " + map[arow][acol] + " " + dis[arow][acol]);

		}
	}

	private static void dfs(int sr, int sc, int row, int col, int depth) {
		if (dis[sr][sc] < depth)
			dis[sr][sc] = depth;

		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				continue;
			if (map[row][col] + 1 == map[nr][nc]) {
				dfs(sr, sc, nr, nc, depth + 1);
			}
		}
	}

}