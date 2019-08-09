import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution4613 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			String[] input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);

			map = new char[n][m];
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
			}

			int ans = -1;
			for (int w = 1; w <= 50; w++) {
				for (int b = 1; b <= 50; b++) {
					for (int r = 1; r < 50; r++) {
						if (w + b + r > n)
							break;
						if (w + b + r == n) {
							int res = cal(w, b, r);
							if (ans == -1 || ans > res) {
								ans = res;
							}
						}
					}
				}
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static int cal(int w, int b, int r) {
		int ret = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 'W')
					ret++;
			}
		}
		for (int i = w; i < w + b; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 'B')
					ret++;
			}
		}
		for (int i = w + b; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 'R')
					ret++;
			}
		}
		return ret;
	}
}
