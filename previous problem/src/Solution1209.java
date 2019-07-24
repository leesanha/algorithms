import java.util.Scanner;

public class Solution1209 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 10; i++) {
			int num = sc.nextInt();
			int[][] arr = new int[100][100];

			int max = -9999;
			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int k = 0; k < 100; k++) {
					arr[j][k] = sc.nextInt();
					sum += arr[j][k];
				}
				if (max < sum)
					max = sum;
			}

			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int k = 0; k < 100; k++) {
					sum += arr[k][j];
				}
				if (max < sum)
					max = sum;
			}
			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int k = 0; k < 100; k++) {
					if(j==k)sum+=arr[j][k];
				}
				if (max < sum)
					max = sum;
			}
			for (int j = 0; j < 100; j++) {
				int sum = 0;
				for (int k = 0; k < 100; k++) {
					if(j+k == 99)sum+=arr[j][k];
				}
				if (max < sum)
					max = sum;
			}
			System.out.format("#%d %d\n",i,max);
		}

	}

}
