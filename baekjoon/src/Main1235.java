import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main1235 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] stu = new String[n];

		for (int i = 0; i < n; i++)
			stu[i] = br.readLine();

		int size = stu[0].length();

		int cnt = 0;
		for (int i = size - 1; i >= 0; i--) {
			cnt++;
			Set<String> s = new HashSet<>();

			for (int j = 0; j < n; j++)
				s.add(stu[j].substring(i));
			if (s.size() == n)
				break;
		}
		System.out.println(cnt);
	}

}
