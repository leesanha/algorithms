import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException  {
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		ArrayList<Integer> messages = new ArrayList<Integer>();
		int[] con = new int[c];


		for (int i = 0; i < m; i++) {
			Arrays.sort(con);
			con[0] += Integer.parseInt(br.readLine());
		}
		int max = -1;
		for(int i=0;i<c;i++) {
			if(max < con[i])
				max = con[i];
		}
		System.out.println(max);
	}

}