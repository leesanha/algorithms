import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int tc = sc.nextInt();

		for (int i = 1; i <= 10; i++) {
			int num = sc.nextInt();
			System.out.printf("#%d ", num);

			int[] arr = new int[8];
			for (int j = 0; j < 8; j++)
				arr[j] = sc.nextInt();

			for (int j = 0;; j++) {
				int temp = arr[0] - ((j % 5) + 1);
				for (int k = 0; k < 7; k++)
					arr[k] = arr[k + 1];
				if (temp <= 0) {
					arr[7] = 0;
					break;
				} else
					arr[7] = temp;
			}
			for (int j = 0; j < 8; j++)
				System.out.print(arr[j] + " ");
			System.out.println();
		}
	}

}
