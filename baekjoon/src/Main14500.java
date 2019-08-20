import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14500 {
	static int[][][] arr = { 
			{ { 0, 1 }, { 0, 2 }, { 0, 3 } }, 
			{ { 1, 0 }, { 2, 0 }, { 3, 0 } },//일자
			{ { 0, 1 }, { 1, 0 }, { 1, 1 } },//사각
			{ { 1, 0 }, { 2, 0 }, { 2, 1 } }, 
			{ { 1, 0 }, { 2, 0 }, { 2, -1 } },
			{ { 0, 1 }, { 0, 2 }, { 1, 2 } }, 
			{ { 0, 1 }, { 0, 2 }, { -1, 2 } }, 
			{ { 1, 0 }, { 0, 1 }, { 0, 2 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 } }, 
			{ { 0, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 1 }, { 1, 1 }, { 2, 1 } },//기역자
			{ { 1, 0 }, { 1, 1 }, { 2, 1 } }, 
			{ { 1, 0 }, { 1, -1 }, { 2, -1 } }, 
			{ { 0, 1 }, { 1, 1 }, { 1, 2 } },
			{ { 0, 1 }, { -1, 1 }, { -1, 2 } }, //ㄹ 모양
			{ { 0, 1 }, { 1, 1 }, { 0, 2 } }, 
			{ { 0, 1 }, { -1, 1 }, { 1, 1 } },
			{ { 0, 1 }, { -1, 1 }, { 0, 2 } }, 
			{ { -1, 0 }, { 1, 0 }, { 0, 1 } } //ㅗ 모양
			};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int ans = 0;

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < 19; k++) {
					int sum = map[i][j];
					int cnt = 0;
					for (int l = 0; l < 3; l++) {
						int row = i + arr[k][l][0];
						int col = j + arr[k][l][1];

						if (row < 0 || row >= n || col < 0 || col >= m)
							break;
						cnt++;
						sum += map[row][col];
					}
					if(cnt == 3 && ans < sum) {
//						System.out.println(k + " " + i + " " + j + " " + sum);
						ans = sum;
					}
				}
			}
		}
		System.out.println(ans);
	}

}
