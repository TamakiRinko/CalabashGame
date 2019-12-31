package view;

import control.Control;
import control.utils.ConstValues;
import control.utils.FileUtils;
import control.utils.RecordUtils;
import model.basic.Record;
import model.controlled.BulletList;
import view.draw.*;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class GamePaint {

    private Control control;
    private Canvas canvas;

    private DrawServer drawServer;                          //画图
    private DrawCalabashTeam drawCalabashTeam;
    private DrawEnemyTeam drawEnemyTeam;
    private DrawMap drawMap;
    private DrawBulletList drawBulletList;
    private boolean isRunning = true;
    private GraphicsContext graphicsContext;

    public GamePaint(Canvas canvas, Control control){
        this.canvas = canvas;
        this.control = control;
        graphicsContext = canvas.getGraphicsContext2D();

        drawServer = new DrawServer();
        drawCalabashTeam = new DrawCalabashTeam(graphicsContext, control.getCalabashTeam());
        drawEnemyTeam = new DrawEnemyTeam(graphicsContext, control.getEnemyTeam());
        drawMap = new DrawMap(graphicsContext, control.getnMap());
        drawBulletList = new DrawBulletList(graphicsContext);

    }

    /**
     * 绘画线程
     */
    private class DrawThread extends Thread{
        @Override
        public void run() {
            while (isRunning){
                Platform.runLater(() -> {
                    drawServer.draw(drawMap);
                    drawServer.draw(drawCalabashTeam);
                    drawServer.draw(drawEnemyTeam);
                    synchronized (BulletList.bulletList){
                        drawServer.draw(drawBulletList);
                    }
                    Record record = new Record(Control.getCalabashTeam(), Control.getEnemyTeam(), BulletList.bulletList);
                    RecordUtils.writeRecord(record);
                });
                //画完后休息一段时间，避免阻塞
                try {
                    Thread.sleep(ConstValues.DrawThreadSleepInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(Control.getEnemyTeam().getNormalTeam().size() == 0){
                    control.suspend();
                    drawStop();
                    RecordUtils.closeOutputStream();
                    DrawPic drawPic = new DrawPic(graphicsContext, FileUtils.getImage("Win"));
                    drawServer.draw(drawPic);
                }else if(Control.getCalabashTeam().getNormalTeam().size() == 0){
                    control.suspend();
                    drawStop();
                    RecordUtils.closeOutputStream();
                    DrawPic drawPic = new DrawPic(graphicsContext, FileUtils.getImage("Lose"));
                    drawServer.draw(drawPic);
                }
            }
        }
    }


    public void drawStop(){
        isRunning = false;
    }

    private void startDraw(){
        DrawThread drawThread = new DrawThread();
        drawThread.start();
    }

    public void start(){
        RecordUtils.openOutputStream();
        startDraw();
    }
}
