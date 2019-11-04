import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main_wait {
	static int ans, score;
	static int[][] map = { { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0 },
			{ 0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40, 0 },
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40, 0 },
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40, 0 } };
	static boolean[][] visit;
	static int[] dice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];
		for (int i = 0; i < 10; i++)
			dice[i] = Integer.parseInt(st.nextToken());
		ans = 0;
		visit = new boolean[4][23];
		int[][] player = new int[4][2];// player[][0]은 맵, player[][1]은 움직인 거리
		move(player, 0, 0);
		System.out.println(ans);
	}

	private static void move(int[][] player, int sum, int depth) {
//		System.out.println(sum);
		for (int i = 0; i < 4; i++) {
			System.out.print((i + 1) + "'s map: " + player[i][0] + ", dis: " + player[i][1] + " || ");
		}
		System.out.println();
		if (depth == 10) {
			System.out.println("sum : " + sum);
			if (sum > ans)
				ans = sum;
			return;
		}
		int[][] cpPlayer = new int[4][2];
		for (int j = 0; j < 4; j++)
			cpPlayer[j] = Arrays.copyOf(player[j], player[j].length);

		for (int i = 0; i < 4; i++) {
//			int beforeMap = cpPlayer[i][0];
//			int beforeDis = cpPlayer[i][1];
			// 이미 도착한 상태면 넘긴다.
			if (cpPlayer[i][1] == map[cpPlayer[i][0]].length - 1)
				continue;
			// 도착한 상태가 아니면 다음 위치를 구한다.
			int nextPos = cpPlayer[i][1] + dice[depth];
			// 다음 위치가 도착을 넘어가면 도착에서 이동을 마친다. visit 할 필요 없음.
			if (nextPos >= map[cpPlayer[i][0]].length) {
				visit[cpPlayer[i][0]][cpPlayer[i][1]] = false;
				nextPos = map[cpPlayer[i][0]].length - 1;
				cpPlayer[i][1] = nextPos;
				move(cpPlayer, sum + map[cpPlayer[i][0]][nextPos], depth + 1);
				continue;
			}
			// 25, 30, 35, 40을 구분해줘야 한다.

			// 누가 이미 있으면 넘어간다.
			if (visit[cpPlayer[i][0]][nextPos])
				continue;
			else {// 움직일 수 있는 것이므로, 이전 위치를 FALSE로 두고 움직인다.
				visit[cpPlayer[i][0]][cpPlayer[i][1]] = false;
				cpPlayer[i][1] = nextPos;
			}

			// 인덱스가 5의 배수면 다른 길로 가야한다. 다른 길로 가는 경우에는 다른 맵으로 넘긴다. 40이면 도착지점이므로 map을 바꿀 필요가
			// 없다.
			if (cpPlayer[i][0] == 0 && nextPos % 5 == 0 && map[cpPlayer[i][0]][nextPos] != 40) {
				cpPlayer[i][0] = nextPos / 5;
			}
//			System.out.println(i + " " + cpPlayer[i][0] + " " + nextPos);
			visit[cpPlayer[i][0]][nextPos] = true;
			move(cpPlayer, sum + map[cpPlayer[i][0]][nextPos], depth + 1);
			visit[cpPlayer[i][0]][nextPos] = false;
		}
	}
}
