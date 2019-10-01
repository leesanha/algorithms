import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16637 {
	static int ans, n;
	static int[] operation;
	static char[] operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		operation = new int[n / 2 + 1];
		operator = new char[n / 2];

		int idx1 = 0;
		int idx2 = 0;
		st = new StringTokenizer(br.readLine());
		String token = st.nextToken();
		for (int i = 0; i < token.length(); i++) {
			if (token.charAt(i) == '+' || token.charAt(i) == '-' || token.charAt(i) == '*') {
				operator[idx2++] = token.charAt(i);
			} else {
				operation[idx1++] = Integer.parseInt(token.charAt(i) + "");
			}
		}

		ans = Integer.MIN_VALUE;
		dfs(0, operation[0]);
		System.out.println(ans);
	}

	private static void dfs(int idx, int res) {
		if (idx >= n / 2) {// 모든 연산 다 한 경우
			if (ans < res)
				ans = res;
			return;
		}
		// 현재 idx의 연산자 수행(괄호를 침)
		int ret = cal(res, operator[idx], operation[idx + 1]);
		dfs(idx + 1, ret);

		// 현재 idx의 연산자에 괄호를 치지 않고, 뒤에 연산자가 있을 경우 뒤에 연산자에 괄호를 침
		if (idx + 1 < n / 2) {
			ret = cal(res, operator[idx], cal(operation[idx + 1], operator[idx + 1], operation[idx + 2]));
			dfs(idx + 2, ret);
		}

	}

	private static int cal(int a, char oper, int b) {
		switch (oper) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 0;
	}

}