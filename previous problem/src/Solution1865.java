import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static double ans;
	static double[][] arr;
	static int n;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());

			arr = new double[n][n];
			check = new boolean[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = (double) Integer.parseInt(st.nextToken()) / 100;
				}
			}

			ans = (double) 0;
			dfs(0, 1.0);
			System.out.format("#%d %f\n", t, ans * 100);
		}
	}

	private static void dfs(int depth, double mul) {
		// double형이므로 곱할 수록 값이 더 작아진다. 따라서 현재 mul 값이 ans보다 작다면 더 커질 수 없으므로
		// return한다. 이를 통해서 연산 횟수를줄인다.
		if(mul <= ans)
			return;
		if (depth == n) {
			if (mul > ans) {
				ans = mul;
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if(arr[depth][i] == 0)continue;
			if (!check[i]) {
				check[i] = true;
				dfs(depth + 1, mul * arr[depth][i]);
				check[i] = false;
			}

		}
	}

}
