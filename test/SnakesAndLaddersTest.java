import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SnakesAndLaddersTest {

    @Test
    void theGameShouldRunAndCompleteSuccessfully() {
        TestLogger testLogger = new TestLogger();
        ProgrammableDie die = new ProgrammableDie();
        Board board = createBoard();
        Player[] players = createPlayers();

        Game game = new Game(die, testLogger, () -> {
            throw new RuntimeException("Game Finished!");
        }, board, players);

        try {
            game.play();
            fail("Exception to be thrown!");
        } catch (RuntimeException re) {
            assertEquals("Player three wins! Game finished.", testLogger.messageAt(testLogger.messages() - 1));
        }
    }

    private Board createBoard() {
        Map<Integer, Integer> snakePositions = Map.of(
                18, 2, 25, 8, 38, 11, 41, 19, 59, 21, 72, 12, 78, 7, 86, 31, 92, 26, 97, 5
        );
        Map<Integer, Integer> ladderPositions = Map.of(
                9, 32, 12, 53, 17, 90, 21, 50, 27, 66, 29, 42, 44, 73, 63, 88
        );
        return new Board(snakePositions, ladderPositions);
    }

    private Player[] createPlayers() {
        return new Player[]{
                new Player("Player one"),
                new Player("Player two"),
                new Player("Player three"),
                new Player("Player four")
        };
    }

    static class ProgrammableDie implements GameDie {
        private final List<Integer> moves = List.of(
                6, 6, 6, 6, 6, 6, 5, 4, 2, 1, 6, 4, 2, 6, 4, 6, 4, 2, 6, 5, 4, 6, 6, 6, 5, 3, 4, 3, 1, 2, 3, 4, 5, 6
        );
        private int currentDieIndex = 0;

        @Override
        public Integer roll() {
            if (currentDieIndex >= moves.size()) {
                throw new RuntimeException("No Moves Left!");
            }
            return moves.get(currentDieIndex++);
        }
    }

    static class TestLogger implements Logger {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void log(String message) {
            messages.add(message);
        }

        public int messages() {
            return messages.size();
        }

        public String messageAt(int index) {
            return messages.get(index);
        }
    }
}
