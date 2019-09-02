import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1592 {
	static int n, m, l, ans;
	static int[] player;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		player = new int[n];
		player[0] = 1;

		int now = 0;
		int cnt = 1;
		while (true) {
			if (player[now] % 2 == 0) {
				now -= l;
				if (now < 0)
					now = n - Math.abs(now);
				player[now]++;
			} else {
				now = (now + l) % n;
				player[now]++;
			}
			if (player[now] == m)
				break;
			cnt++;
		}

		System.out.println(cnt);
	}

}
