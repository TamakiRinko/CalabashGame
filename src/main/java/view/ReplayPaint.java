package view;

import control.Control;
import control.utils.ConstValues;
import control.utils.RecordUtils;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.basic.Record;
import model.controlled.BulletList;
import model.controlled.NMap;
import view.draw.*;

import java.io.IOException;

public class ReplayPaint {

    private Canvas canvas;
    GraphicsContext graphicsContext;
    NMap nMap;
    Record record;

    private DrawServer drawServer;                          //画图
    private DrawCalabashTeam drawCalabashTeam;
    private DrawEnemyTeam drawEnemyTeam;
    private DrawMap drawMap;
    private DrawBulletList drawBulletList;

    public ReplayPaint(Canvas canvas){
        this.canvas = canvas;
        nMap = new NMap("Map1", "Map2");
        graphicsContext = canvas.getGraphicsContext2D();
        drawServer = new DrawServer();

        RecordUtils.openInputStream();
    }

    private class ReplayThread extends Thread{
        @Override
        public void run() {
            record = RecordUtils.readRecord();
            while (record != null){
                Platform.runLater(() -> {
                    try {
                        record.setImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    drawCalabashTeam = new DrawCalabashTeam(graphicsContext, record.getCalabashTeam());
                    drawEnemyTeam = new DrawEnemyTeam(graphicsContext, record.getEnemyTeam());
                    drawMap = new DrawMap(graphicsContext, nMap);
                    drawBulletList = new DrawBulletList(graphicsContext, record.getBulletList());
                    drawServer.draw(drawMap);
                    drawServer.draw(drawCalabashTeam);
                    drawServer.draw(drawEnemyTeam);
//                    synchronized (BulletList.bulletList) {
                        drawServer.draw(drawBulletList);
//                    }
                });
                try {
                    Thread.sleep(ConstValues.ReplayThreadSleepInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                record = RecordUtils.readRecord();
            }
            RecordUtils.closeInputStream();
        }
    }

    public void startReplay(){
        ReplayThread replayThread = new ReplayThread();
        replayThread.start();
    }

}
