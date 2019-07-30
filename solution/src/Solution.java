import java.util.Scanner;

public class Solution {
	static Scanner sc = new Scanner(System.in);
	static int ans;

	public static void main(String[] args) {
		int tc = sc.nextInt();
		int num;
		int[] check;
		for (int t = 1; t <= tc; t++) {
			check = new int[10];
			ans = -1;
			for (int i = 0; i < 10; i++) {
				check[i] = sc.nextInt();
			}
			num = sc.nextInt();
			dfs(check, num, 0);
			System.out.format("#%d %d\n", t, (ans == -1) ? -1 : ans + 1);
		}
		sc.close();
	}

	static void dfs(int[] check, int num, int cnt) {
		int res = go(check, num);

		if (res != -1) {
			if (ans == -1 || ans > res + cnt)
				ans = res + cnt;
			return;
		}
		for (int i = 2; i < 10; i++) {
			if (num < i)
				break;
			if (check[i] == 1 && num % i == 0) {
				dfs(check, num / i, cnt + 2);
			}
		}

	}

	private static int go(int[] check, int num) {
		int res = 0;
		boolean flag = true;
		while (num > 0) {
			if (check[num % 10] == 0) {
				flag = false;
				break;
			}
			res++;
			num /= 10;
		}
		return flag ? res : -1;
	}

}
