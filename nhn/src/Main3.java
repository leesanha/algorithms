import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main3 {
	static ArrayList<Integer>[] follower;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		int[] candies = new int[n];
		follower = new ArrayList[n];
		for (int i = 0; i < n; i++)
			follower[i] = new ArrayList<Integer>();

		int order = 0;
		for (int i = 0; i < input.length; i++) {
			boolean[] check = new boolean[n];// follower 체크
			switch (input[i]) {
			case "A":
				candies[order]++;
				candy_check(check, order, candies);
				break;
			case "J":
				if (order == 0) {
					candies[n - 1]++;
					candies[1]++;
					candy_check(check, n - 1, candies);
					candy_check(check, 1, candies);
				} else if (order == n - 1) {
					candies[n - 2]++;
					candies[0]++;
					candy_check(check, n - 2, candies);
					candy_check(check, 0, candies);
				} else {
					candies[order - 1]++;
					candies[order + 1]++;
					candy_check(check, order - 1, candies);
					candy_check(check, order + 1, candies);
				}
				break;
			case "Q":
				for (int j = 0; j < n; j++)
					candies[j]++;
				break;
			case "K":
				int target = Integer.parseInt(input[i + 1]);
				follower[order].add(Integer.parseInt(input[i + 1]));// 팔로워 추가
				i++;
				break;
			}
			order++;
			order = order % n;
		}
		for (int i = 0; i < n; i++)
			System.out.print(candies[i] + " ");
	}

	private static void candy_check(boolean[] check, int order, int[] candies) {
		for (int next : follower[order]) {
			if (!check[next]) {
				check[next] = true;
				candies[next]++;
				candy_check(check, next, candies);
			}
		}
	}

}