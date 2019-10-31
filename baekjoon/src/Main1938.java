import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main1938 {
	static boolean[][] eee;
	static int ans, n;
	static int[][][] visit;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] drro = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dcro = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		eee = new boolean[n][n];
		Node[] bbb = new Node[3];
		visit = new int[n][n][n];

		int num = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'E') {
					eee[i][j] = true;
				}
				if (map[i][j] == 'B') {
					bbb[num] = new Node(i, j, 0);
					// 통나무가 누워 있으면 dir에 0을 저장
					if (num == 1 && bbb[1].row == bbb[0].row) {
						bbb[num].dir = 0;
					}
					// 통나무가 서 있으면 dir에 1을 저장
					if (num == 1 && bbb[1].col == bbb[0].col) {
						bbb[num].dir = 1;
					}
					num++;
				}
			}
		}
		ans = Integer.MAX_VALUE;
//		visit[bbb[0].row][bbb[0].col][bbb[1].row][bbb[1].col][bbb[2].row][bbb[2].col] = true;
//		dfs(bbb, 0);
		bfs(bbb);
		System.out.println((ans == Integer.MAX_VALUE) ? 0 : ans);
	}

	private static void bfs(Node[] bbb) {
		Queue<Node[]> q = new LinkedList<Node[]>();
		q.add(bbb);

		while (!q.isEmpty()) {
			Node[] cur = q.poll();

//			for (int i = 0; i < 3; i++) {
//				System.out.println(cur[i]);
//			}
//			System.out.println();

			// 현재 꺼낸 노드가 종료 조건이면 종료 한다.
			boolean isEnd = true;
			for (int i = 0; i < 3; i++) {
				if (!eee[cur[i].row][cur[i].col]) {
					isEnd = false;
					break;
				}
			}
			if (isEnd) {
				if (cur[1].cnt < ans)
					ans = cur[1].cnt;
				return;
			}

			// 상하좌우로 움직인다.
			for (int dir = 0; dir < 4; dir++) {
				// 이동하는 칸에 1이 있는지 살핀다.
				boolean canMove = true;
				for (int i = 0; i < 3; i++) {
					int nr = cur[i].row + dr[dir];
					int nc = cur[i].col + dc[dir];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') {
						canMove = false;
						break;
					}
				}
				Node[] next = new Node[3];
				// 움직일 수 있으면 옮긴다.
				if (canMove) {
					for (int i = 0; i < 3; i++) {
						next[i] = new Node(cur[i].row + dr[dir], cur[i].col + dc[dir], cur[i].cnt + 1);
					}
					next[1].dir = cur[1].dir;
					// 갔던 곳인지 체크
					if (visit[next[1].row][next[1].col][next[1].dir] == 0) {
						visit[next[1].row][next[1].col][next[1].dir] = next[1].cnt;
						q.add(next);
					}
				}
			}
			// 회전 시킨다.
			// 90도 회전
			boolean canRotate = true;
			int row = cur[1].row;
			int col = cur[1].col;
			for (int i = 0; i < 8; i++) {
				int nr = row + drro[i];
				int nc = col + dcro[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') {
					canRotate = false;
					break;
				}
			}
			if (canRotate) {
				Node[] next = new Node[3];
				next[1] = new Node(cur[1].row, cur[1].col, cur[1].cnt + 1);
				// 통나무가 누워있을 때
				if (cur[1].dir == 0) {
					// 0번을 가운데 통나무의 위로 한다.
					next[0] = new Node(next[1].row + dr[0], next[1].col + dc[0], cur[1].cnt + 1);
					// 2번을 가운데 통나무의 밑으로 한다.
					next[2] = new Node(next[1].row + dr[2], next[1].col + dc[2], cur[1].cnt + 1);
					// 방향 수정
					next[1].dir = 1;
				} else {// 통나무가 서 있을 때
					// 0번을 가운데 통나무의 왼쪽으로 한다.
					next[0] = new Node(next[1].row + dr[3], next[1].col + dc[3], cur[1].cnt + 1);
					// 2번을 가운데 통나무의 오른쪽으로 한다.
					next[2] = new Node(next[1].row + dr[1], next[1].col + dc[1], cur[1].cnt + 1);
					next[1].dir = 0;
				}
				if (visit[next[1].row][next[1].col][next[1].dir] == 0) {
					visit[next[1].row][next[1].col][next[1].dir] = next[1].cnt;
					q.add(next);
				}
			}
		}
	}

	/*
	 * private static void dfs(Node[] bbb, int depth) { if (depth >= ans) return; //
	 * System.out.println(depth); // for (int i = 0; i < 3; i++) { //
	 * System.out.println(bbb[i]); // } // System.out.println(); boolean isEnd =
	 * true; for (int i = 0; i < 3; i++) { if (!eee[bbb[i].row][bbb[i].col]) { isEnd
	 * = false; break; } } if (isEnd) { if (depth < ans) ans = depth; return; } //
	 * 이동(4방향) for (int i = 0; i < 4; i++) { Node[] next = move(bbb, i, depth + 1);
	 * if (next != null) { dfs(next, depth + 1); } } // 90도 회전 boolean canRotate =
	 * true; int row = bbb[1].row; int col = bbb[1].col; for (int i = 0; i < 8; i++)
	 * { int nr = row + drro[i]; int nc = col + dcro[i];
	 * 
	 * if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') { canRotate
	 * = false; break; } } if (canRotate) { Node[] next = new Node[3]; next[1] = new
	 * Node(bbb[1].row, bbb[1].col); // 통나무가 누워있을 때 if (bbb[1].dir == 0) { // 0번을
	 * 가운데 통나무의 위로 한다. next[0] = new Node(next[1].row + dr[0], next[1].col + dc[0]);
	 * // 2번을 가운데 통나무의 밑으로 한다. next[2] = new Node(next[1].row + dr[2], next[1].col +
	 * dc[2]); // 방향 수정 next[1].dir = 1; } else {// 통나무가 서 있을 때 // 0번을 가운데 통나무의 왼쪽으로
	 * 한다. next[0] = new Node(next[1].row + dr[3], next[1].col + dc[3]); // 2번을 가운데
	 * 통나무의 오른쪽으로 한다. next[2] = new Node(next[1].row + dr[1], next[1].col + dc[1]);
	 * next[1].dir = 0; } if
	 * (visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2].row][next[
	 * 2].col] == 0 ||
	 * visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2].row][next[2
	 * ].col] > depth + 1) {
	 * visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2].row][next[2
	 * ].col] = depth + 1; dfs(next, depth + 1); } }
	 * 
	 * }
	 * 
	 * private static Node[] move(Node[] bbb, int dir) { // 이동하는 칸에 1이 있는지 체크 Node[]
	 * next = new Node[3]; boolean canMove = true; for (int i = 0; i < 3; i++) { int
	 * nr = bbb[i].row + dr[dir]; int nc = bbb[i].col + dc[dir];
	 * 
	 * if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == '1') { canMove =
	 * false; break; } }
	 * 
	 * // 움직일 수 있으면 옮긴다. if (canMove) { for (int i = 0; i < 3; i++) { next[i] = new
	 * Node(bbb[i].row + dr[dir], bbb[i].col + dc[dir]); }next[1].dir=bbb[1].dir; //
	 * 갔던 곳인지 체크 if
	 * (visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2].row][next[
	 * 2].col]==0||visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2]
	 * .row][next[2].col]>depth+1)
	 * 
	 * {
	 * visit[next[0].row][next[0].col][next[1].row][next[1].col][next[2].row][next[2
	 * ].col] = depth + 1; return next; } }return null;}
	 */

	static class Node {
		int row;
		int col;
		int dir;
		int cnt;

		public Node(int row, int col, int dir, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.cnt = cnt;
		}

		public Node(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", dir=" + dir + ", cnt=" + cnt + "]";
		}

	}
}
