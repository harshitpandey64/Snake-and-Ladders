public class GameController implements GameEvents {

    @Override
    public void endGame() {
        System.exit(1);
    }
}