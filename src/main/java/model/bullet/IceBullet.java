package model.bullet;

import javafx.scene.image.Image;
import model.basic.Bullet;
import model.basic.Character;

import java.io.Serializable;

public class IceBullet extends Bullet implements Serializable {
    public IceBullet(Image image, int attackPower, double x, double y, double speedX, double speedY, Character targetCharacter) {
        super(image, attackPower, x, y, speedX, speedY, targetCharacter);
    }

    public IceBullet(Bullet bullet) {
        super(bullet);
    }
}
