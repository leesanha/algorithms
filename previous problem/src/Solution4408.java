import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution4408 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int stu_num;
	static int ans;
	static int[] room;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			stu_num = Integer.parseInt(br.readLine().trim());

			room = new int[401];
			for (int i = 0; i < stu_num; i++) {
				String[] input = br.readLine().split(" ");
				int start = Integer.parseInt(input[0]);
				int end = Integer.parseInt(input[1]);

				if(start > end) {
					int temp = start;
					start = end;
					end = temp;
				}
				
				start = (start % 2 == 0) ? start - 1 : start;
				end = (end % 2 == 0) ? end : end + 1;

				for (int j = start; j <= end; j++) {
					room[j]++;
				}
			}
			ans = room[1];
			for (int i = 1; i < 401; i++) {
				if (ans < room[i])
					ans = room[i];
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}

}
