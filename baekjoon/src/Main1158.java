import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<Integer>();
		LinkedList<Integer> ans = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++)
			q.offer(i);

		while (!q.isEmpty()) {

			for (int i = 0; i < m - 1; i++) {
				if (q.isEmpty())
					break;
				q.offer(q.poll());
			}
			ans.add(q.poll());
		}

		System.out.print("<");
		for (int i = 0; i < ans.size(); i++) {
			if(i == ans.size() - 1)
				System.out.print(ans.get(i));
			else System.out.print(ans.get(i) + ", ");
		}
		System.out.print(">");
	}

}
