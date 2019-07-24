import java.util.Scanner;

public class Solution1244 {
	static Scanner sc = new Scanner(System.in);
	static int ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();
		sc.nextLine();
		for (int i = 1; i <= tc; i++) {
			ans = 0;
			String input = sc.nextLine();
			String num = input.split(" ")[0];
			int size = Integer.parseInt(input.split(" ")[1]);

			solve(num, 0, size);
			System.out.println(String.format("#%d %d", i, ans));
		}
	}

	static void solve(String num, int cnt, int size) {
		// TODO Auto-generated method stub
		if (cnt == size) {
			if (ans < Integer.parseInt(num)) {
				ans = Integer.parseInt(num);
			}
			return;
		}

		char[] nums = num.toCharArray();
		for (int i = 0; i < num.length(); i++) {
			for (int j = i + 1; j < num.length(); j++) {
				if(nums[i] <= nums[j]) {
					char temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
					solve(new String(nums), cnt + 1, size);
				}
			}
		}
	}

}
