package ir.ac.kntu.gamelogic;

public class Player {
    private int life = 3;
    private Position position = new Position(0, 0);

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Dice.Options option, int boardSize) {
        switch (option) {
            case UP_1 -> {
                if (isValidPosition(this.getPosition().getLength() + 1, boardSize)) {
                    this.getPosition().setLength(this.getPosition().getLength() + 1);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case UP_2 -> {
                if (isValidPosition(this.getPosition().getLength() + 2, boardSize)) {
                    this.getPosition().setLength(this.getPosition().getLength() + 2);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case DOWN_1 -> {
                if (isValidPosition(this.getPosition().getLength() - 1, boardSize)) {
                    this.getPosition().setLength(this.getPosition().getLength() - 1);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case DOWN_2 -> {
                if (isValidPosition(this.getPosition().getLength() - 2, boardSize)) {
                    this.getPosition().setLength(this.getPosition().getLength() - 2);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case RIGHT_1 -> {
                if (isValidPosition(this.getPosition().getWidth() + 1, boardSize)) {
                    this.getPosition().setWidth(this.getPosition().getWidth() + 1);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case RIGHT_2 -> {
                if (isValidPosition(this.getPosition().getWidth() + 2, boardSize)) {
                    this.getPosition().setWidth(this.getPosition().getWidth() + 2);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case LEFT_1 -> {
                if (isValidPosition(this.getPosition().getWidth() - 1, boardSize)) {
                    this.getPosition().setWidth(this.getPosition().getWidth() - 1);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case LEFT_2 -> {
                if (isValidPosition(this.getPosition().getWidth() - 2, boardSize)) {
                    this.getPosition().setWidth(this.getPosition().getWidth() - 2);
                } else {
                    System.out.println("Can not move. ");
                }
            }
            case EXTRA_LIFE -> addLife();

        }
    }

    public void lostLife() {
        if (this.life > 0) {
            this.life--;
            System.out.println("lost life. ");
        }
    }

    public void addLife() {
        if (this.life < 3) {
            this.life++;
        }
    }

    public boolean isValidPosition(int position, int boardSize) {
        return 0 <= position && position < boardSize;
    }
}