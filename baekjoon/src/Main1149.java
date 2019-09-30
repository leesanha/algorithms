import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		// 0: 빨강, 1: 초록, 2: 파랑
		int[][] input = new int[n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
			input[i][2] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[n][3];
		dp[0][0] = input[0][0];
		dp[0][1] = input[0][1];
		dp[0][2] = input[0][2];

		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][1] + input[i][0] < dp[i - 1][2] + input[i][0]) ? dp[i - 1][1] + input[i][0]
					: dp[i - 1][2] + input[i][0];
			dp[i][1] = (dp[i - 1][0] + input[i][1] < dp[i - 1][2] + input[i][1]) ? dp[i - 1][0] + input[i][1]
					: dp[i - 1][2] + input[i][1];
			dp[i][2] = (dp[i - 1][0] + input[i][2] < dp[i - 1][1] + input[i][2]) ? dp[i - 1][0] + input[i][2]
					: dp[i - 1][1] + input[i][2];
		}
		int ans = min(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2]);
		System.out.println(ans);
	}

	private static int min(int i, int j, int k) {
		int ret;
		if (i < j)
			ret = i;
		else
			ret = j;

		if (ret > k)
			ret = k;
		return ret;
	}

}