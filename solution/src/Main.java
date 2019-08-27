import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[] ref = new int[10271];
		int max = -1;
		int min = 10272;

		int[][] arr = new int[2][n];
		boolean[] check = new boolean[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken()) + 270;
			int d = Integer.parseInt(st.nextToken()) + 270;

			if (min > s)
				min = s;
			if (max < d)
				max = d;
			arr[0][i] = s;
			arr[1][i] = d;

			for (int j = s; j <= d; j++)
				ref[j]++;
		}
		int ans = 0;
		int cnt = n;
		while (true) {
			int maxRef = -1;
			int idx = -1;
			for (int i = min; i <= max; i++) {
				if (maxRef < ref[i]) {
					maxRef = ref[i];
					idx = i;
				}
			}
			for (int i = 0; i < n; i++) {
				if (idx >= arr[0][i] && idx <= arr[1][i]) {
					check[i] = true;
					for (int j = arr[0][i]; j <= arr[1][i]; j++)
						ref[j]--;
					cnt--;
				}
			}
			ans++;
			if (cnt == 0)
				break;
			
		}
		System.out.println(ans);
	}
}
