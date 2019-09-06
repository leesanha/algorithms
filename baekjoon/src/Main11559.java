import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11559 {
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		char[][] map = new char[12][6];
		for (int i = 0; i < 12; i++)
			map[i] = br.readLine().toCharArray();

		ans = 0;
		while (true) {
			boolean flag = false;
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						boolean[][] visit = new boolean[12][6];
						char target = map[i][j];
						ArrayList<Integer> del = new ArrayList<>();
						Queue<Integer> q = new LinkedList<>();
						q.add(i);
						q.add(j);
						visit[i][j] = true;
						del.add(i);
						del.add(j);

						while (!q.isEmpty()) {
							int row = q.poll();
							int col = q.poll();

							for (int dir = 0; dir < 4; dir++) {
								int nr = row + dr[dir];
								int nc = col + dc[dir];

								if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6 || visit[nr][nc] || map[nr][nc] == '.')
									continue;
								if (map[nr][nc] == target) {
									del.add(nr);
									del.add(nc);
									visit[nr][nc] = true;
									q.add(nr);
									q.add(nc);
								}
							}
						}
						if (del.size() >= 8) {// 뿌요 삭제
							for (int cnt = 0; cnt < del.size(); cnt += 2) {
								map[del.get(cnt)][del.get(cnt + 1)] = '.';
							}
							flag = true;
						}
					} // 한 번 터뜨리는 것.
				}
			} // 터질 것들 찾는다.
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					int cur = i;
					if (map[cur][j] != '.') {
						while (true) {
							if (cur + 1 == 12)
								break;
							if (map[cur + 1][j] != '.')
								break;
							cur++;
						}
						if(cur != i) {
							map[cur][j] = map[i][j];
							map[i][j] = '.';
						}
					}
				}
			}
			
			if (!flag)
				break;
			ans++;
		} // 다 터질때까지
		System.out.println(ans);
	}

}