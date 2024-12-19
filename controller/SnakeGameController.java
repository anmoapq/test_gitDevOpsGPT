package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import service.SnakeGameService;
import model.GameResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/snake-game")
public class SnakeGameController {
    
    @Autowired
    private SnakeGameService snakeGameService;

    @PostMapping("/control")
    public ResponseEntity<Map<String, Object>> controlSnake(@RequestBody Map<String, Object> params) {
        String direction = (String) params.get("direction");
        Map<String, Integer> currentPosition = (Map<String, Integer>) params.get("current_position");
        String gameId = (String) params.get("game_id");

        if (direction == null || currentPosition == null || gameId == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid input parameters");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        GameResponse gameResponse = snakeGameService.updateGame(direction, currentPosition, gameId);
        Map<String, Object> response = new HashMap<>();
        response.put("food_collected", gameResponse.isFoodCollected());
        response.put("current_length", gameResponse.getCurrentLength());
        response.put("game_status", gameResponse.getGameStatus());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
