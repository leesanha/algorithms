import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static char[][] map;
	static boolean[][][][] visit;
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		visit = new boolean[n][m][n][m];

		int rrow = 0, rcol = 0, brow = 0, bcol = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'R') {
					rrow = i;
					rcol = j;
				} else if (map[i][j] == 'B') {
					brow = i;
					bcol = j;
				}
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(rrow);
		q.offer(rcol);
		q.offer(brow);
		q.offer(bcol);
		q.offer(0);
		visit[rrow][rcol][brow][bcol] = true;
		ans = -1;

		int cnt;
		while (!q.isEmpty()) {
			rrow = q.poll();
			rcol = q.poll();
			brow = q.poll();
			bcol = q.poll();
			cnt = q.poll();

			for (int i = 0; i < 4; i++) {
				move(rrow, rcol, brow, bcol, i);
			}
		}
		System.out.println(ans);
	}

	static void move(int rrow, int rcol, int brow, int bcol, int dir) {
		int rdis = 0, bdis = 0;
		while (true) {
			rrow = rrow + dr[dir];
			rcol = rcol + dc[dir];
			rdis++;
			if (map[rrow][rcol] == '#') {
				rrow = rrow + dr[(dir + 2) % 4];
				rcol = rcol + dc[(dir + 2) % 4];
				rdis--;
				break;
			}
			if (map[rrow][rcol] == 'O')
				break;
		}
		while (true) {
			brow = brow + dr[(dir + 2) % 4];
			bcol = bcol + dc[(dir + 2) % 4];
			bdis++;
			if (map[brow][bcol] == '#') {
				bdis--;
				break;
			}
			if (map[brow][bcol] == 'O')
				break;
		}

		if (map[rrow][rcol] != 'O' && rrow == brow && rcol == bcol) {
			if (rdis > bdis) {
				brow = brow + dr[(dir + 2) % 4];
				bcol = bcol + dc[(dir + 2) % 4];
			} else {
				rrow = rrow + dr[dir];
				rcol = rcol + dc[dir];
			}
		}

		if (!visit[rrow][rcol][brow][bcol]) {
			visit[rrow][rcol][brow][bcol] = true;
		}

	}
}
