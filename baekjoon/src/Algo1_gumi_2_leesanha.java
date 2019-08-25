import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo1_gumi_2_leesanha {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int ans, n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new int[n][m];

			// 총잡이 1, 목표 2. 벽 3
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					String token = st.nextToken();
					if (token.equals("0"))
						map[i][j] = 0;
					if (token.equals("G"))
						map[i][j] = 1;
					if (token.equals("T"))
						map[i][j] = 2;
					if (token.equals("W"))
						map[i][j] = 3;
				}
			}

			ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {// 총잡이면 move 실행
						move(i, j);
					}
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void move(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			while (true) {// 이미 맞춘 목표물, 벽, 총잡이를 만나면 다른 방향으로 바꾼다.
				if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == -1)
					break;
				if (map[nr][nc] == 2) {// 맞춘 목표물은 -1로 바꿔서 중복 체크를 막는다.
					ans++;
					map[nr][nc] = -1;
					break;
				} // 한 칸 더 이동
				nr += dr[i];
				nc += dc[i];
			}
		}
	}

}
