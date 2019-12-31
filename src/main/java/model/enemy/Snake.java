package model.enemy;

import model.basic.KeyCharacter;
import model.basic.Picture;
import javafx.scene.image.Image;

public class Snake extends KeyCharacter {
    private Picture orderPicture;
    public Snake(int blood, int px, int py, Image image, Picture orderPicture){
        super(blood, px, py, image);
        this.orderPicture = orderPicture;
    }

    public Snake(Snake snake){
        super(snake);
    }

    public Picture getOrderPicture(){
        return orderPicture;
    }

    @Override
    public void run() {
        sleep(2000);
    }
}
