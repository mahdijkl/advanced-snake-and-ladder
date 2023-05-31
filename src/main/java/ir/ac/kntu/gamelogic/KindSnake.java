package ir.ac.kntu.gamelogic;

public class KindSnake extends Snake {

    private static int nextId = 1;
    private Position position;

    public KindSnake(int boardSize) {
        this.setBoardSize(boardSize);
        this.setSource(
                new Position(RandomHelper.nextInt(this.getBoardSize() - 1), RandomHelper.nextInt(this.getBoardSize())));

        this.setDestiny(new Position(RandomHelper.nextInt(boardSize, this.getSource().getLength() + 1),
                RandomHelper.nextInt(boardSize)));
        this.setId(nextId);
        nextId++;
    }

    @Override
    public void setDestiny(Position destiny) {

        if (destiny.getLength() <= this.getSource().getLength()) {
            System.out.println("destiny must be higher than source! ");
        } else {
            super.setDestiny(destiny);

        }
    }

    public void changePosition() {
        position = new Position(RandomHelper.nextInt(this.getBoardSize(), this.getSource().getLength() + 1),
                RandomHelper.nextInt(this.getBoardSize()));

        this.setDestiny(position);
    }
}
