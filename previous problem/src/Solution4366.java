import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution4366 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {

		int tc = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= tc; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] bi = br.readLine().toCharArray();
			char[] ter = br.readLine().toCharArray();

			HashSet<Long> set = new HashSet<>();
			for (int i = 0; i < bi.length; i++) {
				char[] cp_bi = bi.clone();
				if (cp_bi[i] == '0')
					cp_bi[i] = '1';
				else
					cp_bi[i] = '0';
				Long binary = Long.parseLong(new String(cp_bi), 2);
				set.add(binary);
			}
			
			long ans = 0;
			Long ternary = 0l;
			for (int i = 0; i < ter.length; i++) {
				char[] cp_ter = ter.clone();
				if (cp_ter[i] == '0') {
					cp_ter[i] = '1';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
					cp_ter[i] = '2';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
				}else if(cp_ter[i] == '1') {
					cp_ter[i] = '0';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
					
					cp_ter[i] = '2';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
				}else {
					cp_ter[i] = '0';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
					cp_ter[i] = '1';
					ternary = Long.parseLong(new String(cp_ter), 3);
					if(set.contains(ternary)) {
						ans = ternary;
						break;
					}
				}
				
			}

			System.out.format("#%d %d\n", t, ans);
		}
	}
}