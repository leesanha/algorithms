import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main17136 {
	static int ans;
	static ArrayList<int[]> list;
	static int[][] map;
	static int[][] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		map = new int[10][10];
		num = new int[10][10];
		int cnt = 0;
		list = new ArrayList<int[]>();
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					num[i][j] = cnt++;
					int[] temp = { i, j };
					list.add(temp);
				}
			}
		}

		ans = Integer.MAX_VALUE;
		cnt = list.size();
		boolean[] check = new boolean[cnt];
		int[] squares = { 5, 5, 5, 5, 5 };
		dfs(cnt, squares, check);

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void dfs(int cnt, int[] squares, boolean[] check) {
		int sum = 0;
		for (int i = 0; i < 5; i++)
			sum += squares[i];
		if (25 - sum > ans)// 이미 ans 보다 많이 썼으면 return
			return;
		if (cnt == 0) {// 모든 곳을 다 덮었으면
			if (25 - sum < ans) {// 총 사용 횟수가 ans보다 작으면
				ans = 25 - sum;
			}
			return;
		}
		if (sum == 0)// 모든 종이를 다썼으면 return
			return;

		int next = -1;
		for (int i = 0; i < list.size(); i++) {
			if (!check[i]) {// 아직 덮지 않은 곳이면
				next = i;
				break;
			}
		}

		if (next == -1)
			return;
		for (int j = 4; j >= 0; j--) {// 한 변의 길이가 얼마일 때까지 덮을 수 있는가 체크
			if (squares[j] == 0)
				continue;
			boolean[] cp_check = new boolean[list.size()];
			cp_check = check.clone();
			if (square(next, j, cp_check)) {
				int[] cp_squares = new int[5];
				cp_squares = squares.clone();
				cp_squares[j]--;

				dfs(cnt - (j + 1) * (j + 1), cp_squares, cp_check);
			} else
				continue;
		}

	}

	private static boolean square(int idx, int length, boolean[] check) {
		int row = list.get(idx)[0];
		int col = list.get(idx)[1];
		for (int i = row; i <= row + length; i++) {
			if (i >= 10)
				return false;
			for (int j = col; j <= col + length; j++) {
				if (j >= 10)
					return false;
				if (check[num[i][j]] || map[i][j] == 0) {// 정사각형으로 찾는데, 못만들면 false return, 만들 수 있으면 true return
					return false;
				}
			}
		}
		for (int i = row; i <= row + length; i++) {
			for (int j = col; j <= col + length; j++) {
				check[num[i][j]] = true;
			}
		}
		return true;
	}
}