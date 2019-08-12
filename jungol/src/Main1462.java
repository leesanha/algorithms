import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1462 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 'L')
					continue;

				boolean[][] visit = new boolean[n][m];
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(i);
				q.offer(j);
				q.offer(0);
				visit[i][j] = true;

				int max = 0;
				while (!q.isEmpty()) {
					int row = q.poll();
					int col = q.poll();
					int dis = q.poll();

					for (int k = 0; k < 4; k++) {
						int nr = row + dr[k];
						int nc = col + dc[k];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc] || map[nr][nc] == 'W')
							continue;
						q.offer(nr);
						q.offer(nc);
						q.offer(dis + 1);
						visit[nr][nc] = true;

						if (max < dis + 1) {
							max = dis + 1;
						}
					}
				}
				if (max > ans) {
					ans = max;
				}
			}
		}
		System.out.println(ans);
	}

}
