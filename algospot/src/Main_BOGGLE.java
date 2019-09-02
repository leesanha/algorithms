import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOGGLE {
	static char[][] map;
	static String[] words;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			map = new char[5][5];

			for (int i = 0; i < 5; i++)
				map[i] = br.readLine().toCharArray();

			int n = Integer.parseInt(br.readLine());

			words = new String[n];
			first: for (int i = 0; i < n; i++) {
				words[i] = br.readLine();
				for (int x = 0; x < 5; x++) {
					for (int y = 0; y < 5; y++) {
						if (dfs(words[i], x, y)) {
							System.out.println(words[i] + " " + "YES");
							continue first;
						}
					}
				}
				System.out.println(words[i] + " " + "NO");
			}

		}
	}

	private static boolean dfs(String word, int row, int col) {
		if (word.length() == 1 && word.charAt(0) == map[row][col])
			return true;
		if (word.length() == 1)
			return false;
		if (word.charAt(0) == map[row][col]) {
			for (int i = 0; i < 8; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];

				if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5)
					continue;
				if (dfs(word.substring(1), nr, nc))
					return true;
			}
		}
		return false;
	}

}
