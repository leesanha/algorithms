import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3124_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			ArrayList<int[]> list = new ArrayList<>();
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()) };

				list.add(temp);
			}

			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2] - o2[2];
				}
			});

			long ans = 0;
			parent = new int[v + 1];
			for (int i = 0; i <= v; i++)
				parent[i] = i;
			int cnt = 0;
			for (int i = 0; i < list.size(); i++) {
				int a = list.get(i)[0];
				int b = list.get(i)[1];
				int dis = list.get(i)[2];

				if (find(a) != find(b)) {
					union(a, b);
					ans += dis;
					cnt++;
				}
				if (cnt == v - 1)
					break;
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		parent[y] = x;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		int res = find(parent[a]);
		parent[a] = res;
		return res;
	}
}