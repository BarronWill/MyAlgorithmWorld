package Mario;

import java.io.*;
import java.util.*;

public class Solution {
    static int N, bomb[], gates[][], coin, max;
    static int bombDamage[] = {1, 2, 5};

    public static void throwBomb (int gate) {
        int currentBomb1 = bomb[1];
        int currentBomb2 = bomb[2];
        int currentBomb5 = bomb[3];
        int currentNewBomb = bomb[4];
        int petalLeft = gates[gate][2];
        
        int totalDamage = currentBomb1 * bombDamage[1] + currentBomb2 * bombDamage[2] + currentBomb5 * bombDamage[3]
        if (totalDamage < gates[gate][2]) return;

        for (int i = 1; i <= 3; i++) {
            while (bomb[i] > 0 && petalLeft > 0) {
                petalLeft -= bomb[i] * bombDamage[i];
                bomb[i]--;
            }
        }

        bomb[1] = bomb[2];
        bomb[2] = bomb[3];
        bomb[3] = bomb[4];
        bomb[4] = 0;

        doAction(gate + 1);

        bomb[1] = currentBomb1;
        bomb[2] = currentBomb2;
        bomb[3] = currentBomb5;
        bomb[4] = currentNewBomb;
    }

    public static void payCoin (int gate) {
        if (coin < gates[gate][2])  return;

        int currentBomb1 = bomb[1];
        int currentBomb2 = bomb[2];
        int currentBomb5 = bomb[3];
        int currentNewBomb = bomb[4];
        int currentCoin = coin;

        coin -= gates[gate][2];
        coin += gates[gate][3];

        bomb[1] = bomb[2];
        bomb[2] = bomb[3];
        bomb[3] = bomb[4];
        bomb[4] = gates[gate][4];

        doAction(gate + 1);

        bomb[1] = currentBomb1;
        bomb[2] = currentBomb2;
        bomb[3] = currentBomb5;
        bomb[4] = currentNewBomb;
        coin = currentCoin;
    }

    public static void giveBomb (int gate) {
        if (bomb[1] == 0 && bomb[2] == 0 && bomb[3] == 0 ) return;

        int currentBomb1 = bomb[1];
        int currentBomb2 = bomb[2];
        int currentBomb5 = bomb[3];
        int currentNewBomb = bomb[4];
        int currentCoin = coin;

        bomb[1] = bomb[2] = bomb[3] = 0;
        bomb[3] = gates[gate][4];
        coin += gates[gate][3];

        doAction(gate + 1) ;

        bomb[1] = currentBomb1;
        bomb[2] = currentBomb2;
        bomb[3] = currentBomb5;
        bomb[4] = currentNewBomb;
        coin = currentCoin;
    }

    public static void doAction (int gate) {
        if (gate > N + 1)  return;
        if (gate == N + 1) {
            max = coin > max ? coin : max;
            return;
        }

        giveBomb(gate);
        payCoin(gate);
        throwBomb(gate);
    }
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));

        int T = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(reader.readLine());
            gates = new int[N+1][5];
            for (int gate = 1; gate <= N; gate++) {
                StringTokenizer ts = new StringTokenizer(reader.readLine());
                for (int i = 1; i <= 4; i++) {
                    gates[gate][i] = Integer.parseInt(ts.nextToken());
                }
            }

            coin = 0;
            max = -1;
            bomb = new int[5];

            doAction(1);

            System.out.println("Case #" + test + ": " + max); 

        }
    }
}
