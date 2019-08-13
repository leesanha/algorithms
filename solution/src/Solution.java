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
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		int tc = Integer.parseInt(br.readLine().trim());

//		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			ans = 0;
			n = Integer.parseInt(st.nextToken());
			flag = true;

			int[][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken().trim());
				}
			}
			for (int i = 0; i < 4; i++) {
				solve(arr, i, 0);
			}
			System.out.println(ans);
//		}
	}

	private static void solve(int[][] arr, int dir, int depth) {
		if (depth == 5)
			return;

		arr = move(arr, dir);
		// arr이 변한게 없으면 더이상 재귀에 들어가지 않고 return 한다.
		if (flag) {
			flag = !flag;
			return;
		}

		for (int i = 0; i < 4; i++) {
			solve(arr, i, depth + 1);
		}
	}

	private static int[][] move(int[][] arr, int dir) {
		if (dir == 0) {
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 0)
						continue;
					boolean already = false;
					for (int k = i; k > 0; k--) {
						if (!already && (arr[k - 1][j] == arr[k][j])) {
							already = true;
							arr[k - 1][j] += arr[k - 1][j];
							arr[k][j] = 0;
							if (ans < arr[k - 1][j]) {
								ans = arr[k - 1][j];
							}
							flag = false;
						} else if (arr[k - 1][j] == 0) {
							arr[k - 1][j] = arr[k][j];
							arr[k][j] = 0;
							if (ans < arr[k - 1][j]) {
								ans = arr[k - 1][j];
							}
							flag = false;
						} else{
							break;
						}
					}
				}
			}
		} else if (dir == 3) {
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[j][i] == 0)
						continue;
					boolean already = false;
					for (int k = i; k > 0; k--) {
						if (!already && (arr[j][k - 1] == arr[j][k])) {
							already = true;
							arr[j][k - 1] += arr[j][k - 1];
							arr[j][k] = 0;
							if (ans < arr[j][k - 1]) {
								ans = arr[j][k - 1];
							}
							flag = false;
						} else if (arr[j][k - 1] == 0) {
							arr[j][k - 1] = arr[j][k];
							arr[j][k] = 0;
							if (ans < arr[j][k - 1]) {
								ans = arr[j][k - 1];
							}
							flag = false;
						} else {
							break;
						}
					}
				}
			}
		} else if (dir == 1) {
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if (arr[j][i] == 0)
						continue;
					boolean already = false;
					for (int k = i; k < n - 1; k++) {
						if (!already && (arr[j][k + 1] == arr[j][k])) {
							already = true;
							arr[j][k + 1] += arr[j][k + 1];
							arr[j][k] = 0;
							if (ans < arr[j][k + 1]) {
								ans = arr[j][k + 1];
							}
							flag = false;
						} else if (arr[j][k + 1] == 0) {
							arr[j][k + 1] = arr[j][k];
							arr[j][k] = 0;
							if (ans < arr[j][k + 1]) {
								ans = arr[j][k + 1];
							}
							flag = false;
						} else {
							break;
						}
					}
				}
			}
		} else {
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if (arr[j][i] == 0)
						continue;
					boolean already = false;
					for (int k = i; k < n - 1; k++) {
						if (!already && (arr[k + 1][j] == arr[k][j])) {
							already = true;
							arr[k + 1][j] += arr[k + 1][j];
							arr[k][j] = 0;
							if (ans < arr[k + 1][j]) {
								ans = arr[k + 1][j];
							}
							flag = false;
						} else if (arr[k + 1][j] == 0) {
							arr[k + 1][j] = arr[k][j];
							arr[k][j] = 0;
							if (ans < arr[k + 1][j]) {
								ans = arr[k + 1][j];
							}
							flag = false;
						} else {
							break;
						}
					}
				}
			}
		}
		return arr;
	}

}
