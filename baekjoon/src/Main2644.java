import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2644 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
		int size = sc.nextInt();

		ArrayList<Integer>[] parents = new ArrayList[n + 1];
		ArrayList<Integer>[] childs = new ArrayList[n + 1];
		boolean[] visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			parents[i] = new ArrayList<>();
			childs[i] = new ArrayList<>();
		}

		for (int i = 0; i < size; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();

			parents[parent].add(child);
			childs[child].add(parent);
		}

		int start = (a > b) ? b : a;
		int end = (a > b) ? a : b;

		Queue<Integer> q = new LinkedList();

		int ans = -1;
		q.offer(start);
		q.offer(0);
		visit[start] = true;
		while (!q.isEmpty()) {
			int node = q.poll();
			int lv = q.poll();

			if (node == end)
				ans = lv;

			for (int v : parents[node]) {
				if (!visit[v]) {
					visit[v] = true;
					q.offer(v);
					q.offer(lv + 1);
				}
			}
			for (int v : childs[node]) {
				if (!visit[v]) {
					visit[v] = true;
					q.offer(v);
					q.offer(lv + 1);
				}
			}
		}
		System.out.println(ans);
	}

}