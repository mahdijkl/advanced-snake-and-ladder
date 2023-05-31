package ir.ac.kntu.gamelogic;

public class Dice {
    enum Options {
        UP_1,
        UP_2,
        DOWN_1,
        DOWN_2,
        RIGHT_1,
        RIGHT_2,
        LEFT_1,
        LEFT_2,
        EXTRA_LIFE
    }

    private Options[] options = { Options.EXTRA_LIFE, Options.UP_1, Options.UP_2, Options.DOWN_1, Options.DOWN_2,
            Options.RIGHT_1, Options.RIGHT_2, Options.LEFT_1, Options.LEFT_2 };
    private int noMode;
    private Options state = null;

    public Options drop() {
        noMode = RandomHelper.nextInt(8);
        state = options[noMode];
        return options[noMode];
    }

    public Options getState() {
        return state;
    }
}
