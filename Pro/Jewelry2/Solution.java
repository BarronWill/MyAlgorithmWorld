import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static int N, Q, parent[], lines[][];
    public static long value[];

    public static int find (int a) {
        int nextNode = a;
        long sum = 0L;

        while (nextNode != parent[nextNode]) {
            sum += value[nextNode];
            nextNode = parent[nextNode];
        }

        value[a] = sum;
        parent[a] = nextNode;

        return nextNode;
    }

    public static long exe() {
        long result = 0L;
        for (int i = 1; i <= Q; i++) {
            int label = lines[i][1];
            int to = lines[i][2];
            int from = lines[i][3];
            int rootFrom = find(from);
            int rootTo = find(to);
            if (label == 1) {
                int weight  = lines[i][4];
                if (parent[from] != from) {
                    if (value[to] > value[from]) {
                        parent[rootFrom] = rootTo;
                        value[rootFrom] = weight + value[to] - value[from];
                    } else {
                        if (value[to] + weight < value[from]) {
                            parent[rootTo] = rootFrom;
                            value[rootTo] = value[from] - value[to] - weight;
                        } else {
                            parent[rootFrom] = rootTo;
                            value[rootFrom] = weight + value[to] - value[from];
                        }
                    }
                } else {
                    parent[from] = to;
                    value[from] = weight;
                }
            } else {
                if (rootFrom != rootTo) {
                    result += -1L;
                } else {
                    result += Math.abs(value[to] - value[from]);
                }
            }
        }
        return result;
    }

    public static void main (String[] args) throws Exception {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(ts.nextToken());
            Q = Integer.parseInt(ts.nextToken());
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            lines = new int[Q+1][5];
            value = new long[N + 1];
            for (int i = 1; i <= Q; i++) {
                ts = new StringTokenizer(reader.readLine());
                lines[i][1] = Integer.parseInt(ts.nextToken());
                lines[i][2] = Integer.parseInt(ts.nextToken());
                lines[i][3] = Integer.parseInt(ts.nextToken());
                if (lines[i][1] == 1) {
                    lines[i][4] = Integer.parseInt(ts.nextToken());
                }
            }

            System.out.println("#" + test + " " + exe());
        }
    }
}