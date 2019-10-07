import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static int[] tickets;
	static int[] months;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tickets = new int[4];
			for (int i = 0; i < 4; i++)
				tickets[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			months = new int[12];
			for (int i = 0; i < 12; i++)
				months[i] = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;

			dfs(0, 0);

			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void dfs(int idx, int price) {
		if (idx >= 12) {
			if (ans > price)
				ans = price;
			return;
		}
		dfs(idx + 1, price + months[idx] * tickets[0]);
		dfs(idx + 1, price + tickets[1]);
		dfs(idx + 3, price + tickets[2]);
		dfs(idx + 12, price + tickets[3]);
	}
}