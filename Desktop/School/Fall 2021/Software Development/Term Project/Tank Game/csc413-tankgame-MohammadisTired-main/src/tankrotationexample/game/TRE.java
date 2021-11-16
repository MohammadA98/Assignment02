/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.game;


import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;
import tankrotationexample.menus.EndGamePanel;
import tankrotationexample.menus.StartMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


import static javax.imageio.ImageIO.read;

/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel implements Runnable {

    private BufferedImage world;
    private  Tank t1;
    private  Tank t2;
    public static BufferedImage bulletImage;
    public static HealthPowerUp healthPowerUp;
    public static SpeedPowerUp speedPowerUp;
    public static ShieldPowerUp shieldPowerUp;
    public static UnbreakableWall unbreakableWall;
    public static BreakableWall breakableWall;
    public static ArrayList Walls = new ArrayList();
    private Launcher lf;
    static long tick = 0;

    public TRE(Launcher lf){
        this.lf = lf;
    }

    @Override
    public void run(){
       try {
           this.resetGame();
           while (true) {
                this.tick++;
                this.t1.update(); // update tank
                this.t2.update();
                this.repaint();// redraw game
                if (this.t1.getHitBox().intersects(this.t2.getHitBox())){
                    t1.unToggleUpPressed();
                    t1.unToggleDownPressed();
                    t1.unToggleLeftPressed();
                    t1.unToggleRightPressed();
                }
               if (this.t2.getHitBox().intersects(this.t1.getHitBox())){
                   t2.unToggleUpPressed();
                   t2.unToggleDownPressed();
                   t2.unToggleLeftPressed();
                   t2.unToggleRightPressed();
               }
               ArrayList<Bullet> bullets = this.t1.getAmmo();
                for (Bullet b: bullets){
                    if (this.t2.getHitBox().intersects(b.getHitBox())){
                        this.t2.loseHealth();
                        b.x = -30;
                        b.y = -30;
                        if (t2.healthPoints < 1){
                            t2.respawnTank();

                        }
                    }
                }
               ArrayList<Bullet> bullets2 = this.t2.getAmmo();
               for (Bullet b: bullets2){
                   if (this.t1.getHitBox().intersects(b.getHitBox())){
                       this.t1.loseHealth();
                       b.x = -30;
                       b.y = -30;
                       if (t1.healthPoints < 1){
                           t1.respawnTank();

                       }
                   }
               }
               ArrayList<Bullet> Wallbullets2 = this.t2.getAmmo();
               for (Bullet b: Wallbullets2){
                   if (breakableWall.getHitBox().intersects(b.getHitBox())){
                       breakableWall.getHitBox().x = -30;
                       breakableWall.getHitBox().y = -30;


                   }
               }

               ArrayList<Bullet> Wallbullets = this.t1.getAmmo();
               for (Bullet b: Wallbullets){
                   if (breakableWall.getHitBox().intersects(b.getHitBox())){
                       breakableWall.getHitBox().x = -30;
                       breakableWall.getHitBox().y = -30;


                   }
               }


               if (this.t1.getHitBox().intersects(unbreakableWall.getHitBox())){
                   t1.unToggleUpPressed();
                   t1.unToggleDownPressed();
                   t1.unToggleLeftPressed();
                   t1.unToggleRightPressed();
               }

               if (this.t2.getHitBox().intersects(unbreakableWall.getHitBox())){
                   t2.unToggleUpPressed();
                   t2.unToggleDownPressed();
                   t2.unToggleLeftPressed();
                   t2.unToggleRightPressed();
               }

               if (this.t1.getHitBox().intersects(breakableWall.getHitBox())){
                   t1.unToggleUpPressed();
                   t1.unToggleDownPressed();
                   t1.unToggleLeftPressed();
                   t1.unToggleRightPressed();
               }

               if (this.t2.getHitBox().intersects(breakableWall.getHitBox())){
                   t2.unToggleUpPressed();
                   t2.unToggleDownPressed();
                   t2.unToggleLeftPressed();
                   t2.unToggleRightPressed();
               }
               if (this.t1.getHitBox().intersects(healthPowerUp.getHitBox())){
                   this.t1.gainHealth();
               }

               if (this.t2.getHitBox().intersects(healthPowerUp.getHitBox())){
                   this.t2.gainHealth();
               }

                if (this.t1.getHitBox().intersects(speedPowerUp.getHitBox())){
                   this.t1.gainSpeed();
               }
               if (this.t2.getHitBox().intersects(speedPowerUp.getHitBox())){
                   this.t2.gainSpeed();
               }

               if (this.t1.getHitBox().intersects(shieldPowerUp.getHitBox())){
                   this.t1.haveShield();
               }

               if (this.t2.getHitBox().intersects(shieldPowerUp.getHitBox())){
                   this.t2.haveShield();
               }



                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if(this.t1.livesCount < 1 || this.t2.livesCount < 1){
                    this.lf.setFrame("end");
                    return;
                }
            }
       } catch (InterruptedException ignored) {
           System.out.println(ignored);
       }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame(){
        this.tick = 0;
        this.t1.setX(300);
        this.t1.setY(300);
        this.t2.setX(900);
        this.t2.setY(300);
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH,
                                       GameConstants.WORLD_HEIGHT,
                                       BufferedImage.TYPE_INT_RGB);

        BufferedImage t1img = null;
        BufferedImage t2img = null;
        BufferedImage healthPowerUp = null;
        BufferedImage speedPowerUp = null;
        BufferedImage shieldPowerUp = null;
        BufferedImage unbreakableWall = null;
        BufferedImage breakableWall = null;
        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            t1img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("tank1.png")));
            t2img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("tank2.png")));
            TRE.bulletImage = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("bullet.png")));
            healthPowerUp = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("HealthPowerUp.png")));
            speedPowerUp = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("SpeedPowerUp.png")));
            shieldPowerUp = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("ShieldPowerUp.png")));
            unbreakableWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("UnbreakableWall.png")));
            breakableWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("BreakableWall.png")));

            InputStream isr1 = TRE.class.getClassLoader().getResourceAsStream("map.csv");
            InputStreamReader isr = new InputStreamReader(isr1);


            BufferedReader mapReader = new BufferedReader(isr);
            int j = 0;
            while (mapReader.ready()){
                String line = mapReader.readLine();
                String[] items = line.split(",");
                for (int i = 0; i < items.length; i++) {
                    if (items[i].equals("9")){
                        Walls.add(new UnbreakableWall(i*62,j*62,unbreakableWall));
                    }
                    if (items[i].equals("2")){
                        Walls.add(new UnbreakableWall(i*32,j*32,unbreakableWall));
                    }
                    if (items[i].equals("3")){
                        Walls.add(new BreakableWall(i*32, j*32, breakableWall));
                    }
                }
                j++;
            }


        }catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        t1 = new Tank(300, 300, 0, 0, 0, t1img);
        t2 = new Tank(600, 600, 0, 0, 180, t2img);
        TRE.unbreakableWall = new UnbreakableWall(100, 100, unbreakableWall);
        TRE.breakableWall = new BreakableWall(200, 200, breakableWall);
        TRE.healthPowerUp = new HealthPowerUp(50,50, healthPowerUp);
        TRE.speedPowerUp = new SpeedPowerUp(1000,50, speedPowerUp);
        TRE.shieldPowerUp = new ShieldPowerUp(50,700, shieldPowerUp);
        TankControl tc1 = new TankControl(t1,KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl2 tc2 = new TankControl2(t2,KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.WHITE);
        buffer.fillRect(0,0,GameConstants.WORLD_WIDTH,GameConstants.WORLD_HEIGHT);
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);

        healthPowerUp.drawImage(buffer);
        speedPowerUp.drawImage(buffer);
        shieldPowerUp.drawImage(buffer);
        Walls.forEach(w -> {
            if(w instanceof UnbreakableWall){
                ((UnbreakableWall)w).drawImage(buffer);
            }else if(w instanceof BreakableWall){
                ((BreakableWall)w).drawImage(buffer);
            }
        });
        breakableWall.drawImage(buffer);
        BufferedImage leftHalf = world.getSubimage(t1.screenX,t1.screenY,GameConstants.GAME_SCREEN_WIDTH/2,GameConstants.GAME_SCREEN_HEIGHT);
        BufferedImage rightHalf = world.getSubimage(t2.screenX,t2.screenY,GameConstants.GAME_SCREEN_WIDTH/2,GameConstants.GAME_SCREEN_HEIGHT);
        BufferedImage miniMap = world.getSubimage(0,0,GameConstants.WORLD_WIDTH,GameConstants.WORLD_HEIGHT);
        g2.drawImage(leftHalf,0,0,null);
        g2.drawImage(rightHalf,GameConstants.WORLD_WIDTH/2,0,null);
        g2.scale(0.15,0.15);
        g2.drawImage(miniMap,0,0, null);

    }

}
