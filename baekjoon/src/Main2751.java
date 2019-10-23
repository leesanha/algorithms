import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main2751 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++)
			pq.add(Integer.parseInt(br.readLine()));
		for (int i = 0; i < n; i++)
			System.out.println(pq.poll());
	}

}
