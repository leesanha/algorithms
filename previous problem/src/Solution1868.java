import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1868 {
	static Scanner sc = new Scanner(System.in);
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Queue<Integer> q;
	static char[][] arr;
	static boolean[][] check;
	static int ans, size;

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			size = sc.nextInt();
			arr = new char[size][size];
			check = new boolean[size][size];
			q = new LinkedList<>();
			ans = 0;

			for (int i = 0; i < size; i++) {
				arr[i] = sc.next().toCharArray();
				for (int j = 0; j < size; j++)
					if (arr[i][j] == '*')
						check[i][j] = true;
			}
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (arr[row][col] == '.') {
						boolean flag = true;
						for (int i = 0; i < 8; i++) {
							int nr = row + dr[i];
							int nc = col + dc[i];

							if (nr < 0 || nr >= size || nc < 0 || nc >= size)
								continue;
							if (arr[nr][nc] == '*') {
								flag = false;
								break;
							}
						}
						if (flag) {
							q.add(row);
							q.add(col);
							bfs();
						}
						/*
						 * for(int i=0;i<size;i++) { for(int j=0;j<size;j++) {
						 * if(check[i][j])System.out.print("1 "); else System.out.print("0 "); }
						 * System.out.println(); } System.out.println();
						 */
					}
				}
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (arr[i][j] == '.')
						ans++;
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
		sc.close();
	}

	private static void bfs() {
		ans++;
		int row, col;
		while (!q.isEmpty()) {
			row = q.poll();
			col = q.poll();

			boolean flag = true;
			for (int i = 0; i < 8; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= size || nc < 0 || nc >= size)
					continue;
				if (arr[nr][nc] == '*') {
					flag = false;
					break;
				}
			}
			if (flag) {
				arr[row][col] = 'x';
				for (int i = 0; i < 8; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (nr < 0 || nr >= size || nc < 0 || nc >= size)
						continue;
					if (arr[nr][nc] != '.' || check[nr][nc])
						continue;
					q.add(nr);
					q.add(nc);
					check[nr][nc] = true;
				}
			} else
				arr[row][col] = 'x';
		}

	}

}
