package ir.ac.kntu.gamelogic;

import java.util.Objects;

public class Position {
    private int length;
    private int width;

    public Position(int length, int width) {
        this.width = width;
        this.length = length;
    }


    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return length == position.length && width == position.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }
}
