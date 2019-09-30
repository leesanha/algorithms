import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] seat = new int[n];
		for (int i = 0; i < n; i++)
			seat[i] = Integer.parseInt(st.nextToken());

//		System.out.println(Arrays.toString(seat));

		ArrayList<int[]> dis = new ArrayList<int[]>();
		int max_dis = -1;
		int max_idx = -1;
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			if (seat[i] == 1) {
				if (max_dis < cnt) {
					max_dis = cnt;
					max_idx = dis.size();
				}
				if (dis.size() != 0) {
					dis.get(dis.size() - 1)[1] = cnt;
				}
				int[] temp = { cnt, 0 };
				dis.add(temp);

				cnt = 0;
			}
			cnt++;
		}
		dis.get(dis.size() - 1)[1] = cnt - 1;
		int ans = 0;
		if (max_idx == 0 || max_idx == dis.size() - 1)
			ans = (dis.get(0)[0] < dis.get(0)[1]) ? dis.get(0)[1] : dis.get(0)[0];
		else
			ans = max_dis / 2;
		System.out.println(ans);
	}

}
