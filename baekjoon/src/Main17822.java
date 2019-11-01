import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17822 {
	static int n, m, t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		int[][] circle = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			spin(circle, x, d, k);
			System.out.println("회전 후");
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < m; b++) {
					System.out.print(circle[a][b]);
				}
				System.out.println();
			}
			System.out.println();
			check(circle);
			System.out.println("인접 삭제 후");
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < m; b++) {
					System.out.print(circle[a][b]);
				}
				System.out.println();
			}
			System.out.println();
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (circle[i][j] != 0) {
					ans += circle[i][j];
				}
			}
		}
		System.out.println(ans);
	}

	private static void check(int[][] circle) {
		boolean flag = false;
		boolean[][] ch = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(circle[i][j] == 0)
					continue;
				if(j == m - 1 && circle[i][j] == circle[i][0]) {
					ch[i][j] = true;
					ch[i][0] = true;
					flag = true;
				}
				if(j == 0 && circle[i][j] == circle[i][m - 1]) {
					ch[i][j] = true;
					ch[i][m - 1] = true;
					flag = true;
				}
				// 인접
				if (j >= 1 && circle[i][j] == circle[i][j - 1]) {
					ch[i][j] = true;
					ch[i][j - 1] = true;
					flag = true;
				}
				if (j < m - 1 && circle[i][j] == circle[i][j + 1]) {
					ch[i][j] = true;
					ch[i][j + 1] = true;
					flag = true;
				}
				if (i < n - 1 && circle[i][j] == circle[i + 1][j]) {
					ch[i][j] = true;
					ch[i + 1][j] = true;
					flag = true;
				}
				if (i >= 1 && circle[i][j] == circle[i - 1][j]) {
					ch[i][j] = true;
					ch[i - 1][j] = true;
					flag = true;
				}
			}
		}
		if (!flag) {// 인접한 게 없으면
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (circle[i][j] != 0) {
						cnt++;
						sum += circle[i][j];
					}
				}
			}

			double aver = (double)sum / cnt;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (circle[i][j] != 0) {
						if ((double)circle[i][j] > aver) {
							circle[i][j]--;
						} else if((double)circle[i][j] < aver){
							circle[i][j]++;
						}
					}
				}
			}

		} else {// 인접했으면
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (ch[i][j])
						circle[i][j] = 0;
				}
			}
		}
	}

	private static void spin(int[][] circle, int x, int d, int k) {
		for (int i = 0; i < n; i++) {
			if ((i + 1) % x == 0) {// 배수인 원판만
				if (d == 0) {// 시계 방향
					int[] temp = new int[m];
					int idx = 0;
					for (int j = m - k; j < m; j++) {
						temp[idx++] = circle[i][j];
					}
					for (int j = 0; j < m - k; j++) {
						temp[idx++] = circle[i][j];
					}
					for (int j = 0; j < m; j++) {
						circle[i][j] = temp[j];
					}
				} else {// 반 시계 방향
					int[] temp = new int[m];
					int idx = 0;
					for (int j = k; j < m; j++) {
						temp[idx++] = circle[i][j];
					}
					for (int j = 0; j < k; j++) {
						temp[idx++] = circle[i][j];
					}
					for (int j = 0; j < m; j++) {
						circle[i][j] = temp[j];
					}
				}
			} else
				continue;
		}
	}

}
