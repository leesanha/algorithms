import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1244_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long ans;
	static int limit;
	static char[] num;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			String[] line = br.readLine().split(" ");
			num = line[0].toCharArray();
			limit = Integer.parseInt(line[1]);
			ans = 0;
			check = new boolean[1000000];

			dfs(0, 0);
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static void dfs(int idx, int cnt) {
		int temp = Integer.parseInt(String.valueOf(num));
//		System.out.println(temp + " " + cnt);
		if (limit == cnt) {
//			System.out.println(temp);
			if (temp > ans) {
				ans = temp;
			}
			return;
		}
		if(check[temp])return;
		check[temp] = true;

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if(i != j) {
					swap(i, j);
					dfs(i, cnt + 1);
					swap(i, j);
					
				}
			}
		}
	}

	static void swap(int i, int j) {
		char temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}

}
