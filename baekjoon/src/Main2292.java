import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main2292 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int num = 1;
		int cnt = 1;
		int base = 0;
		while (true) {
			if (num >= n)
				break;
			cnt++;
			base += 6;
			num += base;
			
			
		}
		System.out.println(cnt);
	}
}
