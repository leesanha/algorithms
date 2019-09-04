import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		ArrayList<int[]> sharks = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// row, col, 속력, 이동 방향, 크기
			int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) };
			sharks.add(temp);
		}

		// idx는 낚시왕 위치
		int idx = 0;
		while (idx <= c) {
			idx++;// 낚시왕 이동
			// 낚시왕이 상어 잡음
			int minValue = Integer.MAX_VALUE;
			int minIdx = -1;
			for (int i = 0; i < sharks.size(); i++) {
				if (sharks.get(i)[1] == idx) {
					if (sharks.get(i)[0] < minValue)
						minIdx = i;
				}
			}
			if (minIdx != -1)
				sharks.remove(minIdx);
			// 상어 이동
			for (int i = 0; i < sharks.size(); i++) {
				int row = sharks.get(i)[0];
				int col = sharks.get(i)[1];
				int speed = sharks.get(i)[2];
				int dir = sharks.get(i)[3];
				int size = sharks.get(i)[4];
				// 끝에서 안쪽을 바라보면서 출발하는 친구
				int togo = 0;
				if (row == 0 && dir == 2) {

					continue;
				} else if (row == r && dir == 1) {

					continue;
				}

				// 아닌 친구
				if (dir == 3 || dir == 4) {
					if (col == 0 && dir == 3 || col == c && dir == 4) {
						togo = speed % ((c - 1) * 2 + 1);
					} else {
						togo = speed % ((c - 1) * 2);
					}
					for (int j = 0; j < togo; j++) {
						row += dr[dir - 1];
						col += dc[dir - 1];
						if (col == 0) {
							dir = 3;
							col = 2;
						} else if (col == c + 1) {
							dir = 2;
							col = c - 1;
						}
					}
				} else {
					if (row == 0 && dir == 2 || row == r && dir == 1) {
						togo = speed % ((r - 1) * 2 + 1);
					} else {
						togo = speed % ((r - 1) * 2);
					}

					for (int j = 0; j < togo; j++) {
						row += dr[dir - 1];
						col += dc[dir - 1];
						if (row == 0) {
							dir = 2;
							row = 2;
						} else if (row == r + 1) {
							dir = 1;
							row = r - 1;
						}
					}
				}
			}
		}
	}

}