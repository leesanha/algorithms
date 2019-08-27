import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14890 {
	static int n, l;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		check = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		ans += getPath();
		changeMap(map);
		ans += getPath();
		System.out.println(ans);
	}

	private static int getPath() {
		int ret = 0;

		for (int i = 0; i < n; i++) {
			boolean flag1 = false;
			for (int j = 0; j < n - 1; j++) {
				boolean flag2 = false;
				if(Math.abs(map[i][j] - map[i][j+1]) >= 2) {
					flag1 = true;
					break;
				}else if (map[i][j] < map[i][j + 1]) {
					int temp = map[i][j];
					for (int k = j; k > j - l; k--) {
						if (k < 0) {
							flag1 = true;
							flag2 = true;
							break;
						}
						if (check[i][k] || map[i][k] != temp) {
							flag1 = true;
							flag2 = true;
							break;
						}
					}
					if (flag2)
						break;
					else {
						for (int k = j; k > j - l; k--) {
							check[i][k] = true;
						}
					}
				} else if (map[i][j] > map[i][j + 1]) {
					int temp = map[i][j + 1];
					for (int k = j + 1; k < j + 1 + l; k++) {
						if (k >= n) {
							flag1 = true;
							flag2 = true;
							break;
						}
						if (check[i][k] || map[i][k] != temp) {
							flag1 = true;
							flag2 = true;
							break;
						}
					}
					if (flag2)
						break;
					else {
						for (int k = j + 1; k < j + 1 + l; k++) {
							check[i][k] = true;
						}
					}
				}

			}
			if (!flag1)
				ret++;
			for (int j = 0; j < n; j++)
				check[i][j] = false;

		}
		return ret;
	}

	private static void changeMap(int[][] map) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i > j) {
					int temp = map[i][j];
					map[i][j] = map[j][i];
					map[j][i] = temp;
				}
			}
		}
	}

}
