package model.move;

import model.basic.Character;
import model.basic.Picture;
import model.basic.Point;
import model.controlled.NMap;

public class MoveRight implements MoveInterface {
    @Override
    public void move(Point point, Character character) {
        if(NMap.changeNMapRight(point, character)){
            point.setPx(point.getPx() + 1);
        }
    }
}