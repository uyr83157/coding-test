package com.example.pccp.lv2;

public class Solution1 {
    // [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 200000;
        int minLevel = 0;

        while (left <= right) {
            int midLevel = left + (right - left) / 2;

            if (canSolveWithinLimit(midLevel, diffs, times, limit)) {
                minLevel = midLevel;
                right = midLevel - 1;
            } else {
                left = midLevel + 1;
            }
        }
        return minLevel;
    }

    private boolean canSolveWithinLimit(int level, int[] diffs, int[] times, long limit) {
        long totalTime = 0;
        totalTime += times[0];

        if (totalTime > limit) {
            return false;
        }

        for (int i = 1; i < diffs.length; i++) {
            long currentDiff = diffs[i];
            long currentTime = times[i];

            if (currentDiff <= level) {
                totalTime += currentTime;
            } else {
                long prevTime = times[i - 1];
                long failures = currentDiff - level;
                long puzzleTime = failures * (currentTime + prevTime) + currentTime;
                totalTime += puzzleTime;
            }

            if (totalTime > limit) {
                return false;
            }
        }

        return true;
    }
}
