import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution1258 {
	static Scanner sc = new Scanner(System.in);
	static int size;
	static ArrayList<Node> mat;

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			size = sc.nextInt();
			int[][] arr = new int[size][size];
			boolean[][] visit = new boolean[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			mat = new ArrayList<Node>();

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (!visit[i][j] && arr[i][j] != 0) {
						solv(arr, visit, i, j);
					}
				}
			}
			Collections.sort(mat);
			System.out.format("#%d %d ", t, mat.size());
			for (int i = 0; i < mat.size(); i++) {
				System.out.print(mat.get(i).row + " " + mat.get(i).col + " ");
			}
			System.out.println();
		}
		sc.close();
	}

	static void solv(int[][] arr, boolean[][] visit, int row, int col) {
		int row_cnt = 0;
		int col_cnt = 0;
		for (int i = col; i < size; i++) {
			if (arr[row][i] == 0)
				break;
			col_cnt++;
			visit[row][i] = true;

		}
		for (int i = row; i < size; i++) {
			if (arr[i][col] == 0)
				break;
			row_cnt++;
			for (int j = col; j < col + col_cnt; j++)
				visit[i][j] = true;

		}
		mat.add(new Node(row_cnt, col_cnt, row_cnt * col_cnt));
	}

	static class Node implements Comparable<Node> {
		int row;
		int col;
		int size;

		@Override
		public int compareTo(Node o) {
			if (this.size != o.size)
				return this.size - o.size;
			return this.row - o.row;
		}

		public Node(int row, int col, int size) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
		}

	}
}
