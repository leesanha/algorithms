import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2636 {
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		int alive_cnt = 0;
		int before = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1)
					before++;
			}
		}
		while (true) {
			ans++;
			boolean[][] visit = new boolean[n][m];
			// 먼저 공기를 list에 넣는다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0 && !visit[i][j]) {
						ArrayList<Integer> air = new ArrayList<>();
						air.add(i);
						air.add(j);
						boolean flag = false;// 공기인지 아닌지 판별하는 flag
						Queue<Integer> q = new LinkedList();
						q.add(i);
						q.add(j);
						visit[i][j] = true;

						while (!q.isEmpty()) {
							int row = q.poll();
							int col = q.poll();

							for (int d = 0; d < 4; d++) {
								int nr = row + dr[d];
								int nc = col + dc[d];

								if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
									flag = true;
									continue;
								}
								if (visit[nr][nc] || map[nr][nc] == 1)
									continue;
								if (map[nr][nc] == 2)
									map[nr][nc] = 0;
								visit[nr][nc] = true;
								q.add(nr);
								q.add(nc);
								air.add(nr);
								air.add(nc);
							}
						}
						// 만약 공기가 아닐 경우 치즈 안에 갇혀 있는 것이므로 map에 2로 표시한다.
						if (!flag) {
							for (int k = 0; k < air.size(); k += 2) {
								int row = air.get(k);
								int col = air.get(k + 1);
								map[row][col] = 2;
							}
						}
					} // if문 끝
				}
			} // 2중 for문 끝
			ArrayList<Integer> delete = new ArrayList<>();
			ArrayList<Integer> alive = new ArrayList<>();
			// 치즈를 찾는다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						ArrayList<Integer> cheese = new ArrayList<>();
						cheese.add(i);
						cheese.add(j);
						Queue<Integer> q = new LinkedList();
						q.add(i);
						q.add(j);
						visit[i][j] = true;

						while (!q.isEmpty()) {
							int row = q.poll();
							int col = q.poll();

							for (int d = 0; d < 4; d++) {
								int nr = row + dr[d];
								int nc = col + dc[d];

								if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc])
									continue;
								visit[nr][nc] = true;
								q.add(nr);
								q.add(nc);
								cheese.add(nr);
								cheese.add(nc);
							}
						}
						// 공기와 닿아있는 치즈를 찾는다.
						for (int k = 0; k < cheese.size(); k += 2) {
							int row = cheese.get(k);
							int col = cheese.get(k + 1);

							// 삭제될 치즈인지 판별
							boolean flag = false;
							for (int d = 0; d < 4; d++) {
								int nr = row + dr[d];
								int nc = col + dc[d];

								if (nr < 0 || nr >= n || nc < 0 || nc >= m)
									continue;
								if (map[nr][nc] == 0) {
									flag = true;
									break;
								}
							}
							if (flag) {
								delete.add(row);
								delete.add(col);
							} else {
								alive.add(row);
								alive.add(col);
							}
						} // 삭제될 치즈 판별 끝
					} // if문 끝
				}
			} // 2중 for문 끝
				// 치즈 삭제
			alive_cnt = before - delete.size() / 2;
			for (int k = 0; k < delete.size(); k += 2) {
				int row = delete.get(k);
				int col = delete.get(k + 1);
				map[row][col] = 0;
			}
			if (alive_cnt == 0)
				break;
			before = alive_cnt;
		} // while문 끝
		System.out.println(ans);
		System.out.println(before);
	}

}