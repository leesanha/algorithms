import java.util.Scanner;

public class Solution1206 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				int ans;
				for (int i = 1; i <= 10; i++) {
					ans = 0;
					int size = sc.nextInt();
//					System.out.println(size);
					int[] apart = new int[size];

					for (int j = 0; j < size; j++) {
						apart[j] = sc.nextInt();
					}
//					System.out.println(Arrays.toString(apart));

					for (int j = 2; j <= size - 3; j++) {
						int max = Math.max(Math.max(apart[j - 2], apart[j - 1]), Math.max(apart[j + 1], apart[j + 2]));
						if (apart[j] > max) {
							ans += apart[j] - max;
						}
					}
					System.out.format("#%d %d\n", i, ans);
				}

	}

}
