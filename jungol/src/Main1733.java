import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1733 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

		int[][] map = new int[19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visit = new boolean[19][19];
		// 왼쪽 위에서 부터 내려온다.
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (!visit[i][j] && map[i][j] != 0) {
					visit[i][j] = true;
					int num = map[i][j];
					for (int dir = 2; dir < 6; dir++) {
						// 이전에 이어지는 방향에 돌이 있었으면 부분 집합이므로 계산할 필요가 없다.
						int pr = i + dr[(dir + 4) % 8];
						int pc = j + dc[(dir + 4) % 8];
						// 부분집합인지 판단한다.
						if (pr >= 0 && pr < 19 && pc >= 0 && pc < 19 && visit[pr][pc] && map[pr][pc] == num)
							continue;

						// 제일 왼쪽 꺼 혹은 가장 위에거 꺼내기 위한 list
						ArrayList<Node> list = new ArrayList<Node>();
						int nr = i;
						int nc = j;
						list.add(new Node(nr, nc));
						int cnt = 0;
						while (true) {
							cnt++;
							nr += dr[dir];
							nc += dc[dir];

							if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19)
								break;
							if (num != map[nr][nc])
								break;
							if (map[nr][nc] == 0)
								break;
							list.add(new Node(nr, nc));
						}
						// 가장 왼쪽꺼, 세로 방향인 경우 가장 위에꺼 나오도록 정렬 한 후 출력
						if (cnt == 5) {
							System.out.println(num);
							Collections.sort(list, new Comparator<Node>() {

								@Override
								public int compare(Node o1, Node o2) {
									if (o1.col == o2.col) {
										return o1.row - o2.row;
									} else {
										return o1.col - o2.col;
									}
								}
							});
							System.out.println((list.get(0).row + 1) + " " + (list.get(0).col + 1));
							return;
						}
					}
				}
			}
		}
		System.out.println(0);
	}

	static class Node {
		int row;
		int col;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
