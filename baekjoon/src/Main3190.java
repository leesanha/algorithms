import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3190 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int row, col, dir;

		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		boolean[][] s = new boolean[n][n];

		int apples = Integer.parseInt(br.readLine());
		for (int i = 0; i < apples; i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			map[row - 1][col - 1] = 1;
		}

		int cnt = Integer.parseInt(br.readLine());
		Queue<Integer> nt = new LinkedList<Integer>();
		Queue<String> nd = new LinkedList<String>();

		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			nt.offer(Integer.parseInt(st.nextToken()));
			nd.offer(st.nextToken());
		}

		Deque<Integer> snake = new LinkedList<Integer>();
		snake.addFirst(0);
		snake.addFirst(0);
		snake.addFirst(1);

		s[0][0] = true;

		int now_time = 0;
		while (true) {
			int next_time = nt.poll();
			String next_dir = nd.poll();

			dir = snake.pollFirst();
			while (true) {
				now_time++;
				row = snake.pollFirst();
				col = snake.peekFirst();
				snake.addFirst(row);

				int nr = row + dr[dir];
				int nc = col + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n || s[nr][nc]) {
					System.out.println(now_time);
					return;
				}

				// 머리 추가
				s[nr][nc] = true;
				snake.addFirst(nc);
				snake.addFirst(nr);
				// 사과가 아니면 꼬리를 삭제
				if (map[nr][nc] != 1) {
					int tcol = snake.pollLast();
					int trow = snake.pollLast();
					s[trow][tcol] = false;
				} else {// 사과면 사과 먹은 표시
					map[nr][nc] = 0;
				}

				if (now_time == next_time) {
					switch (next_dir) {
					case "L":
						dir = (dir + 3) % 4;
						break;
					case "D":
						dir = (dir + 1) % 4;
						break;
					}
					if(!nt.isEmpty())
						next_time = nt.poll();
					if(!nd.isEmpty())
						next_dir = nd.poll();
				}

			}
		}
	}
}
