package com.example.programmers_code_challenge2025.lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution1 {
    // 지게차와 크레인

    int n, m;
    char[][] grid;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = storage[i].toCharArray();
        }

        for (String req : requests) {
            char item = req.charAt(0);
            if (req.length() == 2) {
                handleCrane(item);
            } else {
                handleForklift(item);
            }
        }

        int remainingCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '0') {
                    remainingCount++;
                }
            }
        }
        return remainingCount;
    }

    private void handleCrane(char item) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == item) {
                    grid[i][j] = '0';
                }
            }
        }
    }

    private void handleForklift(char item) {
        boolean[][] isOutside = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0' && (i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
                    if (!isOutside[i][j]) {
                        q.offer(new int[]{i, j});
                        isOutside[i][j] = true;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == '0' && !isOutside[nr][nc]) {
                    isOutside[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        List<int[]> toRemove = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == item) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        toRemove.add(new int[]{i, j});
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dr[k];
                        int nj = j + dc[k];
                        if (isOutside[ni][nj]) {
                            toRemove.add(new int[]{i, j});
                            break;
                        }
                    }
                }
            }
        }

        for (int[] pos : toRemove) {
            grid[pos[0]][pos[1]] = '0';
        }
    }
}
