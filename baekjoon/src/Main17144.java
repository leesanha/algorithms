import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main17144 {
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int cleaner1 = 0, cleaner2 = 0;
		int[][] map = new int[r][c];
		ArrayList<int[]> mess = new ArrayList<>();
		boolean flag = true;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != -1 && map[i][j] != 0) {
					int[] temp = { i, j };
					mess.add(temp);
				}
				if (map[i][j] == -1 && flag) {
					cleaner1 = i;
					cleaner2 = i + 1;
					flag = false;
				}
			}
		}

		while (t-- > 0) {
			int[][] after = new int[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					after[i][j] = map[i][j];
				}
			}
//			System.out.println("확산 전");
//			for (int i = 0; i < r; i++) {
//				for (int j = 0; j < c; j++) {
//					System.out.format("%3d", map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println("미세먼지 위치");
//			System.out.println("사이즈 " + mess.size());
//			for(int[] ar : mess)
//				System.out.println(ar[0] + " " + ar[1]);
			for (int i = 0; i < mess.size(); i++) {
				int row = mess.get(i)[0];
				int col = mess.get(i)[1];

				for (int j = 0; j < 4; j++) {
					int nr = row + dr[j];
					int nc = col + dc[j];

					if (nr < 0 || nr >= r || nc < 0 || nc >= c)
						continue;
					if (map[nr][nc] == -1)
						continue;
					after[row][col] -= (map[row][col] / 5);
					after[nr][nc] += (map[row][col] / 5);
				}
			}
//			System.out.println("확산 후");
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					map[i][j] = after[i][j];
//					System.out.format("%3d", map[i][j]);
				}
//				System.out.println();
			}
//			System.out.println();

			// 청정기 위 왼쪽
			for (int i = cleaner1 - 1; i > 0; i--)
				map[i][0] = map[i - 1][0];
			// 청정기 아래 왼쪽
			for (int i = cleaner2 + 1; i < r - 1; i++)
				map[i][0] = map[i + 1][0];
			// 제일 위줄
			for (int i = 0; i < c - 1; i++)
				map[0][i] = map[0][i + 1];
			// 제일 아래 줄
			for (int i = 0; i < c - 1; i++)
				map[r - 1][i] = map[r - 1][i + 1];
			// 청정기 위 오른쪽
			for (int i = 0; i < cleaner1; i++)
				map[i][c - 1] = map[i + 1][c - 1];
			// 청정기 아래 오른쪽
			for (int i = r - 1; i > cleaner2; i--)
				map[i][c - 1] = map[i - 1][c - 1];
			// 가운데 두 줄
			for (int i = c - 1; i > 1; i--) {
				map[cleaner1][i] = map[cleaner1][i - 1];
				map[cleaner2][i] = map[cleaner2][i - 1];
			}
			map[cleaner1][1] = 0;
			map[cleaner2][1] = 0;

//			System.out.println("이동 후");
//			for (int i = 0; i < r; i++) {
//				for (int j = 0; j < c; j++) {
//					System.out.format("%3d", map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			mess.clear();
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] != 0 && map[i][j] != -1) {
						int[] temp = { i, j };
						mess.add(temp);
					}
				}
			}
//			System.out.println("미세먼지 위치");
//			System.out.println("사이즈 " + mess.size());
//			for(int[] ar : mess)
//				System.out.println(ar[0] + " " + ar[1]);
		}
		int ans = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != -1)
					ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

}