import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14889 {
	static int[][] map;
	static int n;
	static boolean[] check;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		check = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int cnt, int depth) {
		if (cnt == n / 2) {
			cal();
			return;
		}
		if (depth >= n)
			return;
		check[depth] = true;
		dfs(cnt + 1, depth + 1);
		check[depth] = false;
		dfs(cnt, depth + 1);
	}

	static void cal() {
		int ret1 = 0;
		int ret2 = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (check[i] && check[j]) {
					ret1 += map[i][j];
				}
				if (!check[i] && !check[j]) {
					ret2 += map[i][j];
				}
			}
		}
		int ret = (int) Math.abs(ret1 - ret2);
		ans = (ans > ret) ? ret : ans;
	}

}
