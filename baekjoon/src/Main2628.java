import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2628 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int ans, n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());

		ArrayList<Integer> garo = new ArrayList<>();
		ArrayList<Integer> sero = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int option = Integer.parseInt(st.nextToken());
			if (option == 0)
				garo.add(Integer.parseInt(st.nextToken()));
			else
				sero.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(garo);
		Collections.sort(sero);

		ArrayList<Integer> ans1 = new ArrayList<>();
		ArrayList<Integer> ans2 = new ArrayList<>();

		// 가로 영역으로 먼저 나눈다.
		int before = 0;
		for (int i : garo) {
			ans1.add(i - before);
			before = i;
		}
		ans1.add(n - before);// 남은 영역 더함

		// 세로 영역으로 나눈다.
		before = 0;
		for (int i : sero) {
			ans2.add(i - before);
			before = i;
		}
		ans2.add(m - before);

		// 각 영역의 가로 세로를 구했으므로, 영역들을 계산한다.
		ans = 0;
		for (int i : ans1) {
			for (int j : ans2) {
				if (ans < i * j)
					ans = i * j;
			}
		}
		System.out.println(ans);
	}

}
