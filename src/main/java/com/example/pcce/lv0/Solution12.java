package com.example.pcce.lv0;

import java.util.Scanner;

public class Solution12 {
	// [PCCE 기출문제] 4번 / 저축

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt();
		int before = sc.nextInt();
		int after = sc.nextInt();

		int money = start;
		int month = 1;
		while (money < 70) {
			money += before;
			month++;
		}
		while (money < 100) {
			money += after;
			month++;
		}

		System.out.println(month);
	}
}
