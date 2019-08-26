import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] landsR = new int[n];
			int[] landsC = new int[n];
			parent = new int[n];
			for (int i = 0; i < n; i++)
				parent[i] = i;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				landsR[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				landsC[i] = Integer.parseInt(st.nextToken());

			double e = Double.parseDouble(br.readLine());

			ArrayList<Edge> list = new ArrayList();

			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (i == j)
						continue;

					list.add(new Edge(i, j, (long) Math.round((landsR[i] - landsR[j]) * (landsR[i] - landsR[j])
							+ (landsC[i] - landsC[j]) * (landsC[i] - landsC[j]) * e)));
				}
			}

			Collections.sort(list);
			ans = 0;
			for (Edge eg : list) {
				if (find(eg.a) != find(eg.b)) {
					union(eg.a, eg.b);
					ans += eg.val;
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if (x != y)
			parent[x] = y;
	}

	private static int find(int node) {
		if (parent[node] == node)
			return node;
		int idx = find(parent[node]);
		parent[node] = idx;
		return idx;
	}

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		long val;

		public Edge(int a, int b, long val) {
			super();
			this.a = a;
			this.b = b;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return new Long(this.val).compareTo(new Long(o.val));
		}

	}
}