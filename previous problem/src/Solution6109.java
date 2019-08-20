import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution6109 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();

			int[][] map = new int[n][n];
			int[][] ans = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			switch (dir) {
			case "up":
				for (int col = 0; col < n; col++) {
					int idx = 0;
					int value = 0;
					for (int row = 0; row < n; row++) {
						if (map[row][col] == 0)
							continue;
						if (value == 0)
							value = map[row][col];
						else if (value == map[row][col]) {
							ans[idx++][col] = value * 2;
							map[row][col] = 0;
							value = 0;
						} else {
							ans[idx++][col] = value;
							value = map[row][col];
						}
					}
					if (value != 0)
						ans[idx++][col] = value;
				}
				break;
			case "down":
				for (int col = 0; col < n; col++) {
					int idx = n - 1;
					int value = 0;
					for (int row = n - 1; row >= 0; row--) {
						if (map[row][col] == 0)
							continue;
						if (value == 0)
							value = map[row][col];
						else if (value == map[row][col]) {
							ans[idx--][col] = value * 2;
							map[row][col] = 0;
							value = 0;
						} else {
							ans[idx--][col] = value;
							value = map[row][col];
						}
					}
					if (value != 0)
						ans[idx--][col] = value;
				}
				break;
			case "left":
				for (int row = 0; row < n; row++) {
					int idx = 0;
					int value = 0;
					for (int col = 0; col < n; col++) {
						if (map[row][col] == 0)
							continue;
						if (value == 0)
							value = map[row][col];
						else if (value == map[row][col]) {
							ans[row][idx++] = value * 2;
							map[row][col] = 0;
							value = 0;
						} else {
							ans[row][idx++] = value;
							value = map[row][col];
						}
					}
					if (value != 0)
						ans[row][idx++] = value;
				}
				break;
			case "right":
				for (int row = 0; row < n; row++) {
					int idx = n - 1;
					int value = 0;
					for (int col = n - 1; col >= 0; col--) {
						if (map[row][col] == 0)
							continue;
						if (value == 0)
							value = map[row][col];
						else if (value == map[row][col]) {
							ans[row][idx--] = value * 2;
							map[row][col] = 0;
							value = 0;
						} else {
							ans[row][idx--] = value;
							value = map[row][col];
						}
					}
					if (value != 0)
						ans[row][idx--] = value;
				}
				break;
			}

			System.out.format("#%d\n", t);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.format("%d ", ans[i][j]);
				}
				System.out.println();
			}
		}
	}

}
