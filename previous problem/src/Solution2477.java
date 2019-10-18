import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution2477 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int[] a_box = new int[n];
			boolean[] a_check = new boolean[n];
			int[] b_box = new int[m];
			boolean[] b_check = new boolean[m];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				a_box[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++)
				b_box[i] = Integer.parseInt(st.nextToken());

			ArrayList<Node> customers = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; i++)
				customers.add(new Node(i, 0, 0, 0, Integer.parseInt(st.nextToken())));

			ArrayList<Node> repairs = new ArrayList<>();
			ArrayList<Node> recepts = new ArrayList<>();
			ArrayList<Node> ready = new ArrayList<>();
			ArrayList<Node> ans = new ArrayList<>();

			int time = customers.get(0).getArrive();
			while (true) {
				for (int i = 0; i < customers.size(); i++) {
					if (customers.get(i).getArrive() <= time) {
						int recFree = -1;
						for (int j = 0; j < n; j++) {
							if (!a_check[j]) {
								recFree = j;
								break;
							}
						}
						if (recFree != -1) {
							a_check[recFree] = true;
							customers.get(i).setRest(a_box[recFree]);
							customers.get(i).setRec(recFree);
							recepts.add(customers.get(i));
							customers.remove(i);
							i--;
						}
					}
				}
				for (int i = 0; i < recepts.size(); i++) {
					if (recepts.get(i).getRest() <= 0) {
						a_check[recepts.get(i).rec] = false;
						recepts.get(i).setArrive(time);
						ready.add(recepts.get(i));
						recepts.remove(i);
						i--;
					}
				}
				Collections.sort(ready, new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						if (o1.getArrive() > o2.getArrive())
							return 1;
						else if (o1.getArrive() < o2.getArrive())
							return -1;
						else {
							if (o1.rec > o2.rec)
								return 1;
							else if (o1.rec < o2.rec)
								return -1;
							else
								return 0;
						}
					}
				});
//				for (Node nn : ready)
//					System.out.println(nn);
				for (int i = 0; i < ready.size(); i++) {
					int repFree = -1;
					for (int j = 0; j < m; j++) {
						if (!b_check[j]) {
							repFree = j;
							break;
						}
					}
					if (repFree != -1) {
						ready.get(i).setRest(b_box[repFree]);
						b_check[repFree] = true;
						ready.get(i).setRep(repFree);
						repairs.add(ready.get(i));
						ready.remove(i);
						i--;
					}
				}
				for (int i = 0; i < repairs.size(); i++) {
					if (repairs.get(i).getRest() <= 0) {
						b_check[repairs.get(i).rep] = false;
						ans.add(repairs.get(i));
						repairs.remove(i);
						i--;
					}
				}
				for (int i = 0; i < recepts.size(); i++)
					recepts.get(i).setRest(recepts.get(i).getRest() - 1);
				for (int i = 0; i < repairs.size(); i++)
					repairs.get(i).setRest(repairs.get(i).getRest() - 1);
				for (int i = 0; i < recepts.size(); i++) {
					if (recepts.get(i).getRest() <= 0) {
						a_check[recepts.get(i).rec] = false;
						recepts.get(i).setArrive(time);
						ready.add(recepts.get(i));
						recepts.remove(i);
						i--;
					}
				}
				for (int i = 0; i < repairs.size(); i++) {
					if (repairs.get(i).getRest() <= 0) {
						b_check[repairs.get(i).rep] = false;
						ans.add(repairs.get(i));
						repairs.remove(i);
						i--;
					}
				}

				if (ans.size() == k)
					break;
				time++;
			}

			int cnt = 0;
			for (int i = 0; i < ans.size(); i++) {
//				System.out.println(ans.get(i));
				if (ans.get(i).getRec() + 1 == a && ans.get(i).getRep() + 1 == b)
					cnt += ans.get(i).num + 1;
			}

			System.out.format("#%d %d\n", t, cnt = (cnt == 0) ? -1 : cnt);
		}
	}

	static class Node {
		int num;
		int rec;
		int rep;
		int rest;
		int arrive;

		public Node(int num, int rec, int rep, int rest, int arrive) {
			super();
			this.num = num;
			this.rec = rec;
			this.rep = rep;
			this.rest = rest;
			this.arrive = arrive;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public int getRec() {
			return rec;
		}

		public void setRec(int rec) {
			this.rec = rec;
		}

		public int getRep() {
			return rep;
		}

		public void setRep(int rep) {
			this.rep = rep;
		}

		public int getRest() {
			return rest;
		}

		public void setRest(int rest) {
			this.rest = rest;
		}

		public int getArrive() {
			return arrive;
		}

		public void setArrive(int arrive) {
			this.arrive = arrive;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", rec=" + rec + ", rep=" + rep + ", rest=" + rest + ", arrive=" + arrive + "]";
		}

	}
}