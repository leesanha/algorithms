import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st;

			int n = Integer.parseInt(br.readLine().trim());

//			Deque<Character> dq = new LinkedList<Character>();
			char[] input = new char[n];
			String ans = "";
			for (int i = 0; i < n; i++) {
//				dq.add(br.readLine().trim().toCharArray()[0]);
				input[i] = br.readLine().trim().toCharArray()[0];
			}
//			for (int i = 0; i < n; i++) {
//				char front = dq.getFirst();
//				char end = dq.getLast();
//
//				if (front < end) {
//					ans += front;
//					dq.pollFirst();
//				} else if (front > end) {
//					ans += end;
//					dq.pollLast();
//				} else {
//
//				}
//			}
			int front = 0, end = n - 1;
			Queue<Character> frontQ = new LinkedList<Character>();
			Queue<Character> endQ = new LinkedList<Character>();
			while (true) {

				if(!frontQ.isEmpty() && frontQ.peek() ) {
					
				}else if() {
				
				}else if (input[front] > input[end]) {

				} else if (input[front] < input[end]) {

				} else {
					frontQ.add(input[front]);
					front++;
					endQ.add(input[end]);
					end--;
				}

				if (front == end)
					break;
			}
			System.out.format("#%d %s\n", t, ans);
		}
	}
}