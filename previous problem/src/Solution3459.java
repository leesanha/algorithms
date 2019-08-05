import java.util.Scanner;

class Solution3459 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			long num = sc.nextLong();

			boolean flag = true;
			long cnt = 1;
			long mul = 1;
			while (cnt < num) {
				if (flag) {
					mul *= 4;
				}
				cnt += mul;
				flag = !flag;
			}
			System.out.format("#%d %s\n", t, (flag) ? "Bob" : "Alice");
		}
	}
}