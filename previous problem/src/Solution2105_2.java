import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution2105_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, ans;
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
			ans = -1;
			for (int a = 0; a < n - 2; a++) {
				for (int b = 1; b < n - 1; b++) {
					for (int d1 = 1; b + d1 < n; d1++) {
						for (int d2 = 1; b - d2 >= 0; d2++) {
							if (a + d1 + d2 < n) {
								solve(a, b, d1, d2);
							}
						}
					}
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void solve(int a, int b, int d1, int d2) {
		int cnt = 1;
		boolean[] visit = new boolean[101];
		visit[map[a][b]] = true;
		// 시작점으로 부터
		// 오른쪽 대각선
		for (int i = 1; i <= d1; i++) {
			if (visit[map[a + i][b + i]])
				return;
			else {
				visit[map[a + i][b + i]] = true;
				cnt++;
			}
		}
		// 왼쪽 대각선
		for (int i = 1; i <= d2; i++) {
			if (visit[map[a + i][b - i]])
				return;
			else {
				visit[map[a + i][b - i]] = true;
				cnt++;
			}
		}
		// 아래로 내려가는 거
		// 왼쪽 대각선
		for (int i = 1; i <= d1; i++) {
			if (visit[map[a + d2 + i][b - d2 + i]])
				return;
			else {
				visit[map[a + d2 + i][b - d2 + i]] = true;
				cnt++;
			}
		}
		for (int i = 1; i < d2; i++) {
			if (visit[map[a + d1 + i][b + d1 - i]])
				return;
			else {
				visit[map[a + d1 + i][b + d1 - i]] = true;
				cnt++;
			}
		}
		if (cnt > ans)
			ans = cnt;
	}
}