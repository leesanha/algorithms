import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16234 {
	static int n, l, r;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		while (true) {
			int[][] visit = new int[n][n];

			int union = 1;
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visit[i][j] == 0) {
						Queue<Integer> q = new LinkedList<>();
						ArrayList<int[]> list = new ArrayList<>();
						q.offer(i);
						q.offer(j);
						visit[i][j] = union;
						int[] temp = { i, j };
						list.add(temp);
						int sum = map[i][j];

						while (!q.isEmpty()) {
							int row = q.poll();
							int col = q.poll();

							for (int dir = 0; dir < 4; dir++) {
								int nr = row + dr[dir];
								int nc = col + dc[dir];

								if (nr < 0 || nr >= n || nc < 0 || nc >= n)
									continue;
								if (Math.abs(map[row][col] - map[nr][nc]) >= l
										&& Math.abs(map[row][col] - map[nr][nc]) <= r && visit[nr][nc] == 0) {
									q.offer(nr);
									q.offer(nc);
									visit[nr][nc] = union;
									sum += map[nr][nc];
									int[] tem = { nr, nc };
									list.add(tem);
									flag = true;
								}
							}
						}
//						System.out.println("map");
//						for (int x = 0; x < n; x++) {
//							for (int y = 0; y < n; y++) {
//								System.out.print(map[x][y]);
//							}
//							System.out.println();
//						}
//						System.out.println("visit");
//						for (int x = 0; x < n; x++) {
//							for (int y = 0; y < n; y++) {
//								System.out.print(visit[x][y]);
//							}
//							System.out.println();
//						}
						sum /= list.size();
						for (int x = 0; x < list.size(); x++)
							map[list.get(x)[0]][list.get(x)[1]] = sum;

						union++;
					}

				}
			}

			if (!flag)
				break;
			ans++;
		}
		System.out.println(ans);
	}
}
