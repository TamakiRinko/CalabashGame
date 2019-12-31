package model.basic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction implements Serializable {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UPRIGHT,
    UPLEFT,
    DOWNRIGHT,
    DOWNLEFT;
    //...

    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * 四个方向
     * @return
     */
    public static Direction randomFourDirection()  {
        Direction direction = VALUES.get(RANDOM.nextInt(SIZE));
        while (direction != UP && direction != DOWN && direction != LEFT && direction != RIGHT){
            direction = VALUES.get(RANDOM.nextInt(SIZE));
        }
        return direction;
    }

    /**
     * 八个方向
     * @return
     */
    public static Direction randomEightDirection(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
