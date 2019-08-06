import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution5550 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			char[] chs = br.readLine().toCharArray();
			char[] frog = { 'c', 'r', 'o', 'a' };
			Stack<Character> q1 = new Stack<Character>();
			Stack<Character> q2 = new Stack<Character>();

			int ans = 0;
			int cnt = 0;

			for (char ch : chs) {
				if (q1.isEmpty() && ch != 'c') {
					ans = -1;
					break;
				}
				else if (ch == 'c') {
					q1.push(ch);
					cnt++;
				} else if (ch == 'k') {
					int idx = 3;
					while (true) {
						if (q1.isEmpty()) {
							if (!q2.isEmpty()) {
								ans = -1;
							}
							break;
						}
						if (q1.peek() != frog[idx]) {
							q2.push(q1.pop());
						} else {
							q1.pop();
							idx--;
						}
						if (idx == -1) {
							while (!q2.empty()) {
								q1.push(q2.pop());
							}
							cnt--;
							break;
						}
					}
				} else {
					q1.push(ch);
				}
				if (ans != -1 && ans < cnt)
					ans = cnt;
			}
			System.out.format("#%d %d\n", t, ans = (q1.empty() && q2.empty()) ? ans : -1);
		}
	}

}
