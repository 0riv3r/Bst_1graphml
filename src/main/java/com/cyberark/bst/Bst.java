package com.cyberark.bst;

import java.util.ArrayList;

/**
 * Binary Serach Tree class
 * 
 * @param <T>
 */
public class Bst<T extends Comparable<T>> {
    private Node root = null;
    private int nodeCount = 0;

    private class Node {
        T element;
        Node left, right;

        public Node(Node left, Node right, T element) {
            this.left = left;
            this.right = right;
            this.element = element;
        }
    }

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    private Node _insert(Node node, T value) {
        /** a leaf node, insert the value in this position. */
        if (node == null) {
            node = new Node(null, null, value);
        }
        /**
         * If the value is less than the current node's value, choose left.
         */
        else if (value.compareTo(node.element) < 0) {
            node.left = _insert(node.left, value);
        }
        /**
         * If the value is greater than the current node's value, choose right.
         */
        else if (value.compareTo(node.element) > 0) {
            node.right = _insert(node.right, value);
        }
        /** 
         * A new node is created, or
         * a node with this value already exists in the tree.
         * return this node
         * */
        return node;

    }

    public void add(T value) {
        root = _insert(root, value);
        nodeCount++;
    }

    private ArrayList<Integer> _listNodes(Node node, ArrayList<Integer> lstNodes) {
        if (node != null) {
            /** recursively call 'display' on the left sub-tree */
            _listNodes(node.left, lstNodes);
            /** adds the value at the current node */
            lstNodes.add((Integer) node.element);
            /** recursively call 'display' on the right subtree. */
            _listNodes(node.right, lstNodes);
        }
        return(lstNodes);
    }

    public ArrayList<Integer> listTreeNodes() {
        ArrayList<Integer> lstNodes = new ArrayList<Integer>();
        return _listNodes(root, lstNodes);
    }

    private boolean _contains(Node node, T value) {
        /** if the value is not found */
        if (node == null)
            return false;
        /** if the value is found */
        if (value.compareTo(node.element) == 0) {
            return true;
        }
        /** Otherwise, continue the search recursively */
        return value.compareTo(node.element) < 0 ? _contains(node.left, value) : _contains(node.right, value);

    }

    public boolean containValue(T value) {
        return _contains(root, value);
    }

    /** return the smallest value (leftmost value) */
    public T smallest(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    /** return the largest value (rightmost value) */
    public T largest(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.element;
    }

    private boolean _deleteLeaf(Node node, T value) {
        /** if the value is not found */
        if (node == null)
            // not found
            return false;
        /** if the value is found */
        if (value.compareTo(node.element) == 0) {
            // is it a leaf? if yes, delete it
            if(node.left == null && node.right == null){
                node = null;
                return true;
            }
            else{
                // not a leaf
                return false;
            }
        }
        /** Otherwise, continue the search recursively */
        return value.compareTo(node.element) < 0 ? _deleteLeaf(node.left, value) : _deleteLeaf(node.right, value);
    }

    public boolean deleteIfLeaf(T value) {
        return _deleteLeaf(root, value);
    }

    public static void main(String[] args) {
        Bst<Integer> binarySearhTree = new Bst<Integer>();
        binarySearhTree.add(12);
        binarySearhTree.add(4);
        binarySearhTree.add(13);
        binarySearhTree.add(7);
        binarySearhTree.add(28);
        binarySearhTree.add(72);
        binarySearhTree.add(1);
        binarySearhTree.add(12);
        System.out.println("Is 12 in the tree? " + binarySearhTree.containValue(12));
        System.out.println("Is 47 in the tree? " + binarySearhTree.containValue(47));
        System.out.println("The smallest value in the tree is: " + binarySearhTree.smallest(binarySearhTree.root));
        System.out.println("The largest value in the tree is: " + binarySearhTree.largest(binarySearhTree.root));
        System.out.println(binarySearhTree.listTreeNodes());
    }
}