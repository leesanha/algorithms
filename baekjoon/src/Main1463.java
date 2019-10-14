import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[1000001];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;

		for (int i = 4; i <= 1000000; i++) {
			if (i % 3 == 0) {
				if (dp[i] == 0)
					dp[i] = dp[i / 3] + 1;
				else
					dp[i] = (dp[i] > dp[i / 3] + 1) ? dp[i / 3] + 1 : dp[i];
			}
			if (i % 2 == 0) {
				if (dp[i] == 0)
					dp[i] = dp[i / 2] + 1;
				else
					dp[i] = (dp[i] > dp[i / 2] + 1) ? dp[i / 2] + 1 : dp[i];
			}
			if (dp[i] == 0)
				dp[i] = dp[i - 1] + 1;
			else
				dp[i] = (dp[i] > dp[i - 1] + 1) ? dp[i - 1] + 1 : dp[i];
		}
		System.out.println(dp[n]);
	}

}
