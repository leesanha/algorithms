import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 1, 1, -1, -1 };// 오 밑, 왼 밑, 왼 위, 오 위
	static int[] dc = { 1, -1, -1, 1 };
	static int[] drr = { -1, 0, 1, 0 };
	static int[] dcc = { 0, 1, 0, -1 };
	static int ans, n;
	static Stack<Node> edge;
	static boolean[][] visit;
	static int[][] city;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		city = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		edge = new Stack<>();
		ans = Integer.MAX_VALUE;
		visit = new boolean[n][n];
		for (int i = 0; i < n - 2; i++) {// 밑에 2줄은 볼 필요 없음.
			for (int j = 1; j < n - 1; j++) {// 양 끝 단은 볼 필요 없음.
				visit[i][j] = true;
				edge.push(new Node(i, j));
				dfs(i, j, 0, city[i][j]);
				edge.pop();
				visit[i][j] = false;
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int row, int col, int dir, int sum) {
		System.out.println("[" + row + "," + col + "]");
		if (dir == 4) {
			if (edge.get(0).row == row && edge.get(0).col == col) {
				int ret = cal(sum);
				if (ret < ans)
					ans = ret;
			}
			return;
		}
		int nr = row + dr[dir];
		int nc = col + dc[dir];

		if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc])
			return;

		visit[nr][nc] = true;
		dfs(nr, nc, dir, sum + city[nr][nc]);
		visit[nr][nc] = false;

		if (dir + 1 >= 4)
			return;
		nr = nr + dr[dir + 1];
		nc = nc + dc[dir + 1];

		if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc])
			return;

		visit[nr][nc] = true;
		edge.push(new Node(nr, nc));
		dfs(nr, nc, dir + 1, sum + city[nr][nc]);
		edge.pop();
		visit[nr][nc] = false;
	}

	private static int cal(int sum) {
		for (int i = 0; i < 4; i++) {
			System.out.print("[" + edge.get(i).row + "," + edge.get(i).col + "] ");
		}
		boolean[][] cpVisit = new boolean[n][n];
		int[] sums = new int[5];

		for (int i = 0; i < n; i++)
			cpVisit[i] = visit[i].clone();
		// 가운데 계산
		sums[0] = sum;
		Queue<Node> q = new LinkedList<Node>();
		Node first = edge.get(0);
		cpVisit[first.row][first.col] = true;
		q.add(first);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int row = cur.row;
			int col = cur.col;

			for (int i = 0; i < 4; i++) {
				int nr = row + drr[i];
				int nc = col + dcc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || cpVisit[nr][nc])
					continue;
				cpVisit[nr][nc] = true;
				sums[0] += city[nr][nc];
				q.add(new Node(nr, nc));
			}
		}
		// 1번 계산
		sums[1] = bfs(1, cpVisit);
		// 2번 계산
		sums[2] = bfs(2, cpVisit);
		// 3번 계산
		sums[3] = bfs(3, cpVisit);
		// 4번 계산
		sums[4] = bfs(4, cpVisit);
		return 0;
	}

	private static int bfs(int cityNum, boolean[][] cpVisit) {
		int startRow = 0, startCol = 0, sr = 0, sc = 0, er = 0, ec = 0;// 시작과 끝 row, col
		int ret = 0;
		switch (cityNum) {
		case 1:
			sr = 0;
			er = edge.get(3).row - 1;
			sc = 0;
			ec = edge.get(0).col;
			startRow = edge.get(0).row;
			startCol = edge.get(0).col - 1;
			break;
		case 2:
			sr = 0;
			er = edge.get(1).row;
			sc = edge.get(0).col + 1;
			ec = n - 1;
			startRow = edge.get(0).row;
			startCol = edge.get(0).col + 1;
			break;
		case 3:
			sr = edge.get(1).row + 1;
			er = n - 1;
			sc = edge.get(2).col;
			ec = n - 1;
			startRow = edge.get(1).row + 1;
			startCol = edge.get(1).col;
			break;
		case 4:
			sr = edge.get(3).row;
			er = n - 1;
			sc = 0;
			ec = edge.get(2).col - 1;
			startRow = edge.get(3).row - 1;
			startCol = edge.get(3).col;
			break;
		}

		Queue<Node> q = new LinkedList<Node>();
		cpVisit[startRow][startCol] = true;
		q.add(new Node(startRow, startCol));

		while (!q.isEmpty()) {
			Node temp = q.poll();

			int row = temp.row;
			int col = temp.col;

			for (int i = 0; i < 4; i++) {
				int nr = row + drr[i];
				int nc = col + dcc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || cpVisit[nr][nc])
					continue;
				if (nr < sr || nr > er || nc < sc || nc > ec)
					continue;
				cpVisit[nr][nc] = true;
				ret += city[nr][nc];
				q.add(new Node(nr, nc));
			}
		}

		return ret;
	}

	static class Node {
		int row;
		int col;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}

	}
}
