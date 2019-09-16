import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution7465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			parent = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}

			int ans = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				if (find(from) != find(to)) {
					union(from, to);
				}
			}
			for (int i = 1; i <= n; i++) {
				if (parent[i] == i)
					ans++;
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void union(int from, int to) {
		int x = find(from);
		int y = find(to);

		parent[y] = x;
	}

	private static int find(int node) {
		if (parent[node] == node)
			return node;
		int res = find(parent[node]);
		parent[node] = res;
		return res;
	}

}