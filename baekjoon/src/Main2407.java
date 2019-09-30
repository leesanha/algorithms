import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main2407 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		BigInteger[][] dp = new BigInteger[101][101];
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j || j == 0)
					dp[i][j] = BigInteger.valueOf(1);
				else
					dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
			}
		}
		System.out.println(dp[n][m]);
	}
}