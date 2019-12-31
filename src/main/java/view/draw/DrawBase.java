package view.draw;

import control.utils.ConstValues;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.basic.Character;
import model.basic.Picture;
import model.basic.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawBase {
    public GraphicsContext graphicsContext;
    DrawBase(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }
    void drawPic(Picture picture){
        graphicsContext.drawImage(picture.getImage(),
                0, 0, picture.getWidth(), picture.getHeight(),
                picture.getPoint().getPx() * picture.getWidth(),
                picture.getPoint().getPy() * picture.getHeight(),
                picture.getWidth(), picture.getHeight());
    }
    void drawRect(Character character){
        Point point = character.getPoint();
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(point.getPx() * ConstValues.PictureWidth, point.getPy() * ConstValues.PictureHeight,
                                ConstValues.BloodBarWidth * character.remainBloodPercentage(), ConstValues.BloodBarHeight);
    }
    public void draw(){}
}
