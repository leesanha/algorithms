import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1106 {
	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int mr = Integer.parseInt(st.nextToken()) - 1;
		int mc = Integer.parseInt(st.nextToken()) - 1;
		int jr = Integer.parseInt(st.nextToken()) - 1;
		int jc = Integer.parseInt(st.nextToken()) - 1;

		boolean[][] visit = new boolean[n][m];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(mr);
		q.offer(mc);
		q.offer(0);
		visit[mr][mc] = true;

		while (!q.isEmpty()) {
			mr = q.poll();
			mc = q.poll();
			int dis = q.poll();

			for (int i = 0; i < 8; i++) {
				int nr = mr + dr[i];
				int nc = mc + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || visit[nr][nc])
					continue;
				if (nr == jr && nc == jc) {
					System.out.println(dis + 1);
					return;
				}
				q.offer(nr);
				q.offer(nc);
				q.offer(dis + 1);
				visit[nr][nc] = true;
			}
		}
	}

}
