package com.example.pccp.lv2;

public class Solution4 {
    // [PCCP 기출문제] 3번 / 아날로그 시계

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        if (h1 == 0 && m1 == 0 && s1 == 0) {
            return getAlarm(h2, m2, s2);
        }

        answer = getAlarm(h2, m2, s2) - getAlarm(h1, m1, s1);

        if (m1 == 0 && s1 == 0) {
            answer++;
        }

        return answer;
    }

    int getAlarm(int h, int m, int s) {
        if (h >= 12) {
            return getAlarm(11, 59, 59) + getAlarm(h - 12, m, s);
        }

        int count = h * 2 * 59 + h + m * 2 - 1;
        int totalSeconds = h * 3600 + m * 60 + s;
        int secondPosition = s * 720;
        int minutePosition = (totalSeconds * 12) % 43200;
        int hourPosition = totalSeconds % 43200;

        if (secondPosition >= minutePosition) {
            count++;
        }

        if (secondPosition >= hourPosition) {
            count++;
        }

        return count;
    }
}
