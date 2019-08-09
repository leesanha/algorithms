import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution6719 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int size;
	static double ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			ans = 0;
			String[] input = br.readLine().split(" ");

			size = Integer.parseInt(input[0]);
			int cnt = Integer.parseInt(input[1]);

			ArrayList<Double> arr = new ArrayList<>();
			input = br.readLine().split(" ");

			for (int i = 0; i < size; i++) {
				arr.add(Double.parseDouble(input[i]));
			}

			Collections.sort(arr, Collections.reverseOrder());

			
			ans = arr.get(cnt - 1) / 2;
			for (int i = cnt - 2; i >= 0; i--) {
				ans = (ans + arr.get(i)) / 2;
			}
			System.out.format("#%d %f\n", t, ans);
		}
	}
}
