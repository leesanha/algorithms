import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main15683 {
	static int ans, n, m, size;
	static int[][] map;
	static ArrayList<int[]> list;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		boolean[][] check = new boolean[n][m];
		list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					check[i][j] = true;
					if (map[i][j] != 6) {
						int[] temp = { i, j };
						list.add(temp);
					}
				}

			}
		}
		size = list.size();
		ans = Integer.MAX_VALUE;
		dfs(check, 0);
		System.out.println(ans);
	}

	private static void dfs(boolean[][] check, int depth) {
		if (depth == size) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!check[i][j])
						sum++;
				}
			}
			if (ans > sum)
				ans = sum;
			return;
		}

		int row = list.get(depth)[0];
		int col = list.get(depth)[1];

		boolean[][] cpCheck = new boolean[n][m];
		for (int i = 0; i < n; i++)
			cpCheck[i] = Arrays.copyOf(check[i], check[i].length);

		switch (map[row][col]) {
		case 1:
			// 오른쪽
			right(cpCheck, row, col + 1);
			dfs(cpCheck, depth + 1);

			// 왼쪽
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			left(cpCheck, row, col - 1);
			dfs(cpCheck, depth + 1);

			// 위
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			up(cpCheck, row - 1, col);
			dfs(cpCheck, depth + 1);

			// 아래
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			break;
		case 2:
			// 오른쪽, 왼쪽
			right(cpCheck, row, col + 1);
			left(cpCheck, row, col - 1);
			dfs(cpCheck, depth + 1);

			// 위, 아래
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			up(cpCheck, row - 1, col);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			break;
		case 3:
			// 위 오른쪽
			up(cpCheck, row - 1, col);
			right(cpCheck, row, col + 1);
			dfs(cpCheck, depth + 1);
			// 오른쪽 밑
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			right(cpCheck, row, col + 1);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			// 밑 왼쪽
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			down(cpCheck, row + 1, col);
			left(cpCheck, row, col - 1);
			dfs(cpCheck, depth + 1);
			// 왼쪽 위
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			left(cpCheck, row, col - 1);
			up(cpCheck, row - 1, col);
			dfs(cpCheck, depth + 1);
			break;
		case 4:
			// 위 왼 오
			up(cpCheck, row - 1, col);
			left(cpCheck, row, col - 1);
			right(cpCheck, row, col + 1);
			dfs(cpCheck, depth + 1);
			// 위 오 밑
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			up(cpCheck, row - 1, col);
			right(cpCheck, row, col + 1);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			// 오 밑 왼
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			right(cpCheck, row, col + 1);
			left(cpCheck, row, col - 1);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			// 위 왼 밑
			for (int i = 0; i < n; i++)
				cpCheck[i] = Arrays.copyOf(check[i], check[i].length);
			up(cpCheck, row - 1, col);
			left(cpCheck, row, col - 1);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			break;
		case 5:
			up(cpCheck, row - 1, col);
			right(cpCheck, row, col + 1);
			left(cpCheck, row, col - 1);
			down(cpCheck, row + 1, col);
			dfs(cpCheck, depth + 1);
			break;
		}
	}

	static void right(boolean[][] check, int row, int col) {
		while (true) {
			if (col >= m || map[row][col] == 6)
				break;
			check[row][col] = true;
			col++;
		}
	}

	static void left(boolean[][] check, int row, int col) {
		while (true) {
			if (col < 0 || map[row][col] == 6)
				break;
			check[row][col] = true;
			col--;
		}
	}

	static void up(boolean[][] check, int row, int col) {
		while (true) {
			if (row < 0 || map[row][col] == 6)
				break;
			check[row][col] = true;
			row--;
		}
	}

	static void down(boolean[][] check, int row, int col) {
		while (true) {
			if (row >= n || map[row][col] == 6)
				break;
			check[row][col] = true;
			row++;
		}
	}
}