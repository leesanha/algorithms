import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
	
public class Solution1263 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dist;
	static ArrayList<Node>[] list;
	static int n;
//	static int[][] net;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			list = new ArrayList[n];
			for (int i = 0; i < n; i++)
				list[i] = new ArrayList<>();
//			net = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int dis = Integer.parseInt(st.nextToken());
					if(dis != 0)
						list[i].add(new Node(j, dis));
				}
			}

			int[] dist_sum = new int[n];
			for (int i = 0; i < n; i++) {
				dist = new int[n];
				for (int j = 0; j < n; j++)
					dist[j] = Integer.MAX_VALUE;
				dijkstra(i);
				for (int j = 0; j < n; j++)
					dist_sum[j] += dist[j];
//				System.out.println(Arrays.toString(dist_sum));
			}
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (ans > dist_sum[i])
					ans = dist_sum[i];
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		dist[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			int here = pq.peek().to;
			int dis = pq.poll().dis;

			for (Node next : list[here]) {
				if (dist[next.to] > dist[here] + next.dis) {
					dist[next.to] = dist[here] + next.dis;
					pq.add(new Node(next.to, next.dis));
				}
			}

		}
	}

	static class Node implements Comparable<Node> {
		int to;
		int dis;

		public Node(int to, int dis) {
			super();
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return this.dis - o.dis;
		}
	}
}