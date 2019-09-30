import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3074 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			int[] times = new int[n];
			long max_time = 0;
			for (int i = 0; i < n; i++) {
				times[i] = Integer.parseInt(br.readLine());
				if (max_time < times[i])
					max_time = times[i];
			}

			long min_time = 0;
			long mid = 0;
			max_time *= m;
			long min = max_time;

			while (min_time <= max_time) {
				mid = (min_time + max_time) / 2;

				long people = 0;
				for (int i = 0; i < n; i++)
					people += mid / times[i];

				if (people >= m) {
					if (min > mid)
						min = mid;
					max_time = mid - 1;
				} else {
					min_time = mid + 1;
				}
			}
			System.out.format("#%d %d\n", t, min);
		}
	}
}