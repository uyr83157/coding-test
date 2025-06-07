package com.example.pcce.lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution4 {
    // [PCCE 기출문제] 10번 / 데이터 분석

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> index = new HashMap<>();
        index.put("code", 0);
        index.put("date", 1);
        index.put("maximum", 2);
        index.put("remain", 3);

        int extIdx = index.get(ext);
        int sortByIdx = index.get(sort_by);

        return Arrays.stream(data)
                .filter(row -> row[extIdx] < val_ext)
                .sorted(Comparator.comparingInt(row -> row[sortByIdx]))
                .toArray(int[][]::new);
    }
}
