import java.util.Scanner;

public class Solution1213 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 10; i++) {
			int ans = 0;
			int number = sc.nextInt();
			sc.nextLine();
			String str = sc.nextLine();
			String comp = sc.nextLine();
			/*
			 * System.out.println(str); System.out.println(comp);
			 */

			for (int j = 0; j < comp.length() - str.length() + 1; j++) {
				if (comp.substring(j, j + str.length()).equals(str))
					ans++;
			}
			System.out.format("#%d %d\n", number, ans);
		}

	}

}
