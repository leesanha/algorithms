import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

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

		Arrays.fill(d, -1);
		d[0] = 0;

		int cur = 0;
		for (int i = cur; i != dst - 1;i = cur) {
			int min = arr[i][i];
			int min_idx = 0;
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				if (d[j] == -1 || d[j] > d[i] + arr[i][j]) {
					d[j] = d[i] + arr[i][j];
					p[j] = i;
					if(min == 0 || min > arr[i][j]) {
						min = arr[i][j];
						min_idx = j;
					}
				}
			}
			cur = min_idx;
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
