package model;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Point> body;

    // Constructor to initialize the snake starting at the given point
    public Snake(Point startPoint) {
        body = new ArrayList<>();
        body.add(startPoint);
    }

    // Updates the position of the snake, adding a new head point and removing the tail point
    public void updatePosition(Point newHead) {
        // Check if new head is a valid position.
        if (body.contains(newHead)) {
            throw new IllegalArgumentException("Invalid move: The snake cannot move to its own body position.");
        }
        // Add new head position and remove the tail position
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    // Grow the snake by adding a new segment to the tail
    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(tail);
    }

    // Get the length of the snake
    public int getLength() {
        return body.size();
    }

    // Get the head point of the snake
    public Point getHead() {
        return body.get(0);
    }

    // Get the list representing the snake's body
    public List<Point> getBody() {
        return body;
    }
}

// Assuming Point class is as below:
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
