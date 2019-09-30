import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution8500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] seat = new int[n];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				seat[i] = Integer.parseInt(st.nextToken());

			Arrays.sort(seat);
			int sum = seat[n - 1] + n;
			for (int i = n - 1; i >= 0; i--)
				sum += seat[i];

			System.out.format("#%d %d\n", t, sum);
		}
	}
}