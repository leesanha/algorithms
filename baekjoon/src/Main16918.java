import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main16918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		char[][] map = new char[r][c];
		int[][] time = new int[r][c];

		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 1초가 지나고 끝나면 처음 입력값 상태로 종료
		if (n == 1) {
			for (int i = 0; i < r; i++) {
				System.out.println(map[i]);
			}
			return;
		}

		// 2초에 모든 칸에 폭탄을 설치한다.
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'O') {
					time[i][j] = 2;
				} else {
					map[i][j] = 'O';
					time[i][j] = 0;
				}
			}
		}

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		n -= 2;
		boolean flag = true;//시간이 1초 증가할 때 폭탄을 설치하는 것과 폭탄이 터지는 것이 매번 다르기 때문에 boolean으로 check
		while (n-- > 0) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 'O') {
						time[i][j]++;
					}
				}
			}
			if (flag) {
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (time[i][j] == 3) {
							map[i][j] = '.';
							for (int d = 0; d < 4; d++) {
								int nr = i + dr[d];
								int nc = j + dc[d];

								if (nr < 0 || nr >= r || nc < 0 || nc >= c)
									continue;
								map[nr][nc] = '.';
							}
						}
					}
				}
			} else {
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (map[i][j] == '.') {
							time[i][j] = 0;
							map[i][j] = 'O';
						}
					}
				}

			}
			flag = !flag;
		}
		for (int i = 0; i < r; i++) {
			System.out.println(map[i]);
		}
	}
}
