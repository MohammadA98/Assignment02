package tankrotationexample.game;

import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BreakableWall {
    private int x, y;
    private Rectangle hitBox;
    private static BufferedImage breakableWall;

    public BreakableWall(int x, int y, BufferedImage breakableWall){
        this.x = x;
        this.y = y;
        this.breakableWall = breakableWall;
        this.hitBox = new Rectangle(x, y, breakableWall.getWidth(), breakableWall.getHeight());
    }

    public static void setImg(BufferedImage img){
        BreakableWall.breakableWall = img;
    }



    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }




    public void drawImage(Graphics2D buffer){
        buffer.drawImage(breakableWall, x, y, null);
    }

    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.breakableWall, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x,y, this.breakableWall.getWidth(),this.breakableWall.getHeight());
    }

}