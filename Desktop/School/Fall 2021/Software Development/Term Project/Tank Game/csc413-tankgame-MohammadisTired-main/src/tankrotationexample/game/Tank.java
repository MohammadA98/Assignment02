package tankrotationexample.game;



import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author anthony-pc
 */
public class Tank {


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private int R = 2;
    private float ROTATIONSPEED = 3.0f;
    private Rectangle hitBox;
    private ArrayList<Bullet> ammo;
    public int healthPoints = 100;
    public int livesCount = 3;
    public int damageTaken = 2;
    public int screenX;
    public int screenY;


    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
        this.ammo = new ArrayList<>();
        this.healthPoints = 100;
    }

    public ArrayList<Bullet> getAmmo() {
        return ammo;
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }

        if (this.ShootPressed && TRE.tick % 30 == 0) {
            Bullet b = new Bullet(x, y, angle, TRE.bulletImage);
            this.ammo.add(b);

        }
        this.ammo.forEach(bullet -> bullet.update());
        for (int i = 0; i < this.ammo.size(); i++) {
            this.ammo.get(i).update();
        }

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    public void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
        centerScreen();
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
        centerScreen();
    }


    private void checkBorder() {
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

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void loseHealth() {
        healthPoints -= damageTaken;
    }

    public void gainHealth() {
        healthPoints = 100;
    }

    public void gainSpeed() {
        R = 5;
    }

    public final static int ONE_SECOND = 3000;
    Timer timer;

    public void haveShield() {
        this.damageTaken = 0;


         timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                damageTaken = 2;


                timer.stop();

            }
        });
        timer.start();
    }



    public void respawnTank(){
        this.x = 300;
        this.y = 800;
        this.hitBox.setLocation(x,y);
        this.healthPoints = 100;
        this.livesCount -= 1;
    }

    private void centerScreen(){
        screenX = this.x - GameConstants.GAME_SCREEN_WIDTH/4;
        screenY = this.y - GameConstants.GAME_SCREEN_HEIGHT/2;


        if (screenX< 0){
            screenX = 0;
        }

        if (screenY < 0){
            screenY = 0;
        }

        if (screenX> GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH/2){
            screenX = GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH/2;
        }

        if (screenY > GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT){
            screenY = GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT;
        }

    }


    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(x,y,this.img.getWidth(),this.img.getHeight());
        g2d.drawRect(x+30,20,100,25);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x+30,20,healthPoints,25);
        g2d.drawString(livesCount+"",x+10,20);
        g2d.setColor(Color.BLACK);
        this.ammo.forEach(bullet -> bullet.drawImage(g));
        for (int i =0; i< this.ammo.size(); i++){
            this.ammo.get(i).drawImage(g);
        }
    }


}
