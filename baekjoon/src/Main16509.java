import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16509 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] drd = { -1, -1, 1, 1 };
	static int[] dcd = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		Node sang = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		sang.dis = 0;

		st = new StringTokenizer(br.readLine());
		Node king = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


		int ans = -1;
		boolean[][] visit = new boolean[10][9];
		Queue<Node> q = new LinkedList<Node>();
		visit[sang.row][sang.col] = true; 
		q.add(sang);

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int row = cur.row;
			int col = cur.col;
			int dis = cur.dis;
			

			if (cur.row == king.row && cur.col == king.col) {
				System.out.println(cur.dis);
				return;
			}
			for (int i = 0; i < 4; i++) {
				// 직선 한 칸
				int nr = row + dr[i];
				int nc = col + dc[i];
				if (nr < 0 || nr >= 10 || nc < 0 || nc >= 9)
					continue;
				if(nr == king.row && nc == king.col)
					continue;

				loop: for (int j = i; j < i + 2; j++) {
					int nnr = nr;
					int nnc = nc;
					for (int k = 0; k < 2; k++) {
						nnr = nnr + drd[(j) % 4];
						nnc = nnc + dcd[(j) % 4];
						if (nnr < 0 || nnr >= 10 || nnc < 0 || nnc >= 9)
							continue loop;
						if(k == 0 && nnr == king.row && nnc == king.col)
							continue loop;
					}
					if(visit[nnr][nnc])
						continue;
					visit[nnr][nnc] = true;
					Node next = new Node(nnr, nnc);
					next.dis = dis + 1;
					q.add(next);
				}
			}
		}
		System.out.println(ans);
	}

	static class Node {
		int row;
		int col;
		int dis;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", dis=" + dis + "]";
		}
	}
}
