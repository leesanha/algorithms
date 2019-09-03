import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		int[][] visit = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					visit[i][j] = 1;
			}
		}

		int ans = 0, nr, nc, ndir;
		first: while (true) {
			if (visit[row][col] == 0) {
				visit[row][col] = 2;// 먼저 지금 위치 청소
				ans++;
			}
			for (int i = 0; i < 4; i++) {
				ndir = (dir + 3) % 4;
				nr = row + dr[ndir];
				nc = col + dc[ndir];

				if (visit[nr][nc] != 0) {
					dir = ndir;
					continue;
				} else {
					dir = ndir;
					row = nr;
					col = nc;
					continue first;
				}
			}
			row = row + dr[(dir + 2) % 4];
			col = col + dc[(dir + 2) % 4];
			if (map[row][col] == 1) {
				break;
			}
		}
		System.out.println(ans);
	}

}
