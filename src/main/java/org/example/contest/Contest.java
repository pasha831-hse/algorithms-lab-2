package org.example.contest;

import java.util.*;

public class Contest {
    public static void main(String[] args) {
        // Get rectangles from stdin and save them inside rectangles list
        Scanner scanner = new Scanner(System.in);

        int numberOfRectangles = scanner.nextInt();
        List<Rectangle> rectangles = new ArrayList<>();

        for (int i = 0; i < numberOfRectangles; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            rectangles.add(new Rectangle(x1, y1, x2, y2));
        }

        // Create result list and
        // get points from stdin and save them inside points list
        List<Point> points = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        int numberOfPoints = scanner.nextInt();

        if (rectangles.size() == 0) {
            for (int i = 0; i < numberOfPoints; i++) {
                System.out.print(0 + " ");
            }
            return;
        }

        for (int i = 0; i < numberOfPoints; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            Point point = new Point(x, y);
            points.add(point);
        }

        // Create solution class
        // and save answers inside result array

        LPSTSolution solution = new LPSTSolution(rectangles);
        for (Point point : points) {
            result.add(solution.queryPoint(point));
        }

        System.out.println(Arrays.toString(result.toArray()).replace("[", "").replace("]", "").replace(",", ""));
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle {
    public Point leftDownPoint;
    public Point rightUpPoint;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.leftDownPoint = new Point(x1, y1);
        this.rightUpPoint = new Point(x2, y2);
    }
}

class Node {
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

class Side implements Comparable<Side> {
    public int compressedX;
    public int topY;
    public int bottomY;
    public boolean isOpening;

    public Side(int compressedX, int topY, int bottomY, boolean isOpening) {
        this.compressedX = compressedX;
        this.topY = topY;
        this.bottomY = bottomY;
        this.isOpening = isOpening;
    }

    @Override
    public int compareTo(Side anotherSide) {
        return this.compressedX - anotherSide.compressedX;
    }
}

class LPSTSolution {
    public List<Rectangle> rectangles;

    public List<Integer> compressedX;
    public List<Integer> compressedY;

    List<Node> roots;
    List<Integer> compressedRootsX;

    public LPSTSolution(List<Rectangle> rectangles) {
        this.rectangles = rectangles;

        compressCoordinates();

        roots = new ArrayList<>();
        compressedRootsX = new ArrayList<>();

        inflateLSPT();
    }

    private void compressCoordinates() {
        compressedX = new ArrayList<>();
        compressedY = new ArrayList<>();

        for (Rectangle rectangle : rectangles) {
            compressedX.addAll(List.of(
                    rectangle.leftDownPoint.x,
                    rectangle.rightUpPoint.x,
                    rectangle.rightUpPoint.x + 1
            ));
            compressedY.addAll(List.of(
                    rectangle.leftDownPoint.y,
                    rectangle.rightUpPoint.y,
                    rectangle.rightUpPoint.y + 1
            ));
        }

        // Create and fill compressed arrays with TreeSets of coordinates,
        // which are already distinct and sorted in natural (ascending) order
        compressedX = new ArrayList<>(new TreeSet<>(compressedX));
        compressedY = new ArrayList<>(new TreeSet<>(compressedY));
    }

    private int compressPoint(List<Integer> axis, int target) {
        int left = 0;
        int right = axis.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (axis.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public void inflateLSPT() {
        List<Side> sides = new ArrayList<>();

        for (Rectangle rectangle : rectangles) {
            sides.add(new Side(
                    compressPoint(compressedX, rectangle.leftDownPoint.x),
                    compressPoint(compressedY, rectangle.leftDownPoint.y),
                    compressPoint(compressedY, rectangle.rightUpPoint.y + 1),
                    true
            ));
            sides.add(new Side(
                    compressPoint(compressedX, rectangle.rightUpPoint.x + 1),
                    compressPoint(compressedY, rectangle.leftDownPoint.y),
                    compressPoint(compressedY, rectangle.rightUpPoint.y + 1),
                    false
            ));
        }

        Collections.sort(sides);

        Node root = new Node();
        int previousCompressedX = sides.get(0).compressedX;
        for (Side side : sides) {
            if (side.compressedX != previousCompressedX) {
                roots.add(root);
                compressedRootsX.add(previousCompressedX);
                previousCompressedX = side.compressedX;
            }
            root = root.add(0, compressedY.size(), side.topY, side.bottomY, side.isOpening);
        }

        compressedRootsX.add(previousCompressedX);
        roots.add(root);
    }

    public int queryPoint(Point point) {
        if (point.x < compressedX.get(0) || point.y < compressedY.get(0) ||
                point.x > compressedX.get(compressedX.size() - 1) || point.y > compressedY.get(compressedY.size() - 1)) {
            return 0;
        }

        int compressedXIndex = compressPoint(compressedX, point.x);
        int compressedYIndex = compressPoint(compressedY, point.y);

        return roots.get(compressPoint(compressedRootsX, compressedXIndex))
                .getTotalQuantity(0, compressedY.size(), compressedYIndex);
    }
}
