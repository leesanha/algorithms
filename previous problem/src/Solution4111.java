import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Solution4111 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int camera, receiver, ans;
	static int[] arr;
	static ArrayList<Integer> res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			camera = Integer.parseInt(br.readLine());
			receiver = Integer.parseInt(br.readLine());

			String[] input = br.readLine().split(" ");

			arr = new int[camera];
			for (int i = 0; i < camera; i++) {
				int num = Integer.parseInt(input[i]);
				arr[i] = num;
			}

			Arrays.sort(arr);

			res = new ArrayList<Integer>();
			for (int i = 0; i < arr.length - 1; i++) {
				res.add(Math.abs(arr[i] - arr[i + 1]));
			}

			Collections.sort(res, Collections.reverseOrder());
			ans = 0;

			for (int i = receiver - 1; i < res.size(); i++) {
				ans += res.get(i);
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

}
