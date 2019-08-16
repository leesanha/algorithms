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

			boolean[] attend = new boolean[4];
			attend[0] = true;//시작할 때는 A가 키를 가지고 있음.

			for (int i = 0; i < arr.length - 1; i++) {
				attend[arr[i] - 'A'] = true;//책임자 체크
				int cnt = 0;
				long temp = 1;
				for (int j = 0; j < 4; j++) {
					if (!attend[j])
						cnt++;
				}
				temp = (long) Math.pow(cnt, 2);//키를 가지고 있는 사람과 책임자를 제외한 수만큼 곱해준다.

				//책임자를 빼고는 무조건 3명 이니까 곱하기 8
				temp *= 8;
				if(attend[arr[i+1] - 'A'])
					ans *= temp %1000000007;
				//책임자를 체크
				attend[arr[i + 1] - 'A'] = true;
				
				res = 0;
				//한 쪽에만 들어가야 함.
//				dfs(attend);
//				temp -= 
			}
		}
	}

}
