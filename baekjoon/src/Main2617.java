import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2617 {
	static int n;
	static int[] h;
	static int[] l;
	static ArrayList<Integer>[] heavy;
	static ArrayList<Integer>[] light;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		heavy = new ArrayList[n + 1];// 해당 인덱스 동전보다 무거운 동전 저장
		light = new ArrayList[n + 1];// 해당 인덱스 동전보다 가벼운 동전 저장

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (heavy[b] == null)
				heavy[b] = new ArrayList<>();
			heavy[b].add(a);
			if (light[a] == null)
				light[a] = new ArrayList<>();
			light[a].add(b);
		}

		int ans = 0;
		h = new int[n + 1];// 해당 인덱스 동전보다 무거운 동전의 개수 저장
		l = new int[n + 1];// 해당 인덱스 동전보다 가벼운 동전의 개수 저장

		// 무거운 동전 고름
		for (int i = 1; i <= n; i++) {
			if (heavy[i] == null)
				continue;
			boolean[] hvisit = new boolean[n + 1];
			hv(heavy[i], i, hvisit);
		}
		// 가벼운 동전 고름
		for (int i = 1; i <= n; i++) {
			if (light[i] == null)
				continue;
			boolean[] lvisit = new boolean[n + 1];
			lt(light[i], i, lvisit);
		}

		for (int i = 1; i <= n; i++) {
			if (h[i] > n / 2 || l[i] > n / 2)// 가볍거나 무거운 동전이 과반수 넘게 있으면 불가능
				ans++;
		}
		System.out.println(ans);
	}

	// 무거운 동전들을 연쇄적으로 찾는다.
	static void hv(ArrayList<Integer> hea, int idx, boolean[] visit) {
		if (hea == null)
			return;

		for (int next : hea) {
			if(!visit[next]) {
				h[idx]++;
				visit[next] = true;
				hv(heavy[next], idx, visit);
			}
		}
	}

	static void lt(ArrayList<Integer> lig, int idx, boolean[] visit) {
		if (lig == null)
			return;

		for (int next : lig) {
			if(!visit[next]) {
				l[idx]++;
				visit[next] = true;
				lt(light[next], idx, visit);
			}
		}
	}

}
