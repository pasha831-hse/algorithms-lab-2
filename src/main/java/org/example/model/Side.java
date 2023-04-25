package org.example.model;

public class Side implements Comparable<Side> {
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
