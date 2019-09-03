import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
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
//		Collections.sort(sadae);
//		Collections.sort(animals, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[0] + o1[1] - o2[0] + o2[1];
//			}
//		});

		int ans = 0;
		for (int i = 0; i < sadae.size(); i++) {
			for (int j = 0; j < animals.size(); j++) {
				int x = animals.get(j)[0];
				int y = animals.get(j)[1];

				if (Math.abs(sadae.get(i) - x) + y <= l) {
					animals.remove(j);
					j--;
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

}