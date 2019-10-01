import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static HashSet<String> set = new HashSet<>();
	static ArrayList<int[]> vs;

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

		int[][] table = new int[6][3];

		dfs(0, table);

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			String t = "";
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					t += st.nextToken();
				}
			}
			if (set.contains(t)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	private static void dfs(int idx, int[][] table) {
//		for (int i = 0; i < 6; i++) {
//			for (int j = 0; j < 3; j++) {
//				System.out.print(table[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		if (idx == 15) {
			String t = "";
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					t += table[i][j] + "";
				}
			}
			set.add(t);
			return;
		}
		int player1 = vs.get(idx)[0];
		int player2 = vs.get(idx)[1];
		// 앞에가 이길 때
		table[player1][0]++;
		table[player2][2]++;
		dfs(idx + 1, table);
		table[player1][0]--;
		table[player2][2]--;
		// 비길 때
		table[player1][1]++;
		table[player2][1]++;
		dfs(idx + 1, table);
		table[player1][1]--;
		table[player2][1]--;
		// 앞에가 질 떄
		table[player1][2]++;
		table[player2][0]++;
		dfs(idx + 1, table);
		table[player1][2]--;
		table[player2][0]--;
	}

}