package control.factory;

import control.utils.ConstValues;
import control.utils.FileUtils;
import javafx.scene.image.Image;
import javafx.util.Pair;
import model.annotation.BulletAttributes;
import model.basic.Bullet;
import model.basic.Character;
import model.bullet.IceBullet;

public class BulletFactory {

    @BulletAttributes
    private static int NormalBulletAttr;
    @BulletAttributes(attackPower = 3)
    private static int IceBulletAttr;

    public static Bullet generateBullet(Character srcCharacter, Character targetCharacter){

//        System.out.println("targetCharacter: x = " + targetCharacter.getPx() + ", y = " + targetCharacter.getPy());
//        System.out.println("srcCharacter: x = " + srcCharacter.getPx() + ", y = " + srcCharacter.getPy());


        double x = srcCharacter.getPx() * ConstValues.PictureWidth + (ConstValues.PictureWidth - ConstValues.BulletWidth) / 2;
        double y = srcCharacter.getPy() * ConstValues.PictureHeight + (ConstValues.PictureHeight - ConstValues.BulletHeight) / 2;
        double desX = targetCharacter.getPx() * ConstValues.PictureWidth + (ConstValues.PictureWidth - ConstValues.BulletWidth) / 2;
        double desY = targetCharacter.getPy() * ConstValues.PictureHeight + (ConstValues.PictureHeight - ConstValues.BulletHeight) / 2;

//        System.out.println("x = " + x + ", y = " + y);
//        System.out.println("desX = " + desX + ", desY = " + desY);


        Pair<Integer, Integer> pair = getBulletAttr("NormalBulletAttr");
        int speed = pair.getKey();
        int attackPower = pair.getValue();

        double dx = desX - x;
        double dy = desY - y;
        double dis = Math.sqrt(dx * dx + dy * dy);
        double speedX = speed * dx * 1.0 / dis;
        double speedY = speed * dy * 1.0 / dis;

//        System.out.println("desX = " + desX + ", desY = " + desY + ", speedX = " + speedX + ", speedY = " + speedY);

        Image image = FileUtils.getImage("Bullet");

        return new Bullet(image, attackPower, x, y, speedX, speedY, targetCharacter);
    }

    public static IceBullet generateIceBullet(Character srcCharacter, Character targetCharacter){

        double x = srcCharacter.getPx() * ConstValues.PictureWidth + (ConstValues.PictureWidth - ConstValues.BulletWidth) / 2;
        double y = srcCharacter.getPy() * ConstValues.PictureHeight + (ConstValues.PictureHeight - ConstValues.BulletHeight) / 2;
        double desX = targetCharacter.getPx() * ConstValues.PictureWidth + (ConstValues.PictureWidth - ConstValues.BulletWidth) / 2;
        double desY = targetCharacter.getPy() * ConstValues.PictureHeight + (ConstValues.PictureHeight - ConstValues.BulletHeight) / 2;

        Pair<Integer, Integer> pair = getBulletAttr("IceBulletAttr");
        int speed = pair.getKey();
        int attackPower = pair.getValue();

        double dx = desX - x;
        double dy = desY - y;
        double dis = Math.sqrt(dx * dx + dy * dy);
        double speedX = speed * dx * 1.0 / dis;
        double speedY = speed * dy * 1.0 / dis;


        Image image = FileUtils.getImage("IceBullet");

        return new IceBullet(image, attackPower, x, y, speedX, speedY, targetCharacter);
    }

    private static Pair<Integer, Integer> getBulletAttr(String fieldName){
        BulletAttributes bulletAttributes = null;
        try {
            bulletAttributes = BulletFactory.class.getDeclaredField(fieldName).getAnnotation(BulletAttributes.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int speed = bulletAttributes.speed();
        int attackPower = bulletAttributes.attackPower();
        return new Pair<>(speed, attackPower);
    }

}
