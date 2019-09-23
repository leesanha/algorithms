import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			String[] input = new String[p];
			String[] output = new String[q];

			for (int i = 0; i < p; i++)
				input[i] = br.readLine();
			for (int i = 0; i < q; i++)
				output[i] = br.readLine();

			ArrayList<int[]> list = new ArrayList<>();
			for (int i = 1; i < p; i++) {
				int[] oper = getCnt(input, i);
				int j = 0;
				for (j = 0; j < input[i].length(); j++) {
					if (input[i].charAt(j) != '.')
						break;
				}
				int dot = j;

				if (i == 1) {
					for (int r = 1; r <= 20; r++) {
						for (int c = 1; c <= 20; c++) {
							for (int s = 1; s <= 20; s++) {
								if (r * oper[0] + c * oper[1] + s * oper[2] == dot) {
									int[] temp = { r, c, s };
									list.add(temp);
								}
							}
						}
					}
				} else {
					ArrayList<int[]> cp_list = new ArrayList<>();
					for (j = 0; j < list.size(); j++) {
						int ar = list.get(j)[0];
						int ac = list.get(j)[1];
						int as = list.get(j)[2];

						if (ar * oper[0] + ac * oper[1] + as * oper[2] == dot)
							cp_list.add(list.get(j));
					}
					list.clear();
					list.addAll(cp_list);
				}
			}
//			for(int[] tt : list)
//				System.out.println(Arrays.toString(tt));

			ArrayList<Integer> ans = null;
			if (list.size() == 1) {
				ans = print(output, list.get(0));
				ans.add(0, 0);

				System.out.print("#" + t + " ");
				for (int i = 0; i < ans.size(); i++) {
					System.out.print(ans.get(i) + " ");
				}
				System.out.println();
			} else {
				HashSet<Integer> rr = new HashSet<>();
				for (int i = 0; i < list.size(); i++)
					rr.add(list.get(i)[0]);
				HashSet<Integer> cc = new HashSet<>();
				for (int i = 0; i < list.size(); i++)
					cc.add(list.get(i)[1]);
				HashSet<Integer> ss = new HashSet<>();
				for (int i = 0; i < list.size(); i++)
					ss.add(list.get(i)[2]);
				
				System.out.print("#" + t + " " + "0 ");
				print2(output, rr, cc, ss);
				System.out.println();
			}

		}
	}

	private static void print2(String[] output, HashSet<Integer> rr, HashSet<Integer> cc, HashSet<Integer> ss) {
		for (int i = 1; i < output.length; i++) {
			int[] oper = getCnt(output, i);
			
			if(oper[0] != 0 && rr.size() != 1) {
				System.out.print("-1 ");
				return;
			}
			if(oper[1] != 0 && cc.size() != 1) {
				System.out.print("-1 ");
				return;
			}
			if(oper[2] != 0 && ss.size() != 1) {
				System.out.print("-1 ");
				return;
			}
			
			int sum = 0;
			if(rr.size() == 1) {
				int r = 0;
				for(Integer target : rr) {
					r = target;
				}
				sum += oper[0] * r;
			}
			if(cc.size() == 1) {
				int c = 0;
				for(Integer target : cc) {
					c = target;
				}
				sum += oper[1] * c;
			}
			if(ss.size() == 1) {
				int s = 0;
				for(Integer target : ss) {
					s = target;
				}
				sum += oper[0] * s;
			}
			System.out.print(sum + " ");
		}
	}

	private static ArrayList<Integer> print(String[] output, int[] list) {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 1; i < output.length; i++) {
			int[] oper = getCnt(output, i);
			ret.add(oper[0] * list[0] + oper[1] * list[1] + oper[2] * list[2]);
		}
		return ret;
	}

	private static int[] getCnt(String[] input, int limit) {
		int[] oper = new int[6];
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				switch (input[i].charAt(j)) {
				case '(':
					oper[0]++;
					break;
				case ')':
					oper[1]++;
					break;
				case '{':
					oper[2]++;
					break;
				case '}':
					oper[3]++;
					break;
				case '[':
					oper[4]++;
					break;
				case ']':
					oper[5]++;
					break;
				}
			}
		}

		int[] ret = { Math.abs(oper[0] - oper[1]), Math.abs(oper[2] - oper[3]), Math.abs(oper[4] - oper[5]) };
		return ret;
	}
}