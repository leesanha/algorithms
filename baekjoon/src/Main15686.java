import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main15686 {
	static int n, m, ans;
	static ArrayList<int[]> homes;
	static ArrayList<int[]> chickens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		homes = new ArrayList<>();
		chickens = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					int[] ar = { i, j };
					homes.add(ar);
				}
				if (temp == 2) {
					int[] ar = { i, j };
					chickens.add(ar);
				}
			}
		}

		ans = Integer.MAX_VALUE;
		ArrayList<int[]> list = new ArrayList<>();
		dfs(0, 0, list);
		System.out.println(ans);
	}

	private static void dfs(int idx, int cnt, ArrayList<int[]> list) {
		if (cnt == m) {
			int temp = getDistance(list);
			if (ans > temp)
				ans = temp;
			return;
		}
		if (idx >= chickens.size())
			return;

		list.add(chickens.get(idx));
		dfs(idx + 1, cnt + 1, list);// 선택하고
		list.remove(chickens.get(idx));
		dfs(idx + 1, cnt, list);// 선택안하고
	}

	private static int getDistance(ArrayList<int[]> list) {
		int sum = 0;
		for (int i = 0; i < homes.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < list.size(); j++) {
				int temp = Math.abs(homes.get(i)[0] - list.get(j)[0]) + Math.abs(homes.get(i)[1] - list.get(j)[1]);
				if (min > temp)
					min = temp;
			}
			sum += min;
		}
		return sum;
	}
}
