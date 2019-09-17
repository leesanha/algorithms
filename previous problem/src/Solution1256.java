import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1256 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			int k = Integer.parseInt(br.readLine());

			String input = br.readLine();
			String[] list = new String[input.length()];

			for (int i = 0; i < input.length(); i++)
				list[i] = input.substring(i);
			
			Arrays.sort(list);
			
			System.out.format("#%d %s\n", t, list[k - 1]);
		}
	}
}