package view.controller;

import control.Control;
import control.utils.ConstValues;
import control.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import view.GamePaint;

import java.io.IOException;

public class GameController {

    private enum GameMode{
        NOTSTATRED, RUNNING, PAUSE
    }

    @FXML
    private ComboBox CalabashComboBox;
    @FXML
    private ComboBox EnemyComboBox;
    @FXML
    private Canvas MapCanvas;

    private Control control;
    private GamePaint gamePaint;                                                        //游戏棋盘主体
//    private boolean isStarted = false;
    private GameMode gameMode;

    ObservableList<String> cOptions;
    ObservableList<String> eOptions;

    /**
     * 在构造函数内控件为null
     * @throws IOException
     */
    public GameController(){
        control = new Control();
        gameMode = GameMode.NOTSTATRED;
    }

    @FXML
    public void initialize(){
        MapCanvas.setWidth(ConstValues.MapWidth);
        MapCanvas.setHeight(ConstValues.MapHeight);
        gamePaint = new GamePaint(MapCanvas, control);
        gamePaint.start();
        setControls();
        setAction();
        setListener();
    }

    private void setControls(){
        cOptions = FXCollections.observableArrayList("长蛇","雁行","鹤翼", "衡轭", "方门", "锋矢");
        CalabashComboBox.setItems(cOptions);
        eOptions = FXCollections.observableArrayList("长蛇","雁行","鹤翼", "衡轭", "方门", "锋矢");
        EnemyComboBox.setItems(eOptions);
    }

    private void setListener(){
        CalabashComboBox.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue)-> {
            if(gameMode == GameMode.NOTSTATRED){
                control.changeCalabashTeamFormation(FileUtils.getPointList("葫芦娃" + cOptions.get((int) newValue)));
            }
        });
        EnemyComboBox.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue)-> {
            if(gameMode == GameMode.NOTSTATRED){
                control.changeEnemyTeamFormation(FileUtils.getPointList("敌人" + eOptions.get((int) newValue)));
            }
        });
    }

    private void setAction(){
        MapCanvas.setFocusTraversable(true);
        MapCanvas.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case SPACE: {
                    if(gameMode == GameMode.PAUSE || gameMode == GameMode.NOTSTATRED){
                        control.start();
                        gameMode = GameMode.RUNNING;
                    }else if(gameMode == GameMode.RUNNING){
                        control.suspend();
                        gameMode = GameMode.PAUSE;
                    }
                    break;
                }
                default: break;
            }
        });
    }

    public void gameStartButtonAction(){
        if(gameMode == GameMode.PAUSE || gameMode == GameMode.NOTSTATRED){
            control.start();
            gameMode = GameMode.RUNNING;
        }
    }

    public void gamePauseButtonAction(){
        if(gameMode == GameMode.RUNNING){
            control.suspend();
            gameMode = GameMode.PAUSE;
        }
    }
}
