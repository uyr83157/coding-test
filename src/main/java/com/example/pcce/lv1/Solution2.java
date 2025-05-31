package com.example.pcce.lv1;

import java.util.Arrays;

public class Solution2 {
	// [PCCE 기출문제] 10번 / 공원

	 public int solution(int[] mats, String[][] park) {
		Arrays.sort(mats);

		int parkRows = park.length;
		if (parkRows == 0) {
			return -1;
		}
		int parkCols = park[0].length;
		if (parkCols == 0) {
			return -1;
		}

		for (int i = mats.length - 1; i >= 0; i--) {
			int matSize = mats[i];

			if (matSize <= 0 || matSize > parkRows || matSize > parkCols) {
				continue;
			}

			for (int r = 0; r <= parkRows - matSize; r++) {
				for (int c = 0; c <= parkCols - matSize; c++) {
					if (canPlace(park, r, c, matSize)) {
						return matSize;
					}
				}
			}
		}
		return -1;
	}

	private boolean canPlace(String[][] park, int startRow, int startCol, int matSize) {
		for (int i = 0; i < matSize; i++) {
			for (int j = 0; j < matSize; j++) {
				if (!park[startRow + i][startCol + j].equals("-1")) {
					return false;
				}
			}
		}
		return true;
	}
}
