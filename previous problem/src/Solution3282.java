import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3282 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			int[][] knapsack = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				knapsack[i][0] = Integer.parseInt(st.nextToken());
				knapsack[i][1] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[n + 1][w + 1];

			for (int i = 1; i <= n; i++) {
				for (int j = 0; j <= w; j++) {
					if (j < knapsack[i - 1][0])
						dp[i][j] = dp[i - 1][j];
					else {
						dp[i][j] = (dp[i - 1][j - knapsack[i - 1][0]] + knapsack[i - 1][1] < dp[i - 1][j])
								? dp[i - 1][j]
								: dp[i - 1][j - knapsack[i - 1][0]] + knapsack[i - 1][1];
					}
				}
			}
			System.out.format("#%d %d\n", t, dp[n][w]);
		}
	}
}