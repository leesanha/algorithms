import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2247 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[] temp = { a, b };
			list.add(temp);
		}

		Collections.sort(list, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				if (o1[0] > o2[0])
					return 1;
				else if (o1[0] < o2[0])
					return -1;
				else {
					if (o1[1] > o2[1])
						return 1;
					else if (o1[1] < o2[1])
						return -1;
					else
						return 0;
				}
			}
		});

		int start = list.get(0)[0];
		int end = list.get(0)[1];
		
		int exist = 0;
		int empty = 0;

		for (int i = 1; i < list.size(); i++) {
			if (end >= list.get(i)[0]) {
				if (list.get(i)[1] > end)
					end = list.get(i)[1];
			} else {
				if(exist < end - start)
					exist = end - start;
				if(empty < list.get(i)[0] - end)
					empty = list.get(i)[0] - end;
				start = list.get(i)[0];
				end = list.get(i)[1];
			}
		}
		if(exist < end - start)
			exist = end - start;
		System.out.println(exist + " " + empty);
	}

}