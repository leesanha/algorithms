import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1238 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
//		int tc = sc.nextInt();

		for (int t = 1; t <= 10; t++) {
			int size = sc.nextInt();
			int start = sc.nextInt();

//			System.out.println(size + " " + start);

			ArrayList<Integer>[] g = new ArrayList[101];
			ArrayList<Integer>[] level = new ArrayList[101];

			boolean[] visit = new boolean[101];

			for (int i = 0; i < size / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();

				if (g[from] == null)
					g[from] = new ArrayList<Integer>();
				g[from].add(to);
			}

			int cnt = 0;
			Queue q = new LinkedList<Integer>();

			q.add(start);
			q.add(0);
			visit[start] = true;
			while (!q.isEmpty()) {
				int node = (int) q.poll();
				int lv = (int) q.poll();

				if (g[node] == null)
					continue;

				for (int n : g[node]) {
					if (!visit[n]) {
						q.add(n);
						q.add(lv + 1);
						visit[n] = true;
						if (level[lv + 1] == null)
							level[lv + 1] = new ArrayList<Integer>();
						level[lv + 1].add(n);
					}
				}
			}
			for(int i=1;i<size;i++) {
				if(level[i] == null)
					break;
				cnt = i;
			}
			int max = level[cnt].get(0);

			for (int n : level[cnt]) {
				if (max < n)
					max = n;
			}
			System.out.format("#%d %d\n", t, max);
		}
		sc.close();
	}

}
