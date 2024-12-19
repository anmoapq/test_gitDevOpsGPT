package repository;

import model.GameState;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private static final String NAMESPACE = "repository.GameRepository.";
    private static final String SAVE_GAME = NAMESPACE + "saveGame";
    private static final String LOAD_GAME = NAMESPACE + "loadGame";

    private final SqlSession sqlSession;

    @Autowired
    public GameRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void saveGame(GameState gameState) {
        try {
            sqlSession.insert(SAVE_GAME, gameState);
        } catch (Exception e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    public GameState loadGame(String gameId) {
        try {
            return sqlSession.selectOne(LOAD_GAME, gameId);
        } catch (Exception e) {
            System.err.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }
}
