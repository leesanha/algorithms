import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main6087 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		char[][] map = new char[h][w];
		int[][] ans = new int[h][w];
		for (int i = 0; i < h; i++)
			Arrays.fill(ans[i], -1);
		boolean isFirst = true;
		int sr = 0, sc = 0, er = 0, ec = 0;
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if (isFirst && map[i][j] == 'C') {
					sr = i;
					sc = j;
					isFirst = !isFirst;
				}
				if (!isFirst && map[i][j] == 'C') {
					er = i;
					ec = j;
				}
			}
		}
		Queue<Node> q = new LinkedList<Node>();
		ans[sr][sc] = 0;

		// 초기 4개를 넣어준다.
		for (int i = 0; i < 4; i++) {
			int nr = sr + dr[i];
			int nc = sc + dc[i];

			if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == '*')
				continue;
			ans[nr][nc] = 0;
			q.add(new Node(nr, nc, 0, i));
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();
//			System.out.println("빼는 놈");
//			System.out.println(cur);
			int row = cur.row;
			int col = cur.col;
			int dir = cur.dir;
			int cnt = cur.cnt;

//			if(row == er && col == ec)
//				break;

			int nr = 0, nc = 0;

//			System.out.println("넣는놈");
			// 현재 기준
			// 그대로 진행하는거
			nr = row + dr[dir];
			nc = col + dc[dir];
			if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] != '*') {
				if ((ans[nr][nc] != -1 && cnt <= ans[nr][nc]) || ans[nr][nc] == -1) {
					Node next = new Node(nr, nc, cnt, dir);
					ans[nr][nc] = cnt;
					q.add(next);// 직진은 거울 추가 없이 방향 그대로 다시 큐에 넣는다.
//					System.out.println(next);
				}
			}

			// 왼쪽으로 꺽는거
			nr = row + dr[(dir + 3) % 4];
			nc = col + dc[(dir + 3) % 4];
			if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] != '*') {
				if ((ans[nr][nc] != -1 && cnt + 1 <= ans[nr][nc]) || ans[nr][nc] == -1) {
					Node next = new Node(nr, nc, cnt + 1, (dir + 3) % 4);
					ans[nr][nc] = cnt + 1;
					q.add(next);// 꺾는 거는 거울 추가 & 방향 전환해서 큐에 넣는다.
//					System.out.println(next);
				}
			}

			// 오른쪽으로 꺾는거
			nr = row + dr[(dir + 1) % 4];
			nc = col + dc[(dir + 1) % 4];
			if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] != '*') {
				if ((ans[nr][nc] != -1 && cnt + 1 <= ans[nr][nc]) || ans[nr][nc] == -1) {
					Node next = new Node(nr, nc, cnt + 1, (dir + 1) % 4);
					ans[nr][nc] = cnt + 1;
					q.add(next);
//					System.out.println(next);
				}
			}
		}
		System.out.println(ans[er][ec]);
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

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", cnt=" + cnt + ", dir=" + dir + "]";
		}
	}
}
