import java.io.IOException;
import java.util.Scanner;

public class Solution4796 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			int n = sc.nextInt();
			int[] h = new int[n];
			
			for (int i = 0; i < n; i++)
				h[i] = Integer.parseInt(sc.next());

			int start = 0;
			int sum = 0;
			for (int i = 0; i < h.length; i = start) {
				int end = i + 1;

				while (true) {
					if (end >= n || h[end] < h[end - 1])
						break;
					if (h[end] > h[end - 1])
						end++;
				}
				if (end == n)
					break;
				int cnt = end - 1 - i;
				int peak = end;
				while (true) {
					if (peak >= n || h[peak] > h[peak - 1])
						break;
					if (h[peak] < h[peak - 1])
						peak++;
				}
				sum += cnt * (peak - end);
				start = peak - 1;
			}

			System.out.format("#%d %d\n", t, sum);
		}
	}
}