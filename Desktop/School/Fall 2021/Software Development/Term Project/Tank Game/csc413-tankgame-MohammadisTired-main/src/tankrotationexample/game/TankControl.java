/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.game;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author anthony-pc
 */
public class TankControl implements KeyListener {

    private Tank t1;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    
    public TankControl(Tank t1, int up, int down, int left, int right, int shoot) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == 87) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == 83) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == 65) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == 68) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == 16) {
            this.t1.toggleShootPressed();
        }

        

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == 87) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == 83) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased  == 65) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased  == 68) {
            this.t1.unToggleRightPressed();
        }
        if (keyReleased == 16) {
            this.t1.unToggleShootPressed();
        }


    }
}
