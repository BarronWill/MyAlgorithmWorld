import java.io.*;
import java.util.*;

public class Solution {
        static int N, M, ordering[];
        static boolean checked[];
        static List<Set<Integer>> graph;

        public static void show() {
            for (int i = 1; i <= N; i++) {
                System.out.println(i + ": " + graph.get(i));
            }
        }

        public static void topoSort() {
            checked = new boolean[N+1];
            ordering = new int[N+1];
            int i = N;
            for (int at = 1; at <= N; at++) {
                if (checked[at] == true) continue;
                dfs(i, at);
            }
        }

        public static void dfs(int i, int at) {
            checked[at] = true;
            for (int v : graph.get(at)) {
                if (checked[v] == true) continue;
                dfs(i - 1, v);
            }
            ordering[i] = at;
        }

        public static void main (String[] args) throws IOException {
            FileInputStream file = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));

            int T = Integer.parseInt(reader.readLine());
            for (int test = 1; test <= T; test++) {
                StringTokenizer ts = new StringTokenizer(reader.readLine());
                N = Integer.parseInt(ts.nextToken());
                M = Integer.parseInt(ts.nextToken());

                graph = new ArrayList<>();
                for (int i = 0; i <= N; i++) {
                    graph.add(new HashSet<>());
                }
                
                for (int edge = 1; edge <= M; edge++) {
                    ts = new StringTokenizer(reader.readLine());
                    int from = Integer.parseInt(ts.nextToken());
                    int to = Integer.parseInt(ts.nextToken());

                    graph.get(from).add(to);
                }
                show();
                topoSort();
                for (int i = 1; i <= N; i++) {
                    System.out.print(ordering[i] + " ");
                }
                System.out.println();
            }
        }
}
