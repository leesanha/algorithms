import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3055 {
	static int ans;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[][] map = new char[r][c];

		Queue<int[]> player = new LinkedList<int[]>();
		Queue<int[]> fire = new LinkedList<int[]>();

		int fr = 0;
		int fc = 0;
		boolean[][] visit = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'S') {
					int[] temp = { i, j };
					player.add(temp);
					visit[i][j] = true;
				}
				if (map[i][j] == '*') {
					int[] temp = { i, j };
					fire.add(temp);
				}
				if (map[i][j] == 'D') {
					fr = i;
					fc = j;
				}
			}
		}
		String ans = "KAKTUS";

		int time = 0;
		int row = 0;
		int col = 0;
		int nr = 0;
		int nc = 0;
		while (true) {
			time++;
			if (player.isEmpty() && fire.isEmpty())
				break;
			Queue<int[]> next_fire = new LinkedList<int[]>();

			while (!fire.isEmpty()) {
				int[] temp = fire.poll();
				row = temp[0];
				col = temp[1];

				for (int i = 0; i < 4; i++) {
					nr = row + dr[i];
					nc = col + dc[i];

					if (nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] == '*' || map[nr][nc] == 'D'
							|| map[nr][nc] == 'X')
						continue;
					map[nr][nc] = '*';
					int[] t = { nr, nc };
					next_fire.add(t);
				}
			}
			fire = next_fire;

			Queue<int[]> next_player = new LinkedList<int[]>();
			while (!player.isEmpty()) {
				int[] temp = player.poll();
				row = temp[0];
				col = temp[1];
				
				for (int i = 0; i < 4; i++) {
					nr = row + dr[i];
					nc = col + dc[i];

					if (nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] == '*' || map[nr][nc] == 'X'
							|| visit[nr][nc])
						continue;
					if (map[nr][nc] == 'D') {
						System.out.println(time);
						return;
					}
					visit[nr][nc] = true;
					int[] t = { nr, nc };
					next_player.add(t);
				}
			}
			player = next_player;
		}

		System.out.println(ans);
	}

}