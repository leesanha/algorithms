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

		int cnt = 0, rnr = 0, rnc = 0, bnr = 0, bnc = 0;
		boolean rflag = false, bflag = false;
		while (!q.isEmpty()) {
			rrow = q.poll();
			rcol = q.poll();
			brow = q.poll();
			bcol = q.poll();
			cnt = q.poll();

			if (cnt > 10)
				break;

			for (int i = 0; i < 4; i++) {
				int rdis = 0, bdis = 0;
				rflag = bflag = false;
				rnr = rrow;
				rnc = rcol;
				bnr = brow;
				bnc = bcol;
				while (true) {
					rnr = rnr + dr[i];
					rnc = rnc + dc[i];
					rdis++;
					if (map[rnr][rnc] == '#') {
						rnr = rnr + dr[(i + 2) % 4];
						rnc = rnc + dc[(i + 2) % 4];
						rdis--;
						break;
					}
					if (map[rnr][rnc] == 'O') {
						rflag = true;
						break;
					}
				}
				while (true) {
					bnr = bnr + dr[i];
					bnc = bnc + dc[i];
					bdis++;
					if (map[bnr][bnc] == '#') {
						bnr = bnr + dr[(i + 2) % 4];
						bnc = bnc + dc[(i + 2) % 4];
						bdis--;
						break;
					}
					if (map[bnr][bnc] == 'O') {
						bflag = true;
						break;
					}
				}

				if (!rflag && !bflag) {
					if (map[rnr][rnc] != 'O' && rnr == bnr && rnc == bnc) {
						if (rdis > bdis) {
							bnr = bnr + dr[(i + 2) % 4];
							bnc = bnc + dc[(i + 2) % 4];
						} else {
							rnr = rnr + dr[(i + 2) % 4];
							rnc = rnc + dc[(i + 2) % 4];
						}
					}

					if (!visit[rnr][rnc][bnr][bnc]) {
						visit[rnr][rnc][bnr][bnc] = true;
						q.offer(rnr);
						q.offer(rnc);
						q.offer(bnr);
						q.offer(bnc);
						q.offer(cnt + 1);
					}
				} else if (rflag && !bflag) {
					ans = cnt + 1;
					break;
				}

			}
			if (rflag && !bflag) {
				ans = cnt + 1;
				break;
			}
		}
		System.out.println(ans);
	}

	static void move(int rrow, int rcol, int brow, int bcol, int dir) {

	}
}
