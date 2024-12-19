package repository;

import model.GameState;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameRepository {

    /**
     * Saves the current game state.
     *
     * @param gameState the current state of the game to be saved
     */
    void saveGame(GameState gameState);
    
    /**
     * Loads the game state for a given game ID.
     *
     * @param gameId the ID of the game whose state is to be loaded
     * @return the loaded game state
     */
    GameState loadGame(String gameId);
}
