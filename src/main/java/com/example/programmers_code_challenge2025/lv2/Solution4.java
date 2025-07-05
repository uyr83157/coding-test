package com.example.programmers_code_challenge2025.lv2;

public class Solution4 {
    // 서버 증설 횟수

    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] addedServers = new int[24];

        for (int hour = 0; hour < 24; hour++) {
            int requiredServers = players[hour] / m;

            int runningServers = 0;
            for (int i = 1; i < k; i++) {
                if (hour - i >= 0) {
                    runningServers += addedServers[hour - i];
                }
            }

            if (requiredServers > runningServers) {
                int newServers = requiredServers - runningServers;
                addedServers[hour] = newServers;
                answer += newServers;
            }
        }
        return answer;
    }
}
