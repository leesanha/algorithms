import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1370 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st;

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) };
			list.add(temp);
		}
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[2] > arg1[2]) {
					return 1;
				} else if (arg0[2] < arg1[2]) {
					return -1;
				} else {
					if (arg0[1] > arg1[1]) {
						return 1;
					} else if (arg0[1] < arg1[1]) {
						return -1;
					} else
						return 0;
				}
			}
		});
			
		ArrayList<Integer> res = new ArrayList<>();
		int ans = 1;
		int before = list.get(0)[2];
		res.add(list.get(0)[0]);
		for (int i = 1; i < list.size(); i++) {
			if (before <= list.get(i)[1]) {
				ans++;
				before = list.get(i)[2];
				res.add(list.get(i)[0]);
			}
		}
		System.out.println(ans);
		for(int i : res)
			System.out.print(i + " ");
	}

}
