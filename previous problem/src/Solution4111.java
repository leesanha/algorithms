import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Solution4111 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int camera, receiver, ans;
	static LinkedList<Integer> list;
	static LinkedList<Integer> res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			camera = Integer.parseInt(br.readLine());
			receiver = Integer.parseInt(br.readLine());

			String[] input = br.readLine().split(" ");

			list = new LinkedList<Integer>();
			for (int i = 0; i < camera; i++) {
				int num = Integer.parseInt(input[i]);
				if (!list.contains(num)) {
					list.add(num);
				}
			}
			Collections.sort(list);

			res = new LinkedList<Integer>();
			for (int i = list.size() - 1; i >= 1; i--) {
				res.add(list.get(i) - list.get(i - 1));
			}
			
			

			System.out.format("#%d %d\n", t, ans);
		}
	}

}
