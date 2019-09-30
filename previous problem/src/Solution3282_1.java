import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3282_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int w, n, ans;
	static int[][] knapsack;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			knapsack = new int[n][2];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				knapsack[i][0] = Integer.parseInt(st.nextToken());
				knapsack[i][1] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MIN_VALUE;

			dfs(0, 0, 0);
			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void dfs(int idx, int sum, int weight) {
		if (weight > w)
			return;
		if (sum > ans) {
			ans = sum;
		}
		if (idx >= n)
			return;
		dfs(idx + 1, sum + knapsack[idx][1], weight + knapsack[idx][0]);
		dfs(idx + 1, sum, weight);
	}
}