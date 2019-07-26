import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Rc {
	int row;
	int col;
	int dir;

	public Rc() {
	}

	public Rc(int row, int col, int dir) {
		super();
		this.row = row;
		this.col = col;
		this.dir = dir;
	}

}

public class Solution {
	static Scanner sc = new Scanner(System.in);
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][][][] visit;
	static char[][] arr;
	static int row_size;
	static int col_size;
	static int mem;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tc = sc.nextInt();
		boolean hasEnd = false;

		for (int t = 1; t <= tc; t++) {
			visit = new boolean[20][20][16][4];
			arr = new char[20][20];
			mem = 0;
			row_size = sc.nextInt();
			col_size = sc.nextInt();

			
			for (int i = 0; i < row_size; i++) {
				arr[i] = sc.next().toCharArray();
				for(int j=0;j<arr[i].length;j++) {
					if(arr[i][j] == '@') hasEnd = true;
				}
			}

			if(!hasEnd) {
				System.out.format("#%d NO\n", t);
				continue;
			}
			if(!dfs(0, 0, 1))System.out.format("#%d NO\n", t);
			else System.out.format("#%d YES\n", t);
		}
		sc.close();
	}

	static boolean dfs(int row, int col, int dir) {
		int nr, nc;

		boolean flag = true;
		
		if (row >= row_size)
			row = 0;
		if (row < 0)
			row = row_size - 1;
		if (col >= col_size)
			col = 0;
		if (col < 0)
			col = col_size - 1;

		if (visit[row][col][mem][dir]) 
			return false;
		visit[row][col][mem][dir] = true;

		if ((arr[row][col] == '<') || (arr[row][col] == '_' && mem != 0)) 
			dir = 3;
		else if (arr[row][col] == '>' || (arr[row][col] == '_' && mem == 0)) 
			dir = 1;
		 else if ((arr[row][col] == '^') || (arr[row][col] == '|' && mem != 0)) 
			dir = 0;
		else if ((arr[row][col] == 'v') || (arr[row][col] == '|' && mem == 0)) 
			dir = 2;
		else if (arr[row][col] == '?') {
			for (int i = 0; i < 4; i++) {
				if (Math.abs(dir - i) == 2)
					continue;
				nr = row + dr[i];
				nc = col + dc[i];
				flag = dfs(nr, nc, i);
				if(flag)return true;
			}
			return flag;
		} else if (arr[row][col] == '@')
			return true;
		else if (arr[row][col] - '0' >= 0 && arr[row][col] - '0' <= 9)
			mem = arr[row][col] - '0';
		else if (arr[row][col] == '+') 
			mem = (mem + 1) % 16;
		else if (arr[row][col] == '-') {
			mem--;
			if (mem == -1)
				mem = 15;
		}
		nr = row + dr[dir];
		nc = col + dc[dir];

		return dfs(nr, nc, dir);
	}
}
