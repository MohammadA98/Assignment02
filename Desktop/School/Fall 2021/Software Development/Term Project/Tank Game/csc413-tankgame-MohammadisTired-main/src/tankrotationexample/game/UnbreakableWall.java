package tankrotationexample.game;

import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class UnbreakableWall {
    private int x, y;
    private Rectangle hitBox;
    private static BufferedImage unbreakableWall;

    public UnbreakableWall(int x, int y, BufferedImage unbreakableWall){
        this.x = x;
        this.y = y;
        this.unbreakableWall = unbreakableWall;
        this.hitBox = new Rectangle(x, y, unbreakableWall.getWidth(), unbreakableWall.getHeight());
    }

    public static void setImg(BufferedImage img){
        UnbreakableWall.unbreakableWall = img;
    }



    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(unbreakableWall, x, y, null);
    }

    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.unbreakableWall, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x,y, this.unbreakableWall.getWidth(),this.unbreakableWall.getHeight());
    }

}