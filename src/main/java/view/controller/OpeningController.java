package view.controller;

import control.utils.ConstValues;
import control.utils.RecordUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OpeningController {


    public OpeningController(){

    }

    @FXML
    public void gameButtonAction() throws IOException {

        Stage gameStage = new Stage();

        FileChooser chooser=new FileChooser();
        chooser.setTitle("选择保存路径");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("out", "*.out"));
        File file = chooser.showSaveDialog(gameStage);
        if(file != null){
            String path = file.getAbsolutePath();
            RecordUtils.setOutPath(path);
            gameStage.setTitle("游戏");
            gameStage.getIcons().add(new Image("file:file/hlw.jpg"));
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/Game.fxml"));
            gameStage.setScene(new Scene(root));
            gameStage.setOnCloseRequest(event -> System.exit(0));
            gameStage.show();
        }
    }

    @FXML
    public void replayButtonAction() throws IOException {
        Stage replayStage = new Stage();

        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("out", "*.out"));
        chooser.setTitle("选择读取的记录");
        File file = chooser.showOpenDialog(replayStage);
        if(file != null){
            String path = file.getAbsolutePath();
            RecordUtils.setInPath(path);
            replayStage.setTitle("回放: " + path);
            replayStage.getIcons().add(new Image("file:file/hlw.jpg"));
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/Replay.fxml"));
            replayStage.setScene(new Scene(root));
            replayStage.setOnCloseRequest(event -> System.exit(0));
            replayStage.show();
        }
    }

}
