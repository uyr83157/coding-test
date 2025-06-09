package com.example.pccp.lv1;

public class Solution1 {
    // [PCCP 기출문제] 1번 / 동영상 재생기

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        // 초 단위로 변환
        int videoLenSec = timeToSeconds(video_len);
        int currentPosSec = timeToSeconds(pos);
        int opStartSec = timeToSeconds(op_start);
        int opEndSec = timeToSeconds(op_end);

        // 자동 오프닝 건너뛰기
        if (currentPosSec >= opStartSec && currentPosSec <= opEndSec) {
            currentPosSec = opEndSec;
        }

        // 명령어 실행
        for (String command : commands) {
            if (command.equals("prev")) {
                currentPosSec -= 10;

                if (currentPosSec < 0) {
                    currentPosSec = 0;
                }
            } else if (command.equals("next")) {
                currentPosSec += 10;

                if (currentPosSec > videoLenSec) {
                    currentPosSec = videoLenSec;
                }
            }

            if (currentPosSec >= opStartSec && currentPosSec <= opEndSec) {
                currentPosSec = opEndSec;
            }
        }

        return secondsToTime(currentPosSec);
    }

    private int timeToSeconds(String timeStr) {
        String[] parts = timeStr.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    private String secondsToTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
