import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Festival {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			int[] ar = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				ar[i] = Integer.parseInt(st.nextToken());
			int size = l;
			double ans = 10000000.0;
			while (true) {
				for (int i = 0; i <= n - size; i++) {
					double sum = 0;
					for (int j = i; j < i + size; j++)
						sum += (double) ar[j];
					sum /= (double) size;
					if (ans > sum)
						ans = sum;
				}
				if (size == n)
					break;
				size++;
			}
			System.out.format("%.12f\n", ans);
		}
	}

}
