package view.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.basic.Picture;
import model.basic.Point;


public class DrawPic extends DrawBase{
    Picture picture;
    public DrawPic(GraphicsContext graphicsContext, Image image) {
        super(graphicsContext);
        picture = new Picture(new Point(0, 0), image);
    }

    @Override
    public void draw() {
        drawPic(picture);
    }
}
