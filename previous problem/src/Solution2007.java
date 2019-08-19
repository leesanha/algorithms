import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2007 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			String input = br.readLine();

			String banbok = "";
			int start = 0;
			for (int i = 0; i < input.length(); i++) {
				String temp = "";
				boolean flag = false;
				for (int j = i + 1; j < input.length(); j++) {
					temp = input.substring(i, j);
					if(temp.equals(input.subSequence(j, j + j - i))) {
						start = i;
						banbok = new String(temp);
						flag = true;
						break;
					}
				}
				if(flag)
					break;
			}
			System.out.format("#%d %d\n",t, banbok.length());
		}
	}

}
