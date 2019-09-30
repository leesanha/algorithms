import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());

		int[][] map = new int[row + 1][col + 1];
		for (int i = 0; i < row + 1; i++) {
			for (int j = 0; j < col + 1; j++)
				map[i][j] = Integer.MAX_VALUE;
		}
		int[][] cnt = new int[row + 1][col + 1];

		st = new StringTokenizer(br.readLine());
		int mc = Integer.parseInt(st.nextToken());
		int mr = Integer.parseInt(st.nextToken());

		if (mr > row || mc > col) {
			System.out.println("fail");
			System.exit(0);
		}
		Queue<int[]> q = new LinkedList<int[]>();
		int[] temp = { 0, 0, 0 };
		map[0][0] = 0;
		q.add(temp);

		while (!q.isEmpty()) {
			temp = q.remove();
			int r = temp[0];
			int c = temp[1];
			int dis = temp[2];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr > row || nr < 0 || nc > col || nc < 0 || map[nr][nc] < dis + 1)
					continue;
				if (map[nr][nc] == dis + 1) {
					cnt[nr][nc]++;
				} else {
					cnt[nr][nc] = 1;
					map[nr][nc] = dis + 1;
				}
				int[] t = { nr, nc, dis + 1 };
				q.add(t);
			}
		}
		System.out.println(map[mr][mc]);
		System.out.println(cnt[mr][mc]);
	}

}
