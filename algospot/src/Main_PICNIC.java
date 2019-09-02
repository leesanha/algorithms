import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_PICNIC {
	static int ans, n, m;
	static boolean[][] check;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			check = new boolean[n][n];
			visit = new boolean[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

//				if (a > b)
					check[b][a] = true;
//				else
					check[a][b] = true;
			}
			ans = 0;
			dfs(0);
			System.out.println(ans);
		}
	}

	private static void dfs(int depth) {
		if (depth == n / 2) {
			for (int i = 0; i < n; i++) {
				if (!visit[i])
					return;
			}
			ans++;
			return;
		}

		int start = 0;
		for (int i = 0; i < n; i++) {
			if(!visit[i]) {
				start = i;
				break;
			}
		}

		for (int i = start; i < n; i++) {
			if (!visit[i] && check[start][i]) {
				visit[start] = true;
				visit[i] = true;
				dfs(depth + 1);
				visit[start] = false;
				visit[i] = false;
			}
		}
	}

}
