import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 0, 1, 1 };
	static int[] dc = { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 가로: 0, 세로: 1, 대각선: 2
		long[][][] ans = new long[n][n][3];
		ans[0][1][0] = 1;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 1, 0));

		while (!q.isEmpty()) {
			Node nn = q.poll();
			int row = nn.row;
			int col = nn.col;
			int dir = nn.dir;

			if (dir == 0) {// 지금 가로
				for (int i = 0; i < 3; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (i == 1)
						continue;

					if (nr >= n || nr < 0 || nc >= n || nc < 0 || map[nr][nc] == 1)
						continue;
					if ((i == 2 && map[nr - 1][nc] == 1) || (i == 2 && map[nr][nc - 1] == 1))
						continue;

					q.add(new Node(nr, nc, i));
					ans[nr][nc][i] += ans[row][col][dir];

				}
			} else if (dir == 1) {
				for (int i = 1; i < 3; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (nr >= n || nr < 0 || nc >= n || nc < 0 || map[nr][nc] == 1)
						continue;
					if ((i == 2 && map[nr - 1][nc] == 1) || (i == 2 && map[nr][nc - 1] == 1))
						continue;
					q.add(new Node(nr, nc, i));
					ans[nr][nc][i] += ans[row][col][dir];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (nr >= n || nr < 0 || nc >= n || nc < 0 || map[nr][nc] == 1)
						continue;
					if ((i == 2 && map[nr - 1][nc] == 1) || (i == 2 && map[nr][nc - 1] == 1))
						continue;
					q.add(new Node(nr, nc, i));
					ans[nr][nc][i] += ans[row][col][dir];
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(ans[i][j][0] + ans[i][j][1] + ans[i][j][2] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println(ans[n - 1][n - 1][0] + ans[n - 1][n - 1][1] + ans[n - 1][n - 1][2]);
	}

	static class Node {
		int row;
		int col;
		int dir;

		public Node(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

	}
}
