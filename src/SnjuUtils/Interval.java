//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package SnjuUtils;

import java.util.Comparator;

class Interval implements Comparable<Interval> {
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
