import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			int[][] map = new int[n][m];
			boolean[][] visit = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 1;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(sr);
			q.add(sc);
			q.add(1);
			visit[sr][sc] = true;

			int[] dr = { -1, 0, 1, 0 };
			int[] dc = { 0, 1, 0, -1 };

			while (!q.isEmpty()) {
				int row = q.poll();
				int col = q.poll();
				int cnt = q.poll();
				System.out.println(row + " " + col + " " + ans);
				for(int i=0;i<n;i++) {
					for(int j=0;j<m;j++) {
						System.out.print(visit[i][j]?1:0);
					}
					System.out.println();
				}
				System.out.println();
				
				if (cnt >= time)
					continue;

				switch (map[row][col]) {
				case 1:
					for (int i = 0; i < 4; i++) {
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 2:
					for (int i = 0; i < 4; i++) {
						if (i % 2 != 0)
							continue;
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 3:
					for (int i = 0; i < 4; i++) {
						if (i % 2 == 0)
							continue;
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 4:
					for (int i = 0; i < 2; i++) {
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 5:
					for (int i = 1; i < 3; i++) {
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 6:
					for (int i = 2; i < 4; i++) {
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				case 7:
					for (int i = 0; i < 4; i++) {
						if (i == 1 || i == 2)
							continue;
						int nr = row + dr[i];
						int nc = col + dc[i];

						if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visit[nr][nc])
							continue;
						visit[nr][nc] = true;
						q.add(nr);
						q.add(nc);
						q.add(cnt + 1);
						ans++;
					}
					break;
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}
}