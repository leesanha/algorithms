import java.util.Scanner;
import java.util.Stack;

public class Solution1808 {
	static int ans;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			arr = new int[10];
			int num;
			ans = 9999;
			for (int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
			}
			num = sc.nextInt();

			solv(num, 0);

			System.out.format("#%d %d\n", t, ans = (ans == 9999) ? -1 : ans + 1);
		}
	}

	static void solv(int num, int cnt) {
		if (check(num + "")) {
			if (ans > cnt + (num + "").length()) {
//				System.out.println("num: " + num);
				ans = cnt + (num + "").length();
			}
			return;
		}

		for (int i = 2; i * i < num; i++) {
			if (check(i + "") && num % i == 0)
				solv(num / i, cnt + 1 + (i + "").length());
		}

	}

	static boolean check(String num) {
		char[] temp = num.toCharArray();
		for (char c : temp) {
			if (arr[c - '0'] == 0)
				return false;
		}
		return true;
	}

}
