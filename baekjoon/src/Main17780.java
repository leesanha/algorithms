import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17780 {
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 맵정보
		int[][] map = new int[n][n];
		// 각 칸에 몇 번 드론들이 쌓여 있는지 저장할 2차원 배열 arraylist
		// ex. position[1][0]에 {0,4,2}면 0번 위에 4번, 4번 위에 2번 쌓여있는 것.
		Stack<Integer>[][] position = new Stack[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				position[i][j] = new Stack<Integer>();
			}
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 드론들의 정보를 저장할 배열
		Drone[] drones = new Drone[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			drones[i] = new Drone(row, col, dir);
			position[row][col].push(i);
		}

//		int ans = -1;
//		boolean flag = false;
		for (int turn = 0; turn < 1000; turn++) {
//			System.out.println();
//			System.out.println(turn + ". 이동전");
//			for(int i=0;i<k;i++) {
//				int row = drones[i].row;
//				int col = drones[i].col;
//				System.out.println(i + " " + row + " " + col);
//			}
			// 말 이동
			for (int i = 0; i < k; i++) {
				int row = drones[i].row;
				int col = drones[i].col;
				int dir = drones[i].dir;

				// 가장 아래에 있는 말이 아니면 그냥 넘어간다.
				if (position[row][col].get(0) != i)
					continue;

				int nr = row + dr[dir];
				int nc = col + dc[dir];
//				System.out.println(i + " " + nr + " " + nc);

				// 다음 방향이 벽 or 벗어나는 경우
				if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 2) {
					// 방향을 바꿔준다.
					if (dir % 2 == 0)
						dir += 1;
					else
						dir -= 1;
					// 바뀐 방향 정보를 다시 저장해준다.
					drones[i].dir = dir;
					// 바꿔준 방향으로 1칸 이동한다.
					nr = row + dr[dir];
					nc = col + dc[dir];

					// 반대 방향으로 한 칸 갔는데도 벽 or 벗어나는 경우
					if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 2) {
						// 원래 자리로 돌린다.
						nr = row;
						nc = col;
						continue;
					}
				}

				// nr, nc의 위치가 빨간색인 경우
				if (map[nr][nc] == 1) {
					// 큐 사용
//					Queue<Integer> q = new LinkedList<Integer>();
					// 현재 드론이 나올 때 까지 계속 빼낸다.(x)
					//현재 위치에 있는 드론들을 다 빼낸다. => 어짜피 가장 밑에 있는 드론만 움직일 수 있기 때문에
					while (!position[row][col].isEmpty()) {
						int cur = position[row][col].pop();
						drones[cur].row = nr;
						drones[cur].col = nc;
						position[nr][nc].push(cur);
//						int num = position[row][col].size() - 1;
//						int cur = position[row][col].get(num);
//						position[row][col].remove(num);
//						q.add(cur);
//						// 현재 드론이 나오면
//						if (i == cur)
//							break;
					}
					// 큐에 있는 거를 nr, nc 위치에 다 넣어준다.
//					while (!q.isEmpty()) {
//						int cur = q.poll();
//						drones[cur].row = nr;
//						drones[cur].col = nc;
//						position[nr][nc].push(cur);
//					}
				} else {// 흰색인 경우
					// 스택 사용
					Stack<Integer> stack = new Stack<Integer>();
					while (!position[row][col].isEmpty()) {
						stack.push(position[row][col].pop());
//						int num = position[row][col].size() - 1;
//						int cur = position[row][col].get(num);
//						position[row][col].remove(num);
//						stack.push(cur);
//						// 현재 드론이 나오면
//						if (i == cur)
//							break;
					}
					// 스택에 있는 거를 nr, nc 위치에 다 넣어준다.
					while (!stack.isEmpty()) {
						int cur = stack.pop();
						drones[cur].row = nr;
						drones[cur].col = nc;
						position[nr][nc].push(cur);
					}
				}
				int cnt = position[nr][nc].size();

				if (cnt >= 4) {
					System.out.println(turn + 1);
					return;
//					flag = true;
//					ans = turn + 1;
//					ans = cnt;
//					break;
				}
			}
//			if (flag)
//				break;
			
//			System.out.println("이동 후");
//			for(int i=0;i<k;i++) {
//				int row = drones[i].row;
//				int col = drones[i].col;
//				System.out.println(i + " " + row + " " + col);
//			}
		}
//		System.out.println(ans);
		System.out.println(-1);
	}

	static class Drone {
		int row;
		int col;
		int dir;

		public Drone(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "drone [row=" + row + ", col=" + col + ", dir=" + dir + "]";
		}
	}
}
