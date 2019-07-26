import java.util.Scanner;

public class Solution1493 {
	static Scanner sc = new Scanner(System.in);
	static int[][] arr = new int[300][300];
	static int[] rows = new int[2];
	static int[] cols = new int[2];
	static int index;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int row = 0, col = 0;

			index = 0;
			findRC(p);
			findRC(q);

			for (int i = 0; i < 2; i++) {
				row += rows[i];
				col += cols[i];
			}

			findPoint(row, col, t);
		}
		sc.close();
	}

	static void findRC(int target) {
		int row, col = 1;
		int point = 1;
		for (row = 1; row <= 300; row++) {
			point += row;
			if (point > target)
				break;
		}
		point -= row;
		int i;
		for (i = 0; i <= 300; i++) {
			if (point == target)
				break;
			point++;
		}
		rows[index] = row - i;
		cols[index] = col + i;
		index++;
	}

	static void findPoint(int row, int col, int t) {
		int r, c = 1;
		int point = 1;
		for (r = 1; r < row; r++) {
			point += r;
		}

		int i;
		for (i = 1; i <= 300; i++) {
			c++;
			point = point + row + i;
			if (row == r && col == c)
				break;
		}
		
		System.out.format("#%d %d\n", t, point);
	}
}
