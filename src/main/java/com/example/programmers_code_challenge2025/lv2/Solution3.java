package com.example.programmers_code_challenge2025.lv2;

import java.util.*;

public class Solution3 {
    // 완전범죄

    private void generateCombinations(int[][] items, int index, int traceA, int traceB, Map<Integer, Integer> combinations) {
        if (index == items.length) {
            combinations.put(traceA, Math.min(combinations.getOrDefault(traceA, Integer.MAX_VALUE), traceB));
            return;
        }

        generateCombinations(items, index + 1, traceA + items[index][0], traceB, combinations);
        generateCombinations(items, index + 1, traceA, traceB + items[index][1], combinations);
    }

    public int solution(int[][] info, int n, int m) {
        int numItems = info.length;
        int mid = numItems / 2;

        int[][] firstHalf = Arrays.copyOfRange(info, 0, mid);
        int[][] secondHalf = Arrays.copyOfRange(info, mid, numItems);

        Map<Integer, Integer> firstCombinations = new HashMap<>();
        generateCombinations(firstHalf, 0, 0, 0, firstCombinations);

        Map<Integer, Integer> secondCombinations = new HashMap<>();
        generateCombinations(secondHalf, 0, 0, 0, secondCombinations);

        List<int[]> secondProcessed = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : secondCombinations.entrySet()) {
            secondProcessed.add(new int[]{entry.getValue(), entry.getKey()});
        }

        secondProcessed.sort(Comparator.comparingInt(a -> a[0]));

        for (int i = 1; i < secondProcessed.size(); i++) {
            secondProcessed.get(i)[1] = Math.min(secondProcessed.get(i)[1], secondProcessed.get(i - 1)[1]);
        }

        int minTotalTraceA = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : firstCombinations.entrySet()) {
            int traceA1 = entry.getKey();
            int traceB1 = entry.getValue();

            if (traceA1 >= n || traceB1 >= m) {
                continue;
            }

            int requiredB2 = m - traceB1;

            int left = 0, right = secondProcessed.size() - 1;
            int bestIndex = -1;

            while (left <= right) {
                int searchMid = left + (right - left) / 2;
                if (secondProcessed.get(searchMid)[0] < requiredB2) {
                    bestIndex = searchMid;
                    left = searchMid + 1;
                } else {
                    right = searchMid - 1;
                }
            }

            if (bestIndex != -1) {
                int traceA2 = secondProcessed.get(bestIndex)[1];
                int totalTraceA = traceA1 + traceA2;

                if (totalTraceA < n) {
                    minTotalTraceA = Math.min(minTotalTraceA, totalTraceA);
                }
            }
        }

        return minTotalTraceA == Integer.MAX_VALUE ? -1 : minTotalTraceA;
    }
}
