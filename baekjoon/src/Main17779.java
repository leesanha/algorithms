import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 대각선으로 4번 이동하면서 4각형을 그려야 된다는 것은 알았음.
 * 하지만 제대로 만들어 지지 않았다. 이유는 아래에 있음.
 * 대각선을 이동하기 위한 ddr, ddc
 * BFS를 위한 dr, dc
 * 꼭지점 4개를 저장하기 위한 edge
 */

public class Main17779 {
	static int n, ans;
	static int[] ddr = { 1, 1, -1, -1 };
	static int[] ddc = { 1, -1, -1, 1 };
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] check;

	static Stack<Node> edge;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check = new boolean[n][n];
		edge = new Stack<>();
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < n - 2; i++) {
			for (int j = 1; j < n - 1; j++) {
//				check[i][j] = true;
				edge.push(new Node(i, j));
				dfs(0, 0, i, j);
				edge.pop();
//				check[i][j] = false;
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int dir, int sum, int row, int col) {
		// 처음 꼭지점을 만나게 되면 계산에 들어간다.
		if (dir == 3 && row == edge.get(0).row && col == edge.get(0).col) {
//			for (int i = 0; i < 4; i++) {
//				System.out.println(edge.get(i));
//			}

			int ret = cal(sum);
//			System.out.println();

			if (ret < ans)
				ans = ret;
			return;
		}

		// 직진
		int nr = row + ddr[dir];
		int nc = col + ddc[dir];

		/*
		 * 처음에는 이렇게 코드를 짰었음. 하지만 코드가 제대로 동작하지 않았다. 예를 들어 6 X 6 배열에서 (row,col)이 (0,1),
		 * (1,2), (2,3), (3,4), (4,5)로 진행되었을 때, nr = 5, nc = 6이 되어 return하게 된다. 이 때 row
		 * = 4, col = 5 이다. 따라서, (5,4)방향을 꺽어서 가야 하는데, return을 해버려서 row = 3, col = 4로 돌아
		 * 가버리는 코드가 된다.
		 * 
		 * if (nr < 0 || nr >= n || nc < 0 || nc >= n || check[nr][nc]) return;
		 * check[nr][nc] = true; dfs(dir, sum + map[nr][nc], nr, nc); check[nr][nc] =
		 * false;
		 */

		/*
		 * 그래서 아래와 같이 코드를 변경했다. row = 4, col = 5일 때 nr = 5, nc =6이 된다. 이 때 return 시키는 것이
		 * 아니라. 꺽은 방향인 (5,4)를 봐야 하기 때문에
		 */
		if (nr >= 0 && nr < n && nc >= 0 && nc < n && !check[nr][nc]) {
			check[nr][nc] = true;
			dfs(dir, sum + map[nr][nc], nr, nc);
			check[nr][nc] = false;
		}

		/*
		 * 여기로 코드가 진행되어야 한다. 따라서 return이 아니라 조건이 해당할 때만 직진을 시켜주는 코드로 변경했다. 아래는 방향을 꺾는
		 * 코드이다.
		 */

		// 방향 꺽기
		if (dir >= 3)
			return;
		nr = row + ddr[dir + 1];
		nc = col + ddc[dir + 1];

		if (nr >= 0 && nr < n && nc >= 0 && nc < n && !check[nr][nc]) {
			check[nr][nc] = true;
			edge.push(new Node(row, col));
			dfs(dir + 1, sum + map[nr][nc], nr, nc);
			edge.pop();
			check[nr][nc] = false;
		}

	}

	private static int cal(int sum) {
		int[] sums = new int[5];
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < n; i++)
			visit[i] = Arrays.copyOf(check[i], n);

		/*
		 * 계산법 4각형을 만들면서 sum을 구했고, edge라는 arraylist에 꼭지점 4개를 담았다. 모든 마을은 어느 한 점을 기준으로
		 * 하고, 경계값을 두어서 BFS를 돌리면 된다고 생각했다. 그리고 그 이유는 대각선을 그리면서 visit를 체크했고, 이를 통해 마을의
		 * 경계가 생겼기 때문이다. 처음에는 다 경계가 달라서 BFS를 각각 만들어야 되나 싶었는데, BFS에서 if (nr < sr || nr >=
		 * er || nc < sc || nc >= ec || visit[nr][nc]) continue; 위와 같은 코드처럼 경계를 벗어나는 것을
		 * 항상 제외해왔던 것을 생각했다.(빨리 생각안났음..) 따라서 BFS 메소드를 하나만 짜두고 모든 마을을 계산하는데 재사용했다. 하지만
		 * 가운데 마을은 예외가 발생했다.
		 */

		/*
		 * 1. 처음에는 가운데 있는 친구들도 BFS로 계산이 될 거라고 생각했다. 하지만 o o o o o o 이런 경우에 BFS가 안돌아 간다.
		 * 따라서 이 경우만 따로 계산을 해주었고, 나머지 도시들은 BFS를 돌려주었다.
		 * 
		 * 계산은 2중 for문을 도는 데 row는 0번 꼭지점의 row에서 부터 2번 꼭지점의 row까지 돌면 되는 것을 알게 됨. col은 3번
		 * 꼭지점의 col에서 부터 1번 꼭지점의 col까지 돌면 되는 것을 알게 됨. 나머지는 더하기만 하면 된다.
		 */

		// 시작 좌표는 0번 꼭지점의 왼쪽
		sums[1] = bfs(edge.get(0).row, edge.get(0).col - 1, 0, 0, edge.get(3).row, edge.get(0).col + 1, visit);
//		print_visit(visit);

		// 시작 좌표는 0번 꼭지점의 오른쪽
		sums[2] = bfs(edge.get(0).row, edge.get(0).col + 1, 0, edge.get(0).col + 1, edge.get(1).row + 1, n, visit);
//		print_visit(visit);

		// 시작 좌표는 2번 꼭지점의 오른쪽
		sums[3] = bfs(edge.get(2).row, edge.get(2).col + 1, edge.get(1).row + 1, edge.get(2).col, n, n, visit);
//		print_visit(visit);

		// 시작 좌표는 2번 꼭지점의 왼쪽
		sums[4] = bfs(edge.get(2).row, edge.get(2).col - 1, edge.get(3).row, 0, n, edge.get(2).col, visit);
//		print_visit(visit);

		sums[0] = sum;
		for (int i = edge.get(0).row; i < edge.get(2).row; i++) {
			for (int j = edge.get(3).col; j < edge.get(1).col; j++) {
				if (!visit[i][j])
					sums[0] += map[i][j];
			}
		}
		// 최대값, 최소값을 찾는다.
		int max = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
//			System.out.print(sums[i] + " ");
			if (sums[i] > max)
				max = sums[i];
			if (sums[i] < min)
				min = sums[i];
		}
//		System.out.println();
//		System.out.println(max - min);
		return max - min;
	}

	private static void print_visit(boolean[][] visit) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j])
					System.out.print(1);
				else
					System.out.print(0);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int bfs(int startRow, int startCol, int sr, int sc, int er, int ec, boolean[][] visit) {
		int sum = 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(startRow, startCol));
		visit[startRow][startCol] = true;
		sum += map[startRow][startCol];

		while (!q.isEmpty()) {
			Node temp = q.poll();

			int row = temp.row;
			int col = temp.col;

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < sr || nr >= er || nc < sc || nc >= ec || visit[nr][nc])
					continue;
				visit[nr][nc] = true;
				sum += map[nr][nc];
				q.add(new Node(nr, nc));
			}
		}
		return sum;
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
