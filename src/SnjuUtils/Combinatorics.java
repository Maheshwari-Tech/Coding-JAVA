package SnjuUtils;
import java.util.Arrays;

public class Combinatorics {
    static final int MOD = 1000000007;
    static final int MAX_N = 100000;
    int[] factorial;

    Combinatorics(int N) {
        this.factorial = new int[N + 1];
        this.factorial[0] = 1;

        for(int i = 1; i <= N; ++i) {
            this.factorial[i] = this.multiply(i, this.factorial[i - 1]);
        }

    }

    int multiply(int a, int b) {
        return (int)((long)a * 1L * (long)b % 1000000007L);
    }

    int add(int a, int b) {
        return a + b > 1000000007 ? a + b - 1000000007 : a + b;
    }

    int ncr(int n, int r) {
        if (r > n) {
            return 0;
        } else {
            int inv = this.power(this.multiply(this.factorial[r], this.factorial[n - r]), 1000000005);
            System.out.println(inv);
            return this.multiply(this.factorial[n], inv);
        }
    }

    int power(int a, int b) {
        int result;
        for(result = 1; b > 0; a = this.multiply(a, a)) {
            if ((b & 1) > 0) {
                result = this.multiply(result, a);
            }

            b /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        Combinatorics c = new Combinatorics(20);
        System.out.println(Arrays.toString(c.factorial));
        System.out.println(c.ncr(10, 3));
    }
}

