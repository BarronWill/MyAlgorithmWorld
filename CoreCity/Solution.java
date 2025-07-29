/*
 * Find core city that indispensable and import position
 * Constraints
 * 3 <= V <= 10000
 * V-1 <= E <= 300000
 * There is no direction of a edge
 * The inforamtion of the same edge is not given multiple times
 * The edge that returns to itself is not given
 * Input
 * T test
 * The total number of vertices V in the graph , total number E of edges are given
 * Edge information
 * Output
 * Print core city
 * 
 * Test
 * #1 4 1 2 7 8
 * #2 0
 */

import java.util.*;
import java.io.*;

public class Solution {
    static int N, M, ids[], low[], idCounter;
    static boolean checked[];
    static List<Edge> bridges;
    static List<Set<Integer>> map;
    public static class Edge {
        int from; int to;
        public Edge (int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void show () {
        // for (Edge v : arr) {
        //     System.out.println("Node " + " " + v.from + " " + v.to);
        // }
        for (int i = 1; i <= N; i++) {
            System.out.print(low[i] + " ");
        }
    }

    public static void dfs(int at, int parent) {
        checked[at] = true;
        ids[at] = low[at] = idCounter++;
        System.out.println(at + " " + parent);
        for (int to : map.get(at)) {
            if (to == parent) continue;

            if (!checked[to]) {
                dfs(to, at);
                low[at] = Math.min(low[at], low[to]);
                

                if (ids[at] < low[to]) {
                    bridges.add(new Edge(at, to));
                }
            } else {
                low[at] = Math.min(low[at], ids[to]); // back edge
            }
        }
    }
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= 1; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(ts.nextToken());
            M = Integer.parseInt(ts.nextToken());

            map = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                map.add(new HashSet<>());
            }

            for (int i = 1; i <= M; i++) {
                ts = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(ts.nextToken());
                int to = Integer.parseInt(ts.nextToken());

                map.get(from).add(to);
                map.get(to).add(from);
            }
            bridges = new ArrayList<>();
            checked = new boolean[N+1];
            ids = new int[N+1];
            low = new int[N+1];
            idCounter = 0;
            dfs(1, -1);
            show();
            
        }
    }
}
