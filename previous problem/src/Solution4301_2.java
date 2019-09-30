import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution4301_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			boolean[][] map = new boolean[n][m];
			map[0][0] = true;

			int[] dr = { -2, 0, 2, 0 };
			int[] dc = { 0, 2, 0, -2 };

			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					boolean flag = true;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m)
							continue;
						if (map[nr][nc]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						map[i][j] = true;
						ans++;
					}
				}
			}
			System.out.format("#%d %d\n",t, ans);
		}
	}
}