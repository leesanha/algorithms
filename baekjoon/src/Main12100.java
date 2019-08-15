import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12100 {
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
		solve(arr, 0);

//		멍청하게 dfs를 시작했다. dfs 문 안에 들어가서 for문을 통해서 시작해도 되는 거였는데
//		굳이 dir을 인자로 넣음으로써 멍청한 시작을 했다.
//		for(int i=0;i<4;i++) {
//			solve(arr, i, 0);
//		}

		System.out.println(ans);
	}

//	문제의 함수
//	solve(int[][]arr, int dir, int depth);

	private static void solve(int[][] arr, int depth) {
		if (depth == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (ans < arr[i][j])
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

		for (int i = 0; i < 4; i++) {
			move(arr, i);
			solve(arr, depth + 1);
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					arr[j][k] = cp[j][k];
				}
			}
		}
	}

	private static void move(int[][] arr, int dir) {
		// 한 번 합쳐진 곳은 다시 합쳐지지 않도록 체크하는 배열
		boolean[] check = new boolean[n];
		// 방향 설정
		if (dir == 0) {// 위로
			for (int j = 0; j < n; j++) {// col
				check = new boolean[n];
				for (int i = 1; i < n; i++) {// row
					if (arr[i][j] == 0)
						continue;
					int cur = i;
					while (true) {//while문으로 블럭을 한 칸씩 이동시킨다.
						if (cur == 0)//끝에 다다르면 종료
							break;
						//합쳐지지 않았고, 같은 숫자라면 더한다.
						if ((!check[cur - 1] && !check[cur]) && arr[cur][j] == arr[cur - 1][j]) {
							arr[cur - 1][j] *= 2;
							arr[cur][j] = 0;
							check[cur - 1] = true;
						} else if (arr[cur - 1][j] == 0) {//0이라면 블럭을 이동시킨다.
							arr[cur - 1][j] = arr[cur][j];
							arr[cur][j] = 0;
						} else//숫자가 있어서 막혔다면 밑에 있는 block을 이동하러 넘어간다.
							break;
						cur--;
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
					while (true) {
						if (cur == 0)
							break;
						if ((!check[cur - 1] && !check[cur]) && arr[i][cur - 1] == arr[i][cur]) {
							arr[i][cur - 1] *= 2;
							arr[i][cur] = 0;
							check[cur - 1] = true;
						} else if (arr[i][cur - 1] == 0) {
							arr[i][cur - 1] = arr[i][cur];
							arr[i][cur] = 0;
						} else
							break;
						cur--;
					}

				}
			}
		} else if (dir == 1) {// 오른쪽으로
			for (int i = 0; i < n; i++) {// row
				check = new boolean[n];
				for (int j = n - 2; j >= 0; j--) {// col
					if (arr[i][j] == 0)
						continue;
					int cur = j;
					while (true) {
						if (cur == n - 1)
							break;
						if ((!check[cur + 1] && !check[cur]) && arr[i][cur + 1] == arr[i][cur]) {
							arr[i][cur + 1] *= 2;
							arr[i][cur] = 0;
							check[cur + 1] = true;
						} else if (arr[i][cur + 1] == 0) {
							arr[i][cur + 1] = arr[i][cur];
							arr[i][cur] = 0;
						} else
							break;
						cur++;
					}

				}
			}
		} else {// 밑으로
			for (int j = 0; j < n; j++) {// row
				check = new boolean[n];
				for (int i = n - 2; i >= 0; i--) {// col
					if (arr[i][j] == 0)
						continue;
					int cur = i;
					while (true) {
						if (cur == n - 1)
							break;
						if ((!check[cur + 1] && !check[cur]) && arr[cur][j] == arr[cur + 1][j]) {
							arr[cur + 1][j] *= 2;
							arr[cur][j] = 0;
							check[cur + 1] = true;
						} else if (arr[cur + 1][j] == 0) {
							arr[cur + 1][j] = arr[cur][j];
							arr[cur][j] = 0;
						} else
							break;
						cur++;
					}

				}
			}

		}

	}

}
