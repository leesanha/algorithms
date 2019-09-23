import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main9655_2 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		boolean[] dp = new boolean[1001];

		int n = Integer.parseInt(br.readLine());

		// true면 SK, false면 CY
		dp[1] = true;
		dp[3] = true;
		dp[2] = false;
		dp[4] = false;
		dp[6] = false;

		for (int i = 5; i <= n; i++) {
			if (dp[i - 1] || dp[i - 3])
				dp[i] = false;
			if (!dp[i - 1] || !dp[i - 3])
				dp[i] = true;
		}
		System.out.println(dp[n]?"SK":"CY");
	}

}