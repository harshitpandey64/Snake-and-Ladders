class Game {
    private final GameDie die;
    private final Logger logger;
    private final GameEvents gameEvents;
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;

    public Game(GameDie die, Logger logger, GameEvents gameEvents, Board board, Player[] players) {
        this.die = die;
        this.logger = logger;
        this.gameEvents = gameEvents;
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public void play() {
        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            int dieRoll = die.roll();
            logger.log(currentPlayer.getName() + " got dice roll of " + dieRoll);

            if (!movePlayer(currentPlayer, dieRoll)) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                logger.log(players[currentPlayerIndex].getName() + " will play next turn");
            }
        }
    }

    private boolean movePlayer(Player player, int dieRoll) {
        int newPosition = player.getPosition() + dieRoll;

        if (newPosition > 100) {
            logger.log(player.getName() + " needs to score exactly " + (100 - player.getPosition()) + " on dice roll to win. Passing chance.");
            return true;
        }

        if (newPosition == 100) {
            logger.log(player.getName() + " wins! Game finished.");
            gameEvents.endGame();
        }

        if (player.getPosition() == 0 && dieRoll != 6) {
            logger.log(player.getName() + " did not score 6. First a 6 needs to be scored to start moving on board.");
            return true;
        }

        newPosition = board.getNewPosition(newPosition);
        if (newPosition != player.getPosition() + dieRoll) {
            logger.log(player.getName() + " encountered a special tile and moved to position " + newPosition);
        }

        player.setPosition(newPosition);
        logger.log(player.getName() + " moved to position " + newPosition);

        return false;
    }
}
