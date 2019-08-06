import java.util.Arrays;
import java.util.Scanner;

public class Solution1228 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int tc = sc.nextInt();

		for (int i = 1; i <= 10; i++) {
			int size = sc.nextInt();
			int[] arr = new int[10];
			for (int j = 0; j < size; j++) {
				if (j >= 10) {
					sc.nextInt();
					continue;
				}
				arr[j] = sc.nextInt();
			}
			size = sc.nextInt();
			for (int j = 0; j < size; j++) {
				char option = sc.next().charAt(0);
				switch (option) {
				case 'I':
					int idx = sc.nextInt();
					int num = sc.nextInt();

					if (idx + num < 10) {
						for (int k = 9; k >= idx + num; k--) {
							arr[k] = arr[k - num];
						}
					}

					for (int k = idx; k < idx + num; k++) {
						if (k >= 10) {
							sc.nextInt();
							continue;
						}
						arr[k] = sc.nextInt();
					}
					break;
				}
			}
			System.out.printf("#%d ", i);
			for (int j = 0; j < 10; j++) {
				System.out.print(arr[j] + " ");
			}
			System.out.println();
		}
	}

}
