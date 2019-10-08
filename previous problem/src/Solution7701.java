import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution7701 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(br.readLine());

			Set<String> hs = new HashSet<>();
			for (int i = 0; i < n; i++)
				hs.add(br.readLine());

			ArrayList<String> list = new ArrayList<>();

			Iterator<String> it = hs.iterator();
			while (it.hasNext()) {
				list.add(it.next());
			}

			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if (o1.length() > o2.length())
						return 1;
					else if (o1.length() < o2.length())
						return -1;
					else {
						if (o1.compareTo(o2) > 0)
							return 1;
						else if (o1.compareTo(o2) < 0)
							return -1;
						else
							return 0;
					}
				}
			});

			System.out.format("#%d\n", t);
			for (String s : list)
				System.out.println(s);
		}
	}
}