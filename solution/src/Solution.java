import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static int n;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			ans = 0;
			n = Integer.parseInt(st.nextToken());

			int[][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken().trim());
				}
			}
			for (int i = 0; i < 4; i++) {
				solve(arr, 0, 0);
			}
		}
	}

	private static void solve(int[][] arr, int dir, int depth) {
		if (depth == 5)
			return;

		arr = move(arr, 0);

		for (int i = 0; i < 4; i++) {
			solve(arr, i, depth + 1);
		}
	}

	private static int[][] move(int[][] arr, int dir) {
		if (dir == 0 || dir == 3) {
			for (int i = 0; i < n; i++) {
				
			}
		} else {
			for (int i = n - 2; i >= 0; i--) {

			}
		}
		return null;
	}

}
