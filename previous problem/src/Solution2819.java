import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * String으로 하면 시간 초과
 * 중복이 안되도록 하는 것은 set을 사용하는 방법을 생각해보기
 */

public class Solution2819 {
	static Scanner sc = new Scanner(System.in);
	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] map;
	static Set<Integer> s = new HashSet<>();

	public static void main(String[] args) {
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			map = new int[4][4];
			ans = 0;
			
			s.clear();

			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					map[i][j] = sc.nextInt();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 1, map[i][j]);
				}
			}
			System.out.format("#%d %d\n", t, ans);
		}
		sc.close();
	}

	private static void dfs(int row, int col, int len, int val) {
		if (len == 7) {
			if(s.add(val)) {
				ans++;
			}
			return;
		}
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = row + dr[i];
			nc = col + dc[i];

			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
				continue;

			dfs(nr, nc, len + 1, map[nr][nc] + 10 * val);
		}
	}

}

/*
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 

 * String으로 하면 시간 초과
 * 중복이 안되도록 하는 것은 set을 사용하는 방법을 생각해보기
 * list로 해서 숫자로 비교해도 됨.
 
 
public class Solution {
    static Scanner sc = new Scanner(System.in);
    static int ans;
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static int[][] map;
    static ArrayList<Integer> list;
 
    public static void main(String[] args) {
        int tc = sc.nextInt();
 
        for (int t = 1; t <= tc; t++) {
            map = new int[4][4];
            ans = 0;
             
            list = new ArrayList<Integer>();
 
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    map[i][j] = sc.nextInt();
 
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, 1, map[i][j]);
                }
            }
            System.out.format("#%d %d\n", t, ans);
        }
        sc.close();
    }
 
    private static void dfs(int row, int col, int len, int val) {
        if (len == 7) {
            if(!list.contains(val)) {
            	list.add(val);
                ans++;
            }
            return;
        }
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = row + dr[i];
            nc = col + dc[i];
 
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
                continue;
 
            dfs(nr, nc, len + 1, map[nr][nc] + 10 * val);
        }
    }
 
}
*/
