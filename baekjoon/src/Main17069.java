import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17069 {
	static int[] dr = { 0, 1, 1 };
	static int[] dc = { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[34][34];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 가로: 0, 세로: 1, 대각선: 2
		long[][][] ans = new long[34][34][3];
		ans[0][1][0] = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				if (map[i][j] == 1)
					continue;

				if (map[i][j + 1] == 0)// 오른쪽 방향
					ans[i][j + 1][0] = ans[i][j][2] + ans[i][j][0];
				if (map[i + 1][j] == 0)// 아래 방향
					ans[i + 1][j][1] = ans[i][j][1] + ans[i][j][2];
				if (map[i + 1][j] == 0 && map[i + 1][j + 1] == 0 && map[i][j + 1] == 0)// 대각선 방향
					ans[i + 1][j + 1][2] = ans[i][j][0] + ans[i][j][1] + ans[i][j][2];
			}
		}
		System.out.println(ans[n - 1][n - 1][0] + ans[n - 1][n - 1][1] + ans[n - 1][n - 1][2]);
	}
}
