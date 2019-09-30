import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		HashMap<String, Integer> map = new HashMap<>();

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (map.containsKey(input[i])) {
				int val = map.get(input[i]);
				map.put(input[i], val + 1);
			} else {
				map.put(input[i], 1);
			}
			cnt++;
		}
		int species = map.size();

		int ans = 0;
		int comp = 0;
		boolean flag = false;
		HashMap<Integer, Integer> cnts = new HashMap<Integer, Integer>();
		for (String key : map.keySet()) {
			if (cnts.containsKey(map.get(key))) {
				cnts.put(map.get(key), cnts.get(map.get(key)) + 1);
			} else {
				cnts.put(map.get(key), 1);
			}
		}

		if (cnts.size() == 1)
			flag = true;
		if (cnts.size() == 2) {
			int min = 0;
			int min_key = -1;
			int other_key = -1;
			for (int key : cnts.keySet()) {
				if (min == 0) {
					min = cnts.get(key);
					min_key = key;
				} else if (min > cnts.get(key)) {
					min = cnts.get(key);
					other_key = min_key;
					min_key = key;
				}else {
					other_key = key;
				}
			}
			if(min_key < other_key) {
				flag = true;
				cnt++;
			}
		}

		System.out.println((flag) ? "Y" : "N");
		System.out.println(cnt);
		System.out.println(species);
	}

}