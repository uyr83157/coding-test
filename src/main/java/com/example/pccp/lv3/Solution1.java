package com.example.pccp.lv3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {
    // [PCCP 기출문제] 4번 / 수식 복원하기

    public String[] solution(String[] expressions) {
        int maxDigit = 0;
        for (String exp : expressions) {
            String[] parts = exp.split(" ");

            for (char c : parts[0].toCharArray()) {
                maxDigit = Math.max(maxDigit, c - '0');
            }

            for (char c : parts[2].toCharArray()) {
                maxDigit = Math.max(maxDigit, c - '0');
            }

            if (!parts[4].equals("X")) {
                for (char c : parts[4].toCharArray()) {
                    maxDigit = Math.max(maxDigit, c - '0');
                }
            }
        }

        Set<Integer> possibleBases = new HashSet<>();
        for (int i = Math.max(2, maxDigit + 1); i <= 9; i++) {
            possibleBases.add(i);
        }

        for (String exp : expressions) {
            String[] parts = exp.split(" ");

            if (parts[4].equals("X")) {
                continue;
            }

            String strA = parts[0];
            String op = parts[1];
            String strB = parts[2];
            String strC = parts[4];

            Set<Integer> invalidBases = new HashSet<>();

            for (int base : possibleBases) {
                try {
                    long decA = Long.parseLong(strA, base);
                    long decB = Long.parseLong(strB, base);
                    long decC = Long.parseLong(strC, base);

                    long calculatedResult;
                    if (op.equals("+")) {
                        calculatedResult = decA + decB;
                    } else {
                        calculatedResult = decA - decB;
                    }

                    if (calculatedResult != decC) {
                        invalidBases.add(base);
                    }

                } catch (NumberFormatException e) {
                    invalidBases.add(base);
                }
            }
            possibleBases.removeAll(invalidBases);
        }

        List<String> answerList = new ArrayList<>();

        for (String exp : expressions) {
            String[] parts = exp.split(" ");

            if (!parts[4].equals("X")) {
                continue;
            }

            String strA = parts[0];
            String op = parts[1];
            String strB = parts[2];

            Set<String> resultsInDifferentBases = new HashSet<>();

            for (int base : possibleBases) {
                long decA = Long.parseLong(strA, base);
                long decB = Long.parseLong(strB, base);

                long resultDecimal;
                if (op.equals("+")) {
                    resultDecimal = decA + decB;
                } else {
                    resultDecimal = decA - decB;
                }

                resultsInDifferentBases.add(Long.toString(resultDecimal, base));
            }

            String finalResult;

            if (resultsInDifferentBases.size() == 1) {
                finalResult = resultsInDifferentBases.iterator().next();
            } else {
                finalResult = "?";
            }

            answerList.add(strA + " " + op + " " + strB + " = " + finalResult);
        }

        return answerList.toArray(new String[0]);
    }
}
