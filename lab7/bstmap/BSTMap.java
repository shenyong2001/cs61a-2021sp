package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
        Set<K> keys = new HashSet<>();
        keySet(root, keys);
        return keys;
    }

    private void keySet(BSTNode node, Set<K> keys) {
        if (node == null) {
            return;
        }
        if (node.size == 1) {
            keys.add(node.key);
        }
        keySet(node.left, keys);
        keys.add(node.key);
        keySet(node.right, keys);
    }

    private BSTNode deleteMin(BSTNode x) {
        if (x.left == null) {
            return x.right;
        }
        ;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private BSTNode min(BSTNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Override
    public V remove(K key) {
        BSTNode toRemove = get(root, key);
        if (toRemove == null) {
            throw new NoSuchElementException("The key to be deleted isn't exist.");
        }
        root = remove(root, key);
        return toRemove.value;
    }

    @Override
    public V remove(K key, V value) {
        BSTNode toRemove = get(root, key);
        if (toRemove == null) {
            throw new NoSuchElementException("The key to be deleted isn't exist.");
        }
        if (toRemove.value == value) {
            root = remove(root, key);
            return toRemove.value;
        }
        return null;
    }

    private BSTNode remove(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            BSTNode tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
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
}
