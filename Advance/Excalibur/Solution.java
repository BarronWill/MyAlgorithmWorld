import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, min, arr[], count = 0;
    static boolean visited[][][];
    static char map[][];
    static int dx[] = {0,0,1,-1};
    static int dy[] = {1,-1,0,0};

    static void show(char arr[][]) {
        for(int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }    
    }

    static public class State {
        int x, y, mask, step;
        State (int x, int y, int mask, int step) {
            this.x = x;
            this.y = y;
            this.mask = mask;
            this.step = step;
        }
    }

    static public int bfs(int locX, int locY) {
        Queue<State> queue = new LinkedList<>();
        visited = new boolean[N+1][M+1][8];
        queue.add(new State(locX, locY, 0, 0));
        visited[locX][locY][0] = true;

        while(!queue.isEmpty()) {
            State cur = queue.poll();
            count++;
            

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nextMask = cur.mask;

                if (nx <= 0 || ny <= 0 || nx > N || ny > M) continue;
                char cell = map[nx][ny];
                if (map[nx][ny] == 'S' && cur.mask == 0b111) {
                    return cur.step;
            }
                if (cell == 'X' && cur.mask != 0b111) continue;

                if (cell == 'A') nextMask |= 1;
                if (cell == 'B') nextMask |= 2;
                if (cell == 'C') nextMask |= 4;

                // if (visited[nx][ny][nextMask]) continue;

                // visited[nx][ny][nextMask] = true;

                queue.add(new State(nx, ny, nextMask, cur.step + 1));
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException{
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int testcase = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testcase; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(ts.nextToken());
            M = Integer.parseInt(ts.nextToken());
            int locX = Integer.parseInt(ts.nextToken());
            int locY = Integer.parseInt(ts.nextToken());
            min = Integer.MAX_VALUE;
            map = new char[N+1][M+1];
            for (int i = 1; i <= N; i++) {
                ts = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= M; j++) {
                    map[i][j] = ts.nextToken().charAt(0);
                }
            }

            System.out.println(bfs(locX, locY));
            System.out.println(count);
        }
        
    }
}
