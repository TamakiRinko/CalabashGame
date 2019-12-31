import control.utils.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesTest {
    @Test
    public void imagePathTest(){
        System.out.println("Test imagePathProperties, 测试是否能够 根据正确名称获得对应图片的地址");
        String path = FileUtils.getImagePathFromProperties("Bullet");
        assertEquals(path, "file/Bullet.png");
    }

    @Test
    public void pointListPathTest(){
        System.out.println("Test formationPathProperties, 测试是否能够 根据正确名称获得对应阵型的地址");
        String path = FileUtils.getPointListPathFromProperties("敌人鹤翼");
        assertEquals(path, "file/敌人队伍_2.txt");
    }

    @Test(expected = NullPointerException.class)
    public void throwExceptionTest(){
        System.out.println("Test formationPathProperties, 测试是否能够 在错误阵型名称时抛出NullPointerException异常");
        String path = FileUtils.getPointListPathFromProperties("敌人错误阵型");
        if(path == null){
            throw new NullPointerException();
        }
    }
}
