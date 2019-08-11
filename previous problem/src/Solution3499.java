import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution3499 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<String> list1;
	static Queue<String> list2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			int size = Integer.parseInt(br.readLine());

			String[] input = br.readLine().split(" ");
			list1 = new LinkedList<String>();
			list2 = new LinkedList<String>();

			if (size % 2 != 0) {
				for (int i = 0; i < input.length; i++) {
					if (i <= input.length / 2)
						list1.add(input[i]);
					else
						list2.add(input[i]);
				}
			}else {
				for (int i = 0; i < input.length; i++) {
					if (i < input.length / 2)
						list1.add(input[i]);
					else
						list2.add(input[i]);
				}
			}

			System.out.format("#%d ", t);

			for (int i = 0; i < input.length; i++) {
				if(!list1.isEmpty())
					System.out.print(list1.poll() + " ");
				if (!list2.isEmpty())
					System.out.print(list2.poll() + " ");
			}
			System.out.println();
		}
	}

}
