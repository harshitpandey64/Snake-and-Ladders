import java.util.HashMap;
import java.util.Map;
public class SnakesAndLadders {
    public static void main(String[] args) {
        GameDie die = new RandomDie();
        Logger logger = new TerminalLogger();
        GameEvents gameEvents = new GameController();

        Map<Integer, Integer> snakePositions = new HashMap<>() {{
            put(18, 2);
            put(25, 8);
            put(38, 11);
            put(41, 19);
            put(59, 21);
            put(72, 12);
            put(78, 7);
            put(86, 31);
            put(92, 26);
            put(97, 5);
        }};
        Map<Integer, Integer> ladderPositions = new HashMap<>() {{
            put(9, 32);
            put(12, 53);
            put(17, 90);
            put(21, 50);
            put(27, 66);
            put(29, 42);
            put(44, 73);
            put(63, 88);
        }};

        Board board = new Board(snakePositions, ladderPositions);
        Player[] players = {
                new Player("Player one"),
                new Player("Player two"),
                new Player("Player three"),
                new Player("Player four")
        };

        Game game = new Game(die, logger, gameEvents, board, players);
        game.play();
    }
}
