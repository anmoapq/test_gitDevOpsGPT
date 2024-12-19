package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GameRepository;
import model.GameResponse;
import model.GameState;

import java.util.Map;

@Service
public class SnakeGameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    public GameResponse updateGame(String direction, Map<String, Integer> currentPosition, String gameId) {
        GameState gameState = gameRepository.loadGame(gameId);
        
        if (gameState == null) {
            throw new IllegalArgumentException("Game not found for id: " + gameId);
        }

        if (!isValidDirection(direction)) {
            throw new IllegalArgumentException("Invalid direction provided: " + direction);
        }
        
        gameState.updateDirection(direction);
        gameState.updatePosition(currentPosition);
        gameState.checkFoodCollision();

        gameRepository.saveGame(gameState);

        return new GameResponse(gameState.isFoodCollected(), gameState.getSnakeLength(), gameState.getGameStatus());
    }
    
    private boolean isValidDirection(String direction) {
        return "up".equals(direction) || "down".equals(direction) || 
               "left".equals(direction) || "right".equals(direction);
    }
}
