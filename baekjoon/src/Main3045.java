import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3045 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		LinkedList<String> list = new LinkedList();
		Queue<String> q = new LinkedList();
		Queue<Prin> buf = new LinkedList();

		list.add("0");
		for (int i = 1; i <= n; i++) {
			list.add(i + "");
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String option = st.nextToken();
			String from = st.nextToken();
			String to = st.nextToken();
			q.offer(from);
			list.remove(from);
			if (option.equals("A")) {
				list.add(list.indexOf(to), from);
			} else {
				list.add(list.indexOf(to) + 1, from);
			}
		}

		int ans = 0;
		while (!q.isEmpty()) {
			String target = q.poll();
			if (Integer.parseInt(target) != list.indexOf(target)) {
				list.add(Integer.parseInt(target), target);
				if (Integer.parseInt(target) == list.size()) {
					buf.offer(new Prin('B', target, list.get(list.size() - 1)));
				} else {
					buf.offer(new Prin('A',list.get(Integer.parseInt(target) + 1), target));
				}
				ans++;
			}
		}
		System.out.println(ans);
		while (!buf.isEmpty()) {
			Prin p = buf.poll();
			System.out.format("%c %s %s", p.option, p.from, p.to);
		}
	}

	static class Prin {
		char option;
		String from;
		String to;

		public Prin(char option, String from, String to) {
			super();
			this.option = option;
			this.from = from;
			this.to = to;
		}

	}
}
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] prev = new int[n + 1];
		int[] next = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			prev[i] = i - 1;
			next[i] = i + 1;
		}

		Queue<Integer> q = new LinkedList();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char option = st.nextToken().charAt(0);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			q.offer(from);
			prev[next[from]] = prev[from];
			next[prev[from]] = next[from];
			if (option == 'A') {
				prev[from] = prev[to];
				next[from] = to;
			} else {
				prev[from] = to;
				next[from] = next[to];
			}
		}

		int ans = 0;
		Queue<Buf> buf = new LinkedList<Buf>();
		while (!q.isEmpty()) {
			int target = q.poll();
			if (prev[target] != target - 1 || next[target] != target + 1) {
				ans++;
				for (int i = 1; i <= n; i++) {
					if (prev[i] == target - 1) {
						buf.offer(new Buf('A', target, i));
						next[prev[target]] = next[target];
						prev[next[target]] = prev[target];
						prev[target] = target - 1;
						next[target] = target + 1;
					}
				}

			}
		}
		System.out.println(ans);
		while (!buf.isEmpty()) {
			Buf b = buf.poll();
			System.out.format("%c %d %d", b.option, b.from, b.to);
		}
	}

	static class Buf {
		char option;
		int from;
		int to;

		public Buf(char option, int from, int to) {
			super();
			this.option = option;
			this.from = from;
			this.to = to;
		}

	}
}
*/

