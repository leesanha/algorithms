import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static int ans, score;
	static int[][] map = { { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40 },
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40 },
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40 } };
	static boolean[][] visit;
	static int[] dice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];
		for (int i = 0; i < 10; i++)
			dice[i] = Integer.parseInt(st.nextToken());
		ans = 0;
		visit = new boolean[41][23];
		int[][] player = new int[4][2];
		move(player, 0, 0);
		System.out.println(ans);
	}

	private static void move(int[][] player, int sum, int depth) {
		System.out.println(sum);
		if (depth == 10) {
			if (sum > ans)
				ans = sum;
			return;
		}
		for (int i = 0; i < 4; i++) {
			player[i][1] += dice[depth];
			if (visit[player[i][0]][player[i][1]])
				return;
			if (player[i][0] == 0 && player[i][1] % 5 == 0) {
				int[][] cpPlayer = new int[4][2];
				for (int j = 0; j < 4; j++)
					cpPlayer[j] = Arrays.copyOf(player[j], player[j].length);
				cpPlayer[i][0] = cpPlayer[i][1] / 5;
				visit[player[i][0]][player[i][1]] = true;
			}
			visit[player[i][0]][player[i][1]] = true;
			move(player, sum + map[player[i][0]][player[i][1]], depth + 1);
			visit[player[i][0]][player[i][1]] = false;
		}
	}

//	static class Node {
//		int pos;
//
//		public Node(int pos) {
//			super();
//			this.pos = pos;
//		}
//
//		@Override
//		public String toString() {
//			return "Node [pos=" + pos + "]";
//		}
//	}
}
