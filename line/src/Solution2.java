import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2 {
	static int cnt, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> list = new ArrayList<Integer>();
		while (st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		k = Integer.parseInt(br.readLine());

		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);

		Arrays.sort(arr);

		int[] out = new int[list.size()];
		boolean[] visited = new boolean[list.size()];
		cnt = 0;
		permutation(arr, out, visited, 0, arr.length, arr.length);
	}

	static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
		if (depth == r) {
			cnt++;
			if(cnt == k) {
				String result = "";
				for (int i = 0; i < output.length; i++)
					result += output[i];
				System.out.println(result);
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, visited, depth + 1, n, r);
				output[depth] = 0; // 이 줄은 없어도 됨
				visited[i] = false;
				;
			}
		}
	}

}