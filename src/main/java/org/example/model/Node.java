package org.example.model;

public class Node {
    Node left;
    Node right;
    int sum;

    public Node(Node left, Node right, int sum) {
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    public Node(Node node) {
        this(node.left, node.right, node.sum);
    }

    public Node() {
        this(null, null, 0);
    }

    public Node add(int left, int right, int start, int end, boolean isOpening) {
        if (left >= end || right <= start) {
            return this;
        }
        if (start <= left && right <= end) {
            Node node = new Node(this);
            node.sum += (isOpening ? 1 : -1);
            return node;
        }

        int mid = (left + right) / 2;
        Node node = new Node(this);

        if (this.left == null) {
            this.left = new Node();
        }
        if (this.right == null) {
            this.right = new Node();
        }

        node.left = this.left.add(left, mid, start, end, isOpening);
        node.right = this.right.add(mid, right, start, end, isOpening);

        return node;
    }

    public int getTotalQuantity(int left, int right, int target) {
        if (right - left == 1) {
            return this.sum;
        }

        int middle = (left + right) / 2;

        if (target < middle) {
            if (this.left == null) {
                return this.sum;
            }

            return this.sum + this.left.getTotalQuantity(left, middle, target);
        } else {
            if (this.right == null) {
                return this.sum;
            }

            return this.sum + this.right.getTotalQuantity(middle, right, target);
        }
    }
}
