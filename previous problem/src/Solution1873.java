import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1873 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int h, w;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h][w];

			int row = 0, col = 0, dir = 0;
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '<') {
						row = i;
						col = j;
						dir = 3;
					}
					if (map[i][j] == '^') {
						row = i;
						col = j;
						dir = 0;
					}
					if (map[i][j] == 'v') {
						row = i;
						col = j;
						dir = 2;
					}
					if (map[i][j] == '>') {
						row = i;
						col = j;
						dir = 1;
					}
				}
			}

			int n = Integer.parseInt(br.readLine());
			char[] option = br.readLine().toCharArray();

			for (int i = 0; i < n; i++) {
				if (option[i] == 'S') {
					shoot(row, col, dir);
				} else {
					char next = '9';
					if (option[i] == 'U') {
						dir = 0;
						next = '^';
					}
					if (option[i] == 'D') {
						dir = 2;
						next = 'v';
					}
					if (option[i] == 'L') {
						dir = 3;
						next = '<';
					}
					if (option[i] == 'R') {
						dir = 1;
						next = '>';
					}

					int nr = row + dr[dir];
					int nc = col + dc[dir];

					if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == '*' || map[nr][nc] == '#' || map[nr][nc] == '-') {
						map[row][col] = next;
						continue;
					}
					map[row][col] = '.';
					map[nr][nc] = next;
					row = nr;
					col = nc;
				}

			}

			System.out.print("#" + t + " ");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void shoot(int row, int col, int dir) {
		while (true) {
			row += dr[dir];
			col += dc[dir];

			if (row < 0 || row >= h || col < 0 || col >= w)
				return;
			if (map[row][col] == '#')
				return;
			if (map[row][col] == '*') {
				map[row][col] = '.';
				return;
			}

		}
	}

}