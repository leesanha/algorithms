
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BoardCover {
	static int ans, h, w;
	static int[][][] block = { { { 1, 0 }, { 1, -1 } }, { { 0, 1 }, { 1, 1 } }, { { 1, 0 }, { 0, 1 } },
			{ { 1, 0 }, { 1, 1 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			boolean[][] map = new boolean[h][w];
			int cnt = h * w;
			for (int i = 0; i < h; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (temp[j] == '#') {
						map[i][j] = true;
						cnt--;
					}
				}
			}

			ans = 0;
			if (cnt % 3 != 0) {
				System.out.println(ans);
				continue;
			}
			dfs(map, cnt);
			System.out.println(ans);
		}
	}

	private static void dfs(boolean[][] map, int cnt) {
		if (cnt == 0) {
			ans++;
			return;
		}
		int x = -1;
		int y = -1;
		first: for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (!map[i][j]) {
					x = i;
					y = j;
					break first;
				}
			}
		}

		if (x == -1)
			return;

		for (int i = 0; i < 4; i++) {
			boolean flag = true;
			for (int j = 0; j < 2; j++) {
				int nr = x + block[i][j][0];
				int nc = y + block[i][j][1];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				boolean[][] cpMap = new boolean[h][w];
				for (int j = 0; j < h; j++)
					cpMap[j] = Arrays.copyOf(map[j], map[j].length);
				cpMap[x][y] = true;
				for (int j = 0; j < 2; j++) {
					int nr = x + block[i][j][0];
					int nc = y + block[i][j][1];
					cpMap[nr][nc] = true;
				}
//				for (int a = 0; a < h; a++) {
//					for (int b = 0; b < w; b++) {
//						System.out.print(cpMap[a][b] ? 1 : 0);
//					}
//					System.out.println();
//				}
//				System.out.println();
				dfs(cpMap, cnt - 3);
			}

		}

	}

}