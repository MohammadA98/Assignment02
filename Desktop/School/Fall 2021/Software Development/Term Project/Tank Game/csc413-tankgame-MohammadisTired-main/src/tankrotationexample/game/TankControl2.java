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
public class TankControl2 implements KeyListener {

    private Tank t2;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl2(Tank t2, int up, int down, int left, int right, int shoot) {
        this.t2 = t2;
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
        if (keyPressed == up) {
            this.t2.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t2.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t2.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t2.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.t2.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.t2.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t2.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.t2.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.t2.unToggleRightPressed();
        }
        if (keyReleased == shoot) {
            this.t2.unToggleShootPressed();
        }


    }
}
