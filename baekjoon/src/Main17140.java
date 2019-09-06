import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main17140 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer>temp = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			list.add(temp);
		}
		ans = 0;
		while (true) {
			if (list.size() > r && list.get(0).size() > c && list.get(r).get(c) == k)
				break;
			if (list.get(0).size() > list.size()) {
				ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
				// 방향을 바꾼다.
				for (int i = 0; i < list.get(0).size(); i++) {
					ArrayList<Integer> co = new ArrayList<>();
					for (int j = 0; j < list.size(); j++) {
						co.add(list.get(j).get(i));
					}
					list2.add(co);
				}
				solve(list2);
				list.clear();
				for (int i = 0; i < list2.get(0).size(); i++) {
					ArrayList<Integer> co = new ArrayList<>();
					for (int j = 0; j < list2.size(); j++) {
						co.add(list2.get(j).get(i));
					}
					list.add(co);
				}
			} else
				solve(list);
//			for (int i = 0; i < list.size(); i++) {
//				for (int j = 0; j < list.get(0).size(); j++) {
//					System.out.print(list.get(i).get(j));
//				}
//				System.out.println();
//			}
			
			ans++;
			if (ans > 100) {
				ans = -1;
				break;
			}
		}
		System.out.println(ans);
	}

	private static void solve(ArrayList<ArrayList<Integer>> list) {
//		for (int i = 0; i < list.get(0).size(); i++) {
//			for (int j = 0; j < list.size(); j++) {
//				System.out.print(list.get(i).get(j));
//			}
//			System.out.println();
//		}
		for (int i = 0; i < list.size(); i++) {
			Collections.sort(list.get(i));
//			for(int j : list.get(i))
//				System.out.print(j + " ");
//			System.out.println();
			int cur = 0;
			int cnt = 0;
			ArrayList<int[]> al = new ArrayList<>();
			for (int j = 0; j < list.get(i).size(); j++) {
				if (list.get(i).get(j) == 0)// 0은 뺀다.
					continue;
				if(cur == 0)// 0이 아니면 숫자 세기 시작
					cur = list.get(i).get(j);
				if (cur == list.get(i).get(j))// 같은 숫자가 몇 개 인지 센다.
					cnt++;
				else {// 숫자가 달라지면 이전꺼 저장한다.
					int[] temp = { cur, cnt };
					al.add(temp);
					cur = list.get(i).get(j);
					cnt = 1;
				}
			}
			int[] temp = {cur, cnt};
			al.add(temp);
			// 등장 횟수가 적은 순으로 정렬한다.
			Collections.sort(al, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] > o2[1])
						return 1;
					else if (o1[1] < o2[1])
						return -1;
					else {// 등장 횟수가 같으면 숫자 오름차순으로
						if (o1[0] > o2[0])
							return 1;
						else if (o1[0] < o2[0])
							return -1;
						else
							return 0;
					}
				}
			});
			// 저장한거 다 끝나면 다시 배열 생성한다.
			ArrayList<Integer> toAdd = new ArrayList<>();
			// 다시 넣는다.
			for (int j = 0; j < al.size(); j++) {
				toAdd.add(al.get(j)[0]);
				toAdd.add(al.get(j)[1]);
			}
			// 갱신
			list.remove(i);
			list.add(i, toAdd);
		}
		// 길이가 가장 긴 행을 찾는다.
		int mcol = 0;
		for (int i = 0; i < list.size(); i++) {
			if (mcol < list.get(i).size())
				mcol = list.get(i).size();
		}
		// 채워 넣는다.
		for (int i = 0; i < list.size(); i++) {
			if (mcol > list.get(i).size()) {
				int size = list.get(i).size();
				for (int j = 0; j < mcol - size; j++) {
					list.get(i).add(0);
				}
			}

		}
	}

}