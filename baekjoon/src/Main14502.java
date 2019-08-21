import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14502 {
	static int ans, n, m, size;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] rows;
	static int[] cols;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];// 연구소
		int safe = 0;// 안전한 곳

		Queue<Integer> virus = new LinkedList<Integer>();

		int[][] visit = new int[n][m];// 방문지 체크
		rows = new int[64];// 안전한 곳 좌표
		cols = new int[64];

		ans = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {// 안전한 곳 저장
					rows[safe] = i;
					cols[safe++] = j;
				}
				if (arr[i][j] == 2) {// 바이러스 있는 곳 큐에 저장
					virus.offer(i);
					virus.offer(j);
					visit[i][j] = 2;
				}
				if (arr[i][j] == 1)// 벽있는 곳 visit
					visit[i][j] = 1;
			}
		}
		size = safe;
		perm(visit, virus, safe, 0, 0);
		System.out.println(ans);
	}

	static void perm(int[][] visit, Queue<Integer> virus, int safe, int cnt, int idx) {
		if (cnt == 3) {
			Queue<Integer> cpVirus = new LinkedList<>();
			for (int i = 0; i < virus.size(); i++)
				cpVirus.addAll(virus);
			int[][] cpVisit = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					cpVisit[i][j] = visit[i][j];
				}
			}
			bfs(cpVirus, cpVisit, safe);
			return;
		}
		if (size <= idx)
			return;

		int row = rows[idx];
		int col = cols[idx];
		visit[row][col] = 1;
		perm(visit, virus, safe - 1, cnt + 1, idx + 1);
		visit[row][col] = 0;
		perm(visit, virus, safe, cnt, idx + 1);
	}

	private static void bfs(Queue<Integer> cpVirus, int[][] cpVisit, int safe) {
		while (!cpVirus.isEmpty()) {
			int row = cpVirus.poll();
			int col = cpVirus.poll();

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || cpVisit[nr][nc] != 0)
					continue;
				cpVisit[nr][nc] = 2;
				cpVirus.offer(nr);
				cpVirus.offer(nc);
				safe--;
			}
		}
		ans = (ans < safe) ? safe : ans;
	}
}
