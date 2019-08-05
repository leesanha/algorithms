import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution1231 {
	static Scanner sc = new Scanner(System.in);
	static int size;

	public static void main(String[] args) {
//		int tc = sc.nextInt();

		for (int t = 1; t <= 10; t++) {
			size = sc.nextInt();
			sc.nextLine();
			Character[] tree = new Character[size + 1];
			String line = sc.nextLine();
			int num = Integer.parseInt(line.split(" ")[0]);
			char data = line.split(" ")[1].charAt(0);
			tree[num] = data;

			for (int i = 1; i < size; i++) {
				line = sc.nextLine();
				num = Integer.parseInt(line.split(" ")[0]);
				data = line.split(" ")[1].charAt(0);
				tree[num] = data;			
			}
			System.out.print("#" + t + " ");
			inorder(tree, 1);
			System.out.println();
		}
		sc.close();
	}

	static void inorder(Character[] tree, int idx) {
		if (idx > size)
			return;

		inorder(tree, idx * 2);
		System.out.print(tree[idx]);
		inorder(tree, idx * 2 + 1);
	}

}
