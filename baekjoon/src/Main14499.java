import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main14499 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0: 위, 1: 동쪽, 2: 아래, 3: 서쪽, 4: 앞, 5: 뒤
		int[] dice = new int[6];
		for (int i = 0; i < 6; i++)
			dice[i] = 0;

		int[] dr = { 0, 0, -1, 1 };
		int[] dc = { 1, -1, 0, 0 };

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			int dir = Integer.parseInt(st.nextToken()) - 1; // 동: 0 , 서 : 1, 북: 2, 남: 3
			row = row + dr[dir];
			col = col + dc[dir];

			if (row < 0 || row >= n || col < 0 || col >= m) {
				row = row - dr[dir];
				col = col - dc[dir];
				continue;
			}
			int temp;
			switch (dir) {
			// 동쪽 : 0->1, 1->2, 2->3, 3->0
			case 0:
				temp = dice[3];
				dice[3] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[0];
				dice[0] = temp;
				break;
			// 서쪽 : 1->0, 2->1, 3->2, 0->3
			case 1:
				temp = dice[3];
				dice[3] = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
				break;
			// 북쪽: 0->5, 5->2, 2->4, 4->0
			case 2:
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[2];
				dice[2] = dice[5];
				dice[5] = temp;
				break;
			// 남쪽: 0->4, 4->2, 2->5, 5->0
			case 3:
				temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[2];
				dice[2] = dice[4];
				dice[4] = temp;
				break;
			}
			if (map[row][col] == 0)
				map[row][col] = dice[2];
			else {
				dice[2] = map[row][col];
				map[row][col] = 0;
			}
			System.out.println(dice[0]);

		}
	}

}
