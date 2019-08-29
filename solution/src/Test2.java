import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

			for (int i = 0; i < n; i++) {	
				StringTokenizer st = new StringTokenizer(br.readLine());
				// 0: x위치, 1: y위치, 2:이동 방향, 3: 보유에너지
				int[] temp = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
				list.add(temp);
			}

			int ans = 0;
			while (true) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < list.size(); j++) {
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
			System.out.format("#%d %d\n", t, ans);
		}
	}

}
