package model;

import java.util.List;
import java.util.Random;

public class Food {

    private Point position;
    private static final int GRID_SIZE = 20; // Arbitrary grid size
    private final Random random = new Random();

    public Food() {
        position = new Point(0, 0); // Optionally setting an initial position
    }

    public synchronized void generateRandomPosition(List<Point> snakeBody) {
        do {
            int x = random.nextInt(GRID_SIZE);
            int y = random.nextInt(GRID_SIZE);
            position = new Point(x, y);
        } while (snakeBody.contains(position));
    }

    public synchronized Point getPosition() {
        return position;
    }
}
