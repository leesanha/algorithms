import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static long ans, n;
	static ArrayList<Long> operation;
	static ArrayList<Character> operator;
	static char[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();

		operation = new ArrayList<Long>();
		operator = new ArrayList<Character>();
		ans = Long.MIN_VALUE;
		dfs(0);
	}

	private static void dfs(int idx) {
		if (idx + 2>= n - 1) {
			operation.add(Long.parseLong(input[idx] + ""));
			long sum = operation.get(0);
			for (int i = 0; i < operator.size(); i++) {
				switch (operator.get(i)) {
				case '+':
					sum += operation.get(i + 1);
					break;
				case '-':
					sum -= operation.get(i + 1);
					break;
				case '*':
					sum *= operation.get(i + 1);
				}
			}
			operation.remove(operation.size() - 1);
			if (sum > ans)
				ans = sum;

			return;
		}
		// 앞에꺼 두 개 계산
		long ret = cal(input[idx + 1], Integer.parseInt(input[idx] + ""), Integer.parseInt(input[idx + 2] + ""));
		operation.add(ret);
		operator.add(input[idx + 3]);
		dfs(idx + 4);
		// 앞에꺼 두고 뒤에꺼 2개 계산
		operation.remove(operation.size() - 1);
		operator.remove(operator.size() - 1);
		ret = cal(input[idx + 1], Integer.parseInt(input[idx] + ""),
				cal(input[idx + 3], Integer.parseInt(input[idx + 2] + ""), Integer.parseInt(input[idx + 4] + "")));
		if (idx + 5 >= n - 1) {
			long sum = operation.get(0);
			for (int i = 0; i < operator.size(); i++) {
				switch (operator.get(i)) {
				case '+':
					sum += ret;
					break;
				case '-':
					sum -= ret;
					break;
				case '*':
					sum *= ret;
				}
			}
			if (sum > ans)
				ans = sum;

			return;
		}
		operator.add(input[idx + 5]);
		dfs(idx + 6);
	}

	static long cal(char oper, long a, long b) {
		long ret = 0;
		switch (oper) {
		case '+':
			ret = a + b;
			break;
		case '-':
			ret = a - b;
			break;
		case '*':
			ret = a * b;
			break;
		}
		return ret;
	}
}