import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import jdk.nashorn.internal.ir.Node;

public class Main17142 {
	static int[][] map;
	static int n, m;
	static boolean[] comb;
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static ArrayList<Node> virus;
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int free = 0;
		virus = new ArrayList<>();
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					free++;
				if (map[i][j] == 2)
					virus.add(new Node(i, j));
			}
		}
		comb = new boolean[virus.size()];
		ans = Integer.MAX_VALUE;

		dfs(0, 0, free);
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}

	private static void dfs(int depth, int cnt, int free) {
		if (cnt == m) {
			ArrayList<Node> active = new ArrayList<>();
			for (int i = 0; i < comb.length; i++) {
				if (comb[i]) {
					active.add(virus.get(i));
				}
			}
			flag = false;
			int ret = bfs(active, free);
			if (flag && ret < ans)
				ans = ret;
			return;
		}
		if (depth >= virus.size())
			return;
		comb[depth] = true;
		dfs(depth + 1, cnt + 1, free);
		comb[depth] = false;
		dfs(depth + 1, cnt, free);
	}

	private static int bfs(ArrayList<Node> active, int free) {
//		System.out.println("BFS 시작");
		int ret = 0;

		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < active.size(); i++) {
			Node nd = active.get(i);
//			System.out.println(nd);
			int[] temp = { nd.row, nd.col, 0 };
			q.add(temp);
			visit[nd.row][nd.col] = true;
		}
//		System.out.println();

		int[][] distance = new int[n][n];

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int row = now[0];
			int col = now[1];
			int dis = now[2];

			if (map[row][col] != 2) {
				distance[row][col] = dis;
				if (dis > ret)
					ret = dis;
			}

			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || visit[nr][nc] || map[nr][nc] == 1)
					continue;
				if(map[nr][nc] == 0)
					free--;
				visit[nr][nc] = true;
				int[] temp = { nr, nc, dis + 1 };
				q.add(temp);
			}
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					if (map[i][j] == 1)
//						System.out.print("-");
//					else
//						System.out.print(distance[i][j]);	
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

//		System.out.println("ret: " + ret);
//		System.out.println("free: " + free);
		if (free == 0)
			flag = true;

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