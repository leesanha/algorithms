import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main15685 {
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static boolean[][] map;
	static int x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		map = new boolean[102][102];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			Stack<Integer> stack = new Stack<>();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			map[a][b] = true;
			int dir = Integer.parseInt(st.nextToken());

			stack.add(dir);
			x = a + dx[dir];
			y = b + dy[dir];
			map[x][y] = true;
			int gener = Integer.parseInt(st.nextToken());
			dfs(stack, gener, 0);
		}
		int ans = 0;
		for (int j = 0; j < 102; j++) {
			for (int i = 0; i < 102; i++) {
				if (map[i][j] && map[i + 1][j + 1] && map[i + 1][j] && map[i][j + 1])
					ans++;
			}
		}
		System.out.println(ans);
	}

	private static void dfs(Stack<Integer> stack, int gener, int depth) {
		if (depth == gener)
			return;
		Stack<Integer> cpStack = new Stack<>();
		cpStack.addAll(stack);
		while (!stack.isEmpty()) {
			int dir = (stack.pop() + 1) % 4;
			cpStack.add(dir);
			x += dx[dir];
			y += dy[dir];

			map[x][y] = true;
		}
		dfs(cpStack, gener, depth + 1);
	}

}
