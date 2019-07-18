import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Player implements ActionListener {
int x;
int y;
double maxHealth;
int damage;
double health;
Rectangle collisionBox;
boolean canAttack=true;
Timer attackTimer;
Timer regenerateTimer;
int level=1;
int XP=0;
Player(int x, int y, int maxHealth, int damage){
	this.x=x;
	this.y=y;
	this.maxHealth=maxHealth;
	health=maxHealth;
	this.damage=damage;
	collisionBox=new Rectangle();
	attackTimer=new Timer(1000,this);
	attackTimer.start();
	regenerateTimer=new Timer(759,this);
	regenerateTimer.start();
}
void draw(Graphics g) {
	g.setColor(new Color(0,0,255));
	g.fillRect(x, y, 50, 50);
	g.setColor(new Color(127,0,0));
	g.fillRect(x-30, y+70, 110, 10);
	g.setColor(new Color(0,127,0));
	g.fillRect(x-30, y+70, (int) (110*(health/maxHealth)),10);
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
	if(enemy.health<=0) {
		gainXP(enemy.XPboost);
	}
	attackTimer.start();
}
public void gainXP(int XPboost) {
	XP+=XPboost;
	while(XP>=level*20) {
		XP-=level*20;
		level++;
		maxHealth=maxHealth+25;
	}
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
	Timer timer=(Timer) arg0.getSource();
	if(timer==regenerateTimer) {
		double regeneration=maxHealth/30;
		if(health+regeneration<=maxHealth) {
			health+=regeneration;
		}else {
			health=maxHealth;
		}
	}else {
		canAttack=true;
		attackTimer.stop();
	}
}
}
