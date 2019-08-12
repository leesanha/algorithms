import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1082 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		boolean[][] bvisit = new boolean[n][m];
		boolean[][] yvisit = new boolean[n][m];

		Queue<Integer> yong = new LinkedList<Integer>();
		Queue<Integer> bull = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'S') {
					yong.offer(0);
					yong.offer(i);
					yong.offer(j);
					yvisit[i][j] = true;
				}
				if (map[i][j] == '*') {
					bull.offer(0);
					bull.offer(i);
					bull.offer(j);
					bvisit[i][j] = true;
				}
			}
		}

		int level = 0;
		while (true) {
			int row, col, nr, nc, minute;

			while (true) {
				if (bull.isEmpty())
					break;
				if (bull.peek() == level + 1)
					break;
				minute = bull.poll();
				row = bull.poll();
				col = bull.poll();

				for (int i = 0; i < 4; i++) {
					nr = row + dr[i];
					nc = col + dc[i];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 'X' || map[nr][nc] == 'D'
							|| bvisit[nr][nc])
						continue;
					bull.offer(minute + 1);
					bull.offer(nr);
					bull.offer(nc);
					bvisit[nr][nc] = true;
				}
			}
			while (true) {
				if (yong.isEmpty()) {
					System.out.println("impossible");
					return;
				}
				if (yong.peek() == level + 1)
					break;
				minute = yong.poll();
				row = yong.poll();
				col = yong.poll();

				for (int i = 0; i < 4; i++) {
					nr = row + dr[i];
					nc = col + dc[i];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 'X' || yvisit[nr][nc]
							|| bvisit[nr][nc])
						continue;

					if (map[nr][nc] == 'D') {
						System.out.println(minute + 1);
						return;
					}
					yong.offer(minute + 1);
					yong.offer(nr);
					yong.offer(nc);
					yvisit[nr][nc] = true;
				}
			}
			level++;

		}
	}

}
