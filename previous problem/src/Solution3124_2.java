import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3124_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			ArrayList<Edge>[] g = new ArrayList[v + 1];
			for (int i = 0; i < v + 1; i++)
				g[i] = new ArrayList<>();
			boolean[] visit = new boolean[v + 1];

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				g[from].add(new Edge(to, val));
				g[to].add(new Edge(from, val));
			}

			long ans = 0;
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (Edge eg : g[1])
				pq.offer(eg);
			visit[1] = true;

			while (!pq.isEmpty()) {
				Edge eg = pq.poll();
				if (visit[eg.to])
					continue;
				visit[eg.to] = true;
				ans += eg.val;
				for (Edge egg : g[eg.to])
					pq.offer(egg);
			}

			System.out.println("#" + t + " " + ans);
		}
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int val;

		public Edge(int to, int val) {
			super();
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}

	}
}