package com.zq.autumn;

import java.util.Arrays;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author apple
 * @date 2019-07-23:10:12
 */
public class MinCoin {

    private static Integer getMin(int[] nums, int amount) {
        int[] dp = new int[amount + 1];
        dp[0]=0;
        for (int i = 1; i <= amount; i++) {
            dp[i]=Integer.MAX_VALUE;
        }

        for(int i=1;i<=amount;i++) {
            for (int num : nums) {
                if (i >= num) {
                    if (dp[i - num] != -1 && dp[i - num] + 1 < dp[i]) {
                        dp[i] = dp[i - num] + 1;
                    }
                }

            }
            if(dp[i]==Integer.MAX_VALUE){
                dp[i]=-1;
            }
        }
        return dp[amount];
    }

    public static void main (String[]args){
        int[] nums = new int[]{2};
        int amount = 6;
        Arrays.sort(nums);
        System.out.println(getMin(nums, amount).toString());

    }
}
