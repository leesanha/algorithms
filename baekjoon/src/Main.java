import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static HashSet<String> set = new HashSet<>();
	static ArrayList<int[]> vs;
	static int[] win = new int[6];
	static int[] draw = new int[6];
	static int[] lose = new int[6];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		vs = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				int[] temp = { i, j };
				vs.add(temp);
			}
		}

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			flag = false;

			for (int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
//				System.out.println(win[i] + " " + draw[i] + " " + lose[i]);
			}
			int sum_win = 0;
			int sum_draw = 0;
			int sum_lose = 0;
			for (int j = 0; j < 6; j++) {
				sum_win += win[j];
				sum_draw += draw[j];
				sum_lose += lose[j];
			}
			if(sum_win + sum_draw + sum_lose != 30) 
				flag = false;
			else
				dfs(0);
//			System.out.println(flag);
			System.out.print(flag ? 1 + " " : 0 + " ");
		}
	}

	private static void dfs(int idx) {
		if (flag)
			return;
		if (idx == 15) {
//			System.out.println("idx == 15");
			flag = true;
			return;
		}
		int player1 = vs.get(idx)[0];
		int player2 = vs.get(idx)[1];
//		System.out.println(player1 + " " + player2);
		// 앞에가 이길 때
		if (win[player1] > 0 && lose[player2] > 0) {
			win[player1]--;
			lose[player2]--;
			dfs(idx + 1);
			win[player1]++;
			lose[player2]++;
		}

		// 비길 때
		if (draw[player1] > 0 && draw[player2] > 0) {
			draw[player1]--;
			draw[player2]--;
			dfs(idx + 1);
			draw[player1]++;
			draw[player2]++;
		}

		// 앞에가 질 떄
		if (lose[player1] > 0 && win[player2] > 0) {
			lose[player1]--;
			win[player2]--;
			dfs(idx + 1);
			lose[player1]++;
			win[player2]++;
		}

	}
}
