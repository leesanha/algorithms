import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1949 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int n, ans, k;
	static int[][] mountain;
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			mountain = new int[n][n];
			visit = new boolean[n][n];

			int max = -1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					mountain[i][j] = Integer.parseInt(st.nextToken());
					if (max < mountain[i][j])
						max = mountain[i][j];
				}
			}

			ArrayList<int[]> high = new ArrayList<int[]>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (max == mountain[i][j]) {
						int[] temp = { i, j };
						high.add(temp);
					}
				}
			}

			ans = 1;
			for (int i = 0; i < high.size(); i++) {
				int[] temp = high.get(i);
				int row = temp[0];
				int col = temp[1];

				visit[row][col] = true;
				dfs(row, col, 1, false);
				visit[row][col] = false;
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void dfs(int row, int col, int len, boolean flag) {
		if (len > ans) {
			ans = len;
		}

		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc])
				continue;

			if (!flag) {
				flag = true;
				for (int i = 1; i <= k; i++) {
					if (mountain[row][col] > mountain[nr][nc] - i) {
						mountain[nr][nc] -= i;
						visit[nr][nc] = true;
						dfs(nr, nc, len + 1, flag);
						visit[nr][nc] = false;
						mountain[nr][nc] += i;
					}
				}
				flag = false;
			}

			visit[nr][nc] = true;
			if (mountain[row][col] > mountain[nr][nc]) {
				dfs(nr, nc, len + 1, flag);
			}
			visit[nr][nc] = false;
		}

	}
}