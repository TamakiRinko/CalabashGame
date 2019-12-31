import javafx.util.Pair;
import model.annotation.BulletAttributes;
import model.annotation.CharacterAttributes;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class AnnotationTest {

    @CharacterAttributes(blood = 12)
    private static int characterAttr;
    @BulletAttributes(attackPower = 3, speed = 8)
    private static int bulletAttr;

    private static Field characterAttrField;
    private static Field bulletAttrField;
    private static Class<AnnotationTest> testClass;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("-------- Before AnnotationTest start, 获得class对象 --------");
        testClass = AnnotationTest.class;
    }

    @Test
    public void characterAttributesTest(){
        System.out.println("Test CharacterAttributes, 测试 人物属性 读取是否正确");
        CharacterAttributes characterAttributes = null;
        try {
            characterAttributes = testClass.getDeclaredField("characterAttr").getAnnotation(CharacterAttributes.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int blood = characterAttributes.blood();
        assertEquals(blood, 12);
    }

    @Test
    public void bulletAttributesTest(){
        System.out.println("Test CharacterAttributes, 测试 子弹属性 读取是否正确");
        BulletAttributes bulletAttributes = null;
        try {
            bulletAttributes = testClass.getDeclaredField("bulletAttr").getAnnotation(BulletAttributes.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int speed = bulletAttributes.speed();
        int attackPower = bulletAttributes.attackPower();
        assertEquals(speed, 8);
        assertEquals(attackPower, 3);
    }

}
