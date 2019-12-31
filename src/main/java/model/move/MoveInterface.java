package model.move;

import model.basic.Character;
import model.basic.Point;

import java.io.Serializable;

public interface MoveInterface extends Serializable {
    void move(Point point, Character character);
}
