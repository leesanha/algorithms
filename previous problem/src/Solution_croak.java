import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_croak {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String input = br.readLine();

			HashMap<Character, Integer> hm = new HashMap<>();
			hm.put('c', 0);
			hm.put('r', 1);
			hm.put('o', 2);
			hm.put('a', 3);
			hm.put('k', 4);
			ArrayList<String> list = new ArrayList<String>();
			boolean flag1 = false;
			int max = 0;
			int cnt = 0;
			for (int i = 0; i < input.length(); i++) {
				char ch = input.charAt(i);
				flag1 = false;
				if (ch == 'c') {
					cnt++;
					list.add(ch + "");
				} else {
					int size = list.size();
					boolean flag = false;
					for (int j = 0; j < size; j++) {
						int len = list.get(j).length();
						if (hm.get(list.get(j).charAt(len - 1)) + 1 == hm.get(ch)) {// 이어지는 자리라면
							flag = true;
							String ss = list.get(j) + ch;
							list.remove(j);
							list.add(ss);
							break;
						}
					}
					if(ch == 'k' && flag) {
						cnt--;
					}
					if (!flag) {
						flag1 = true;
						break;
					}
				}
				if(cnt > max)
					max = cnt;
			}
			if (flag1)
				System.out.format("#%d %d\n", t, -1);
			else
				System.out.format("#%d %d\n", t, cnt);
		}
	}
}