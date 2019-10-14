import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Main1717 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int opt = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (opt == 0) {
				if (find(a) != find(b))
					union(a, b);
			} else {
				if (find(a) == find(b))
					System.out.println("yes");
				else
					System.out.println("no");
			}
		}
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		parent[x] = y;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		int ret = find(parent[a]);
		parent[a] = ret;
		return ret;
	}

}
