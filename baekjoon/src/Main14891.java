
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14891 {
	static int ans;
	static char[][] tire;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 12시 방향부터 시작
		tire = new char[4][8];
		for (int i = 0; i < 4; i++)
			tire[i] = br.readLine().toCharArray();

		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			int[] circle = new int[4];
			check(circle, num, dir);
			move(circle);
		}
		int ans = 0;
		for (int i = 0; i < 4; i++)
			ans += ((tire[i][0] - '0') << i);
		System.out.println(ans);
	}

	private static void move(int[] circle) {
		for (int i = 0; i < 4; i++) {
			if (circle[i] == 1) {
				char temp = tire[i][7];
				for (int j = 7; j > 0; j--)
					tire[i][j] = tire[i][j - 1];
				tire[i][0] = temp;
			} else if (circle[i] == -1) {
				char temp = tire[i][0];
				for (int j = 1; j < 8; j++)
					tire[i][j - 1] = tire[i][j];
				tire[i][7] = temp;
			}
		}
	}

	private static void check(int[] circle, int num, int dir) {
		circle[num] = dir;
		// 왼쪽 가면서
		int before = num;
		int beforeDir = dir;
		for (int i = num - 1; i >= 0; i--) {
			if (tire[before][6] != tire[i][2]) {// 극이 다르면 다른 방향으로 움직인다.
				circle[i] = beforeDir * (-1);
				before = i;
				beforeDir *= -1;
			} else// 극이 같으면 더 진행하지 않는다.
				break;
		}
		// 오른쪽 가면서
		before = num;
		beforeDir = dir;
		for (int i = num + 1; i < 4; i++) {
			if (tire[before][2] != tire[i][6]) {
				circle[i] = beforeDir * (-1);
				before = i;
				beforeDir *= -1;
			} else
				break;
		}
	}
}