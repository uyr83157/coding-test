package com.example.pcce.lv1;

public class Solution1 {
	// [PCCE 기출문제] 9번 / 지폐 접기

	public int solution(int[] wallet, int[] bill) {
		int answer = 0;

		int currentBillW = bill[0];
		int currentBillH = bill[1];

		int walletDim1 = wallet[0];
		int walletDim2 = wallet[1];

		while (true) {
			boolean fitsOrientation1 = (currentBillW <= walletDim1 && currentBillH <= walletDim2);
			boolean fitsOrientation2 = (currentBillW <= walletDim2 && currentBillH <= walletDim1);

			if (fitsOrientation1 || fitsOrientation2) {
				break;
			}

			answer++;

			if (currentBillW >= currentBillH) {
				currentBillW /= 2;
			} else {
				currentBillH /= 2;
			}
		}

		return answer;
	}
}
