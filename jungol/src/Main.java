import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine().trim());

		ArrayList<int[]> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {

			int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			list.add(temp);
		}

		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0] < o2[0]) ? -1 : 1;
			}
		});

		for (int[] ar : list)
			System.out.println(Arrays.toString(ar));

		int preS = list.get(0)[0];
		int preL = list.get(0)[1];

		int ans = 1;
		for (int i = 1; i < n; i++) {
			if (list.get(i)[0] < preS || list.get(i)[1] < preL)
				ans++;
			preS = list.get(i)[0];
			preL = list.get(i)[1];
		}
		System.out.println(ans);
	}
}
