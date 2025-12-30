import java.io.*;
import java.util.*;

class Solution {
    static int V, E, K, S, totalStep;
    static boolean visited[];
    static List<Set<Integer>> node2Nodes;

    public static class Node {
        int index; int step;
        public Node (int index, int step) {
            this.index = index;
            this.step = step;
        }
    }

    public static int bfs () {
        Queue<Node> queue = new LinkedList<>();
        int checked[] = new int[V+1];   // đánh dấu đỉnh đã đến với số bước nào
        visited = new boolean[V+1];     // đánh dấu đỉnh đã đến đúng totalStep bước
        int ans = 0;

        Arrays.fill(checked, -1);

        queue.add(new Node(S, 0));  // bắt đầu từ S, bước 0

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            // Nếu đã duyệt đỉnh này với cùng số bước thì bỏ qua
            if (checked[cur.index] == cur.step) continue;
            if (cur.step == totalStep) {
                visited[cur.index] = true;  // đến đích với đủ bước
                continue;
            }

            checked[cur.index] = cur.step;

            // Duyệt tất cả đỉnh kề
            for (int node : node2Nodes.get(cur.index)) {
                queue.offer(new Node(node, cur.step+1));
            }
        }

        // Đếm số đỉnh đã visited
        for (int i = 1; i <= V; i++) {
            if (visited[i] == false)    continue;
            ans++;
        }

        return ans;
    }
    public static void main (String[] args) throws IOException {
        
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            V = Integer.parseInt(ts.nextToken());
            E = Integer.parseInt(ts.nextToken());
            K = Integer.parseInt(ts.nextToken());
            S = Integer.parseInt(ts.nextToken());

            node2Nodes = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                node2Nodes.add(new HashSet<>());
            }

            for (int i = 1; i <= E; i++) {
                ts = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(ts.nextToken());
                int to = Integer.parseInt(ts.nextToken());

                Set<Integer> seList1 = node2Nodes.get(from);
                seList1.add(to);
                Set<Integer> seList2 = node2Nodes.get(to);
                seList2.add(from);
            }

            ts = new StringTokenizer(reader.readLine());
            totalStep = 0;
            for (int i = 1; i <= K; i++) {
                totalStep += Integer.parseInt(ts.nextToken());
            }

            System.out.println("#" + test + " " + bfs());
            
        }
    }
}