import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans, res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int t = 1; t <= tc; t++) {
			String aa = st.nextToken();
			String bb = st.nextToken();

			int sizeA = (aa.charAt(0) == '-')? aa.length()-1 : aa.length();
			int sizeB = (bb.charAt(0) == '-')? bb.length()-1 : bb.length();

			long a = Long.parseLong(aa);
			long b = Long.parseLong(bb);
			a = (a < 0) ? -a : a;
			b = (b < 0) ? -b : b;

			res = 0;
			dfs(a, 1, 1, sizeA, 9);
			ans = a - res;
			res = 0;
			dfs(b, 1, 1, sizeB, 9);
			ans = b - res;
			
			System.out.println(ans);
		}
	}

	static void dfs(long num, int mul, int depth, int size, int sum) {
		if (depth > size)
			return;

		long temp = num / mul;

		if (temp >= 5) {
			res += sum * (temp - 1) + mul;
		}else {
			res += sum * temp;
		}

		dfs(num, mul * 10, depth + 1, size, (sum + mul) * 9);
	}

}
