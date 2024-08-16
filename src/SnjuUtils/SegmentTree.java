//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package SnjuUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SegmentTree {
    private static final Scanner sc;
    Node root;

    public SegmentTree(int N, int[] arr) {
        this.root = this.create(0, N - 1, arr, (Node)null);
    }

    private Node create(int low, int high, int[] arr, Node par) {
        if (low > high) {
            return null;
        } else if (low == high) {
            return new Node(low, high, arr[low], par);
        } else {
            int mid = (low + high) / 2;
            Node n = new Node(low, high, par);
            n.left = this.create(low, mid, arr, n);
            n.right = this.create(mid + 1, high, arr, n);
            int min_value = Integer.MAX_VALUE;
            if (n.left != null) {
                min_value = n.left.value;
            }

            if (n.right != null) {
                min_value = Math.min(min_value, n.right.value);
            }

            n.value = min_value;
            return n;
        }
    }

    public void update(int index, int value) {
        this.update(index, value, this.root);
    }

    private void update(int index, int value, Node root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                root.value = value;
            }

            if (root.left != null && root.left.high < index) {
                this.update(index, value, root.right);
            } else {
                this.update(index, value, root.left);
            }

            root.value = Math.min(root.left.value, root.right.value);
        }
    }

    public int query(Node root, int L, int R) {
        if (L <= root.low && R >= root.high) {
            return root.value;
        } else if (root.high >= L && root.low <= R) {
            int ans1 = this.query(root.left, L, R);
            int ans2 = this.query(root.right, L, R);
            return Math.min(ans1, ans2);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        int testcase = sc.nextInt();

        for(int test = 0; test < testcase; ++test) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            Map<Integer, Integer> valuesToGap = new HashMap();
            int prev = 0;

            for(int i = 0; i < n; ++i) {
                arr[i] = sc.nextInt();
                valuesToGap.put(prev, arr[i] - prev);
                prev = arr[i];
            }
        }

    }

    static {
        sc = new Scanner(System.in);
    }

    public static class Node {
        int low;
        int high;
        Node left;
        Node right;
        Node par;
        int value;

        public Node(int low, int high) {
            this.low = low;
            this.high = high;
        }

        public Node(int low, int high, int value, Node par) {
            this.low = low;
            this.high = high;
            this.value = value;
            this.par = par;
        }

        public Node(int low, int high, Node par) {
            this.low = low;
            this.high = high;
            this.par = par;
        }
    }
}
