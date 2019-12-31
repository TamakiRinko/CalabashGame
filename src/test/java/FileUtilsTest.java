import control.utils.FileUtils;
import javafx.scene.image.Image;
import model.basic.Character;
import model.basic.Point;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FileUtilsTest {
    @Test
    public void pointListTest(){
        System.out.println("Test PointList, 测试 葫芦娃长蛇队伍 坐标获取是否正确");
        List<Point> list = FileUtils.getPointList("葫芦娃长蛇");
        assertEquals(7, list.size());
        assertEquals(new Point(1, 4), list.get(0));
        assertEquals(new Point(1, 5), list.get(1));
        assertEquals(new Point(1, 6), list.get(2));
        assertEquals(new Point(1, 7), list.get(3));
        assertEquals(new Point(1, 8), list.get(4));
        assertEquals(new Point(1, 9), list.get(5));
        assertEquals(new Point(1, 10), list.get(6));
    }

    @Test
    public void imageTest(){
        System.out.println("Test Image, 测试 蝎子精图像 能否正确获取");
        Image image = FileUtils.getImage("Scorpion");
        assertNotNull(image);
    }

    @Test(expected = NullPointerException.class)
    public void IOExceptionTest ()throws Exception{
        System.out.println("Test getImage, 测试 错误图像 能否抛出NullPointerException异常");
        Image image = FileUtils.getImage("Scorpion");
        //无GrandMother，应抛出IOException
        FileUtils.setImage(new Character(1, 1, 1, image), "GrandMother");
    }

}
