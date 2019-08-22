import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4530 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans, res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String aa = st.nextToken();
			String bb = st.nextToken();

			int sizeA = (aa.charAt(0) == '-') ? aa.length() - 1 : aa.length();
			int sizeB = (bb.charAt(0) == '-') ? bb.length() - 1 : bb.length();

			long a = Long.parseLong(aa);
			long b = Long.parseLong(bb);

			if(a < 0 &&  b < 0) {
				a = -a;
				b = -b;
				
				res = 0;
				dfs(a, 0, 0, sizeA, 0);
				ans = a - res;
				
				res = 0;
				dfs(b, 0, 0, sizeB, 0);
				ans -= b - res;
			}else if (a > 0 && b > 0){// 둘다 양수
				res = 0;
				dfs(b, 0, 0, sizeB, 0);
				ans = b - res;
				
				res = 0;
				dfs(a, 0, 0, sizeA, 0);
				ans -= a - res;
			}else {//a는 음수 b는 양수
				a = -a;
				ans = -1;
				res = 0;
				dfs(a, 0, 0, sizeA, 0);
				ans += a - res;
				
				res = 0;
				dfs(b, 0, 0, sizeB, 0);
				ans += b - res;
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void dfs(long num, long mul, int depth, int size, long sum) {
		if (depth > size) {
			res = sum;
			return;
		}
		long temp = num % 10;

		if (temp >= 5)
			sum += mul * (temp - 1) + (long) Math.pow(10, depth);
		else
			sum += mul * temp;

		dfs(num / 10, (mul * 9 + (long) Math.pow(10, depth)), depth + 1, size, sum);

	}

}
