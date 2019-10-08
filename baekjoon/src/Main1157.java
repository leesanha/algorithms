import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class Main1157 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		HashMap<String, Integer> hm = new HashMap<>();
		int max = -1;
		for (int i = 0; i < input.length(); i++) {
			String ch = (input.charAt(i)+"").toUpperCase();
			if (hm.containsKey(ch)) {
				int data = hm.get(ch);
				hm.put(ch, data + 1);
			} else {
				hm.put(ch, 1);
			}
			if(hm.get(ch) > max)
				max = hm.get(ch);
		}
		String ans = null;
//		System.out.println(ans);
		for(String ch : hm.keySet()) {
			if(max == hm.get(ch)) {
				if(ans != null) {
					ans = "?";
					break;
				}else
					ans = ch;
			}
		}
		System.out.println(ans);
	}

}
