import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1240 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			LinkedList<String> slist = new LinkedList<String>();

			boolean flag = false;
			int ans = 0;
			for (int i = 0; i < n; i++) {
				char[] input = br.readLine().toCharArray();
				char temp = input[0];
				boolean f = false;
				for (int j = 1; j < input.length; j++) {
					if (temp != input[j]) {
						f = true;
						break;
					}
				}
				if (!f)
					continue;

				for (int j = 0; j < m - 56; j++) {
					String str = "";
					int cnt = 0;
					if (j + 56 > m)
						break;
					for (int k = j; k < j + 56; k++) {
						str += input[k];
						cnt++;
						if (cnt % 7 == 0) {
							slist.add(str);
							str = "";
						}
					}
					int ret = check(slist);
					if (ret == -1) {
						slist.clear();
						continue;
					} else {
						ans = ret;
						flag = true;
						break;
					}
				}
				if (flag) {
					for (int j = i + 1; j < n; j++) {
						br.readLine();
					}
					break;
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
	}

	static int check(LinkedList<String> slist) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		for (String str : slist) {
			switch (str) {
			case "0001101":
				list.add(0);
				break;
			case "0011001":
				list.add(1);
				break;
			case "0010011":
				list.add(2);
				break;
			case "0111101":
				list.add(3);
				break;
			case "0100011":
				list.add(4);
				break;
			case "0110001":
				list.add(5);
				break;
			case "0101111":
				list.add(6);
				break;
			case "0111011":
				list.add(7);
				break;
			case "0110111":
				list.add(8);
				break;
			case "0001011":
				list.add(9);
				break;
			default:
				list.add(-1);
			}
		}
		for (int i : list) {
			if (i == -1)
				return -1;
		}
		int sum = 0;
		int ret = 0;
		for (int i = 0; i < list.size(); i++) {
			ret += list.get(i);
			if (i % 2 != 0) {
				sum += list.get(i);
			}
		}
		sum *= 3;
		for (int i = 0; i < list.size() - 1; i++) {
			if (i % 2 == 0) {
				sum += list.get(i);
			}
		}

		if ((sum + list.get(list.size() - 1)) % 10 == 0)
			return ret;
		else
			return 0;
	}

}
