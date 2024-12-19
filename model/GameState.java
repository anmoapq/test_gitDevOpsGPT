package model;

import com.google.gson.Gson;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class GameState {

    private Snake snake;
    private Point food;
    private String direction;
    private String gameStatus;

    public void updateDirection(String direction) {
        // Update the snake's direction
        this.direction = direction;
    }

    public void updatePosition(Map<String, Integer> currentPosition) {
        // Validate keys and values
        if (currentPosition.containsKey("x") && currentPosition.containsKey("y")) {
            Integer x = currentPosition.get("x");
            Integer y = currentPosition.get("y");
            if (x != null && y != null) {
                Point newHead = new Point(x, y);
                snake.updatePosition(newHead);
            }
        }
    }

    public void checkFoodCollision() {
        // Check if the snake collides with food
        if (snake.getHead().equals(food)) {
            snake.grow();
            food.generateRandomPosition(snake.getBody());
        }
    }

    public boolean isFoodCollected() {
        // Return whether the snake collected food
        return snake.getHead().equals(food);
    }

    public int getSnakeLength() {
        // Return the length of the snake
        return snake.getLength();
    }

    public String getGameStatus() {
        // Return the current game status
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Point getFood() {
        return food;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    // Additional classes for Snake and Point

    public class Snake {
        private List<Point> body;

        public Point getHead() {
            return body.get(0);
        }

        public void updatePosition(Point newHead) {
            // Move the snake's body to the new head position
            body.add(0, newHead);
            body.remove(body.size() - 1);
        }

        public void grow() {
            // Add new segment to the snake's body
            body.add(new Point(body.get(body.size() - 1)));
        }

        public int getLength() {
            return body.size();
        }

        public List<Point> getBody() {
            return body;
        }
    }

    public class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }

        public void generateRandomPosition(List<Point> snakeBody) {
            Random rand = new Random();
            do {
                this.x = rand.nextInt(100); // Assuming 100x100 grid
                this.y = rand.nextInt(100);
            } while (snakeBody.contains(this));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
