import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main8983 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		ArrayList<Integer> sadae = new ArrayList<>();
		ArrayList<int[]> animals = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++)
			sadae.add(Integer.parseInt(st.nextToken()));
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y > l)
				continue;
			int[] temp = { x, y };
			animals.add(temp);
		}
		Collections.sort(sadae);
		Collections.sort(animals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int sadaeCur = 0;
		for (int i = 0; i < animals.size(); i++) {
			while (sadaeCur != m - 1 && sadae.get(sadaeCur + 1) < animals.get(i)[0])
				sadaeCur++;
			if (cal(animals.get(i)[0], animals.get(i)[1], sadae.get(sadaeCur)) <= l) {
				ans++;
				continue;
			}
			if (sadaeCur != m - 1) {
				if (cal(animals.get(i)[0], animals.get(i)[1], sadae.get(sadaeCur + 1)) <= l) 
					ans++;
			}
		}

		System.out.println(ans);
	}

	private static int cal(int x, int y, int xx) {
		return Math.abs(x - xx) + y;
	}

}