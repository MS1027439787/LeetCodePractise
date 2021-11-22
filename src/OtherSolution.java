public class OtherSolution {
    public static final int MAX = 1000;


    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 滚动数组方法
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 面试题：假设random()函数用于返回0-max（定值）之间的一个随机正整数，实现random（m,n）,返回m，n之间的随机数
     */

    public static int random(int m, int n) {
        return (int) random()*(n-m)/MAX + m;
    }
    public static int random() {
        return (int) (Math.random() * MAX);
    }

    /**
     * 不调用函数实现x的n次方
     */
    public static double myPow(double x, int n) {
        if (n == 1)
            return x;
        if (n == 0)
            return 1;
        if (n == -1)
            return 1 / x;
        return myPow(x, n / 2) * myPow(x, n / 2) * myPow(x, n % 2);
    }
}
