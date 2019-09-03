import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Test2 {
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
//			for (int j = 0; j < 1000; j++) {
//				System.out.println(-1000 + " " + j + " " + "0 " + "30");
//			}
			int n = Integer.parseInt(br.readLine());

			ArrayList<int[]> list = new ArrayList<>();
			ArrayList<int[]> time = new ArrayList<>();
/*
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				// 0: x위치, 1: y위치, 2:이동 방향, 3: 보유에너지
				int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
				list.add(temp);
				for (int j = 0; j < i; j++) {
					int prevDir = list.get(j)[2];
					if (prevDir == temp[2])// 방향 같으면 부딪힐 일이 없음
						continue;
					int prevRow = list.get(j)[1];
					int prevCol = list.get(j)[0];
					int prevVal = list.get(j)[3];
					if ((temp[2] == 0 && prevDir == 1) || (temp[2] == 1 && prevDir == 0)) {// 아래 위로 있을 때
						if(prevCol == temp[0]) {// 컬럼이 같을 때
							int[] diff = { Math.abs(temp[1] - prevRow), temp[3] + prevVal, i, j };// 부딪히면 거리, 에너지, 두 개의 index 저장하여 정렬할 list에 넣음
							time.add(diff);
						}
					}else if((temp[2] == 2 && prevDir == 3) || (temp[2] == 3 || prevDir == 2)) {//좌우로 있을 때
						if(prevRow == temp[1]) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					}else if(temp[2] == 0 && prevDir == 3) {//우상
						if(temp[0] > prevCol && Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					}else if(temp[2] == 3 && prevDir == 0) {//우상
						
					}
					
					else if (prevDir == 3 && temp[0] > prevCol && Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {// 왼쪽에서 오는 것, 부딪힐 수 있는 것
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						} else if (prevDir == 2 && temp[0] < prevCol
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					} else if (temp[2] == 1) {// 하
						} else if (prevDir == 3 && temp[0] > prevCol
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {// 왼쪽에서 오는 것, 부딪힐 수 있는 것
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						} else if (prevDir == 2 && temp[0] < prevCol
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					} else if (temp[2] == 2) {// 좌
						else if (prevDir == 0 && temp[1] > prevRow
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {// 밑에서 오는 것, 부딪힐 수 있는 것
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						} else if (prevDir == 1 && temp[1] < prevRow
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					} else {// 우
						} else if (prevDir == 0 && temp[1] > prevRow
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {// 밑에서 오는 것, 부딪힐 수 있는 것
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						} else if (prevDir == 1 && temp[1] < prevRow
								&& Math.abs(temp[0] - prevCol) == Math.abs(temp[1] - prevRow)) {
							int[] diff = { Math.abs(temp[0] - prevCol), temp[3] + prevVal, i, j };
							time.add(diff);
						}
					}
				}
			}

			boolean[] check = new boolean[n];

			Collections.sort(time, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			int ans = 0;
			for (int i = 0; i < time.size(); i++) {
				int a = time.get(i)[2];
				int b = time.get(i)[3];
				
				if(!check[a] && !check[b]) {
					ans += time.get(i)[1];
					check[a] = true;
					check[b] = true;
				}
			}
*/
/*			
			while (true) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < list.size(); j++) {
						if (i >= list.size())
							break;
						if (i == j)
							continue;
						// 기준이 되는 점은 움직인다.
						int arow = list.get(i)[1];
						int acol = list.get(i)[0];
						int narow = arow + dr[list.get(i)[2]];
						int nacol = acol + dc[list.get(i)[2]];

						list.get(i)[1] = narow;
						list.get(i)[0] = nacol;

						// 비교하는 점은 그대로 둔다.
						int brow = list.get(j)[1];
						int bcol = list.get(j)[0];
						int aval = list.get(i)[3];
						int bval = list.get(j)[3];

						int nbrow = brow + dr[list.get(j)[2]];
						int nbcol = bcol + dc[list.get(j)[2]];

						// 충돌직전(a는 움직였고, b는 아직 안움직임)이면 ans에 더하고 둘 다 삭제, 충돌하면 마찬가지
						if ((narow == brow && nacol == bcol && arow == nbrow && acol == nbcol)
								|| (narow == nbrow && nacol == nbcol)) {
							ans += aval + bval;
							list.remove(j);
							list.remove(i);
							j--;
						}

					}
				}
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i)[0] < -1000 || list.get(i)[0] > 1000 || list.get(i)[1] < -1000
							|| list.get(i)[1] > 1000)
						list.remove(i);
				}

				if (list.size() == 0)
					break;
			}
			*/
//			System.out.format("#%d %d\n", t, ans);
		}
	}

}

