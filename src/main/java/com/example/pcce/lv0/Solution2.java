package com.example.pcce.lv0;

import java.util.Scanner;

public class Solution2 {

	// [PCCE 기출문제] 2번 / 각도 합치기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int angle1 = sc.nextInt();
		int angle2 = sc.nextInt();

		int sum_angle = (angle1 + angle2) % 360;
		System.out.println(sum_angle);
	}

}
