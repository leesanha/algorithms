import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Rc {
	int row;
	int col;

	public Rc(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

}

public class Solution1249 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();
		int size;
		char[][] map;
		int[][] dis;
		int ans = 0;
		boolean[][] visit;
		int row, col;
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		Queue<Rc> q = new LinkedList<Rc>();
		for (int t = 1; t <= tc; t++) {
			size = sc.nextInt();
			map = new char[size][size];
			dis = new int[size][size];
			visit = new boolean[size][size];

			for (int i = 0; i < size; i++)
				map[i] = sc.next().toCharArray();

			q.add(new Rc(0, 0));
			visit[0][0] = true;

			while (!q.isEmpty()) {
				row = q.peek().row;
				col = q.peek().col;
				q.poll();

				for (int i = 0; i < 4; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];

					if (nr < 0 || nr >= size || nc < 0 || nc >= size)
						continue;
					if ((!visit[nr][nc]) || (dis[nr][nc] > dis[row][col] + map[nr][nc] - '0')) {
						dis[nr][nc] = dis[row][col] + map[nr][nc] - '0';
						q.add(new Rc(nr, nc));
						visit[nr][nc] = true;
					}
				}
			}
			ans = dis[size - 1][size - 1];
			System.out.format("#%d %d\n", t, ans);

		}
		sc.close();
	}

}
