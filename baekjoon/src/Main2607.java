import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2607 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());

		//알파벳 갯수가 중요하다고 생각해서 27개의 배열을 만들어서 해당 알파벳의 갯수만큼 증가시킴
		int[][] arr = new int[size][27];
		for (int i = 0; i < size; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < input.length; j++) {
				arr[i][input[j] - 'A']++;
			}
		}

		int ans = 0;
		for (int i = 1; i < size; i++) {
			int cnt = 0;
			for (int j = 0; j < 27; j++) {
				if (Math.abs(arr[0][j] - arr[i][j]) >= 1)//같은 인덱스에 같은 값을 가지고 있으면 같은 구성
					cnt++;//하지만 그 차이가 1이상이면 해당 알파벳의 개수가 다르므로 일단 체크한다.
				if (cnt >= 3)//그 차이가 3이상이라면 2개 이상의 알파벳이 다른 것이므로 비슷한 단어, 같은 단어가 아님
					break;
			}
			if (cnt <= 1)
				ans++;
			else if (cnt == 2) {//만약 알파벳 하나만 바꿔서 비슷한 단어가 되는 경우라면 단어의 길이가 같다고 생각하고 풀었다.
				int sum1 = 0, sum2 = 0;
				for (int j = 0; j < 27; j++) {
					sum1 += arr[0][j];
				}
				for (int j = 0; j < 27; j++) {
					sum2 += arr[i][j];
				}
				if (sum1 == sum2)
					ans++;
			}
		}
		System.out.println(ans);
	}

}
