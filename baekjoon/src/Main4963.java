import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4963 {
	static int ans;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			int[][] map = new int[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			boolean[][] visit = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						visit[i][j] = true;
						Queue<int[]> q = new LinkedList<int[]>();
						int[] temp = { i, j };
						q.add(temp);

						while (!q.isEmpty()) {
							int[] t = q.poll();
							int row = t[0];
							int col = t[1];

							for (int d = 0; d < 8; d++) {
								int nr = row + dr[d];
								int nc = col + dc[d];

								if (nr < 0 || nr >= h || nc < 0 || nc >= w || visit[nr][nc] || map[nr][nc] == 0)
									continue;
								visit[nr][nc] = true;
								int[] tt = { nr, nc };
								q.add(tt);
							}
						}
						ans++;
					}
				}
			}
			System.out.println(ans);
		}

	}

}