import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1861_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());

			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			/*
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
*/
			int room = -1;
			int cnt = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int res = bfs(i, j);
					if (cnt < res) {
						cnt = res;
						room = map[i][j];
					}else if(cnt == res) {
						if (room > map[i][j]) {
							cnt = res;
							room = map[i][j];
						}
					}
				}
			}

			System.out.format("#%d %d %d\n", t, room, cnt);
		}
	}

	static int bfs(int row, int col) {
		boolean[][] visit = new boolean[n][n];
		int ret = 0;

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(row);
		q.offer(col);
		q.offer(1);
		visit[row][col] = true;

		while (!q.isEmpty()) {
			row = q.poll();
			col = q.poll();
			int dis = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc])
					continue;
				if (map[row][col] + 1 == map[nr][nc]) {
					q.offer(nr);
					q.offer(nc);
					q.offer(dis + 1);
					visit[nr][nc] = true;
				}
			}
			if (dis > ret)
				ret = dis;
		}

		return ret;
	}

}
