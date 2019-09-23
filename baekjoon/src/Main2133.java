import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2133 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[31];

		dp[0] = 1;
		dp[2] = 3;

		for (int i = 4; i <= n; i += 2) {
			dp[i] = dp[i - 2] * 3;
			for (int j = 4; i - j >= 0; j += 2)
				dp[i] += dp[i - j] * 2;
		}
		System.out.println(dp[n]);
	}

}