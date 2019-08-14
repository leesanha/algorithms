import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static int n;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
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
	}

	private static void solve(int[][] arr, int dir, int depth) {
		if (depth == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(ans < arr[i][j])
						ans = arr[i][j];
				}
			}
			return;
		}

		int[][] cp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cp[i][j] = arr[i][j];
			}
		}
		
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		move(arr, dir);


		for (int i = 0; i < 4; i++) {
			solve(arr, i, depth + 1);
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					arr[j][k] = cp[j][k];
				}
			}
		}
	}

	private static void move(int[][] arr, int dir) {
		boolean [] check = new boolean[n];
		if (dir == 0) {// 위로
			for (int j = 0; j < n; j++) {// col
				check = new boolean[n];
				for (int i = 1; i < n; i++) {// row
					if (arr[i][j] == 0)
						continue;
					int cur = i;
					for (int k = cur - 1; k >= 0; k--) {
						if(arr[k][j] == 0) {
							arr[k][j] = arr[cur][j];
							arr[cur][j] = 0;
							cur--;
							continue;
						}else {
							if((!check[k] && !check[cur]) && arr[k][j] == arr[cur][j]) {
								arr[k][j] *= 2;
								arr[cur][j] = 0;
								cur--;
								check[k] = true;
								continue;
							}else {
								break;
							}
						}
						/*
						if (!check[k - 1] && arr[k - 1][j] == arr[i][j]) {
							arr[k - 1][j] *= 2;
							arr[i][j] = 0;
							check[k - 1] = true;
							break;
						} else if (arr[k - 1][j] != 0) {
							arr[k][j] = arr[i][j];
							arr[i][j] = 0;
							break;
						} else if (k == 1 && arr[k - 1][j] == 0) {
							arr[k - 1][j] = arr[i][j];
							arr[i][j] = 0;
						}
						*/
					}
				}
			}
		} else if (dir == 3) {// 왼쪽으로
			for (int i = 0; i < n; i++) {// row
				check = new boolean[n];
				for (int j = 1; j < n; j++) {// col
					if (arr[i][j] == 0)
						continue;
					
					int cur = j;
					for (int k = cur - 1; k >= 0; k--) {
						if(arr[i][k] == 0) {
							arr[i][k] = arr[i][cur];
							arr[i][cur] = 0;
							cur--;
							continue;
						}else {
							if((!check[k] && !check[cur]) && arr[i][k] == arr[i][cur]) {
								arr[i][k] *= 2;
								arr[i][cur] = 0;
								cur--;
								check[k] = true;
								continue;
							}else {
								break;
							}
						}
					}
					/*
					for (int k = j; k > 0; k--) {
						if (!check[k - 1] &&  arr[i][k - 1] == arr[i][j]) {
							arr[i][k - 1] *= 2;
							arr[i][j] = 0;
							check[k - 1] = true;
							break;
						} else if (arr[j][k - 1] != 0) {
							arr[i][k] = arr[i][j];
							arr[i][j] = 0;
							break;
						} else if (k == 1 && arr[i][k - 1] == 0) {
							arr[i][k - 1] = arr[i][j];
							arr[i][j] = 0;
						}
					}
					*/
				}
			}
		} else if (dir == 1) {// 오른쪽으로
			for (int i = 0; i < n; i++) {// row
				check = new boolean[n];
				for (int j = n - 2; j >= 0; j--) {// col
					if (arr[i][j] == 0)
						continue;
					int cur = j;
					for (int k = cur + 1; k < n; k++) {
						if(arr[i][k] == 0) {
							arr[i][k] = arr[i][cur];
							arr[i][cur] = 0;
							cur++;
							continue;
						}else {
							if((!check[k] && !check[cur]) && arr[i][k] == arr[i][cur]) {
								arr[i][k] *= 2;
								arr[i][cur] = 0;
								cur++;
								check[k] = true;
								continue;
							}else {
								break;
							}
						}
					}
					/*
					for (int k = j; k < n - 1; k++) {
						if (!check[k + 1] &&  arr[i][k + 1] == arr[i][j]) {
							arr[i][k + 1] *= 2;
							arr[i][j] = 0;
							check[k + 1] = true;
							break;
						} else if (arr[i][k + 1] != 0) {
							arr[i][k] = arr[i][j];
							arr[i][i] = 0;
							break;
						} else if (k == n - 2 && arr[i][k + 1] == 0) {
							arr[i][k + 1] = arr[i][j];
							arr[i][j] = 0;
						}
					}
					*/
				}
			}
		} else {// 밑으로
			for (int j = 0; j < n - 1; j++) {// row
				check = new boolean[n];
				for (int i = n - 2; i >= 0; i--) {// col
					if (arr[i][j] == 0)
						continue;
					int cur = i;
					for (int k = cur + 1; k < n; k++) {
						if(arr[k][j] == 0) {
							arr[k][j] = arr[cur][j];
							arr[cur][j] = 0;
							cur++;
							continue;
						}else {
							if((!check[k] && !check[cur]) && arr[k][j] == arr[cur][j]) {
								arr[k][j] *= 2;
								arr[cur][j] = 0;
								cur++;
								check[k] = true;
								continue;
							}else {
								break;
							}
						}
					}
					/*
					for (int k = i; k < n - 1; k++) {
						if (!check[k + 1] && arr[k + 1][j] == arr[i][j]) {
							arr[k + 1][j] *= 2;
							arr[i][j] = 0;
							check[k + 1] = true;
							break;
						} else if (arr[k + 1][j] != 0) {
							arr[k][j] = arr[i][j];
							arr[i][j] = 0;
							break;
						} else if (k == n - 2 && arr[k + 1][j] == 0) {
							arr[k + 1][j] = arr[i][j];
							arr[i][j] = 0;
						}
					}
					*/
				}
			}
		
		}

	}

}
