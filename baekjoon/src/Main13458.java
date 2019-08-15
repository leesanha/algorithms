import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] testRoom = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			testRoom[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		long ans = 0;
		for (int i = 0; i < n; i++) {
			testRoom[i] -= b;
			if (testRoom[i] < 0)
				testRoom[i] = 0;
		}

		ans += n;
		for (int i = 0; i < n; i++) {
			ans += (testRoom[i] % c == 0) ? testRoom[i] / c : testRoom[i] / c + 1;
		}
		System.out.println(ans);
	}

}
