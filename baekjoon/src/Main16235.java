import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main16235 {
	static int ans;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = 5;
			}
		}

		int ans = 0;
		int[][] yang = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				yang[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<int[]> trees = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			int[] temp = { row, col, age };
			trees.add(temp);
			ans++;
		}

		for (int i = 0; i < k; i++) {
			// 봄
			ArrayList<int[]> dead = new ArrayList<>();
			Collections.sort(trees, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[2] > o2[2])
						return 1;
					else if (o1[2] < o2[2])
						return -1;
					else
						return 0;
				}
			});
			ArrayList<int[]> alive = new ArrayList<>();
			for (int j = 0; j < trees.size(); j++) {
				int row = trees.get(j)[0];
				int col = trees.get(j)[1];
				int age = trees.get(j)[2];

				if (map[row][col] < age) {
					int[] temp = { row, col, age };
					dead.add(temp);
					ans--;
					continue;
				}
				map[row][col] -= age;
				trees.get(j)[2]++;
				alive.add(trees.get(j));
			}
			trees = alive;


			// 여름
			for (int j = 0; j < dead.size(); j++) {
				int row = dead.get(j)[0];
				int col = dead.get(j)[1];
				int age = dead.get(j)[2];

				map[row][col] += age / 2;
			}

			// 가을
			for (int j = 0; j < trees.size(); j++) {
				int row = trees.get(j)[0];
				int col = trees.get(j)[1];
				int age = trees.get(j)[2];

				if (age % 5 == 0) {
					for (int dir = 0; dir < 8; dir++) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if (nr < 0 || nr >= n || nc < 0 || nc >= n)
							continue;

						int[] temp = { nr, nc, 1 };
						trees.add(temp);
						ans++;
					}
				}

			}

			// 겨울
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					map[x][y] += yang[x][y];
				}
			}

		}
		System.out.println(ans);
	}

}