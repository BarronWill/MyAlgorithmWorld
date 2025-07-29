/*
 * In excel, when set up correctly, if you change the value of a particular cell, the valus of other cells will be changed according to the function or algorithm used.
 * Write code to caltulate the total number of cells in which the values are also changed, when you change the value of a particular cell.
 * Note that you must count the initail cell you change when couting the total of cells changed
 * Input:
 * T test case
 * 3 positive integer N (number of cells), M (influence information between two cells), and C (total number of initial cells changed)
 * (2 <= N <= 1000), (1 <= M < 1000), (1 <= C <= 100)
 * For the next M lines, 2 values X and Y are given, which means if cell X is changed, cell Y is also changed.
 * Note that the above information does not mean: if cell Y changed, Cell X is also changed
 * For the next C lines, the initial cells chagned are given
 * No circular reference is input in this question
 */

import java.util.*;
import java.io.*;

public class Solution {
    static int N, M, C, count;
    static List<HashSet<Integer>> map;
    static List<Integer> start;
    static boolean checked[];

    public static void show () {
        for (int i = 1; i <= N; i++) {
            System.out.println("Node: " + i + " " + map.get(i));
        }
    }

    public static void run (int index) {
        if (checked[index]) return;
        checked[index] = true;
        count++;
        for (int v : map.get(index)) {
            run(v);
        }
    }

    public static void exe () {
        for (int i = 0; i < C; i++) {
            int currentCell = start.get(i);
            if (checked[currentCell])   continue;
            run(currentCell);
        }
    }
    public static void main(String[] args) throws IOException{
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer ts = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(ts.nextToken());
            M = Integer.parseInt(ts.nextToken());
            C = Integer.parseInt(ts.nextToken());

            map = new ArrayList<>();
            for (int cell = 0; cell <= N; cell++) {
                map.add(new HashSet<>());
            }

            for (int cell = 1; cell <= M; cell++) {
                ts = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(ts.nextToken());
                int to = Integer.parseInt(ts.nextToken());

                map.get(from).add(to);
            }

            start = new ArrayList<>();
            for (int cell = 1; cell <= C; cell++) {
                start.add(Integer.parseInt(reader.readLine()));
            }

            count = 0;
            checked = new boolean[N+1];
            //show();
            exe();
            System.out.println(count);
        }
    }
}
