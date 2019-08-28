import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test {
	static int n;
	static int ans;
	static ArrayList<String> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] cards = new int[n];
			for (int i = 0; i < n; i++)
				cards[i] = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			ans = -1;
			dfs(cards, 0);
			System.out.println(ans);
		}
	}

	private static void dfs(int[] cards, int depth) {	
		if (depth > 5)
			return;
		if (check(cards)) {
			if (ans == -1 || ans > depth) {
				ans = depth;
			}
			return;
		}

		int[] lcards = new int[n / 2];
		int[] rcards = new int[n / 2];
		int lidx = 0, ridx = 0;
		for (int i = 0; i < n; i++) {
			if (i < n / 2)
				lcards[lidx++] = cards[i];
			else
				rcards[ridx++] = cards[i];
		}

		for (int x = 0; x < n; x++) {
			int[] nextCards = new int[n];
			lidx = 0;
			ridx = 0;
			int idx = 0;
			if (x < n / 2) {
				for (int i = 0; i < n / 2 - x; i++) {
					nextCards[idx++] = lcards[lidx++];
				}
				for (int i = 0; i < x; i++) {
					nextCards[idx++] = rcards[ridx++];
					nextCards[idx++] = lcards[lidx++];
				}
				for (int i = ridx; i < n / 2; i++) {
					nextCards[idx++] = rcards[i];
				}
			} else {
				for (int i = 0; i < x - n / 2; i++) {
					nextCards[idx++] = rcards[ridx++];
				}
				for (int i = 0; i < n - x; i++) {
					nextCards[idx++] = rcards[ridx++];
					nextCards[idx++] = lcards[lidx++];
				}
				for (int i = lidx; i <n / 2; i++) {
					nextCards[idx++] = lcards[lidx++];
				}
			}
			dfs(nextCards, depth + 1);
		}
	}

	private static boolean check(int[] cards) {
		boolean flag1 = true, flag2 = true;
		for (int i = 0; i < n - 1; i++) {
			if (cards[i] < cards[i + 1]) {
				flag1 = false;
				break;
			}
		}
		for (int i = 0; i < n - 1; i++) {
			if (cards[i] > cards[i + 1]) {
				flag2 = false;
				break;
			}
		}
		return flag1 || flag2;
	}
}
