import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5672 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st;

			int n = Integer.parseInt(br.readLine().trim());

			LinkedList<Character> q = new LinkedList<Character>();
			String ans = "";
			for (int i = 0; i < n; i++)
				q.add(br.readLine().trim().toCharArray()[0]);

			int front = 0, end = n - 1;
			while (!q.isEmpty()) {

				if (q.get(front) != q.get(end)) {
					if (q.get(front) > q.get(end)) {
						ans += q.get(q.size() - 1);
						q.remove(q.size() - 1);
					} else {
						ans += q.get(0);
						q.remove(q.get(0));
					}
					front = 0;
					end = q.size() - 1;
				} else {
					front++;
					end--;
				}
				if (front > end)
					break;
			}
			if (q.size() == 1) {
				ans += q.get(0);
			} else if (q.size() == 2) {
				ans += (q.get(0) < q.get(1)) ? q.get(0) : q.get(1);
			} else {
				front--;
				end++;
				for (int i = 0; i < front; i++) {
					ans += q.get(i);
				}
				for (int i = q.size() - 1; i > end; i--) {
					ans += q.get(i);
				}
				ans += q.get(front);
				if (q.size() % 2 == 0) 
					ans += q.get(end);
			}
			System.out.format("#%d %s\n", t, ans);
		}
	}
}