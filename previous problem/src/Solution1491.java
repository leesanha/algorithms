import java.util.Scanner;

public class Solution1491 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();

		for (int i = 1; i <= tc; i++) {
			long n = sc.nextInt();
			long a = sc.nextInt();
			long b = sc.nextInt();
			long ans = Long.MAX_VALUE;
			for (int c = 1; c <= n/2; c++) {
				for (int r = 1; c * r <= n; r++) {
					long res = a * Math.abs(r - c) + b * (n - r * c);
					if (ans > res) {
						ans = res;
					}
				}
			}

			System.out.format("#%d %d\n", i, ans);
		}
		sc.close();
	}

}
