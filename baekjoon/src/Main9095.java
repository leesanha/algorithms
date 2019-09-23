import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main9095 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			int n = Integer.parseInt(br.readLine());

			int[] dp = new int[12];

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int i = 4; i < 12; i++)
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			System.out.println(dp[n]);
		}
	}

}