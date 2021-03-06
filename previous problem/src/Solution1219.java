import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1219 {
	static Scanner sc = new Scanner(System.in);
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[] visit;
	static int[] arr1;
	static int[] arr2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int tc = sc.nextInt();

		for (int t = 1; t <= 10; t++) {
			arr1 = new int[100];
			arr2 = new int[100];
			visit = new boolean[100];
			
			for (int i = 0; i < 100; i++) {
				arr1[i] = arr2[i] = -1;
			}

			int tc = sc.nextInt();
			int size = sc.nextInt();

			for (int i = 0; i < size; i++) {
				int num1 = sc.nextInt();
				int num2 = sc.nextInt();

				if (arr1[num1] != -1)
					arr2[num1] = num2;
				else
					arr1[num1] = num2;
			}

			Queue<Integer> q = new LinkedList<>();
			q.add(0);
			visit[0] = true;
			
			boolean flag = false;

			while (!q.isEmpty()) {
				int node = q.poll();
				visit[node] = true;
				
				if(node == 99) {
					System.out.format("#%d %d\n", tc, 1);
					flag = true;
					break;
				}

				if(arr1[node] != -1 && arr2[node] == -1) {
					if(!visit[arr1[node]])
						q.add(arr1[node]);
				}else if (arr1[node] != -1 && arr2[node] != -1) {
					if(!visit[arr1[node]])
						q.add(arr1[node]);
					if(!visit[arr2[node]])
						q.add(arr2[node]);
				}
			}
			if(!flag)System.out.format("#%d %d\n", tc, 0);			
		}
		sc.close();
	}

}