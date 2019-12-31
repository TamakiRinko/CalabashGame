package model.basic;

import javafx.scene.image.Image;

public class KeyCharacter extends Character implements Cloneable{
    public KeyCharacter(int blood, int px, int py, Image image){
        super(blood, px, py, image);
    }

    public KeyCharacter(KeyCharacter keyCharacter) {
        super(keyCharacter);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        ((KeyCharacter)obj).picture = (Picture) this.picture.clone();
        ((KeyCharacter)obj).point = (Point) this.point.clone();
        return obj;
    }
}
