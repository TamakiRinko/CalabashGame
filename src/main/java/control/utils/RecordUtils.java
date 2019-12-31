package control.utils;

import model.basic.Record;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class RecordUtils {

    private static FileOutputStream fileOutputStream;
    private static FileInputStream fileInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    private static String inPath;
    private static String outPath;

    public static void addRecord(Record record){
//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void writeRecord(Record record){
        try {
            objectOutputStream.writeObject(record);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Record readRecord(){
        try {
            Record record = (Record)objectInputStream.readObject();
            return record;
        } catch (IOException e) {
            return null;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openOutputStream(){
        try {
            fileOutputStream = new FileOutputStream(outPath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openInputStream(){
        try {
            fileInputStream = new FileInputStream(inPath);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeOutputStream(){
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeInputStream(){
        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setInPath(String inPath) {
        RecordUtils.inPath = inPath;
    }

    public static void setOutPath(String outPath) {
        RecordUtils.outPath = outPath;
    }
}
