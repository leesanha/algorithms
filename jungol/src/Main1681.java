import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main1681 {
	static int[][] map;
	static int n, ans;
	static int[] cities;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cities = new int[n - 1];
		for (int i = 1; i < n; i++) {
			cities[i - 1] = i;
		}
		ans = Integer.MAX_VALUE;
		comb(0);
		System.out.println(ans);
	}

	private static void comb(int depth) {
		if (depth == n - 1) {
//			System.out.println(Arrays.toString(cities));
			int ret = cal();
			if (ret < ans)
				ans = ret;
			return;
		}
		for (int i = depth; i < n - 1; i++) {
			swap(i, depth);
			comb(depth + 1);
			swap(i, depth);
		}
	}

	private static int cal() {
		if (map[0][cities[0]] == 0)
			return Integer.MAX_VALUE;
		int ret = map[0][cities[0]];
		for (int i = 0; i < n - 2; i++) {
			if (map[cities[i]][cities[i + 1]] == 0)
				return Integer.MAX_VALUE;
			ret += map[cities[i]][cities[i + 1]];
		}
		return (map[cities[n - 2]][0] == 0) ? Integer.MAX_VALUE : ret + map[cities[n - 2]][0];
	}

	private static void swap(int i, int depth) {
		int temp = cities[i];
		cities[i] = cities[depth];
		cities[depth] = temp;
	}

}
