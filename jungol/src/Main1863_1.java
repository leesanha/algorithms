import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1863_1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			list[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		boolean[] visit = new boolean[n + 1];

		int ans = 0;
		for (int i = 1; i <= n; i++) {
			Queue<Integer> q = new LinkedList();
			if (!visit[i]) {
				q.offer(i);
				visit[i] = true;
				ans++;
			}

			while (!q.isEmpty()) {
				int node = q.remove();
				for (int next : list[node]) {
					if (!visit[next]) {
						q.offer(next);
						visit[next] = true;
					}
				}
			}
		}

		System.out.println(ans);
	}
}
