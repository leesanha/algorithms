import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		String type = st.nextToken();

		int[] char_size = new int[n];
		ArrayList<Integer>[] list = new ArrayList[n];
		for (int i = 0; i < n; i++)
			list[i] = new ArrayList<Integer>();

		int max_size = -1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			char_size[i] = Integer.parseInt(st.nextToken());
			if (max_size < char_size[i])
				max_size = char_size[i];
			String temp = st.nextToken();
			for (int j = 0; j < temp.length(); j++) {
				list[i].add(Integer.parseInt(temp.charAt(j) + ""));
			}
		}

		for (int i = 0; i < 2 * max_size - 1; i++) {
			for (int j = 0; j < n; j++) {
				if (type.equals("TOP")) {
					if (i == 0) {
						for (int k = 0; k < list[j].size(); k++) {
							switch (list[j].get(k)) {
							case 0:
							case 2:
							case 3:
							case 5:
							case 7:
							case 8:
							case 9:
								all(char_size[j]);
								break;
							case 1:
								right(char_size[j]);
								break;
							case 4:
								side(char_size[j]);
								break;
							case 6:
								left(char_size[j]);
							}
						}
					} else if (i < (2 * max_size - 1) / 2) {
						
					} else if (i == (2 * max_size - 1) / 2) {

					} else if (i == (2 * max_size - 1) - 1) {

					} else {

					}
				} else if (type.equals("BOTTOM")) {

				} else {

				}
			}
		}

	}

	static void left(int n) {
		String ret = "#";
		for (int i = 0; i < n - 1; i++)
			ret += ".";
		System.out.println(ret);
	}

	static void right(int n) {
		String ret = "";
		for (int i = 0; i < n - 1; i++)
			ret += ".";
		ret += "#";
		System.out.println(ret);
	}

	static void all(int n) {
		String ret = "";
		for (int i = 0; i < n; i++)
			ret += "#";
	}

	static void side(int n) {
		String ret = "#";
		for (int i = 0; i < n - 2; i++)
			ret += ".";
		ret += "#";
		System.out.println(ret);
	}
}
