package com.example.pcce.lv1;

public class Solution3 {
    // [PCCE 기출문제] 9번 / 이웃한 칸

    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        int n = board.length;

        String targetColor = board[h][w];

        int[] dh = {0, 1, -1, 0};
        int[] dw = {1, 0, 0, -1};

        for (int i = 0; i < 4; i++) {
            int h_check = h + dh[i];
            int w_check = w + dw[i];

            if (h_check >= 0 && h_check < n && w_check >= 0 && w_check < n) {
                if (board[h_check][w_check].equals(targetColor)) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
