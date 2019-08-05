import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution1225 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
//		int tc = sc.nextInt();

		for (int t = 1; t <= 10; t++) {
			int tc = sc.nextInt();

			Queue<Integer> q = new LinkedList<Integer>();

			for (int i = 0; i < 8; i++)
				q.offer(sc.nextInt());

			int cnt = 0;
			while (true) {
				/*for(int n : q) {
					System.out.print(n + " ");
				}
				System.out.println();*/
				int first = q.poll();
				if (first - (cnt % 5 + 1) < 1) {
					first = 0;
					q.add(first);
					break;
				}
				q.offer(first - (cnt % 5 + 1));
				cnt++;
			}
			System.out.format("#%d ", tc);
			while (!q.isEmpty()) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
	}
}