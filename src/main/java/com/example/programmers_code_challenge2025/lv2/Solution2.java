package com.example.programmers_code_challenge2025.lv2;

public class Solution2 {
    // 비밀 코드 해독

    private int n;
    private int[][] q;
    private int[] ans;
    private int finalAnswerCount;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.finalAnswerCount = 0;

        findCombinations(new int[5], 0, 1);

        return this.finalAnswerCount;
    }

    private void findCombinations(int[] currentCombination, int depth, int start) {
        if (depth == 5) {
            if (isAValidCandidate(currentCombination)) {
                finalAnswerCount++;
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            currentCombination[depth] = i;
            findCombinations(currentCombination, depth + 1, i + 1);
        }
    }

    private boolean isAValidCandidate(int[] candidateCode) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            int[] currentGuess = q[i];

            for (int guessNum : currentGuess) {
                for (int codeNum : candidateCode) {
                    if (guessNum == codeNum) {
                        matchCount++;
                        break;
                    }
                }
            }

            if (matchCount != ans[i]) {
                return false;
            }
        }

        return true;
    }
}
