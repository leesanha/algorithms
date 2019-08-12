import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2607 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());

		//���ĺ� ������ �߿��ϴٰ� �����ؼ� 27���� �迭�� ���� �ش� ���ĺ��� ������ŭ ������Ŵ
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
				if (Math.abs(arr[0][j] - arr[i][j]) >= 1)//���� �ε����� ���� ���� ������ ������ ���� ����
					cnt++;//������ �� ���̰� 1�̻��̸� �ش� ���ĺ��� ������ �ٸ��Ƿ� �ϴ� üũ�Ѵ�.
				if (cnt >= 3)//�� ���̰� 3�̻��̶�� 2�� �̻��� ���ĺ��� �ٸ� ���̹Ƿ� ����� �ܾ�, ���� �ܾ �ƴ�
					break;
			}
			if (cnt <= 1)
				ans++;
			else if (cnt == 2) {//���� ���ĺ� �ϳ��� �ٲ㼭 ����� �ܾ �Ǵ� ����� �ܾ��� ���̰� ���ٰ� �����ϰ� Ǯ����.
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
