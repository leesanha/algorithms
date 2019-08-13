import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1233 {

	static class Node {
		String data;
		Node left;
		Node right;

		public Node(String data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int size = Integer.parseInt(br.readLine().trim());

			Node[] tree = new Node[1001];// 노드 번호에 따라 노드를 저장할 배열
			int[] parent = new int[1001];// 해당 노드의 부모가 몇 번인지 저장할 배열
			for (int i = 0; i < size; i++) {
				String[] input = br.readLine().split(" ");
				int num = Integer.parseInt(input[0]);
				String data = input[1];

				tree[num] = new Node(data);
				// 방금 입력받은 노드의 번호에 해당하는 parent 배열의 값이 0이 아니면 부모 노드가 있다는 뜻
				if (parent[num] < 0) {// 마이너스면 자신이 왼쪽 자식 노드
					tree[-parent[num]].left = tree[num];
				}
				if (parent[num] > 0) {// 플러스면 자신이 오른쪽 자식 노드
					tree[parent[num]].right = tree[num];
				}
				// 방금 입력받은 노드에게 자식 노드가 존재하는 경우
				if (input.length >= 3) {
					int left = Integer.parseInt(input[2]);// 자식 노드의 번호
					parent[left] = -num;// 자신이 왼쪽 자식임을 구분하기 위해 마이너스 값을 넣는다.
					// 오른쪽 자식은 플러스 값을 넣는다.
					if (input.length == 4) {
						int right = Integer.parseInt(input[3]);
						parent[right] = num;
					}
				}

			}
			System.out.format("#%d %d\n", t, (go(tree[1])) ? 1 : 0);
		}
	}

	private static boolean go(Node node) {
		if (node == null)
			return false;

		switch (node.data) {
		case "+":
		case "-":
		case "*":
		case "/":
			boolean flag1 = go(node.left);
			boolean flag2 = go(node.right);
			if (!flag1 || !flag2)//연산자인데 자식이 2개가 아닌 경우 false 리턴
				return false;
			return true;
		default://숫자인데 자식 노드가 있으면 false 리턴
			if(!(node.left == null && node.right == null))
				return false;
			return true;
		}
	}

}
