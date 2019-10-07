import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17472 {
	static int ans, n, m;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static ArrayList<int[]> bridge;
	static int[][] map;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int numbering = 1;
		ArrayList<ArrayList<int[]>> island = new ArrayList<>();
		boolean[][] visit = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0 && !visit[i][j]) {
					ArrayList<int[]> thing = new ArrayList<>();
					Queue<int[]> q = new LinkedList<>();
					int[] temp = { i, j };
					q.add(temp);
					thing.add(temp);
					visit[i][j] = true;
					map[i][j] = numbering;

					while (!q.isEmpty()) {
						int[] tt = q.poll();
						int row = tt[0];
						int col = tt[1];

						for (int d = 0; d < 4; d++) {
							int nr = row + dr[d];
							int nc = col + dc[d];

							if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc] || map[nr][nc] == 0)
								continue;
							int[] t = { nr, nc };
							thing.add(t);
							q.add(t);
							visit[nr][nc] = true;
							map[nr][nc] = numbering;
						}
					}
					island.add(thing);
					numbering++;
				}
			}
		} // 섬을 다 찾음.

		bridge = new ArrayList<>();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();

		for (int i = 0; i < island.size(); i++) {
			ArrayList<int[]> one = island.get(i);
			getBridge(one);
		}

		Collections.sort(bridge, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
//		for (int[] ttt : bridge) {
//			System.out.println(Arrays.toString(ttt));
//		}

		parent = new int[island.size() + 1];
		for (int i = 1; i <= island.size(); i++)
			parent[i] = i;

		int cnt = 0;
		for (int i = 0; i < bridge.size(); i++) {
			int a = bridge.get(i)[0];
			int b = bridge.get(i)[1];
			int dis = bridge.get(i)[2];

			if (find(a) != find(b)) {
				union(a, b);
				ans += dis;
				cnt++;
			}
			if (cnt == island.size() - 1)
				break;
		}
		if (cnt != island.size() - 1)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		parent[y] = x;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		int ret = find(parent[a]);
		parent[a] = ret;
		return ret;
	}

	private static void getBridge(ArrayList<int[]> one) {
		int num = map[one.get(0)[0]][one.get(0)[1]];

		for (int i = 0; i < one.size(); i++) {
			int row = one.get(i)[0];
			int col = one.get(i)[1];

			for (int d = 0; d < 4; d++) {
				int dis = 0;
				int nr = row;
				int nc = col;
				while (true) {
					nr = nr + dr[d];
					nc = nc + dc[d];
					dis++;
					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						break;
					if (map[nr][nc] == num)
						break;

					if (map[nr][nc] != 0) {
						if (dis - 1 >= 2) {
							int[] temp = { num, map[nr][nc], dis - 1 };
							bridge.add(temp);
						}
						break;
					}
				}
			}
		}
	}

}