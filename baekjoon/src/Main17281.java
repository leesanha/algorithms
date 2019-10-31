import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main17281 {
	static int ans, n;
	static int[][] hitter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		hitter = new int[n][9];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				hitter[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = -1;
		int[] player = { 1, 2, 3, 4, 5, 6, 7, 8 };
		permutation(player, 0);
		System.out.println(ans);
	}

	private static void permutation(int[] player, int depth) {
//		System.out.println(Arrays.toString(player));
		if (depth == 8) {
			// 하나의 순열이 완성되면 게임을 시작하고 스코어를 반환한다.
			int ret = play(player);
			if (ret > ans)
				ans = ret;
			return;
		}
		// 1번 타자를 제외한 8명을 순열을 만들어준다.
		for (int i = depth; i < 8; i++) {
			swap(depth, i, player);
			permutation(player, depth + 1);
			swap(depth, i, player);
		}
	}

	private static int play(int[] player) {
		int[] realPlayer = new int[9];
		// 1번 타자를 4번 타순에 넣는다.
		for (int i = 0; i < 9; i++) {
			if (i < 3)
				realPlayer[i] = player[i];
			else if (i == 3)
				realPlayer[i] = 0;
			else
				realPlayer[i] = player[i - 1];
		}
		int outCnt, turn = 0, score = 0;
		boolean[] base;

		// i를 이닝으로 쓸거라서 for문 씀. while문에 while(n-- > 0)으로 할려니까 이닝이 큰 수부터 줄어들어야 함.
		for (int i = 0; i < n; i++) {
			outCnt = 0;
			base = new boolean[3];
			while (outCnt != 3) {
				if (hitter[i][realPlayer[turn]] == 0) {
					outCnt++;
				} else if (hitter[i][realPlayer[turn]] == 1) {
					// 3루에 주자가 있으면 점수 증가
					if (base[2]) {
						score++;
						base[2] = false;
					}
					for (int j = 2; j > 0; j--) {// 나가있는 주자들을 진루시킨다.
						if (base[j - 1]) {// 진루할 주자가 있으면
							base[j] = base[j - 1];// 진루한다.
							base[j - 1] = false;
						}
					}
					base[0] = true;// 타자는 1루로 진루
				} else if (hitter[i][realPlayer[turn]] == 2) {
					for (int j = 1; j < 3; j++) {// 2루 3루에 주자가 있으면 점수 증가
						if (base[j]) {
							score++;
							base[j] = false;
						}
					}
					if (base[0]) {// 1루에 주자가 있었으면 3루 진루
						base[2] = true;
						base[0] = false;
					}
					base[1] = true;// 타자는 2루로 진루
				} else if (hitter[i][realPlayer[turn]] == 3) {
					for (int j = 0; j < 3; j++) {// 진루한 모든 주자들 점수 증가
						if (base[j]) {
							score++;
							base[j] = false;
						}
					}
					base[2] = true;// 타자는 3루로 진루
				}else {
					for (int j = 0; j < 3; j++) {// 진루한 모든 주자들 점수 증가
						if (base[j]) {
							score++;
							base[j] = false;
						}
					}
					score++;//타자 점수 증가
				}
				turn++;
				turn %= 9;
			}
		}
		return score;
	}

	private static void swap(int depth, int i, int[] player) {
		int temp = player[i];
		player[i] = player[depth];
		player[depth] = temp;
	}

}
