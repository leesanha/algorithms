import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3289 {
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			System.out.format("#%d ", t);
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			parent = new int[n + 1];
			makeSet();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());

				int option = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				switch (option) {
				case 0:// 합집합
					unionSet(x, y);
					break;
				case 1:// find
					System.out.format("%d", (findSet(x) == findSet(y)) ? 1 : 0);
					break;
				}
			}
			System.out.println();
		}
	}

	private static int findSet(int x) {
		if(parent[x] == x)
			return x;
		int idx = findSet(parent[x]);
		parent[x] = idx;
		return idx;
	}

	private static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		if(x != y)
			parent[y] = x;
	}

	static void makeSet() {
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}

}
