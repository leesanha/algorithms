import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution5643 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] high_cnt;
	static int[] low_cnt;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st;

			int n = Integer.parseInt(br.readLine().trim());
			int m = Integer.parseInt(br.readLine().trim());

			ArrayList<Integer>[] high = new ArrayList[n + 1];
			ArrayList<Integer>[] low = new ArrayList[n + 1];

			for (int i = 1; i <= n; i++) {
				high[i] = new ArrayList<>();
				low[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine().trim());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				high[from].add(to);
				low[to].add(from);
			}

			high_cnt = new int[n + 1];
			low_cnt = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				visit = new boolean[n + 1];
				add_low(low, i, i);
				visit = new boolean[n + 1];
				add_high(high, i, i);
			}

			int ans = 0;
			for (int i = 1; i <= n; i++) {
				if (high_cnt[i] + low_cnt[i] == n - 1)
					ans++;
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void add_high(ArrayList<Integer>[] high, int node, int comp) {
		visit[node] = true;
		for (int j = 0; j < high[node].size(); j++) {
			int next = high[node].get(j);

			if (!visit[next]) {
				high_cnt[comp]++;
				add_high(high, next, comp);
			}
		}
	}

	private static void add_low(ArrayList<Integer>[] low, int node, int comp) {
		visit[node] = true;
		for (int j = 0; j < low[node].size(); j++) {
			int next = low[node].get(j);

			if (!visit[next]) {
				low_cnt[comp]++;
				add_low(low, next, comp);
			}
		}
	}
}