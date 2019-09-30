import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1010 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			long[][] dp = new long[30][30];

			// m개 중 n개 선택
			for (int i = 0; i <= m; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0 || i == j)
						dp[i][j] = 1;
					else
						dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
			System.out.println(dp[m][n]);
		}

	}
}