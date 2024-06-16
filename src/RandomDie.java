public class RandomDie implements GameDie {
    @Override
    public Integer roll()
    {
        return (int) (Math.random() * 6) + 1;
    }
}