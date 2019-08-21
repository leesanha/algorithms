import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ans, n, m;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];

		int safe = 0;
		int wall = 3;

		Queue<Integer> virus = new LinkedList<Integer>();

		int[][] visit = new int[n][m];
		ArrayList<Posi> list = new ArrayList();

		ans = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					list.add(new Posi(i, j));
					safe++;
				}
				if (arr[i][j] == 2) {
					virus.offer(i);
					virus.offer(j);
					visit[i][j] = 1;
				}
				if (arr[i][j] == 1)
					visit[i][j] = 1;
			}
		}

		perm(visit, virus, list, safe, 0, 0);
		System.out.println(ans);
	}

	static void perm(int[][] visit, Queue<Integer> virus, ArrayList<Posi> list, int safe, int cnt, int idx) {
		if (list.size() <= idx)
			return;
		if (cnt == 3) {
			Queue<Integer> cpVirus = virus;
			int[][] cpVisit = new int[n][m];
//			System.out.println("wall");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
//					System.out.print(visit[i][j] + " ");
					cpVisit[i][j] = visit[i][j];
				}
//				System.out.println();
			}
//			System.out.println();
			int ret = bfs(cpVirus, cpVisit, safe);
			ans = (ans < ret) ? ret : ans;
		}

		int row = list.get(idx).row;
		int col = list.get(idx).col;
		visit[row][col] = 1;
		perm(visit, virus, list, safe - 1, cnt + 1, idx + 1);
		visit[row][col] = 0;
		perm(visit, virus, list, safe - 1, cnt, idx + 1);
	}

	private static int bfs(Queue<Integer> cpVirus, int[][] cpVisit, int safe) {
		while (!cpVirus.isEmpty()) {
			int row = cpVirus.poll();
			int col = cpVirus.poll();

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || cpVisit[nr][nc] == 1)
					continue;
				cpVisit[nr][nc] = 1;
				cpVirus.offer(nr);
				cpVirus.offer(nc);
				safe--;
			}
		}
//		System.out.println("virus");
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(cpVisit[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("safe: " + safe);
		return safe;
	}

	static class Posi {
		int row;
		int col;

		public Posi(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}
}
