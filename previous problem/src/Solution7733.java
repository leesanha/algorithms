import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution7733 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans, size;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());

			map = new int[size][size];
			visit = new boolean[size][size];

			int cnt = 1;
			boolean flag = false;
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (cnt < map[i][j])
						cnt = map[i][j];
					if(map[i][j] != 1)
						flag = true;
				}
			}

			ans = 0;
			for (int i = 1; i <= cnt; i++) {// 최대 일 수 만큼 돈다.
				int temp = 0;
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (map[j][k] == i)
							visit[j][k] = true;
					}
				}
				boolean[][] cpVisit = new boolean[size][size];
				for (int x = 0; x < size; x++) {
					for (int y = 0; y < size; y++) {
						cpVisit[x][y] = visit[x][y];
					}
				}
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (!cpVisit[j][k]) {
							Queue<Integer> q = new LinkedList();
							q.offer(j);
							q.offer(k);
							cpVisit[j][k] = true;
							while (!q.isEmpty()) {
								int row = q.poll();
								int col = q.poll();

								for (int d = 0; d < 4; d++) {
									int nr = row + dr[d];
									int nc = col + dc[d];

									if (nr < 0 || nr >= size || nc < 0 || nc >= size || cpVisit[nr][nc])
										continue;
									q.offer(nr);
									q.offer(nc);
									cpVisit[nr][nc] = true;
								}
							}
							temp++;

						}
					}
				}

				if (ans < temp)
					ans = temp;
			}

			if(!flag)
				System.out.println("#" + t + " " + 1);
			else
				System.out.println("#" + t + " " + ans);
		}
	}
}
