import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static int[] arr;
	static int n, s;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());

			ans = 0;
			arr = new int[n];
			check = new boolean[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0);
			System.out.format("#%d %d\n", t, ans - s);
		}
	}

	private static void dfs(int idx, int sum) {
		if (sum >= s) {
			if (ans == 0 || sum < ans) {
				ans = sum;
			}
			return;
		}

		if (idx == n)
			return;

		check[idx] = true;
		dfs(idx + 1, sum + arr[idx]);
		check[idx] = false;
		dfs(idx + 1, sum);
	}

}
