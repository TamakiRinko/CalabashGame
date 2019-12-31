package model.move;

import model.basic.Character;
import model.basic.Picture;
import model.basic.Point;

import java.io.Serializable;

public class MoveServer implements Serializable {
    public MoveServer(){}

    public void move(MoveInterface moveInterface, Point point, Character character){
        moveInterface.move(point, character);
    }
}
