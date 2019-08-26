import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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
			boolean flag = false;
			int before = 0;
			for (int j = 0; j < n - 1; j++) {
				if (map[i][j] != map[i][j + 1]) {
					if (j + 1 - before <= l) {
						flag = true;
						break;
					}
					
				}
			}
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
					boolean b = check[i][j];
					check[i][j] = check[j][i];
					check[j][i] = b;
				}
			}
		}
	}

}
