import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1230 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			
			int size = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			size = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int idx, num;
			for (int i = 0; i < size; i++) {
				char option = st.nextToken().charAt(0);

				switch (option) {
				case 'I':
					idx = Integer.parseInt(st.nextToken());
					num = Integer.parseInt(st.nextToken());
					for (int j = 0; j < num; j++) {
						list.add(idx + j, Integer.parseInt(st.nextToken()));
					}
					break;
				case 'D':
					idx = Integer.parseInt(st.nextToken());
					num = Integer.parseInt(st.nextToken());
					for (int j = 0; j < num; j++) {
						list.remove(idx);
					}
					break;
				case 'A':
					num = Integer.parseInt(st.nextToken());
					for (int j = 0; j < num; j++) {
						list.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}
			}
			System.out.format("#%d", t);
			for (int i = 0; i < 10; i++) {
				System.out.print(" " + list.get(i));
			}
			System.out.println();
		}
	}
}
