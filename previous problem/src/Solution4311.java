import java.util.Scanner;

public class Solution4311 {
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException {
		Scanner sc = new Scanner(System.in);
		int tc = Integer.parseInt(sc.next());

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(sc.next());
			int o = Integer.parseInt(sc.next());
			int m = Integer.parseInt(sc.next());

			String[] inputNum = new String[n];
			for (int i = 0; i < n; i++) {
				inputNum[i] = sc.next();
			}
			int[] operator = new int[o];
			for (int i = 0; i < o; i++) {
				operator[i] = Integer.parseInt(sc.next());
			}
			dp = new int[1000];
			for(int i=0;i<1000;i++)
				dp[i] = -1;
			// 1자리수
			for (int i = 0; i < inputNum.length; i++) {
//				System.out.println(inputNum[i]);
				dp[Integer.parseInt(inputNum[i])] = 1;
			}
			// 2자리수
			for (int i = 0; i < inputNum.length; i++) {
				for (int j = 0; j < inputNum.length; j++) {
					String sNum = inputNum[i] + inputNum[j];
					int num = Integer.parseInt(sNum);
//					System.out.println(Integer.parseInt(sNum));
					if (dp[num] == -1)
						dp[num] = 2;
				}
			}
			// 3자리수
			for (int i = 0; i < inputNum.length; i++) {
				for (int j = 0; j < inputNum.length; j++) {
					for (int k = 0; k < inputNum.length; k++) {
						String sNum = inputNum[i] + inputNum[j] + inputNum[k];
						int num = Integer.parseInt(sNum);
//						System.out.println(Integer.parseInt(sNum));
						if (dp[num] == -1)
							dp[num] = 3;
					}
				}
			}
			int target = Integer.parseInt(sc.next());

			if (dp[target] != -1) {
				System.out.format("#%d %d\n", t, dp[target]);
				continue;
			}
			int[] now = new int[10000000];
			int nowCnt = 0;
			for (int i = 0; i < dp.length; i++) {
				if (dp[i] != -1)
					now[nowCnt++] = i;
			}
			while (true) {
				int[] next = new int[10000000];
				int nextCnt = 0;
				boolean end = false;
				for (int i = 0; i < nowCnt; i++) {
					if (now[i] == target) {
						end = true;
						if(dp[target] <= m)
						System.out.format("#%d %d\n", t, dp[target] + 1);
						break;
					}
				}
				if (end)
					break;

				for (int i = 0; i < nowCnt; i++) {
					for (int j = 0; j < nowCnt; j++) {
						for (int k = 0; k < operator.length; k++) {
							int num = 0;
							if (operator[k] == 1) {// 더하기
								num = now[i] + now[j];
							} else if (operator[k] == 2) {// 빼기
								num = now[i] - now[j];
							} else if (operator[k] == 3) {// 곱하기
								num = now[i] * now[j];
							} else {// 나누기
								if (now[j] == 0)
									continue;
								num = now[i] / now[j];
							}
							if (num < 0 || num > 999)
								continue;
							if (dp[num] == -1 || dp[num] > dp[now[i]] + dp[now[j]] + 1) {
								dp[num] = dp[now[i]] + dp[now[j]] + 1;
								next[nextCnt++] = num;
							}
						}
					}
				}

				if (nextCnt == 0) {
					System.out.format("#%d %d\n", t, -1);
					break;
				}
				for (int i = 0; i < nextCnt; i++) {
					now[nowCnt++] = next[i];
				}
			}
		}
	}
}