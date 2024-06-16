import java.util.Map;

class Board {
    private final Map<Integer, Integer> snakePositions;
    private final Map<Integer, Integer> ladderPositions;

    public Board(Map<Integer, Integer> snakePositions, Map<Integer, Integer> ladderPositions) {
        this.snakePositions = snakePositions;
        this.ladderPositions = ladderPositions;
    }

    public int getNewPosition(int position) {
        if (snakePositions.containsKey(position)) {
            return snakePositions.get(position);
        }
        if (ladderPositions.containsKey(position)) {
            return ladderPositions.get(position);
        }
        return position;
    }
}