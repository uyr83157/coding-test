package com.example.pccp.lv2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution3 {
    // [PCCP 기출문제] 2번 / 석유 시추

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;

        boolean[][] visited = new boolean[n][m];

        int[] oilPerColumn = new int[m];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    int lumpSize = 0;
                    Set<Integer> touchedColumns = new HashSet<>();

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];

                        lumpSize++;
                        touchedColumns.add(y);

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                if (land[nx][ny] == 1 && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    queue.add(new int[]{nx, ny});
                                }
                            }
                        }
                    }

                    for (int col : touchedColumns) {
                        oilPerColumn[col] += lumpSize;
                    }
                }
            }
        }

        int maxOil = 0;
        for (int oil : oilPerColumn) {
            if (oil > maxOil) {
                maxOil = oil;
            }
        }

        return maxOil;
    }
}
