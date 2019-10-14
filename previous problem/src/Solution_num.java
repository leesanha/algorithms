import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_num {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int ans;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ans = 0;

			int num = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			comb(0, num);

			System.out.format("#%d %d\n", t, ans);
		}
	}

	private static void comb(int cnt, int num) {
		if (cnt > ans)
			ans = cnt;
		if (num == 0) {
			// 횟수 계산
			for (int n : list)
				System.out.print(n + " ");
			int mul = 1;
			for (int i = 0; i < list.size(); i++)
				mul *= list.get(i);
			if (mul >= 10)
				comb(cnt + 1, mul);
			return;
		}
		int len = 0;
		int temp = num;
		while (temp != 0) {
			temp /= 10;
			len++;
		}
		for (int i = 1; i < len; i++) {
			int num1 = num % (int) Math.pow(10, i);
			list.add(num1);
			int num2 = num / (int) Math.pow(10, i);
			comb(cnt, num2);
			list.remove(list.size() - 1);
		}
	}
}