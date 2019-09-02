import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main17135 {
	static int n, m, d, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		ArrayList<int[]> enemys = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					int[] ar = { i, j };
					enemys.add(ar);
				}

			}
		}

		ans = 0;
		ArrayList<int[]> rangers = new ArrayList<>();
		dfs(0, 0, rangers, enemys);
		System.out.println(ans);
	}

	private static void dfs(int idx, int cnt, ArrayList<int[]> rangers, ArrayList<int[]> enemys) {
		if (cnt == 3) {
			for (int[] a : rangers) {
				System.out.print(a[1] + " ");
			}
			System.out.println();
			ArrayList<int[]> cpEnemys = new ArrayList<>();
			for (int i = 0; i < enemys.size(); i++) {
				int[] temp = { enemys.get(i)[0], enemys.get(i)[1] };
				cpEnemys.add(temp);
			}
			int temp = play(rangers, cpEnemys);
			if (ans < temp) {
				ans = temp;
			}
			return;
		}

		if (idx >= m)
			return;

		int[] arr = { n, idx };
		rangers.add(arr);
		dfs(idx + 1, cnt + 1, rangers, enemys);
		rangers.remove(arr);
		dfs(idx + 1, cnt, rangers, enemys);
	}

	private static int play(ArrayList<int[]> rangers, ArrayList<int[]> enemys) {
		int ret = 0;
		for (int i = 0; i < n; i++) {
			Set<int[]> kill = new HashSet<>();
			for (int j = 0; j < rangers.size(); j++) {
				int min = Integer.MAX_VALUE;
				int mrow = -1;
				int mcol = -1;
				for (int k = 0; k < enemys.size(); k++) {
					int distance = Math.abs(rangers.get(j)[0] - enemys.get(k)[0])
							+ Math.abs(rangers.get(j)[1] - enemys.get(k)[1]);
					if (d >= distance && distance < min) {
						min = distance;
						mrow = enemys.get(k)[0];
						mcol = enemys.get(k)[1];
					} else if (distance == min) {
						if (mcol > enemys.get(k)[1]) {
							mrow = enemys.get(k)[0];
							mcol = enemys.get(k)[1];
						}
					}
				}
				int[] ar = { mrow, mcol };
				if (mrow != -1 && mcol != -1)
					kill.add(ar);
			}
			for (int[] a : kill) {
				for (int j = 0; j < enemys.size(); j++) {
					if (a[0] == enemys.get(j)[0] && a[1] == enemys.get(j)[1]) {
						enemys.remove(j);
						ret++;
					}
				}
			}
			for (int j = 0; j < enemys.size(); j++) {
				enemys.get(j)[0]++;
				if (enemys.get(j)[0] >= n) {
					enemys.remove(j);
					j--;
				}
			}
			if (enemys.size() == 0)
				return ret;
		}
		return ret;
	}
}
