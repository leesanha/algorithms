import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
/*
 * String으로 하면 시간 초과
 * 중복이 안되도록 하는 것은 set을 사용하는 방법을 생각해보기
 * list로 해서 숫자로 비교해도 됨.
 */
 
public class Solution {
    static Scanner sc = new Scanner(System.in);
    static int ans;
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static int[][] map;
    static ArrayList<Integer> list;
    static Set<Integer> s = new HashSet<>();
 
    public static void main(String[] args) {
        int tc = sc.nextInt();
 
        for (int t = 1; t <= tc; t++) {
            
        }
        sc.close();
    }
 
}