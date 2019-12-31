package control.utils;

import model.basic.Bullet;
import model.basic.Character;
import model.basic.Point;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javafx.scene.image.Image;

/**
 * 工具类
 */
public class FileUtils {

    private static String imagePathPropertiesPath = "properties/ImagePath.properties";
    private static String formationPathPropertiesPath = "properties/FormationPath.properties";
    private static Properties imageProperties;
    private static Properties formationProperties;

    private static ClassLoader fileClassLoader = FileUtils.class.getClassLoader();
    static {
        imageProperties = new Properties();
        formationProperties = new Properties();
        try {
            imageProperties.load(new InputStreamReader(                     //指定UTF-8编码
                    fileClassLoader.getResourceAsStream(imagePathPropertiesPath), StandardCharsets.UTF_8));
            formationProperties.load(new InputStreamReader(
                    fileClassLoader.getResourceAsStream(formationPathPropertiesPath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据名称从Properties中读取List路径，读取坐标
     * @param name
     * @return
     */
    public static List<Point> getPointList(String name){
        try(InputStream is = fileClassLoader.getResourceAsStream(formationProperties.getProperty(name))){
            if(is == null)  return null;
            Scanner scanner = new Scanner(is);
            List<Point> list = new ArrayList<>();
            int x, y;
            while (scanner.hasNext()){
                x = scanner.nextInt();
                y = scanner.nextInt();
                list.add(new Point(x, y));
            }
            return  list;
        } catch (IOException e) {
            System.out.println(name + "位置文件读取错误");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据名称从Properties中读取image路径，读取图片
     * @param name  名称，详见ImagePath.properties
     * @return
     */
    public static Image getImage(String name){
        try(InputStream is = fileClassLoader.getResourceAsStream(imageProperties.getProperty(name))){
            return is == null ? null: new Image(is);
        } catch (IOException e) {
            System.out.println(name + "图片读取错误");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为character设置Image
     * @param character
     * @param name
     */
    public static void setImage(Character character, String name) throws IOException {
        try(InputStream is = fileClassLoader.getResourceAsStream(imageProperties.getProperty(name))){
            character.setImage(new Image(is));
        } catch (IOException e) {
            System.out.println(name + "图片读取错误");
            e.printStackTrace();
            throw e;            //for test
        }
    }

    /**
     * 为bullet设置Image
     * @param bullet
     * @param name
     */
    public static void setBulletImage(Bullet bullet, String name){
        try(InputStream is = fileClassLoader.getResourceAsStream(imageProperties.getProperty(name))){
            bullet.setImage(new Image(is));
        } catch (IOException e) {
            System.out.println(name + "图片读取错误");
            e.printStackTrace();
        }
    }

    /**
     * 修改葫芦娃队伍位置文件内容
     * @param dx
     * @param dy
     * @throws IOException
     */
    public static void changeCalabashFile(int dx, int dy, boolean isCalabashTeam){

//        for(Object path: formationProperties.values()){
//            String result = "";
//            if(((String)path).substring(0, 6).equals("file/敌"))  continue;
//            try(InputStreamReader inputStreamReader = new InputStreamReader(fileClassLoader.getResourceAsStream((String)path));
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
//                String str = "";
//                while ((str = bufferedReader.readLine()) != null){
//                    int x = Integer.parseInt(str.split(" ")[0]);
//                    int y = Integer.parseInt(str.split(" ")[1]);
//                    x = x + dx;
//                    y = y + dy;
//                    String temp = x + " " + y + "\n";
//                    result += temp;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String outputPath = fileClassLoader.getResource((String)path).getPath();
//            try(FileWriter fileWriter = new FileWriter(outputPath)){
//                fileWriter.write(result);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

        String index = "";
        String result = "";
        if(isCalabashTeam){
            index = "D:\\NJU_Study\\Java\\Maven\\Calabash_Final\\src\\main\\resources\\file\\葫芦娃队伍_";
        }else{
            index = "D:\\NJU_Study\\Java\\Maven\\Calabash_Final\\src\\main\\resources\\file\\敌人队伍_";
        }
        for(int i = 0; i < 6; ++i){
            try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File(index + i + ".txt")));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
                String str = "";
                while ((str = bufferedReader.readLine()) != null){
                    int x = Integer.parseInt(str.split(" ")[0]);
                    int y = Integer.parseInt(str.split(" ")[1]);
                    x = x + dx;
                    y = y + dy;
                    String temp = x + " " + y + "\n";
                    result += temp;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(FileWriter fileWriter = new FileWriter(index + i + ".txt")){
                fileWriter.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = "";
        }
    }

    public static String getImagePathFromProperties(String name){
        String imagePath = imageProperties.getProperty(name);
        return imagePath;
    }

    public static String getPointListPathFromProperties(String name){
        String pointListPath = formationProperties.getProperty(name);
        return pointListPath;
    }
}
