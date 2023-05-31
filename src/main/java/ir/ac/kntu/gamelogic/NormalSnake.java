package ir.ac.kntu.gamelogic;

public class NormalSnake extends Snake {

    private static int nextId = 1;

    public NormalSnake(int boardSize) {
        super(boardSize);
        this.setDestiny(new Position(RandomHelper.nextInt(this.getSource().getLength()), RandomHelper.nextInt(this.getBoardSize())));
        this.setId(nextId);
        nextId++;
    }

    @Override
    public void setDestiny(Position destiny) {

        if (destiny.getLength() >= this.getSource().getLength()) {

            System.out.println("destiny must be lower than source! ");
            return;
        }
        super.setDestiny(destiny);
    }


    public void changePosition() {
        this.setDestiny(new Position(RandomHelper.nextInt(this.getSource().getLength()), RandomHelper.nextInt(this.getBoardSize())));
    }
}
