import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution7396 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };
	static char[][] map;
	static int n, m;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new char[n][m];

			for (int i = 0; i < n; i++)
				map[i] = br.readLine().trim().toCharArray();

			StringBuilder sb = bfs();
			System.out.format("#%d %s\n", t, sb.toString());
		}
	}

	private static StringBuilder bfs() {
		boolean[][] visit = new boolean[n][m];
		visit[0][0] = true;

		Queue<int[]> q = new LinkedList<int[]>();
		int[] temp = { 0, 0 };
		q.add(temp);

		StringBuilder sb = new StringBuilder();

		while (!q.isEmpty()) {
			int size = q.size();
			int[] ttt = q.peek();
			sb.append(map[ttt[0]][ttt[1]]);

			Queue<int[]> toPut = new LinkedList<>();
			char data = 999;
			for (int i = 0; i < size; i++) {
				int[] t = q.poll();
				int row = t[0];
				int col = t[1];

				for (int d = 0; d < 2; d++) {
					int nr = row + dr[d];
					int nc = col + dc[d];

					if (nr >= n || nc >= m || visit[nr][nc])
						continue;
					visit[nr][nc] = true;
					if (data > map[nr][nc]) {
						data = map[nr][nc];
						toPut.clear();
						int[] tt = { nr, nc };
						toPut.add(tt);
					} else if (data == map[nr][nc]) {
						int[] tt = { nr, nc };
						toPut.add(tt);
					}
				}
			}
			q.addAll(toPut);
		}
		return sb;
	}
}