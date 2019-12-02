package com.zq.autumn;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author apple
 * @date 2019-08-01:23:05
 */
public class MySqrt {

    private static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1;
        int right = x / 2;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            int target = mid * mid;
            if (target > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
