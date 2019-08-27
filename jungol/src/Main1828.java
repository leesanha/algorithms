import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			list.add(temp);
		}
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] > o2[1])
					return 1;
				else if (o1[1] < o2[1])
					return -1;
				else {
					if (o1[0] < o2[0])
						return 1;
					else if (o1[0] > o2[0])
						return -1;
					else
						return 0;
				}
			}
		});
		int ans = 1;
		int before = list.get(0)[1];
		for (int i = 1; i < list.size(); i++) {
			int[] arr = list.get(i);
			if (before < arr[0]) {
				ans++;
				before = arr[1];
			}
		}
		System.out.println(ans);
	}

}
