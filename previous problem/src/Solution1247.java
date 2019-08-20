import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1247 {
	static int homeRow;
	static int homeCol;
	static int companyRow;
	static int companyCol;
	static int n;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			Posi[] nodes = new Posi[n];// 정보 저장
			StringTokenizer st = new StringTokenizer(br.readLine());

			homeRow = Integer.parseInt(st.nextToken());
			homeCol = Integer.parseInt(st.nextToken());

			companyRow = Integer.parseInt(st.nextToken());
			companyCol = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());

				nodes[i] = new Posi(row, col);
			}
			ans = -1;
			solve(nodes, 0);
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void solve(Posi[] nodes, int loc) {
		if (loc == n) {
			int sum = 0;
			for (int i = 0; i < n - 1; i++) {
				sum += Math.abs(nodes[i].row - nodes[i + 1].row) + Math.abs(nodes[i].col - nodes[i + 1].col);
			}
			sum += Math.abs(companyRow - nodes[0].row) + Math.abs(companyCol - nodes[0].col);
			sum += Math.abs(homeRow - nodes[n - 1].row) + Math.abs(homeCol - nodes[n - 1].col);
			if (ans == -1 || ans > sum)
				ans = sum;
			return;
		}

		for (int i = loc; i < n; i++) {
			swap(nodes, i, loc);
			solve(nodes, loc + 1);
			swap(nodes, i, loc);
		}
	}

	static void swap(Posi[] nodes, int i, int loc) {
		Posi temp = nodes[i];
		nodes[i] = nodes[loc];
		nodes[loc] = temp;
	}

	static class Posi {
		int row;
		int col;

		public Posi(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
