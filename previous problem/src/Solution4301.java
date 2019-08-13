import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 복잡하게 생각하지 말고 간단하게 풀 수 있는 문제였다.
 * DP로 가능할 줄 알았는데 식을 세우지 못했다.
 * 규칙이 있다고 무조건 DP로 풀지는 않아도 된다.
 * 쉽게 풀 수 있는 방법을 다양하게 찾아보자 
 */

public class Solution4301 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static int[][] arr;
	static int n, m;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			arr = new int[m][n];
			ans = n * m;

			int[] dr = { 0, 0, -2, 2 };
			int[] dc = { 2, -2, 0, 0 };

			// 길이가 2인 것을 찾았고, 특정 규칙이 반복된다는 것을 알았는데
			// 어떻게 이를 해결해야 할 지 몰랐다.
			// 1000 X 1000 이라서 탐색하는 것은 시간 초과가 날 것이라 생각했는데,
			// 걸러지는 조건이 많아서 충분했다.
			
			// 수식을 만들어서도 풀 수 있지만 모르겠음..
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];

							if (nr < 0 || nr >= m || nc < 0 || nc >= n || arr[nr][nc] == 1)
								continue;
							arr[nr][nc] = 1;
							ans--;
						}
					}
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

}
