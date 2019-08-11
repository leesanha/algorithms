import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int camera, receiver, ans;
	static LinkedList<Integer> list;
	static PriorityQueue<Integer> q;

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

			q = new PriorityQueue<Integer>();
			for (int i = 0; i < list.size() - 1; i++) {
				q.add(Math.abs(list.get(i) - list.get(i + 1)));
			}

			/*
			 * for(int i : list) { System.out.print(i + " "); } System.out.println();
			 * for(int i : res) { System.out.print(i + " "); } System.out.println();
			 */
			ans = 0;
			for (int i = 0; i <= receiver - 1; i++) {
				q.poll();
			}
			while (!q.isEmpty()) {
				ans += q.poll();
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

}
