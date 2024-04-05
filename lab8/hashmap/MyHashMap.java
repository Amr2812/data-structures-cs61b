package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private int initialSize;
    private double loadFactor;
    private final int resizeFactor = 2;

    /** Constructors */
    public MyHashMap() {
        this.size = 0;
        this.initialSize = 16;
        this.loadFactor = 0.75;
        this.buckets = this.createTable(this.initialSize);
    }

    public MyHashMap(int initialSize) {
        this.size = 0;
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        this.buckets = this.createTable(this.initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = this.createTable(this.initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public int size() {
        return this.size;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), this.initialSize);
    }

    private void resize() {
        this.initialSize *= this.resizeFactor;
        Collection<Node>[] oldBuckets = this.buckets;
        this.buckets = createTable(initialSize);
        this.size = 0;
        for (Collection<Node> bucket : oldBuckets) {
            if (bucket == null) continue;
            for (Node node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if ((double) this.size / this.initialSize >= loadFactor) {
            resize();
        }

        int idx = hash(key);
        Collection<Node> bucket = this.buckets[idx];

        // create bucket
        if (bucket == null) {
            bucket = createBucket();
            this.buckets[idx] = bucket;
        }

        // upsert
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        bucket.add(createNode(key, value));
        this.size++;
    }

    @Override
    public V get(K key) {
        int idx = hash(key);
        Collection<Node> bucket = this.buckets[idx];

        if (bucket == null) return null;

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int idx = hash(key);
        Collection<Node> bucket = this.buckets[idx];

        if (bucket == null) return false;
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < this.buckets.length; i++) {
            if (this.buckets[i] == null) continue;
            for (Node node : this.buckets[i]) {
                keys.add(node.key);
            }
        }

        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public void clear() {
        Arrays.fill(this.buckets, null);
        this.size = 0;
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
