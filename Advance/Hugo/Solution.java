import java.io.*;
import java.util.*;

public class Solution {
    static final int MAX = 16;
    static int T, n ,m, max, hugo[][], fire[][], exitway[][], diamond[][], lake[][];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean isEscape;

    static int maxDinamonds;
    static boolean isHugoEscape;
    static int sr, sc;
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void show(int arr[][], int n, int m) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfsFire() {
        Queue<Point> q = new LinkedList<>();
        
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (fire[i][j] == 1) {
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // Above the boundaries
                if(nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
                // Violate the rules
                if(lake[nx][ny] == 1 || fire[nx][ny] != 0) continue;

                q.add(new Point(nx, ny));
                fire[nx][ny] = fire[p.x][p.y] + 1;
            }
        }
    }

    public static void run(int x, int y, int step, int sum) {
        if (x <= 0 || y <= 0 || x > n || y > m) return;

        if ((fire[x][y] != 0 && fire[x][y] <= step) || hugo[x][y] == 1) return;
        
        if (exitway[x][y] == 1) {
            isEscape = true;
            max = max < sum + diamond[x][y] ? sum + diamond[x][y] : max;
        }

        hugo[x][y] = 1;
        int delay = 1;
        if (lake[x][y] == 1) {
            delay++;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            run(nx, ny, step+delay, sum + diamond[x][y]);

            hugo[x][y] = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(ts.nextToken());
            m = Integer.parseInt(ts.nextToken());
            int locX = Integer.parseInt(ts.nextToken());
            int locY = Integer.parseInt(ts.nextToken());

            fire = new int[n+1][m+1];
            lake = new int[n+1][m+1];
            exitway = new int[n+1][m+1];
            diamond = new int[n+1][m+1];
            hugo = new int[n+1][m+1];
            isEscape = false;
            max = Integer.MIN_VALUE;

            ts = new StringTokenizer(reader.readLine());
            int nums = Integer.parseInt(ts.nextToken());
            for(int i = 1; i <= nums; i++) {
                int x = Integer.parseInt(ts.nextToken());
                int y = Integer.parseInt(ts.nextToken());
                fire[x][y] = 1;
            }

            ts = new StringTokenizer(reader.readLine());
            nums = Integer.parseInt(ts.nextToken());
            for(int i = 1; i <= nums; i++) {
                int x = Integer.parseInt(ts.nextToken());
                int y = Integer.parseInt(ts.nextToken());
                lake[x][y] = 1;
            }

            ts = new StringTokenizer(reader.readLine());
            nums = Integer.parseInt(ts.nextToken());
            for(int i = 1; i <= nums; i++) {
                int x = Integer.parseInt(ts.nextToken());
                int y = Integer.parseInt(ts.nextToken());
                exitway[x][y] = 1;
            }

            for(int i = 1; i <= n; i++) {
                ts = new StringTokenizer(reader.readLine());
                for(int j = 1; j <= m; j++) {
                    diamond[i][j] = Integer.parseInt(ts.nextToken());
                }
            }   
            
            bfsFire();
            run(locX, locY, 1, 0);
            int ans = isEscape == true ? max : -1;
            System.out.println(ans);
        }
    }
}