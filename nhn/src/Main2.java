import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		ArrayList<Integer> fq = new ArrayList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");

			if (input[0].equals("enqueue")) {// 넣는다.
				int num = Integer.parseInt(input[1]);
				fq.add(num);
				if (map.containsKey(num)) {
					map.put(num, map.get(num) + 1);
				} else {
					map.put(num, 1);
				}
			} else {
				int max = -1;
				for (Integer key : map.keySet()) {// 빈도수 최대 값을 찾는다.
					if (map.get(key) > max) {
						max = map.get(key);
					}
				}
				ArrayList<Integer> same = new ArrayList<Integer>();
				for (Integer key : map.keySet()) {// 빈도수 최대인 값들을 same에 넣어준다.
					if (map.get(key) == max) {
						same.add(key);
					}
				}
				for (int j = 0; j < fq.size(); j++) {// fq를 돌면서 빈도수가 가장 큰 값 중 빠른 것을 먼저 꺼낸다.
					boolean flag = false;
					for (int k = 0; k < same.size(); k++) {
						if (fq.get(j) == same.get(k)) {
							System.out.print(fq.get(j) + " ");
							map.put(fq.get(j), map.get(fq.get(j)) - 1);
							fq.remove(j);
							flag = true;
							break;
						}
					}
					if (flag)
						break;
				}
			}
		}
	}

}