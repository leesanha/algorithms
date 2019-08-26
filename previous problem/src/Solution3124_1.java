import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Solution3124_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long ans;
    static int[] parent;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        int tc = Integer.parseInt(br.readLine().trim());
 
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
 
            parent = new int[v + 1];
            for (int i = 1; i <= v; i++)
                parent[i] = i;
            ArrayList<Edge> list = new ArrayList();
 
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                list.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }
 
            Collections.sort(list);
            long ans = 0;
            for (int i = 0; i < e; i++) {
                Edge eg = list.get(i);
 
                if (find(eg.a) != find(eg.b)) {
                    union(eg.a, eg.b);
                    ans += eg.val;
                }
            }
 
            System.out.println("#" + t + " " + ans);
        }
    }
 
    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
 
        if (x != y)
            parent[x] = y;
    }
 
    private static int find(int node) {
        if (parent[node] == node)
            return node;
        int idx = find(parent[node]);
        parent[node] = idx;
        return idx;
    }
 
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int val;
 
        public Edge(int a, int b, int val) {
            super();
            this.a = a;
            this.b = b;
            this.val = val;
        }
 
        @Override
        public int compareTo(Edge o) {
            return this.val - o.val;
        }
 
    }
}