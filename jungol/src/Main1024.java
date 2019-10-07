import java.util.*;

public class Main1024 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int h, w;
	static int ans;
	static int[][] map;
	static int[][] dp;

	private static int dfs(int row, int col) {
		if (row == h - 1 && col == w - 1) {
//			visit[row][col] = true;
//			ans++;
			return 1;
		}
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] >= map[row][col])
				continue;
			if (dp[nr][nc] != 0){
				dp[row][col] += dp[nr][nc];
				continue;
			}
			dp[row][col] += dfs(nr, nc);
		}
		return dp[row][col];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		h = sc.nextInt();
		w = sc.nextInt();

		ans = 0;
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dp = new int[h][w];
		dfs(0, 0);

		System.out.println(dp[0][0]);
	}

}