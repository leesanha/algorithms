import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14501 {
	static int n, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());

		int[] tn = new int[n + 1];
		int[] pn = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			tn[i] = Integer.parseInt(st.nextToken());
			pn[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 0;
		dfs(tn, pn, 0, 1);
		System.out.println(ans);
	}

	static void dfs(int[] tn, int[] pn, int sum, int day) {
		if(day > n + 1)
			return;
		if(day == n + 1) {
			if(ans < sum)
				ans = sum;
			return;
		}
		
		dfs(tn, pn, sum + pn[day], day + tn[day]);
		dfs(tn, pn, sum, day + 1);
	}

}
