import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 빡치는 문제 넘버원
 */
public class Main13460 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][][][] visit;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		Queue<Integer> red = new LinkedList<Integer>();
		Queue<Integer> blue = new LinkedList<Integer>();

		int srr = 0, src = 0, brr = 0, brc = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'R') {
					red.offer(i);
					red.offer(j);
					red.offer(0);
					srr = i;
					src = j;
				}
				if (map[i][j] == 'B') {
					blue.offer(i);
					blue.offer(j);
					brr = i;
					brc = j;
				}
			}
		}

		// 파란 공만 움직여서 답이 달라지는 경우가 있으므로 4차원 배열로 써야 한다. 개 빡침
		visit = new boolean[n][m][n][m];
		visit[srr][src][brr][brc] = true;

		int ans = -1;

		while (true) {

			// 더 이상 갈 수가 없으면 empty 처리를 해줘야 한다. 개 빡침
			boolean flag = false;
			if (red.isEmpty())
				break;
			int rr = red.poll();
			int rc = red.poll();
			int dis = red.poll();
			int bbr = blue.poll();
			int bbc = blue.poll();
			for (int i = 0; i < 4; i++) {
				int[] rarr = move(rr, rc, i);
				int[] barr = move(bbr, bbc, i);

				if (rarr[0] == barr[0] && rarr[1] == barr[1]) {
					if (map[rarr[0]][rarr[1]] == 'O') {
						continue;
					}
					if (rarr[2] > barr[2]) {
						rarr[0] = rarr[0] + dr[(i + 2) % 4];
						rarr[1] = rarr[1] + dc[(i + 2) % 4];
					} else {
						barr[0] = barr[0] + dr[(i + 2) % 4];
						barr[1] = barr[1] + dc[(i + 2) % 4];
					}
				}
				if (rarr[3] == 1 && barr[3] == 0) {
					if (ans == -1 || dis + 1 < ans)
						ans = dis + 1;
					flag = true;
					break;
				}
				// 파란 공만 들어간 경우에는 visit로 처리하지 않고 다음으로 넘어가야 한다. 개 빡침
				if (rarr[3] == 0 && barr[3] == 1)
					continue;
				if (!visit[rarr[0]][rarr[1]][barr[0]][barr[1]]) {
//					System.out.println(rarr[0] + " " + rarr[1] + " " + barr[0] + " " + barr[1] + " " + i);
					red.offer(rarr[0]);
					red.offer(rarr[1]);
					red.offer(dis + 1);
					blue.offer(barr[0]);
					blue.offer(barr[1]);
					visit[rarr[0]][rarr[1]][barr[0]][barr[1]] = true;
				}
			}
			// 다음에 들어가면 10이 넘어가니까 끝내야 한다 개 빡침
			if (flag || dis > 9) {
				break;
			}

		}
		System.out.println(ans);
	}

	static int[] move(int rr, int rc, int dir) {
		int[] ret = new int[4];
		while (true) {
			rr = rr + dr[dir];
			rc = rc + dc[dir];
			if (map[rr][rc] == '#') {
				rr = rr + dr[(dir + 2) % 4];
				rc = rc + dc[(dir + 2) % 4];
				break;
			}
			if (map[rr][rc] == 'O') {
				ret[3]++;
				break;
			}
			ret[2]++;
		}
		ret[0] = rr;
		ret[1] = rc;

		return ret;
	}

}
