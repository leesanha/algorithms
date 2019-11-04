import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			int[][] garo = new int[m][n - 1];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n - 1; j++) {
					garo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] sero = new int[m - 1][n];

			for (int i = 0; i < m - 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					sero[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(0, 1, g - garo[0][0], l, 0));
			q.add(new Node(1, 0, g - sero[0][0], l, 1));

			int[][] memoDis = new int[m][n];
			int[][] memoGas = new int[m][n];
			for (int i = 0; i < m; i++) {
				Arrays.fill(memoDis[i], -1);
			}

			int ans = Integer.MAX_VALUE;
			while (!q.isEmpty()) {
				Node cur = q.poll();
//				System.out.println("빼내는 놈");
//				System.out.println(cur);
				int row = cur.row;
				int col = cur.col;
				int dir = cur.dir;

				if (row == m - 1 && col == n - 1) {
					if (cur.dis < ans)
						ans = cur.dis;
				}

				for (int i = 0; i < 2; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (nr < 0 || nr >= m || nc < 0 || nc >= n)
						continue;

//					System.out.println("넣는 놈");
					if (dir == 1) {// 내려오고 있었을 때
						if (i == 0) {// 오른쪽으로 변경
							if (cur.gas - garo[nr][nc - 1] >= 0) {
								if(memoDis[nr][nc] != -1 && memoDis[nr][nc] < cur.dis + l + 1 && memoGas[nr][nc] >= cur.gas - garo[nr][nc - 1]) 
									continue;
								
								Node next = new Node(nr, nc, cur.gas - garo[nr][nc - 1], cur.dis + l + 1, i);
//								System.out.println(next);
								q.add(next);
								memoDis[nr][nc] = cur.dis + l + 1;
								memoGas[nr][nc] = cur.gas - garo[nr][nc - 1];
							}
						} else {// 그대로 내려갈 때
							if (cur.gas - sero[nr - 1][nc] >= 0) {
								if(memoDis[nr][nc] != -1 && memoDis[nr][nc] < cur.dis + l && memoGas[nr][nc] >= cur.gas - sero[nr - 1][nc])
									continue;
//								if (memoDis[nr][nc] == -1 || memoDis[nr][nc] >= cur.dis + l) {
									Node next = new Node(nr, nc, cur.gas - sero[nr - 1][nc], cur.dis + l, i); 
//									System.out.println(next);
									q.add(next);
									memoDis[nr][nc] = cur.dis + l;
									memoGas[nr][nc] = cur.gas - garo[nr - 1][nc];
//								}
							}
						}
					} else {// 오른쪽으로 가고 있었을 때
						if (i == 0) {// 그대로 오른쪽
							if (cur.gas - garo[nr][nc - 1] >= 0) {
//								if (memoDis[nr][nc] == -1 || memoDis[nr][nc] >= cur.dis + l) {
								if(memoDis[nr][nc] != -1 && memoDis[nr][nc] < cur.dis + l && memoGas[nr][nc] >= cur.gas - garo[nr][nc - 1])
									continue;
									Node next = new Node(nr, nc, cur.gas - garo[nr][nc - 1], cur.dis + l, i); 
//									System.out.println(next);
									q.add(next);
									memoDis[nr][nc] = cur.dis + l;
									memoGas[nr][nc] = cur.gas - garo[nr][nc - 1];
//								}
							}
						} else {// 내려가는 걸로 변경
							if (cur.gas - sero[nr - 1][nc] >= 0) {
//								if (memoDis[nr][nc] == -1 || memoDis[nr][nc] >= cur.dis + l + 1) {
								if(memoDis[nr][nc] != -1 && memoDis[nr][nc] < cur.dis + l + 1 && memoGas[nr][nc] >= cur.gas - garo[nr - 1][nc])
									continue;
									Node next = new Node(nr, nc, cur.gas - sero[nr - 1][nc], cur.dis + l + 1, i); 
//									System.out.println(next);
									q.add(next);
									memoDis[nr][nc] = cur.dis + l + 1;
									memoGas[nr][nc] = cur.gas - sero[nr - 1][nc];
//								}
							}
						}
					}
				}
//				System.out.println();
			}
			System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
		}
	}

	static class Node {
		int row;
		int col;
		int gas;
		int dis;
		int dir;

		public Node(int row, int col, int gas, int dis, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.gas = gas;
			this.dis = dis;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", gas=" + gas + ", dis=" + dis + ", dir=" + dir + "]";
		}
	}
}
