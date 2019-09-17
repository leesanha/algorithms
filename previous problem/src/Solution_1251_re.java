import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_1251_re {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st;

			int n = Integer.parseInt(br.readLine());

			int[] xs = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				xs[i] = Integer.parseInt(st.nextToken());

			int[] ys = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				ys[i] = Integer.parseInt(st.nextToken());

			double e = Double.parseDouble(br.readLine());

			ArrayList<Node> list = new ArrayList<Node>();
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					list.add(new Node(i, j,
							(Math.pow(Math.abs(xs[i] - xs[j]), 2) + Math.pow(Math.abs(ys[i] - ys[j]), 2)) * e));
				}
			}

			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					if (n1.dis > n2.dis)
						return 1;
					else if (n1.dis < n2.dis)
						return -1;
					else
						return 0;
				}
			});

			parent = new int[n];
			for (int i = 0; i < n; i++)
				parent[i] = i;

			double ans = 0;
			int edge = 0;
			for (int i = 0; i < list.size(); i++) {
				int from = list.get(i).from;
				int to = list.get(i).to;

				if (find(from) != find(to)) {
					union(from, to);
					ans += list.get(i).dis;
					edge++;
				}
				if (edge == n - 1)
					break;
			}
			System.out.println("#" + t + " " + (long) (Math.round(ans)));
		}
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		parent[y] = x;
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		int res = find(parent[x]);
		parent[x] = res;
		return res;
	}

	static class Node {
		int from;
		int to;
		double dis;

		public Node(int from, int to, double dis) {
			super();
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
	}
}