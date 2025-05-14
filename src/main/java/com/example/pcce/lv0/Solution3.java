package com.example.pcce.lv0;

import java.util.Scanner;

public class Solution3 {
	// [PCCE 기출문제] 3번 / 수 나누기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int answer = 0;

		for(int i=0; number > 0; i++){
			answer += number % 100;
			number /= 100;
		}

		System.out.println(answer);
	}
}
