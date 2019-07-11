import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Player implements ActionListener {
int x;
int y;
int maxHealth;
int damage;
int health;
Rectangle collisionBox;
boolean canAttack=true;
Timer attackTimer;
Player(int x, int y, int maxHealth, int damage){
	this.x=x;
	this.y=y;
	this.maxHealth=maxHealth;
	health=maxHealth;
	this.damage=damage;
	collisionBox=new Rectangle();
	attackTimer=new Timer(1000,this);
	attackTimer.start();
}
void draw(Graphics g) {
	g.setColor(new Color(0,0,255));
	g.fillRect(x, y, 50, 50);
}
void up() {
	if(y>0) {
		y=y-5;
	}
}
void down() {
	if(y<RPGgame.HEIGHT) {
		y=y+5;
	}
}
void left() {
	if(x>0) {
		x=x-5;
	}
}
void right() {
	if(x<RPGgame.WIDTH) {
		x=x+5;
	}
}
public void attack(Enemy enemy) {
	enemy.health=enemy.health-damage;
	attackTimer.start();
}
void update() {
	if(health<=0) {
		x=250;
		y=600;
		health=maxHealth;
	}
	collisionBox.setBounds(x, y, 50,50);
}
@Override
public void actionPerformed(ActionEvent arg0) {
	canAttack=true;
	attackTimer.stop();
}
}
