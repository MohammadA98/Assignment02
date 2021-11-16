/*package tankrotationexample.game;
import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class HealthPowerUp {
    int x, y;
    int R = 7;
    public Rectangle hitBox;
    BufferedImage healthPowerUp;

    public HealthPowerUp(int x, int y, BufferedImage healthPowerUp){
        this.x = x;
        this.y = y;
        this.healthPowerUp = healthPowerUp;
        this.hitBox = new Rectangle(x,y,healthPowerUp.getWidth(), healthPowerUp.getHeight());
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    public void checkBorder(){
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.GAME_SCREEN_WIDTH - 88) {
            x = GameConstants.GAME_SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.GAME_SCREEN_HEIGHT - 80) {
            y = GameConstants.GAME_SCREEN_HEIGHT - 80;
        }
    }

    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.healthPowerUp, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x,y, this.healthPowerUp.getWidth(),this.healthPowerUp.getHeight());
    }
}

*/

package tankrotationexample.game;

import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class HealthPowerUp {
    int x, y;
    BufferedImage healthPowerUp;
    public Rectangle hitBox;



    public HealthPowerUp(int x, int y, BufferedImage healthPowerUp) {
        this.x = x;
        this.y = y;
        this.healthPowerUp = healthPowerUp;
        this.hitBox = new Rectangle(x,y,this.healthPowerUp.getWidth(),this.healthPowerUp.getHeight());
    }
    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    public void checkBorder(){
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.WORLD_WIDTH - 88) {
            x = GameConstants.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.WORLD_HEIGHT - 80) {
            y = GameConstants.WORLD_HEIGHT - 80;
        }
    }

    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.healthPowerUp, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x,y, this.healthPowerUp.getWidth(),this.healthPowerUp.getHeight());
    }
}
