import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2630 {
	static int[][] map;
	static int white, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		white = 0;
		blue = 0;
		dfs(0, 0, n);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void dfs(int r, int c, int size) {
//		System.out.println(r + " " + c);
		if (size == 0)
			return;
		boolean flag = true;
		int comp = map[r][c];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (comp != map[i][j]) {
					flag = false;
					break;
				}
			}
		}
		if (flag && comp == 1) {
			blue++;
			return;
		}
		if (flag && comp == 0) {
			white++;
			return;
		}

		dfs(r, c, size / 2);// 왼쪽 위
		dfs(r, c + size / 2, size / 2);// 오른쪽 위
		dfs(r + size / 2, c, size / 2);// 왼쪽 밑
		dfs(r + size / 2, c + size / 2, size / 2);// 오른쪽 밑
	}

}