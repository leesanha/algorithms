import java.util.Scanner;

public class Solution3752 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tc = sc.nextInt();
		int size, ans, max, num;
		boolean[] check;
		for (int t = 1; t <= tc; t++) {
			size = sc.nextInt();
			check = new boolean[10001];
			check[0] = true;
			max = 0;
			ans = 0;

			for (int i = 0; i < size; i++) {
				num = sc.nextInt();
				max += num;

				for (int j = max; j >= 0; j--) {
					if (check[j] == true) {
						check[j + num] = true;
					}
				}
			}
			for (int i = 0; i < 10001; i++) {
				if (check[i])
					ans++;
			}
			/*
			 * System.out.println((long)1<<100);
						1 << size 했을 때 size에 100이 들어가면 overflow남.
						항상 경계값을 넣어서 테스트 해보자.
						
						for (int i = 0; i < (1 << size); i++) {
							sum = 0;
							for (int j = 0; j < size; j++) {
								if ((i & (1 << j)) != 0) {
									sum += arr[j];
								}
							}
							if (!check[sum]) {
								check[sum] = true;
								ans++;
							}
						}
			 */
			System.out.format("#%d %d\n", t, ans);
		}
		sc.close();
	}

}
