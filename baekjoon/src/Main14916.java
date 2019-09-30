import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main14916 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		long[] dp = new long[100001];
		dp[1] = -1;
		dp[2] = 1;
		dp[3] = -1;
		dp[4] = 2;
		dp[5] = 1;

		for (int i = 6; i <= n; i++) {
			if (dp[i - 2] == -1 && dp[i - 5] == -1)
				dp[i] = -1;
			else if (dp[i - 2] == -1)
				dp[i] = dp[i - 5] + 1;
			else if (dp[i - 5] == -1)
				dp[i] = dp[i - 2] + 1;
			else
				dp[i] = (dp[i - 2] < dp[i - 5]) ? dp[i - 2] + 1 : dp[i - 5] + 1;
		}
		System.out.println(dp[n]);
	}

}