import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int boatSize = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> all = new HashMap<String, Integer>();
		for (int i = 0; i < size; i++) {
			all.put(st.nextToken(), i);
		}

		st = new StringTokenizer(br.readLine());
		int canRideSize = Integer.parseInt(st.nextToken());
		boolean[] canRide = new boolean[canRideSize];

		for (int i = 0; i < canRideSize; i++) {
			String canRideThing = st.nextToken();
			canRide[all.get(canRideThing)] = true;
		}

		int dontLeaveSize = Integer.parseInt(br.readLine());
//		boolean[]
		
		for (int i = 0; i < dontLeaveSize; i++) {
			st = new StringTokenizer(br.readLine());
			
		}
	}

}
