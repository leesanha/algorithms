import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			Posi[] nodes = new Posi[n + 2];// 정보 저장
			StringTokenizer st = new StringTokenizer(br.readLine());

			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());

			nodes[0] = new Posi(row, col);// 회사

			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			nodes[n + 1] = new Posi(row, col);// 집

			ArrayList<Integer>[] list = new ArrayList[n + 2];
			for (int i = 0; i <= n + 1; i++)
				list[i] = new ArrayList<>();

			for (int i = 1; i <= n; i++) {
				row = Integer.parseInt(st.nextToken());
				col = Integer.parseInt(st.nextToken());

				nodes[i] = new Posi(row, col);
				for (int j = 0; j < i; j++) {
					list[j].add(i);
				}
				for (int j = i - 1; j > 0; j--) {
					list[i].add(j);
				}
				list[i].add(n + 1);
			}

			boolean[] check = new boolean[n + 2];
			int[] distance = new int[n + 2];
			Arrays.fill(distance, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(0);// 현재노드
			q.offer(0);// 몇 집을 거쳤는가
			check[0] = true;

			while (!q.isEmpty()) {
				int node = q.poll();
				int cnt = q.poll();

				for (int next : list[node]) {
					if (next == n + 1 && cnt != n + 1)
						continue;
					int dis = Math.abs(nodes[node].row - nodes[next].row) + Math.abs(nodes[node].col - nodes[next].col);
					if (distance[next] == -1 || distance[next] > dis) {
						System.out.println(node + " " + next);
						distance[next] = dis;
						q.offer(next);
						q.offer(cnt + 1);
						check[next] = true;
						continue;
					}
					if (!check[next]) {
						check[next] = true;
						q.offer(next);
						q.offer(cnt + 1);
					}
				}
			}
			System.out.format("#%d %d\n", t, distance[n + 1]);
		}
	}

	static class Posi {
		int row;
		int col;

		public Posi(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}
}
