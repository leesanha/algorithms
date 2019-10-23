import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Algo_HW_20191021 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] mabangjin = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				mabangjin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int comp = 0;
		for (int i = 0; i < n; i++) {
			comp += mabangjin[0][i];
		}
		boolean isMabangjin = checkMabangjin(mabangjin, comp, n);
		if (!isMabangjin) {
			System.out.println(isMabangjin);
			return;
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += mabangjin[i][j];
			}
		}
		System.out.println(isMabangjin + " " + sum);
	}

	static private boolean checkMabangjin(int[][] mabangjin, int comp, int size) {
		// 가로
		int comparator = 0;
		for (int i = 0; i < size; i++) {
			comparator = 0;
			for (int j = 0; j < size; j++) {
				comparator += mabangjin[i][j];
			}
			if (comparator != comp)
				return false;
		}
		// 세로
		for (int j = 0; j < size; j++) {
			comparator = 0;
			for (int i = 0; i < size; i++) {
				comparator += mabangjin[i][j];
			}
			if (comparator != comp)
				return false;
		}
		// 대각선
		comparator = 0;
		for (int i = 0; i < size; i++)
			comparator += mabangjin[i][i];
		if (comparator != comp)
			return false;

		comparator = 0;
		for (int i = 0; i < size; i++)
			comparator += mabangjin[i][size - i - 1];
		if (comparator != comp)
			return false;
		return true;
	}
}
