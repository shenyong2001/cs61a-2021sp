package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private int size;

        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode target = get(root, key);
        return target == null ? null : target.value;
    }

    private BSTNode get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode x, K key, V value) {
        if (x == null) return new BSTNode(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public void printInOrder() {
        System.out.println(printHelper(root));
    }

    private String printHelper(BSTNode node) {
        if (node == null) {
            return "";
        }
        String thisNode = "[" + node.key + ", " + node.value + "]";
        if (node.size == 1) {
            return thisNode;
        }

        return printHelper(node.left) + thisNode + printHelper(node.right);
    }

    public static void main(String[] args) {
        BSTMap<String,String> q = new BSTMap<String,String>();
        q.put("f", "a");
        q.put("k", "a");
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("i", "a");
        q.put("d","a");
        q.put("e","a");
        q.put("h", "a");
        q.put("j", "a");
        q.put("g", "a");
        q.printInOrder();
    }
}
