import java.util.Arrays;

public class UnionFind {
    private final int[] list;

    public UnionFind(int n) {
        list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = -1;
        }
    }

    public void validate(int v1) {
        if (v1 > list.length - 1 || v1 < 0) {
            throw new IllegalArgumentException("v1 is not a valid index");
        }
    }

    public int sizeOf(int v1) {
        validate(v1);

        if (list[v1] < 0) return Math.abs(list[v1]);

        return Math.abs(list[find(v1)]);
    }

    public int parent(int v1) {
        validate(v1);

        return list[v1];
    }

    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        int v1Root = find(v1);
        int v2Root = find(v2);

        if (v1Root == v2Root) return;

        if (sizeOf(v1Root) <= sizeOf(v2Root)) {
            list[v2Root] += list[v1Root];
            list[v1Root] = v2Root;
        } else {
            list[v1Root] += list[v2Root];
            list[v2Root] = v1Root;
        }
    }

    public int find(int v1) {
        validate(v1);
        if (list[v1] < 0) {
            return v1;
        }

        return find(parent(v1));
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(16);
    }
}