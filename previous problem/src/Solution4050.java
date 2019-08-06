import java.util.Arrays;
import java.util.Scanner;

class Solution4050 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			int size = sc.nextInt();
			int[] arr = new int[size + 1];

			for (int i = 1; i <= size; i++) {
				arr[i] = sc.nextInt();
			}

			Arrays.sort(arr);
			reverse(arr);

			int sum = 0;
			for (int i = 1; i <= size; i++) {
				if (i % 3 == 0)
					continue;
				sum += arr[i];
			}

			System.out.format("#%d %d\n", t, sum);
		}
	}

	static void reverse(int[] arr) {
		for (int i = 1; i <= arr.length / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - i];
			arr[arr.length - i] = temp;
		}

	}
}