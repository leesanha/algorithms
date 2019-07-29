import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Rcc {
	int row;
	int col;
	int dir;

	public Rcc() {
	}

	public Rcc(int row, int col, int dir) {
		super();
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
}

public class Solution1824 {
	static Scanner sc = new Scanner(System.in);
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static boolean[][][][] visit;
	static char[][] arr;
	static int row_size;
	static int col_size;
	static int mem;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			row_size = sc.nextInt();
			col_size = sc.nextInt();
			visit = new boolean[row_size][col_size][16][4];
			arr = new char[row_size][col_size];
			mem = 0;

			boolean hasEnd = false;

			for (int i = 0; i < row_size; i++) {
				arr[i] = sc.next().toCharArray();
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j] == '@')
						hasEnd = true;
				}
			}

			if (!hasEnd) {
				System.out.format("#%d NO\n", t);
				continue;
			}

			System.out.format("#%d %s\n", t, bfs(0, 0, 0) ? "YES" : "NO");

		}
		sc.close();
	}

	static boolean bfs(int row, int col, int dir) {
		int nr = 0, nc = 0;

		Queue<Rcc> q = new LinkedList<>();
		q.add(new Rcc(row, col, dir));
		visit[row][col][mem][dir] = true;

		while (!q.isEmpty()) {
			row = q.peek().row;
			col = q.peek().col;
			dir = q.peek().dir;
			q.poll();

			if ((arr[row][col] == '<') || (arr[row][col] == '_' && mem != 0))
				dir = 2;
			else if (arr[row][col] == '>' || (arr[row][col] == '_' && mem == 0))
				dir = 0;
			else if ((arr[row][col] == '^') || (arr[row][col] == '|' && mem != 0))
				dir = 3;
			else if ((arr[row][col] == 'v') || (arr[row][col] == '|' && mem == 0))
				dir = 1;
			else if (arr[row][col] == '@')
				return true;
			else if (arr[row][col] >= '0' && arr[row][col] <= '9')
				mem = arr[row][col] - '0';
			else if (arr[row][col] == '+')
				mem = (mem + 1) % 16;
			else if (arr[row][col] == '-') {
				mem--;
				if (mem == -1)
					mem = 15;
			}
			else if (arr[row][col] == '?') {
				for (int i = 0; i < 4; i++) {
					nr = row + dr[i];
					nc = col + dc[i];
					if (nr >= row_size)
						nr = 0;
					if (nr < 0)
						nr = row_size - 1;
					if (nc >= col_size)
						nc = 0;
					if (nc < 0)
						nc = col_size - 1;
					
					if (!visit[nr][nc][mem][i]) {
						q.add(new Rcc(nr, nc, i));
						visit[nr][nc][mem][i] = true;
					}
				}
			}
			
			nr = row + dr[dir];
			nc = col + dc[dir];

			if (nr >= row_size)
				nr = 0;
			if (nr < 0)
				nr = row_size - 1;
			if (nc >= col_size)
				nc = 0;
			if (nc < 0)
				nc = col_size - 1;
			
			if (!visit[nr][nc][mem][dir]) {
				q.add(new Rcc(nr, nc, dir));
				visit[nr][nc][mem][dir] = true;
			}
		}

		return false;
	}
}
