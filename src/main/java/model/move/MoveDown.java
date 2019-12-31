package model.move;

import model.basic.Character;
import model.basic.Picture;
import model.basic.Point;
import model.controlled.NMap;

public class MoveDown implements MoveInterface {
    @Override
    public void move(Point point, Character character) {
        if(NMap.changeNMapDown(point, character)){
            point.setPy(point.getPy() + 1);
        }
    }
}
