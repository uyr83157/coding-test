package com.example.pccp.lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    // [PCCP 기출문제] 3번 / 충돌위험 찾기

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int numRobots = routes.length;
        int numPoints = points.length;

        int[][] coords = new int[numPoints + 1][2];

        for (int i = 0; i < numPoints; i++) {
            coords[i + 1] = points[i];
        }

        List<List<int[]>> allRobotPaths = new ArrayList<>();

        int maxTime = 0;

        for (int[] route : routes) {
            List<int[]> path = generateFullPath(route, coords);
            allRobotPaths.add(path);
            maxTime = Math.max(maxTime, path.size());
        }

        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> positionsAtTimeT = new HashMap<>();

            for (int i = 0; i < numRobots; i++) {
                List<int[]> currentPath = allRobotPaths.get(i);

                if (t >= currentPath.size()) {
                    continue;
                }

                int[] pos = currentPath.get(t);
                String posKey = pos[0] + "," + pos[1];
                positionsAtTimeT.put(posKey, positionsAtTimeT.getOrDefault(posKey, 0) + 1);
            }

            for (int count : positionsAtTimeT.values()) {
                if (count > 1) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private List<int[]> generateFullPath(int[] route, int[][] coords) {
        List<int[]> path = new ArrayList<>();

        int[] startPointCoords = coords[route[0]];
        path.add(startPointCoords);

        for (int i = 0; i < route.length - 1; i++) {
            int[] currentPos = path.get(path.size() - 1);
            int currentR = currentPos[0];
            int currentC = currentPos[1];

            int[] nextPointCoords = coords[route[i + 1]];
            int targetR = nextPointCoords[0];
            int targetC = nextPointCoords[1];

            while (currentR != targetR) {
                currentR += (targetR > currentR) ? 1 : -1;
                path.add(new int[]{currentR, currentC});
            }

            while (currentC != targetC) {
                currentC += (targetC > currentC) ? 1 : -1;
                path.add(new int[]{currentR, currentC});
            }
        }
        return path;
    }
}
