import java.io.*;
import java.util.*;

public class Solution {
    static ArrayList<Set<Integer>> station2Lines;
    static int M, N, S, C;
    static boolean checked[];

    public static class State {
        int line; int step;
        public State(int line, int step) {
            this.line = line;
            this.step = step;
        }
    }

    public static void show() {
        for (int i = 1; i <= M; i++) {
            System.out.println("Node " + i + ": " + station2Lines.get(i));
        }
    }

    public static int solve(int S, int C) {
        Queue<State> q = new LinkedList<State>();
        for (int line : station2Lines.get(S)) {
            q.offer(new State(line, 0));
        }

        while (!q.isEmpty()) {
            State currentNode = q.poll();

            if (checked[currentNode.line] == true){
                continue;
            }

            if (station2Lines.get(C).contains(currentNode.line)) {
                return currentNode.step;
            }

            checked[currentNode.line] = true;

            for (int node = 1; node <= M; node++) {
                if (station2Lines.get(node).contains(currentNode.line) == false) continue;
                for (int line : station2Lines.get(node)) {
                    if (checked[line] == true) {
                        continue;
                    }
                    q.offer(new State(line, currentNode.step + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(ts.nextToken());
            N = Integer.parseInt(ts.nextToken());
            S = Integer.parseInt(ts.nextToken());
            C = Integer.parseInt(ts.nextToken());

            int nums[] = new int[N+1];
            ts = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= N; i++) {
                nums[i] = Integer.parseInt(ts.nextToken());
            }

            station2Lines = new ArrayList<>();
            for (int i = 0; i <= M; i++) {
                station2Lines.add(new HashSet<>());
}
            for (int i = 1; i <= N; i++) {
                ts = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= nums[i]; j++) {
                    station2Lines.get(Integer.parseInt(ts.nextToken())).add(i);
                }
            }

            checked = new boolean[M+1];

            System.out.println(solve(S, C));
        }
    }
}
