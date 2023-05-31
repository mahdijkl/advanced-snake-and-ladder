package ir.ac.kntu.gamelogic;

public class Snake {
    private Position source;
    private Position destiny;
    private int boardSize;
    private int id;

    public Snake(int boardSize) {
        this.boardSize = boardSize;
        this.source = new Position(RandomHelper.nextInt(this.getBoardSize(), 1),
                RandomHelper.nextInt(this.getBoardSize(), 1));
    }

    public Snake() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setSource(Position source) {
        this.source = source;
    }

    public void setDestiny(Position destiny) {
        this.destiny = destiny;
    }

    public Position getSource() {
        return source;
    }

    public Position getDestiny() {
        return destiny;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getId() {
        return id;
    }

}
