import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BinPack {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		ArrayList<Node> list = new ArrayList<>();
		StringTokenizer st;
		String tempinput;
		for (int i = 0; i < 66; i++) {
			st = new StringTokenizer(br.readLine());
			tempinput = st.nextToken().replace(",", "");// 컴마 빼준다.
			double len = Double.parseDouble(tempinput);
			int cnt = Integer.parseInt(st.nextToken());

			list.add(new Node(len, cnt));
		}

		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.len > o2.len)
					return -1;
				else if (o1.len < o2.len)
					return 1;
				else
					return 0;
			}
		});// 내림차순 정렬

//		for (Node n : list) {
//			System.out.println(n.len + " " + n.cnt);
//		}

		ArrayList<Double>[] bins = new ArrayList[3552];// 통
		bins[0] = new ArrayList<>();// 첫 번째 통은 먼저 만들어 준다.
		bins[0].add((double) 10000);
		int B = 0;// 통 개수
		double size = 10000;

		for (int i = 0; i < list.size(); i++) {// 리스트 순차적으로 가면서 탐색
			for (int j = 0; j < list.get(i).cnt; j++) {// 각각 마디 마다 몇개가 있는지
				boolean flag = false;// 새로 통을 추가해야하는가
				for (int k = 0; k <= B; k++) {// 쓰레기통 첨부터 탐색
					if (bins[k].get(0) >= list.get(i).len) {// 해당 통에 들어갈 수 있으면 넣는다.
						double temp = bins[k].get(0) - list.get(i).len;
						bins[k].remove(0);
						bins[k].add(0, temp);
						bins[k].add(list.get(i).len);
						list.get(i).cnt--;
						flag = true;
						break;// 통에 넣었으면 통 탐색을 그만둔다.
					}
				}
				if (!flag) {// 새로운 통을 추가해야한다면
					B++;
					bins[B] = new ArrayList<>();// 통 만들고
					bins[B].add((double) 10000);// 기준값 넣고
					double temp = bins[B].get(0) - list.get(i).len;// 데이터 추가
					bins[B].remove(0);
					bins[B].add(0, temp);
					bins[B].add(list.get(i).len);
					list.get(i).cnt--;
				}
			}
		}

		System.out.println("통 총 갯수: " + B);
		for (int i = 0; i < bins.length; i++) {
			if (bins[i] == null)
				break;
			System.out.print("남은 길이: " + bins[i].get(0) + ", ");

			for (int j = 1; j < bins[i].size(); j++) // j==0은 남은 길이
			{
				System.out.print(bins[i].get(j) + " ");
			}
			System.out.println();
		}
	}

	static class Node {
		double len;
		int cnt;

		public Node(double len, int cnt) {
			super();
			this.len = len;
			this.cnt = cnt;
		}

	}
}
