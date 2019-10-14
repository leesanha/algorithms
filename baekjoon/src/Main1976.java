import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Main1976 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (i > j) {
					if (num == 1) {
						if (find(i) != find(j))
							union(i, j);
					}
				}
			}
		}
		boolean flag = true;
		String[] trip = br.readLine().split(" ");
		int before = Integer.parseInt(trip[0]);
		for (int i = 1; i < trip.length; i++) {
			if (find(before) != find(Integer.parseInt(trip[i]))) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}

	private static void union(int i, int j) {
		int x = find(i);
		int y = find(j);
		parent[y] = x;
	}

	private static int find(int i) {
		if (parent[i] == i)
			return i;
		return parent[i] = find(parent[i]);
	}

}
