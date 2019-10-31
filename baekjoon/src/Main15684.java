import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15684 {
	static int ans, n, m, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		int[][] ladder = new int[h][n - 1];//가로는 1개가 적게

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a - 1][b - 1] = 1;
		}
		ans = Integer.MAX_VALUE;

		for (int i = 0; i <= 3; i++) {//0개부터 3개까지 늘리면서 조합 만든다.
			dfs(ladder, i, 0, 0, 0);
		}

		System.out.println((ans != Integer.MAX_VALUE) ? ans : -1);
	}

	private static void dfs(int[][] ladder, int cnt, int depth, int row, int col) {
		if (cnt == depth) {//모든 사다리 만들면 체크해본다.
			if (check(ladder)) {
				if (ans > depth)
					ans = depth;
			}
			return;
		}
		for (int i = row; i < h; i++) {//내려가면서
			while (true) {//옆으로 가면서
				if (ladder[i][col] == 0) {//사다리를 넣을 수 있으면
					if (col - 1 >= 0 && ladder[i][col - 1] == 1) {//왼쪽에 사다리가 있으면
						col++;
						if (col == n - 1) {//오른쪽 끝에 도달하면 다음 줄로 내려가도록 break
							col = 0;
							break;
						}
						continue;
					} else if (col + 1 < n - 1 && ladder[i][col + 1] == 1) {//오른쪽에 사다리가 있으면
						col++;
						if (col == n - 1) {
							col = 0;
							break;
						}
						continue;
					} else {//왼쪽 오른쪽 모두 사다리가 없으면 사다리를 넣고 dfs 다시 들어간다.
						ladder[i][col] = 1;
						dfs(ladder, cnt, depth + 1, i, col);
						ladder[i][col] = 0;
					}
				}
				col++;
				if (col == n - 1) {
					col = 0;
					break;
				}
			}

		}
	}

	private static boolean check(int[][] ladder) {
		for (int i = 0; i < n; i++) {//한 줄 씩 체크한다.
			int cur = i;
			for (int j = 0; j < h; j++) {//내려가면서 체크
				if (cur - 1 < 0) {
					if (ladder[j][cur] == 1)//오른쪽 사다리가 있으면 오른쪽 이동
						cur++;
					continue;
				}

				if (cur + 1 >= n) {
					if (ladder[j][cur - 1] == 1)//왼쪽 사다리가 있으면 왼쪽 이동
						cur--;
					continue;
				}
				if (ladder[j][cur] == 1) {
					cur++;
					continue;
				}
				if (ladder[j][cur - 1] == 1)
					cur--;

			}
			if (cur != i)
				return false;
		}
		return true;
	}

}