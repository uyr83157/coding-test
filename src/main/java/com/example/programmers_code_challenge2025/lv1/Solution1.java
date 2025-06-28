package com.example.programmers_code_challenge2025.lv1;

public class Solution1 {
    // 유연근무제

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int employeeCount = schedules.length;

        for (int i = 0; i < employeeCount; i++) {
            boolean isEligible = true;
            int desiredTime = schedules[i];

            int desiredHour = desiredTime / 100;
            int desiredMinute = desiredTime % 100;

            int deadlineMinute = desiredMinute + 10;
            int deadlineHour = desiredHour;

            if (deadlineMinute >= 60) {
                deadlineHour += 1;
                deadlineMinute -= 60;
            }

            int deadlineTime = deadlineHour * 100 + deadlineMinute;

            for (int j = 0; j < 7; j++) {
                int currentDayOfWeek = ((startday - 1) + j) % 7 + 1;

                if (currentDayOfWeek >= 1 && currentDayOfWeek <= 5) {
                    int actualLogTime = timelogs[i][j];

                    if (actualLogTime > deadlineTime) {
                        isEligible = false;
                        break;
                    }
                }
            }

            if (isEligible) {
                answer++;
            }
        }

        return answer;
    }
}
