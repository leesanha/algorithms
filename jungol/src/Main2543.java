import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2543 {
	static int n;
	static int[][] map;
	static int row, col;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		map[row][col] = 0;
		dfs(0, 0, n - 1, row, col);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void dfs(int r, int c, int n, int row, int col) {
		int mr = n / 2 + r, mc = n / 2 + c;
		if (row <= mr && col <= mc) {// 왼쪽 위
			map[mr][mc + 1] = 1;
			map[mr + 1][mc + 1] = 1;
			map[mr + 1][mc] = 1;
			if (n > 1) {
				dfs(r, c, n / 2, row, col);
				dfs(r, mc + 1, n / 2, mr, mc + 1);
				dfs(mr + 1, mc + 1, n / 2, mr + 1, mc + 1);
				dfs(mr + 1, c, n / 2, mr + 1, mc);
			}
		} else if (row <= mr && col > mc) {// 오른쪽 위
			map[mr][mc] = 2;
			map[mr + 1][mc + 1] = 2;
			map[mr + 1][mc] = 2;
			if (n > 1) {
				dfs(r, c, n / 2, mr, mc);
				dfs(r, mc + 1, n / 2, row, col);//
				dfs(mr + 1, mc + 1, n / 2, mr + 1, mc + 1);
				dfs(mr + 1, c, n / 2, mr + 1, mc);
			}

		} else if (row > mr && col <= mc) {// 왼쪽 밑
			map[mr][mc + 1] = 3;
			map[mr][mc] = 3;
			map[mr + 1][mc + 1] = 3;
			if (n > 1) {
				dfs(r, mc + 1, n / 2, mr, mc + 1);
				dfs(r, c, n / 2, mr, mc);
				dfs(mr + 1, c, n / 2, row, col);
				dfs(mr + 1, mc + 1, n / 2, mr + 1, mc + 1);
			}

		} else if (row > mr && col > mc) {// 오른쪽 밑
			map[mr][mc + 1] = 4;
			map[mr][mc] = 4;
			map[mr + 1][mc] = 4;
			if (n > 1) {
				dfs(r, mc + 1, n / 2, mr, mc + 1);
				dfs(r, c, n / 2, mr, mc);
				dfs(mr + 1, c, n / 2, mr + 1, mc);
				dfs(mr + 1, mc + 1, n / 2, row, col);
			}
		}
	}
}