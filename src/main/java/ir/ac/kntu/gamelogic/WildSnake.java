package ir.ac.kntu.gamelogic;

public class WildSnake extends Snake {

    private static int nextId = 1;

    public WildSnake(int boardSize) {
        super(boardSize);
        this.setDestiny(new Position(RandomHelper.nextInt(this.getSource().getLength()),
                RandomHelper.nextInt(this.getBoardSize())));
        this.setId(nextId);
        nextId++;
    }

    public void changePosition() {
        this.setSource(
                new Position(RandomHelper.nextInt(this.getBoardSize(), 1), RandomHelper.nextInt(this.getBoardSize())));
        this.setDestiny(new Position(RandomHelper.nextInt(this.getSource().getLength()),
                RandomHelper.nextInt(this.getBoardSize())));

    }
}
