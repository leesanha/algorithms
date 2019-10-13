import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2383 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[] visit;
	static int n, ans;
	static ArrayList<Node> people;
	static Node[] stair;
	static int cri;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());

			people = new ArrayList<Solution2383.Node>();
			stair = new Node[2];

			int cnt = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1)
						people.add(new Node(i, j, 0, 0));
					if (num > 1)
						stair[cnt++] = new Node(i, j, num, 0);
				}
			}
			visit = new boolean[people.size()];

			ans = Integer.MAX_VALUE;
			dfs(0, stair[0].stairDis, stair[1].stairDis);
			System.out.format("#%d %d\n", t, ans + 1);
		}
	}

	private static void dfs(int depth, int firstStair, int secondStair) {
		if (depth == people.size()) {
			ArrayList<Node> first = new ArrayList<Solution2383.Node>();
			ArrayList<Node> second = new ArrayList<Solution2383.Node>();
			for (int i = 0; i < visit.length; i++) {
				Node p = people.get(i);
				if (visit[i]) {
					people.get(i)
							.setStairDis(Math.abs(p.getRow() - stair[0].row) + Math.abs(p.getCol() - stair[0].col));
					first.add(people.get(i));
				} else {
					people.get(i)
							.setStairDis(Math.abs(p.getRow() - stair[1].row) + Math.abs(p.getCol() - stair[1].col));
					second.add(people.get(i));
				}
			}
			int res1 = getTime(first, firstStair);
			int res2 = getTime(second, secondStair);
//			System.out.println(++cri + ": " + res1 + " " + res2);
			int res = (res1 > res2) ? res1 : res2;

			ans = (ans < res) ? ans : res;
			return;
		}
		visit[depth] = true;
		dfs(depth + 1, firstStair, secondStair);
		visit[depth] = false;
		dfs(depth + 1, firstStair, secondStair);
	}

	private static int getTime(ArrayList<Node> queue, int stairDis) {
		Collections.sort(queue, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.stairDis > o2.stairDis)
					return 1;
				else if (o1.stairDis < o2.stairDis)
					return -1;
				else
					return 0;
			}
		});

		if (queue.size() == 0)
			return 0;
		int time = queue.get(0).stairDis;
		ArrayList<Node> q = new ArrayList<Solution2383.Node>();
		q.add(queue.get(0));
		queue.get(0).setRest(stairDis);
		queue.remove(0);

		while (true) {
			if (queue.size() == 0 && q.isEmpty())
				break;
			
			
			for (int i = 0; i < queue.size(); i++) {
				if (queue.get(i).stairDis <= time && q.size() < 3) {
					queue.get(i).setRest(stairDis);
					q.add(queue.get(i));
					queue.remove(i);
					i--;
				} else
					break;
			}
			
			time++;
			for (int i = 0; i < q.size(); i++) {
				q.get(i).setRest(q.get(i).getRest() - 1);
			}
			for (int i = 0; i < q.size(); i++) {
				if (q.get(i).rest == 0) {
					q.remove(i);
					i--;
				}
			}
		}

		return time;
	}

	static class Node {
		int row;
		int col;
		int stairDis;
		int rest;

		public Node(int row, int col, int stairDis, int rest) {
			super();
			this.row = row;
			this.col = col;
			this.stairDis = stairDis;
			this.rest = rest;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getStairDis() {
			return stairDis;
		}

		public void setStairDis(int stairDis) {
			this.stairDis = stairDis;
		}

		public int getRest() {
			return rest;
		}

		public void setRest(int rest) {
			this.rest = rest;
		}

	}
}