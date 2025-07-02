package com.example.programmers_code_challenge2025.lv1;

public class Solution2 {
    // 택배 상자 꺼내기

    public int solution(int n, int w, int num) {
        int[][] positions = new int[n + 1][2];

        int boxNumber = 1;
        int row = 0;

        while (boxNumber <= n) {
            if (row % 2 == 0) {
                for (int col = 0; col < w; col++) {
                    if (boxNumber > n) break;
                    positions[boxNumber][0] = row;
                    positions[boxNumber][1] = col;
                    boxNumber++;
                }
            }
            else {
                for (int col = w - 1; col >= 0; col--) {
                    if (boxNumber > n) break;
                    positions[boxNumber][0] = row;
                    positions[boxNumber][1] = col;
                    boxNumber++;
                }
            }
            row++;
        }

        int targetRow = positions[num][0];
        int targetCol = positions[num][1];

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int currentRow = positions[i][0];
            int currentCol = positions[i][1];

            if (currentCol == targetCol && currentRow >= targetRow) {
                count++;
            }
        }

        return count;
    }
}
