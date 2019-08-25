import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14888 {
	static long[] arr;
	static int[] oper;
	static long max;
	static long min;
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new long[n];
		oper = new int[n - 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int size = Integer.parseInt(st.nextToken());
			while (size-- != 0) {
				oper[idx++] = i;
			}
		}
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;

		dfs(0);

		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int loc) {
		if (n - 1 == loc)
			return;

		for (int i = loc; i < n - 1; i++) {
			swap(i, loc);
			long res = cal();
			max = (max < res) ? res : max;
			min = (min > res) ? res : min;
			dfs(loc + 1);
			swap(i, loc);
		}
	}

	static void swap(int i, int loc) {
		int temp = oper[i];
		oper[i] = oper[loc];
		oper[loc] = temp;
	}

	static long cal() {
		long ret = arr[0];
		for (int i = 0; i < n - 1; i++) {
			if (oper[i] == 0)
				ret += arr[i + 1];
			if (oper[i] == 1)
				ret -= arr[i + 1];
			if (oper[i] == 2)
				ret *= arr[i + 1];
			if (oper[i] == 3)
				ret /= arr[i + 1];
		}
		return ret;
	}
}
