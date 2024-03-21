package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;             // root of BST
    private int size;             // number of nodes in subtree

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key can't be null");
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        return x.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key can't be null");

        root = put(root, key, value);
        size++;
    }

    private Node put(Node x, K key, V value) {
        if (x == null) return new Node(key, value);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, value);
        }
        else {
            x.val = value;
            size--;
        }

        return x;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
