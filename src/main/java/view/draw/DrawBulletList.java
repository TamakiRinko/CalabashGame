package view.draw;

import javafx.scene.canvas.GraphicsContext;
import model.basic.Bullet;
import model.controlled.BulletList;

import java.util.List;

public class DrawBulletList extends DrawBase {
    private List<Bullet> bulletList;
    public DrawBulletList(GraphicsContext graphicsContext, List<Bullet> bulletList) {
        super(graphicsContext);
        this.bulletList = bulletList;
    }

    public DrawBulletList(GraphicsContext graphicsContext) {
        super(graphicsContext);
        this.bulletList = BulletList.bulletList;
    }

    @Override
    public void draw() {
        for(Bullet bullet: bulletList){
            if(bullet.getImage() == null)  continue;
            graphicsContext.drawImage(bullet.getImage(),
                    0, 0, bullet.getImage().getWidth(), bullet.getImage().getHeight(),
                    bullet.getX(), bullet.getY(), bullet.getImage().getWidth(), bullet.getImage().getHeight());
        }
    }
}
