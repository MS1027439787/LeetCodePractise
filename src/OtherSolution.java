public class OtherSolution {


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
