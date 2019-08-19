import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			double a = Double.parseDouble(st.nextToken()) / 100;
			double b = Double.parseDouble(st.nextToken()) / 100;

			double[] arr = new double[19];
			double[] brr = new double[19];

			int cnt = 1;
			for (int i = 1; i <= 18; i++) {
				arr[cnt] = a * i + (1 - a) * (18 - i); 
				brr[cnt++] = b * i + (1 - b) * (18 - i); 
			}

			
		}
	}

}
