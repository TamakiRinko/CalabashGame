package view.draw;

public class DrawServer {
    public DrawServer(){}

    /**
     * 画图，遵循DIP原则
     * @param drawBase
     */
    public void draw(DrawBase drawBase){
        drawBase.draw();
    }
}
