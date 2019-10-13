import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution2382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			Node[][] map = new Node[n][n];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				map[row][col] = new Node(row, col, cnt, dir - 1);
			}

			Node[][] next_map = null;
			ArrayList<Node> list = null;
			while (m-- > 0) {
				list = new ArrayList<Node>();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (map[i][j] != null)
							list.add(map[i][j]);
					}
				}
				next_map = new Node[n][n];
				int[][] max = new int[n][n];

				for (int i = 0; i < list.size(); i++) {
					int row = list.get(i).row;
					int col = list.get(i).col;
					int cnt = list.get(i).cnt;
					int dir = list.get(i).dir;

					int nr = row + dr[dir];
					int nc = col + dc[dir];

					// 0->1, 1->0, 2->3, 3->2
					if (nr == 0 || nr == n - 1 || nc == 0 || nc == n - 1) {
						if (cnt / 2 > 0) {
							dir = (dir % 2 == 0) ? dir + 1 : dir - 1;
							next_map[nr][nc] = new Node(nr, nc, cnt / 2, dir);
						}
					} else {
						if (next_map[nr][nc] != null) {
							int sum = next_map[nr][nc].cnt + list.get(i).cnt;
							if (max[nr][nc] < list.get(i).cnt) {
								next_map[nr][nc].cnt = sum;
								next_map[nr][nc].dir = list.get(i).dir;
								max[nr][nc] = list.get(i).cnt;
							} else {
								next_map[nr][nc].cnt = sum;
							}
						} else {
							next_map[nr][nc] = new Node(nr, nc, list.get(i).cnt, list.get(i).dir);
							max[nr][nc] = next_map[nr][nc].cnt;
						}
					}

				}
				map = new Node[n][n];
				for (int i = 0; i < n; i++)
					map[i] = next_map[i].clone();
			}
			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (next_map[i][j] != null) {
						ans += map[i][j].cnt;
					}
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static class Node {
		int row;
		int col;
		int cnt;
		int dir;

		public Node(int row, int col, int cnt, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.dir = dir;
		}

	}
}