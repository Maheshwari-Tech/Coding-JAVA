//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Codeforces;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class cf1999 {
    static final int MOD = 1000000007;
    private static final Scanner sc;
    static final int MAXSIZE = 200001;
    private static final Combinatorics combinatorics;

    public cf1999() {
    }

    public static void main(String[] args) {
        int testcase = sc.nextInt();

        for(int test = 0; test < testcase; ++test) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];

            int result;
            for(result = 0; result < n; ++result) {
                arr[result] = sc.nextInt();
            }

            result = medianSumSubsequenceOfLengthK(arr, k);
            System.out.println(result);
        }

    }

    static void getMissingNumber(int low, int high) {
        if (low == high) {
            query(low, high);
        } else {
            int oneThird = (high - low) / 3;
            int a = low + oneThird;
            int b = high - oneThird;
            int check = query(a, b);
            if (check != -1) {
                if (check == a * b) {
                    getMissingNumber(b + 1, high);
                } else if (check == (a + 1) * (b + 1)) {
                    getMissingNumber(low, a);
                } else {
                    getMissingNumber(a + 1, b);
                }

            }
        }
    }

    static int query(int a, int b) {
        if (a == b) {
            System.out.println("! " + a);
            System.out.flush();
            return -1;
        } else {
            System.out.println("? " + a + " " + b);
            System.out.flush();
            return sc.nextInt();
        }
    }

    static int medianSumSubsequenceOfLengthK(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int ans = 0;

        for(int i = 0; i < n; ++i) {
            int a = combinatorics.ncr(i, k / 2);
            int b = combinatorics.ncr(n - i - 1, k / 2);
            ans = cf1999.Combinatorics.add(cf1999.Combinatorics.multiply(cf1999.Combinatorics.multiply(a, b), arr[i]), ans);
        }

        return ans;
    }

    static int stepsForN(int n) {
        int x;
        for(x = 0; n > 0; n /= 3) {
            ++x;
        }

        return x;
    }

    static int stepsTillN(int n) {
        int steps = 0;
        int power3 = 1;
        int processed = 0;
        int currentSteps = 1;

        while(true) {
            int totalNumbers = Math.min(power3 * 3 - 1, n) - processed;
            if (totalNumbers == 0) {
                return steps;
            }

            steps += currentSteps * totalNumbers;
            processed += totalNumbers;
            ++currentSteps;
            power3 *= 3;
        }
    }

    static boolean canBeSubsequence(String s, String t, StringBuilder result) {
        int i = 0;
        int j = 0;

        while(j < t.length() && i < s.length()) {
            if (s.charAt(i) != t.charAt(j) && s.charAt(i) != '?') {
                result.append(s.charAt(i));
                ++i;
            } else {
                result.append(t.charAt(j));
                ++i;
                ++j;
            }
        }

        for(; i < s.length(); ++i) {
            if (s.charAt(i) == '?') {
                result.append('a');
            } else {
                result.append(s.charAt(i));
            }
        }

        return j == t.length();
    }

    static boolean isShoweringPossible(Interval[] intervals, int n, int s, int m) {
        Arrays.sort(intervals, cf1999.Interval.compareByStart());
        int prev = 0;
        int gap = 0;

        for(int i = 0; i < n; ++i) {
            gap = Math.max(gap, intervals[i].start - prev);
            prev = intervals[i].end;
        }

        gap = Math.max(gap, m - prev);
        return gap >= s;
    }

    private static int sumOfDigits(int n) {
        int sum;
        for(sum = 0; n > 0; n /= 10) {
            sum += n % 10;
        }

        return sum;
    }

    private static int waysWithWinnerA(int a1, int a2, int b1, int b2) {
        int ans = 0;
        int roundResult = compareRound(a1, b1, a2, b2);
        if (roundResult == 1) {
            ++ans;
        }

        roundResult = compareRound(a1, b2, a2, b1);
        if (roundResult == 1) {
            ++ans;
        }

        return 2 * ans;
    }

    private static int compareRound(int a, int b, int c, int d) {
        int res1 = Integer.compare(a, b);
        int res2 = Integer.compare(c, d);
        if (res1 + res2 > 0) {
            return 1;
        } else {
            return res1 + res2 < 0 ? -1 : 0;
        }
    }

    static {
        sc = new Scanner(System.in);
        combinatorics = new Combinatorics(200001);
    }

    static class Combinatorics {
        static final int MOD = 1000000007;
        int[] factorial;

        Combinatorics(int N) {
            this.factorial = new int[N + 1];
            this.factorial[0] = 1;

            for(int i = 1; i <= N; ++i) {
                this.factorial[i] = multiply(i, this.factorial[i - 1]);
            }

        }

        static int multiply(int a, int b) {
            return (int)((long)a * 1L * (long)b % 1000000007L);
        }

        static int add(int a, int b) {
            return a + b > 1000000007 ? a + b - 1000000007 : a + b;
        }

        int ncr(int n, int r) {
            if (r > n) {
                return 0;
            } else {
                int inv = power(multiply(this.factorial[r], this.factorial[n - r]), 1000000005);
                return multiply(this.factorial[n], inv);
            }
        }

        static int power(int a, int b) {
            int result;
            for(result = 1; b > 0; a = multiply(a, a)) {
                if ((b & 1) > 0) {
                    result = multiply(result, a);
                }

                b /= 2;
            }

            return result;
        }
    }

    static class Interval implements Comparable<Interval> {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval a) {
            return this.start - a.start;
        }

        static Comparator<Interval> compareByStart() {
            return (o1, o2) -> {
                return o1.start - o2.start;
            };
        }

        static Comparator<Interval> compareByEnd() {
            return (o1, o2) -> {
                return o1.end - o2.end;
            };
        }
    }
}
