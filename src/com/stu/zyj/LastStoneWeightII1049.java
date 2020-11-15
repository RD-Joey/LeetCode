package com.stu.zyj;

public class LastStoneWeightII1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone: stones)
            sum += stone;
        int halfSum = sum >>> 1;
        int[] dp = new int[halfSum + 1];
        for (int currStone: stones) {
            for (int i = halfSum; i >= currStone; i--) {
                dp[i] = Math.max(dp[i], dp[i - currStone] + currStone);
            }
        }
        return sum - (dp[halfSum] << 1);
    }
}
