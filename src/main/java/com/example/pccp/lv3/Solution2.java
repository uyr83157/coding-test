package com.example.pccp.lv3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution2 {
    class State {
        int rr, rc, br, bc, rMask, bMask, turn;

        public State(int rr, int rc, int br, int bc, int rMask, int bMask, int turn) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.rMask = rMask;
            this.bMask = bMask;
            this.turn = turn;
        }
    }

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int n, m;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        int rStartR = 0, rStartC = 0, bStartR = 0, bStartC = 0;
        int rGoalR = 0, rGoalC = 0, bGoalR = 0, bGoalC = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    rStartR = i;
                    rStartC = j;
                } else if (maze[i][j] == 2) {
                    bStartR = i;
                    bStartC = j;
                } else if (maze[i][j] == 3) {
                    rGoalR = i;
                    rGoalC = j;
                } else if (maze[i][j] == 4) {
                    bGoalR = i;
                    bGoalC = j;
                }
            }
        }

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        int initialRMask = 1 << (rStartR * m + rStartC);
        int initialBMask = 1 << (bStartR * m + bStartC);

        State startState = new State(rStartR, rStartC, bStartR, bStartC, initialRMask, initialBMask, 0);
        queue.add(startState);
        visited.add(getStateKey(startState));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.rr == rGoalR && current.rc == rGoalC && current.br == bGoalR && current.bc == bGoalC) {
                return current.turn;
            }

            for (int i = 0; i < 4; i++) {
                int nrr = current.rr;
                int nrc = current.rc;
                boolean redAtGoal = (current.rr == rGoalR && current.rc == rGoalC);

                if (!redAtGoal) {
                    nrr += dr[i];
                    nrc += dc[i];
                }

                for (int j = 0; j < 4; j++) {
                    int nbr = current.br;
                    int nbc = current.bc;
                    boolean blueAtGoal = (current.br == bGoalR && current.bc == bGoalC);

                    if (!blueAtGoal) {
                        nbr += dr[j];
                        nbc += dc[j];
                    }

                    if (nrr < 0 || nrr >= n || nrc < 0 || nrc >= m || maze[nrr][nrc] == 5) continue;
                    if (nbr < 0 || nbr >= n || nbc < 0 || nbc >= m || maze[nbr][nbc] == 5) continue;

                    if (!redAtGoal && (current.rMask & (1 << (nrr * m + nrc))) != 0) continue;
                    if (!blueAtGoal && (current.bMask & (1 << (nbr * m + nbc))) != 0) continue;

                    if (nrr == nbr && nrc == nbc) continue;

                    if (nrr == current.br && nrc == current.bc && nbr == current.rr && nbc == current.rc) continue;

                    int nextRMask = redAtGoal ? current.rMask : (current.rMask | (1 << (nrr * m + nrc)));
                    int nextBMask = blueAtGoal ? current.bMask : (current.bMask | (1 << (nbr * m + nbc)));

                    State nextState = new State(nrr, nrc, nbr, nbc, nextRMask, nextBMask, current.turn + 1);
                    String nextStateKey = getStateKey(nextState);

                    if (!visited.contains(nextStateKey)) {
                        visited.add(nextStateKey);
                        queue.add(nextState);
                    }
                }
            }
        }

        return 0;
    }

    private String getStateKey(State s) {
        return s.rr + "," + s.rc + "," + s.br + "," + s.bc + "," + s.rMask + "," + s.bMask;
    }
}
