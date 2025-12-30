/*
 * Nếu con mã cùng 1 bước trong nhiều lượt liên tiếp thì chỉ được tính như 1 bước. Hãy chênh lệch số bước mà con mã và kỳ lân ăn được mục tiêu
 */
import java.util.*;
import java.io.*;

public class Solution {
    static int N, R, C, U, L, checked[][][];
    static Queue<Point> q;
    static int dx[] = {0, 1, 1, -1, -1, 2, 2, -2, -2};
    static int dy[] = {0, 2, -2, 2, -2, 1, -1, 1, -1};

    static class Point {
        int x; int y; int knightStep; int unicornStep; int way; int count;
        public Point (int x, int y, int way, int knightStep, int unicornStep, int count) {
            this.x = x;
            this.y = y;
            this.way = way;
            this.knightStep = knightStep;
            this.unicornStep = unicornStep;
            this.count = count;
        }
    }
    public static int run (int x, int y) {
        q.offer(new Point(x, y, 0, 0, 0, 3));

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.x <= 0 || p.y <= 0 || p.x > N || p.y > N) continue;
            
            if (p.x == U && p.y == L) {
                return p.knightStep - p.unicornStep;
            }

            if (checked[p.x][p.y][p.way] != 0 && checked[p.x][p.y][p.way] <= p.unicornStep)  continue;

            checked[p.x][p.y][p.way] = p.unicornStep;

            for (int i = 1; i <= 8; i++) {
                int delay = 1;
                int count = 3;

                if (p.way == i && p.count > 1) {
                    delay = 0;
                    count = p.count - 1;
                }

                q.offer(new Point(
                    p.x + dx[i], p.y + dy[i], i,
                    p.knightStep + 1,
                    p.unicornStep + delay,
                    count
                ));
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++ ){
            StringTokenizer ts = new StringTokenizer(reader.readLine());

            N = Integer.parseInt(ts.nextToken());
            R = Integer.parseInt(ts.nextToken());
            C = Integer.parseInt(ts.nextToken());
            U = Integer.parseInt(ts.nextToken());
            L = Integer.parseInt(ts.nextToken());

            checked = new int[N+1][N+1][9];
            q = new LinkedList<Point>();

            System.out.println("#" + test + " " + run(R,C));
        }
    }
}
