package com.zq.exam;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-10-11:22:25
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n * m == 0) {
            return n + m;
        }
        int[][] nums = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            nums[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            nums[0][i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = nums[i][j - 1];
                int up = nums[i - 1][j];
                int left_up = nums[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_up += 1;
                }
                nums[i][j] = Math.min(left, Math.min(up, left_up));
            }
        }
        return nums[n][m];
    }
}
