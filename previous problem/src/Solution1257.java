import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1257 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			int k = Integer.parseInt(br.readLine());
			String input = br.readLine();
			Set<String> set = new HashSet<String>();

			int len = input.length();
			while (len >= 0) {
				for (int i = 0; i + len <= input.length(); i++) {
					String temp = input.substring(i, i + len);
					set.add(temp);
				}
				len--;
			}
			String[] list = new String[set.size()];
			set.toArray(list);
			Arrays.sort(list);
			
			System.out.format("#%d %s\n", t, list[k]);
		}
	}
}