import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1863_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;
		int ans = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
//			if (find(a) != find(b))
				union(parent, a, b);
		}

		for (int i = 1; i <= n; i++) {
			if (parent[i] == i) 
				ans++;
		}
		System.out.println(ans);
	}

	private static void union(int[] parent, int x, int y) {
//		System.out.println(x + " " + y);
		int a = find(parent, x);
		int b = find(parent, y);

		if(a != b)
			parent[b] = a;
	}

	private static int find(int[] parent, int x) {
//		System.out.println(x);
		if (parent[x] == x)
			return x;
		int ret = find(parent, parent[x]);
		parent[x] = ret;
		return ret;
	}
}
