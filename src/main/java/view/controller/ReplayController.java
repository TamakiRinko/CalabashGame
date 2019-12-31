package view.controller;

import control.utils.ConstValues;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import view.GamePaint;
import view.ReplayPaint;

import java.io.IOException;

public class ReplayController {

    @FXML
    private Canvas ReplayCanvas;

    private ReplayPaint replayPaint;                                                        //游戏棋盘主体

    /**
     * 在构造函数内控件为null
     * @throws IOException
     */
    public ReplayController(){
    }

    @FXML
    public void initialize(){
        ReplayCanvas.setWidth(ConstValues.MapWidth);
        ReplayCanvas.setHeight(ConstValues.MapHeight);
        replayPaint = new ReplayPaint(ReplayCanvas);
    }

    public void replayStartButtonAction(){
        replayPaint.startReplay();
    }
}
