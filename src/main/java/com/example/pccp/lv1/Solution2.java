package com.example.pccp.lv1;

public class Solution2 {
    // [PCCP 기출문제] 1번 / 붕대 감기

    public int solution(int[] bandage, int health, int[][] attacks) {
        int castTime = bandage[0];
        int healPerSec = bandage[1];
        int bonusHeal = bandage[2];
        int maxHealth = health;
        int currentHealth = health;
        int lastAttackTime = 0;

        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];
            int timeDiff = attackTime - lastAttackTime - 1;

            if (timeDiff > 0) {
                currentHealth += timeDiff * healPerSec;
                int bonusCount = timeDiff / castTime;
                currentHealth += bonusCount * bonusHeal;

                if (currentHealth > maxHealth) {
                    currentHealth = maxHealth;
                }
            }

            currentHealth -= damage;

            if (currentHealth <= 0) {
                return -1;
            }

            lastAttackTime = attackTime;
        }

        return currentHealth;
    }
}
