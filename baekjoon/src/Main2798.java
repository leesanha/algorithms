import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2798 {
	static int n, m, ans;
	static int[] cards;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		cards = new int[n];
		for (int i = 0; i < n; i++)
			cards[i] = Integer.parseInt(st.nextToken());

		ans = 0;
		check = new boolean[n];
		dfs(0, 0, 0);

		System.out.println(ans);
	}

	private static void dfs(int idx, int cnt, int sum) {
		if (sum > m)
			return;
		if (cnt == 3 && sum > ans) {
			ans = sum;
			return;
		}
		if(cnt == 3 || idx == n)
			return;
		check[idx] = true;
		dfs(idx + 1, cnt + 1, sum + cards[idx]);
		check[idx] = false;
		dfs(idx + 1, cnt, sum);
	}
}
