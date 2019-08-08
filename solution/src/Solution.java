import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans;
	static Queue<String> q;
	static boolean check[];
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			String[] line = br.readLine().split(" ");
			char[] num = line[0].toCharArray();
			cnt = Integer.parseInt(line[1]);
			ans = 0;
			q = new LinkedList<String>();
			check = new boolean[1000000];

			dfs(num, 0, 0);
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void dfs(char[] num, int x, int idx) {
		int temp = Integer.parseInt(String.valueOf(num));
		System.out.println(new String(num) + " " + x);
		if (cnt == idx) {
//			System.out.println(new String(num) + " " + x);
			if (temp > ans) {
				ans = temp;
			}
			return;
		}

		for (int i = 0; i < num.length; i++) {
			if (i == x)
				continue;
			if (x >= num.length) {
				x = 0;
			}
			swap(num, x, i);
			/*if (!check[temp]) {
				dfs(num, x + 1, idx + 1);
				check[temp] = true;
			} else {
				dfs(num, x + 1, idx);
			}*/
			dfs(num, x + 1, idx + 1);
			swap(num, x, i);
		}
	}

	static void swap(char[] num, int i, int depth) {
		char temp = num[i];
		num[i] = num[depth];
		num[depth] = temp;
	}

}
