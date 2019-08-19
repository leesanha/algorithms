import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution1266 {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
 
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            double a = Double.parseDouble(st.nextToken()) / 100;
            double b = Double.parseDouble(st.nextToken()) / 100;
             
            double ans = 1;
            double ares = 0;
            double bres = 0;
             
            for (int i = 0; i <= 18; i++) {
                double atemp = 1;
                double btemp = 1;
                if (i == 0 || i == 1 || i == 4 || i == 6 || i == 8 || i == 9 || i == 10 || i == 12 || i == 14 || i == 15
                        || i == 16 || i == 18) {
                    for (int k = 0; k < i; k++) 
                        atemp *= a;
                    for (int k = 0; k < 18 - i; k++) 
                        atemp *= 1 - a;
                    for (int k = 0; k < i; k++) 
                        btemp *= b;
                    for (int k = 0; k < 18 - i; k++) 
                        btemp *= 1 - b;
                    btemp *= comb(i);
                    bres += btemp;
                    atemp *= comb(i);
                    ares += atemp;
                }
            }
            ans = ares * bres;
            System.out.format("#%d %.6f\n", t, 1 - ans);
        }
    }
 
    private static double comb(int cnt) {
        double res = 1;
        for (int i = 18; i > 18 - cnt; i--) 
            res *= i;
        for (int i = cnt; i > 0; i--) 
            res /= i;
        return res;
    }
 
}