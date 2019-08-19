import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * test case
 * a, c
 * ab, c
 * ab, b
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans;
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			ans = 1;
			char[] arr = br.readLine().toCharArray();

			boolean[] attend1 = new boolean[4];
			boolean[] attend2 = new boolean[4];
			attend1[0] = true;// 시작할 때는 A가 키를 가지고 있음.

			long res;
			for (int i = 0; i < arr.length - 1; i++) {
				attend1[arr[i] - 'A'] = true;// 책임자 체크
				attend2[arr[i + 1] - 'A'] = true;
				res = get(attend1);
				res *= get(attend2) % 1000000007;
				attend1[arr[i + 1] - 'A'] = true;

				long temp = 0;
				if (i == 0) { // 시작할 때 A가 key를 가지고 있으므로 따로 처리한다.
					if (arr[i + 1] == 'A') {
						ans *= res % 1000000007;
						continue;
					}
				}
				temp = get1(attend1);
				res -= temp;
				
				ans *= res % 1000000007;

				attend1 = new boolean[4];
				attend2 = new boolean[4];
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

	static long get(boolean[] attend) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			if (!attend[i])
				cnt++;
		}
		return (long) Math.pow(2, cnt);
	}
	static long get1(boolean[] attend) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			if (!attend[i])
				cnt++;
		}
		return (long) Math.pow(3, cnt);
	}
}
