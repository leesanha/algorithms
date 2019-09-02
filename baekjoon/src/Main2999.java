import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2999 {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String mail = br.readLine();

		int n = mail.length();

		int r = 1;
		int c = 1;
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				int a = n / i;
				int b = i;
				if (a > b) {
					int temp = a;
					a = b;
					b = temp;
				}
				if (a >= r) {
					r = a;
					c = b;
				}
			}
		}

		char[][] code = new char[r][c];

		int idx = 0;
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				code[j][i] = mail.charAt(idx++);
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(code[i][j]);
			}
		}
	}

}
