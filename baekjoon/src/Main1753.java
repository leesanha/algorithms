import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		ArrayList<int[]>[] list = new ArrayList[v + 1];
		boolean[] check = new boolean[v + 1];
		int[] d = new int[v + 1];

		for (int i = 0; i <= v; i++) {
			list[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			int[] temp = { to, val };
			list[from].add(temp);
		}

		d[start] = 0;
		check[start] = true;

		int cur = start;
		for (int i = cur;; i = cur) {
			for (int[] eg : list[i]) {
				if (d[eg[0]] > d[i] + eg[1])
					d[eg[0]] = d[i] + eg[1];
			}
			int min = Integer.MAX_VALUE;
			int min_idx = -1;
			for (int j = 1; j <= v; j++) {
				if (!check[j] && min > d[j]) {
					min = d[j];
					min_idx = j;
				}
			}
			if (min == Integer.MAX_VALUE)
				break;
			cur = min_idx;
			check[min_idx] = true;
		}

		for (int i = 1; i <= v; i++) {
			if (d[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(d[i]);
		}
	}
}
