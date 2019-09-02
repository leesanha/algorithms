import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15684 {
	static int ans, n, m, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		int[][] ladder = new int[h][n - 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a - 1][b - 1] = 1;
		}
		ans = Integer.MAX_VALUE;

		for (int i = 0; i <= 3; i++) {
			dfs(ladder, i, 0, 0, 0);
		}

		System.out.println((ans != Integer.MAX_VALUE) ? ans : -1);
	}

	private static void dfs(int[][] ladder, int cnt, int depth, int row, int col) {
		if (cnt == depth) {
			if (check(ladder)) {
				if (ans > depth)
					ans = depth;
			}
			return;
		}
		for (int i = row; i < h; i++) {
			while (true) {
				if (ladder[i][col] == 0) {
					if (col - 1 >= 0 && ladder[i][col - 1] == 1) {
						col++;
						if (col == n - 1) {
							col = 0;
							break;
						}
						continue;
					} else if (col + 1 < n - 1 && ladder[i][col + 1] == 1) {
						col++;
						if (col == n - 1) {
							col = 0;
							break;
						}
						continue;
					} else {
						ladder[i][col] = 1;
						dfs(ladder, cnt, depth + 1, i, col);
						ladder[i][col] = 0;
						
					}
				}
				col++;
				if (col == n - 1) {
					col = 0;
					break;
				}
			}

		}
	}

	private static boolean check(int[][] ladder) {
		for (int i = 0; i < n; i++) {
			int cur = i;
			for (int j = 0; j < h; j++) {
				if (cur - 1 < 0) {
					if (ladder[j][cur] == 1)
						cur++;
					continue;
				}

				if (cur + 1 >= n) {
					if (ladder[j][cur - 1] == 1)
						cur--;
					continue;
				}
				if (ladder[j][cur] == 1) {
					cur++;
					continue;
				}
				if (ladder[j][cur - 1] == 1)
					cur--;

			}
			if (cur != i)
				return false;
		}
		return true;
	}

}