import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2097 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int dst = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] d = new int[n];
		int[] p = new int[n];
		boolean[] check = new boolean[n];

		Arrays.fill(d, -1);
		d[0] = 0;
		check[0] = true;
		
		int cnt = n - 1;
		int cur = 0;
		for (int i = cur; cnt != 0; i = cur, cnt--) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				if (d[j] == -1 || d[j] > d[i] + arr[i][j]) {
					d[j] = d[i] + arr[i][j];
					p[j] = i;
				}
			}
			int min_idx = -1;
			int min = -1;
			for (int j = 0; j < n; j++) {
				if ((min == -1 || d[j] < min) && !check[j]) {
					min = d[j];
					min_idx = j;
				}
			}
			cur = min_idx;
			check[min_idx] = true;
		}
		System.out.println(d[dst - 1]);
		System.out.println();
		path(p, dst - 1);
	}

	static void path(int[] p, int idx) {
		if (idx == 0) {
			System.out.print((idx + 1) + " ");
			return;
		}
		path(p, p[idx]);
		System.out.print((idx + 1) + " ");
	}
}
