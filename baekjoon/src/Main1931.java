import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main1931 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine().trim());

		StringTokenizer st;

		ArrayList<Pair> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			list.add(new Pair(start, end));
		}

		Collections.sort(list);

		for (Pair p : list) {
			System.out.println(p.s + " " + p.e);
		}

		int ans = 0;
		int prevEnd = -1;
		for (int i = 0; i < size; i++) {
			int start = list.get(i).s;
			int end = list.get(i).e;
			if (start < prevEnd)
				continue;
			prevEnd = end;
			ans++;
		}
		System.out.println(ans);
	}

	static class Pair implements Comparable<Pair> {
		int s;
		int e;

		public Pair(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.e == o.e){
				return this.s - o.s;
			}
			return this.e - o.e;
		}

	}
}
